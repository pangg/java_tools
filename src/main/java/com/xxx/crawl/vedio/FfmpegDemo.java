package com.xxx.crawl.vedio;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 下载m3u8的ts文件，并通过ffmpeg合并文件；
 * ffmpeg -i "concat:1.ts|2.ts|3.ts" -c copy output.mp4
 */
public class FfmpegDemo {
    static String m3u8url = "https://tp127.cc/video/m3u8/6b9be43ca7b17cf8ad371d4269ab9c8a3563c6b8.m3u8?et=1660745507&token=856b1d3230755419cccaccf7e03400a5&video_server=dpprague";
    static String fileName = "";// 保存文件名
    static String Dir = "/tmp/vedio/t4/";// 保存路径
    static String ffmpegDir = "/Users/gaopan/Downloads/";// ffmpeg解压路径
    static String KEY = "";// 加密视频的密钥，位数必须为16的倍数
    static String IV = "";// 加密视频的密钥IV
    static int N = 5;// 线程数10
    static int INDEX = 0;// 下标
    public static List<String> UAS = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        addUA(); // 扩展user-agent
        String headUrl = m3u8url.substring(0, m3u8url.lastIndexOf("/") + 1); // 链接头部
        if (headUrl.contains("?")) {
            headUrl = headUrl.substring(0, headUrl.indexOf("?"));
            headUrl = headUrl.substring(0, headUrl.lastIndexOf("/") + 1);
        }

        // https下载
        // String sendGet = sendGet2(m3u8url, StandardCharsets.UTF_8.name());// 下载index.m3u8
        String sendGet = sendGet(m3u8url, StandardCharsets.UTF_8.name());// 下载index.m3u8
        if (sendGet == null) {
            return;
        }
        String[] split = sendGet.split("\n");
        System.out.println(split.toString());
        String url = "";
        List<String> urls = new ArrayList<>();
        // 获取ts链接和加密视频的key
        for (String s : split) {
            if (s.contains("EXT-X-KEY")) {
                int index = s.indexOf("URI=") + 5;
                String keyUrl = s.substring(index, s.indexOf("\"", index));
                if (keyUrl.startsWith("http")) {
                    url = keyUrl;
                } else {
                    url = headUrl + keyUrl;
                }
                KEY = sendGet(url, StandardCharsets.UTF_8.name());
                if (KEY == null || KEY.length() == 0) {
                    String replace = keyUrl.substring(0, keyUrl.lastIndexOf("/") + 1);
                    headUrl = headUrl.replace(replace, "");
                    url = headUrl + keyUrl;
                    KEY = sendGet(url, StandardCharsets.UTF_8.name());
                    System.out.println("key: " + KEY); // 加密视频的key
                }

                if (IV == null || IV.length() == 0) {
                    if (s.contains("IV=")) {
                        index = s.indexOf("IV=") + 3;
                        IV = s.substring(index);
                        if (IV.startsWith("0x")) {
                            IV = IV.substring(2);
                            IV = hexStr2Str(IV);
                        }
                    } else {
                        IV = KEY;
                    }

                    System.out.println("iv：" + IV);// 加密视频的IV

                }
            } else if (s.contains(".ts")) {
                if (s.startsWith("http")) {
                    urls.add(s);
                } else {
                    urls.add(headUrl + s);
                }
            }
        }

        System.out.println(urls.toString());

        File f = new File(Dir + "/test.ts");
        while (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }

        // 开启多线程下载
        // CountDownLatch countDownLatch = new CountDownLatch(N);// 实例化一个倒计数器，N指定计数个数
        // countDownLatch.countDown(); // 计数减一
        // countDownLatch.await();// 等待，当计数减到0时，所有线程并行执行

