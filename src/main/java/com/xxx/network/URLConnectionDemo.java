package com.xxx.network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ResponseCache;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectionDemo {
    private final static String LINK = "https://www.cnblogs.com/zumengjie/p/14897556.html";
    public static void main(String[] args) throws Exception {
        // openConnectionTest();
        // readOriginData();
        // readHeaderParts();
        // readHeaders();
        // webCache();
        // getURLDemo();
        setDoInputDemo();
    }

    // 构建URLConnection
    public static void openConnectionTest() throws Exception {
        URL url = new URL(LINK);
        URLConnection uc = url.openConnection();
        System.out.println(uc.getClass().getName());
    }

    // 读取服务器数据
    public static void readOriginData() throws Exception {
        URL url = new URL(LINK);
        URLConnection uc = url.openConnection();
        InputStream is = uc.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String s = null;
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }
    }

    // 读取首部
    /**
     * getContentType():返回相应主体的MIME内容类型。如何没有提供则返回null如果内容是某种形式的文本，那么首部还会包含一个字符集部分来标识文档的字符编码方式。
     * getContentLength():返回内容中有多少子节，如果没有指定则返回-1
     * getContentLengthLong():Java7的方法和上述方法类型为了防止返回内容过大超出int范围
     * getContentEncoding():返回内容编码，注意内容编码和字符编码是两回事，内容编码指出子节如何编码未其他子节。一般没有或者是x-gzip
     * getDate():获取文档发出的时间
     * getLastModified():获取文档最后修改的时间，一般没有
     * getExpiration():获取文档过期时间，一般没有
     */
    public static void readHeaderParts() throws Exception {
        URL url = new URL(LINK);
        URLConnection uc = url.openConnection();
        System.out.println(uc.getContentType());
        System.out.println(uc.getContentLength());
        System.out.println(uc.getContentEncoding());
        System.out.println(uc.getDate());
        System.out.println(uc.getLastModified());
        System.out.println(uc.getExpiration());
    }

    // 获取全部header
    public static void readHeaders() throws Exception {
        URL url = new URL(LINK);
        URLConnection uc = url.openConnection();
        System.out.println(uc.getHeaderField("a"));
        System.out.println("---------------->");
        for (int i = 0; ; i++) {
            String key = uc.getHeaderFieldKey(i);
            String value = uc.getHeaderField(i);
            if (value == null) {
                break;
            }
            System.out.println(key + "------>" + value);
        }
    }

    // Java的Web缓存
    /**
     * 默认情况下,Java并不完成缓存.要安装URL类使用的系统级缓存需要有ResponseCache的一个子类,CacheRequest的一个子类,CacheResponse的一个子类.
     * 要安装你的ResponseCache子类来处理你的CacheRequest和CacheResponse子类需要把它传递到静态方法ResponseCache.setDefault()这会把这个缓存对象安装为系统的默认缓存.
     * Java虚拟机只支持一个共享缓存.
     *
     * @GetMapping(value="test")
     * public String g(HttpServletResponse response) {
     *      System.out.println("1111111111111111111111111111111111");
     *      response.addHeader("Cache-control", "max-age=60");
     *      return "success1";
     * }//服务端代码
     */
    public static void webCache() throws Exception {
        ResponseCache.setDefault(new MemoryCache());
        URL url = new URL(LINK);
        URLConnection uc = url.openConnection();

        BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        System.out.println(br.readLine());

        URLConnection uc2 = url.openConnection();
        BufferedReader br2 = new BufferedReader(new InputStreamReader(uc2.getInputStream()));
        System.out.println(br2.readLine());

        Thread.sleep(1000*70);

        URLConnection uc3 = url.openConnection();
        BufferedReader br3 = new BufferedReader(new InputStreamReader(uc3.getInputStream()));
        System.out.println(br3.readLine());
    }

    // getURL();获取构建URLConnection的URL
    public static void getURLDemo() throws Exception {
        URL url = new URL(LINK);
        URLConnection uc = url.openConnection();
        System.out.println(uc.getURL());
    }

    // setDoInput(boolean b);默认是true,如果设置成false则不可读
    public static void setDoInputDemo() throws Exception {
        URL url = new URL(LINK);
        URLConnection uc = url.openConnection();
        uc.setDoInput(false);  //设置为false则不可读
        BufferedReader bf = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        System.out.println(bf.readLine());
    }
}