        final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(N);
        for (int i = 0; i < urls.size(); i++) {
            int index = getIndex();
            String localName = "000" + (index + 1) + ".ts";
            File localFile = new File(Dir + localName);
            if (localFile.exists()) {
                continue;
            }
            fixedThreadPool.execute(() -> {
                try {
                    String ts = sendGet(urls.get(index), StandardCharsets.ISO_8859_1.name());
                    byte[] tbyte = ts.getBytes(StandardCharsets.ISO_8859_1.name());
                    if (!"".equals(KEY)) {
                        tbyte = decryptCBC(tbyte, KEY, IV);
                    }
                    saveFile(tbyte, localName);
                    System.out.println("下载000" + (index + 1) + ".ts 结束：" + urls.get(index));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        fixedThreadPool.shutdown();
        // 等待子线程结束，再继续执行下面的代码
        fixedThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

        System.out.println("所有ts下载结束，总共：" + urls.size() + "个文件！");

        // 合并ts文件
        mergeTs(urls);
        System.out.println("合并完成：" + Dir + fileName + ".ts");
        // ffmpeg合并ts文件
        ffmpegMergeTs(urls);
        System.out.println("合并完成：" + Dir + fileName + ".mp4");
        // 删除ts文件
        // deleteTs(urls);
    }

    /**
     * 获取其中的一个ts
     */
    private synchronized static int getIndex() {
        return INDEX++;
    }

    /**
     * 删除ts文件
     */
    private static void deleteTs(List<String> urls) {
        for (int i = 0; i < urls.size(); i++) {
            new File(getLocalFilePath(i)).delete();
        }
    }

    /**
     * 获取文件保存路径+文件名称
     * @param i
     * @return
     */
    private static String getLocalFilePath(int i) {
        return Dir + "000" + (i + 1) + ".ts";
    }

    /**
     * 合并ts文件
     */
    public static void mergeTs(List<String> urls) {
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            if ("".equals(fileName)) {
                fileName = "1" + new Random().nextInt(10000);
            }
            File file = new File(Dir + fileName + ".ts");
            fos = new FileOutputStream(file);
            byte[] buf = new byte[4096];
            int len;
            for (int i = 0; i < urls.size(); i++) {
                fis = new FileInputStream(getLocalFilePath(i));
                while ((len = fis.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }
                fos.flush();
                fis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ffmpeg合并ts文件
     */
    public static void ffmpegMergeTs(List<String> urls) {
        BufferedWriter bw = null;
        try {
            File file = new File(Dir + "file.txt");
            System.out.println("ffmpeg执行命令：");
            String command = ffmpegDir + "ffmpeg -f concat -safe 0 -i " + file.getAbsolutePath() + " -c copy " + Dir + fileName + ".mp4";
            System.out.println(command);
            bw = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < urls.size(); i++) {
                bw.write("file '" + getLocalFilePath(i) + "'");
                bw.newLine();
                bw.flush();
            }

            //
            // ffmpeg执行合并命令
            //
            // ffmpeg -i "concat:1.ts|2.ts|3.ts" -c copy output.mp4
            // # -safe 0: 防止Operation not permitted 不允许操作
            // ffmpeg.exe -f concat -safe 0 -i file.txt -c copy out.mp4
            // ffmpeg -f concat -safe 0 -i file.txt -c copy out.mp4

            try {
                Process exec = Runtime.getRuntime().exec(command);
                exec.getOutputStream().close();
                printMessage(exec.getInputStream());
                printMessage(exec.getErrorStream());
                int value = exec.waitFor();
                System.out.println("ffmpeg合并结束：" + value);
            } catch (IOException e) {
                System.out.println("IO读取异常");
            } catch (Exception e) {
                System.out.println("程序中断异常");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 用于打印日志文件，防止执行阻塞
     */
    private static void printMessage(final InputStream input) {
        new Thread(() -> {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(input, "GBK"));
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("IO读取异常");
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * AES CBC 解密
     * @param key   sSrc ts文件字节数组
     * @param iv    IV，需要和key长度相同
     * @return  解密后数据
     */
    public static byte[] decryptCBC(byte[] src, String key, String iv) {
        try {
            byte[] keyByte = key.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec keySpec = new SecretKeySpec(keyByte, "AES");
            byte[] ivByte = iv.getBytes(StandardCharsets.UTF_8);
            IvParameterSpec ivSpec = new IvParameterSpec(ivByte);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] content = cipher.doFinal(src);
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存ts文件
     * @param ts
     * @param name
     */
    private static void saveFile(byte[] ts, String name) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(Dir + name);
            fos.write(ts);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * http下载文件
     */
    public static String sendGet(String url, String charset) {
        HttpURLConnection con = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        int failTimes = 0;
        int randNum = new Random().nextInt(UAS.size());
        String ua = UAS.get(randNum);
        System.out.println(ua);
        try {
            con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");// 客户端告诉服务器实际发送的数据类型
            con.setRequestProperty("Accept", "*/*");
            // con.setRequestProperty("Connection", "keep-alive");
            con.setRequestProperty("User-Agent", ua);
            // 开启连接
            con.connect();
            if (con.getResponseCode() != 200 && failTimes < 4) {
                failTimes ++;
                sendGet(url, charset);
            }
            is = con.getInputStream();
            baos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len;
            while ((len = is.read(buf)) != -1) {
                baos.write(buf, 0, len);
                baos.flush();
            }
            return baos.toString(charset);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (is != null) {
                    is.close();
                }
                if (con != null) {
                    con.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String sendGet2(String url, String charset) {
        HttpsURLConnection con = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            // 打开连接
            con = (HttpsURLConnection) new URL(url).openConnection();
            // 绕过证书验证
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[] {
                    new TrustAnyTrustManager()
            }, new java.security.SecureRandom());
            con.setSSLSocketFactory(sc.getSocketFactory());
            // 绕过验证主机名和服务器验证方案的匹配是可接受的
            con.setHostnameVerifier(new CustomizedHostnameVerifier());
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");// 客户端告诉服务器实际发送的数据类型
            con.setRequestProperty("Accept", "*/*");
            // con.setRequestProperty("Connection", "keep-alive");
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
            // 开启连接
            con.connect();
            is = con.getInputStream();
            baos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len;
            while ((len = is.read(buf)) != -1) {
                baos.write(buf, 0, len);
                baos.flush();
            }
            return baos.toString(charset);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (is != null) {
                    is.close();
                }
                if (con != null) {
                    con.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    static class TrustAnyTrustManager implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
    }

    static class CustomizedHostnameVerifier implements HostnameVerifier {
        // 重写验证方法
        @Override
        public boolean verify(String urlHostName, SSLSession session) {
            System.out.println("Warning: URL Host: " + urlHostName + " vs. " + session.getPeerHost());
            // 所有都正确
            return true;
        }
    }

    /**
     * 字符串转换成为16进制
     * @param str
     * @return
     */
    public static String str2HexStr(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            // sb.append(' ');
        }
        return sb.toString().trim();
    }

    /**
     * 16进制直接转换成为字符串
     * @param hexStr
     * @return
     */
    public static String hexStr2Str(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }
    
    private static void addUA() {
        List<String> uaList = Arrays.asList(
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/22.0.1207.1 Safari/537.1",
                "Mozilla/5.0 (X11; CrOS i686 2268.111.0) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.57 Safari/536.11",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1092.0 Safari/536.6",
                "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1090.0 Safari/536.6",
                "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/19.77.34.5 Safari/537.1",
                "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.9 Safari/536.5",
                "Mozilla/5.0 (Windows NT 6.0) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.36 Safari/536.5",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1063.0 Safari/536.3",
                "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1063.0 Safari/536.3",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_0) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1063.0 Safari/536.3",
                "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1062.0 Safari/536.3",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1062.0 Safari/536.3",
                "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.1 Safari/536.3",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.1 Safari/536.3",
                "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.1 Safari/536.3",
                "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.0 Safari/536.3",
                "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.24 (KHTML, like Gecko) Chrome/19.0.1055.1 Safari/535.24",
                "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/535.24 (KHTML, like Gecko) Chrome/19.0.1055.1 Safari/535.24",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36");
        UAS = new ArrayList<>(uaList);
        UAS.add("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
        UAS.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.1 Safari/537.36");
        UAS.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36");
        UAS.add("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2226.0 Safari/537.36");
        UAS.add("Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; AS; rv:11.0) like Gecko");
        UAS.add("Mozilla/5.0 (compatible, MSIE 11, Windows NT 6.3; Trident/7.0; rv:11.0) like Gecko");
        UAS.add("Mozilla/5.0 (compatible; MSIE 10.6; Windows NT 6.1; Trident/5.0; InfoPath.2; SLCC1; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; .NET CLR 2.0.50727) 3gpp-gba UNTRUSTED/1.0");
        UAS.add("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 7.0; InfoPath.3; .NET CLR 3.1.40767; Trident/6.0; en-IN)");
        UAS.add("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)");
        UAS.add("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/6.0)");
        UAS.add("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/5.0)");
        UAS.add("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/4.0; InfoPath.2; SV1; .NET CLR 2.0.50727; WOW64)");
        UAS.add("Mozilla/5.0 (compatible; MSIE 10.0; Macintosh; Intel Mac OS X 10_7_3; Trident/6.0)");
        UAS.add("Mozilla/4.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/5.0)");
        UAS.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/532.2 (KHTML, like Gecko) ChromePlus/4.0.222.3 Chrome/4.0.222.3 Safari/532.2");
        UAS.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.28.3 (KHTML, like Gecko) Version/3.2.3 ChromePlus/4.0.222.3 Chrome/4.0.222.3 Safari/525.28.3");
        UAS.add("Opera/9.80 (X11; Linux i686; Ubuntu/14.10) Presto/2.12.388 Version/12.16");
        UAS.add("Opera/9.80 (Windows NT 6.0) Presto/2.12.388 Version/12.14");
        UAS.add("Mozilla/5.0 (Windows NT 6.0; rv:2.0) Gecko/20100101 Firefox/4.0 Opera 12.14");
        UAS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.0) Opera 12.14");
        UAS.add("Opera/12.80 (Windows NT 5.1; U; en) Presto/2.10.289 Version/12.02");
        UAS.add("Opera/9.80 (Windows NT 6.1; U; es-ES) Presto/2.9.181 Version/12.00");
        UAS.add("Opera/9.80 (Windows NT 5.1; U; zh-sg) Presto/2.9.181 Version/12.00");
        UAS.add("Opera/12.0(Windows NT 5.2;U;en)Presto/22.9.168 Version/12.00");
        UAS.add("Opera/12.0(Windows NT 5.1;U;en)Presto/22.9.168 Version/12.00");
        UAS.add("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1");
        UAS.add("Mozilla/5.0 (Windows NT 6.3; rv:36.0) Gecko/20100101 Firefox/36.0");
        UAS.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10; rv:33.0) Gecko/20100101 Firefox/33.0");
        UAS.add("Mozilla/5.0 (X11; Linux i586; rv:31.0) Gecko/20100101 Firefox/31.0");
        UAS.add("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:31.0) Gecko/20130401 Firefox/31.0");
        UAS.add("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0");
        UAS.add("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.13 Safari/537.36");
        UAS.add("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3756.400 QQBrowser/10.5.4043.400");
    }
}
