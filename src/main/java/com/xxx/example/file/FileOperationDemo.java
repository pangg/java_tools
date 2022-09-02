package com.xxx.example.file;

import org.junit.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileOperationDemo {
    /**
     * 使用File类的filename.compareTo(String filename)方法来比较同一目录中两个文件的路径
     */
    @Test
    public void compareFilePath() {
        File file1 = new File("F:/worksp/javaexamples/java_files/demo1.txt");
        File file2 = new File("F:/worksp/javaexamples/java_date_time/demo1.txt");

        if (file1.compareTo(file2) == 0) {
            System.out.println("Both paths are same!");
        } else {
            System.out.println("Paths are not same!");
        }
    }

    /**
     * 使用File()构造函数和File类的file.createNewFile()方法创建一个新文件
     */
    @Test
    public void createFile() {
        try {
            File file = new File("/tmp/t1.txt");
            if(file.createNewFile())
                System.out.println("Success!");
            else
                System.out.println
                        ("Error, file already exists.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用File类的File.createTempFile ()方法在指定的目录下创建一个文件
     * @throws IOException
     */
    @Test
    public void createFile2() throws IOException {
        File file = null;
        File dir = new File("/tmp/");
        file = File.createTempFile
                ("JavaTemp", ".javatemp", dir);
        System.out.println(file.getPath());
    }

    /**
     * 使用File类的file.lastModified()方法获取文件的最后修改日期
     */
    @Test
    public void fileLastModified() {
        File file = new File("/tmp/t1.txt");
        Long lastModified = file.lastModified();
        Date date = new Date(lastModified);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String DateToStr = format.format(date);
        System.out.println(DateToStr);

        System.out.println("Before Format : " + file.lastModified());
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        System.out.println("After Format : " + sdf.format(file.lastModified()));
    }

    /**
     * 使用File类的file.exists()方法检查文件的存在
     */
    @Test
    public void fileExistence() {
        File file = new File("/tmp/t1.txt");
        System.out.println(file.exists());


        File f = new File("/tmp/test/t2.txt");
        System.out.println(f.exists());
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File dir = new File(f.getParentFile(), f.getName());
        try {
            PrintWriter printWriter = new PrintWriter(dir);
            printWriter.print("writing anything...");
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用File类的file.setReadOnly()和file.canWrite()方法设置文件为只读模式
     */
    @Test
    public void readOnlyFile() {
        File file = new File("/tmp/test/t2.txt");
        System.out.println(file.setReadOnly());
        System.out.println(file.canWrite());
    }

    @Test
    public void readOnlyFile2() {
        File file = new File("/tmp/test/t2.txt");
        file.setReadOnly();

        if (file.canWrite()) {
            System.out.println("This file is writable");
        } else {
            System.out.println("This file is read only");
        }
        file.setWritable(true);
        if (file.canWrite()) {
            System.out.println("This file is writable");
        } else {
            System.out.println("This file is read only");
        }
    }

    /**
     * 使用File类的oldName.rename To(new_Name)方法重命名文件
     */
    @Test
    public void renameFile() {
        File oldName = new File("/tmp/test/t2.txt");
        File newName = new File("/tmp/test/t--2.txt");

        if (oldName.renameTo(newName)) {
            System.out.println("renamed");
        } else {
            System.out.println("Error");
        }
    }

    /**
     * 使用File类的file.exists()和file.length()方法获取文件的大小(以字节为单位)
     */
    @Test
    public void fileSize() {
        long size = getFileSize("/tmp/test/t--2.txt");
        System.out.println("Filesize in bytes: " + size);
    }

    private long getFileSize(String filename) {
        File file = new File(filename);
        if (!file.exists() || !file.isFile()) {
            System.out.println("File doesn\'t exist");
            return -1;
        }
        return file.length();
    }

    @Test
    public void fileSize2() {
        File file = new File("/tmp");
        if (file.exists()) {
            double bytes = file.length();
            double kilobytes = (bytes / 1024);
            double megabytes = (kilobytes / 1024);
            double gigabytes = (megabytes / 1024);
            double terabytes = (gigabytes / 1024);
            double petabytes = (terabytes / 1024);
            double exabytes = (petabytes / 1024);
            double zettabytes = (exabytes / 1024);
            double yottabytes = (zettabytes / 1024);

            System.out.println("bytes : " + bytes);
            System.out.println("kilobytes : " + kilobytes);
            System.out.println("megabytes : " + megabytes);
            System.out.println("gigabytes : " + gigabytes);
            System.out.println("terabytes : " + terabytes);
            System.out.println("petabytes : " + petabytes);
            System.out.println("exabytes : " + exabytes);
            System.out.println("zettabytes : " + zettabytes);
            System.out.println("yottabytes : " + yottabytes);
        } else {
            System.out.println("File does not exists!");
        }
    }

    /**
     * 使用File类中的FileToChange.lastModified()和fileToChange setLastModified()来更改文件的最后修改时间
     */
    @Test
    public void settingFileDate() throws IOException {
        File fileToChange = new File("/tmp/myfile.txt");
        fileToChange.createNewFile();
        Date filetime = new Date(fileToChange.lastModified());
        System.out.println(filetime.toString());
        System.out.println(fileToChange.setLastModified(System.currentTimeMillis()));
        filetime = new Date(fileToChange.lastModified());
        System.out.println(filetime.toString());
    }

    /**
     * 使用File类的createTempFile()方法创建临时文件
     */
    @Test
    public void createTemporaryFile() throws IOException {
        File dir = new File("/tmp/");
        File temp = File.createTempFile("pattern", ".suffix", dir);
        // temp.deleteOnExit();
        BufferedWriter out = new BufferedWriter(new FileWriter(temp));
        out.write("aString");
        System.out.println("temporary file created:");
        out.close();
    }

    /**
     * 何使用filewriter()方法在现有文件中附加字符串
     */
    @Test
    public void appendString2File() {
        String filename = "/tmp/write-filename.txt";
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filename));
            out.write("This is the first String1\n");
            out.close();
            out = new BufferedWriter(new FileWriter(filename, true));
            out.write("This is the second String2\n");
            out.close();
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String str;
            // 输出文件内容
            while ((str = in.readLine()) != null) {
                System.out.println(str);
            }
            in.close();
        } catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }

    @Test
    public void appendString2File2() {
        try {
            String data = " yiibai.com is one of the best website in the world";
            File f1 = new File("/tmp/write-filename2.txt");
            if(!f1.exists()) {
                f1.createNewFile();
            }
            FileWriter fileWritter = new FileWriter(f1,true);
            BufferedWriter bw = new BufferedWriter(fileWritter);
            bw.write(data);
            bw.flush();
            bw.close();
            fileWritter.close();
            System.out.println("Done");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 使用BufferedWriter类的read()和write()方法将一个文件的内容复制到另一个文件中
     */
    @Test
    public void copyFile() throws Exception {
        String srcfile = "/tmp/t1.txt";
        String destnfile = "/tmp/t2.txt";
        BufferedWriter out1 = new BufferedWriter(new FileWriter(srcfile));
        out1.write("Line 1 : string to be copied\n");
        out1.write("Line 2 : to be copied\n");
        out1.close();
        InputStream in = new FileInputStream(new File(srcfile));
        OutputStream out = new FileOutputStream(new File(destnfile));
        byte[] buf = new byte[1024];
        int len;

        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
        BufferedReader in1 = new BufferedReader(new FileReader(destnfile));
        String str;

        while ((str = in1.readLine()) != null) {
            System.out.println(str);
        }
        in.close();
    }

    /**
     * 将一个文件复制到另一个文件
     */
    @Test
    public void copyFile2() {
        FileInputStream ins = null;
        FileOutputStream outs = null;
        try {
            File infile = new File("/tmp/t1.txt");
            File outfile = new File("/tmp/t3.txt");
            ins = new FileInputStream(infile);
            outs = new FileOutputStream(outfile);
            byte[] buffer = new byte[1024];
            int length;

            while ((length = ins.read(buffer)) > 0) {
                outs.write(buffer, 0, length);
            }
            ins.close();
            outs.close();
            System.out.println("File copied successfully!!");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * 使用File类的delete()方法删除文件
     */
    @Test
    public void deleteFile() {
        String filename  = "/tmp/t3.txt";
        try {
            BufferedWriter out = new BufferedWriter (new FileWriter(filename));
            out.write("aString1\n");
            out.close();
            boolean success = (new File(filename)).delete();

            if (success) {
                System.out.println("The file has been successfully deleted");
            }
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String str;

            while ((str = in.readLine()) != null) {
                System.out.println(str);
            }
            in.close();
        }catch (IOException e) {
            System.out.println("exception occoured"+ e);
            System.out.println("File does not exist or you are trying to read a file that has been deleted");
        }
    }

    /**
     * 使用BufferedReader类的readLine()方法读取文件
     */
    @Test
    public void readFile() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("/tmp/t1.txt"));
            String str;

            while ((str = in.readLine()) != null) {
                System.out.println(str);
            }
            System.out.println(str);
        } catch (IOException e) {
        }
    }

    /**
     * 使用BufferedWriter的write()方法来写入文件
     */
    @Test
    public void writeToFile() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("/tmp/t4.txt"));
            out.write("This a String write to file.\n");
            out.close();
            System.out.println("File created and write successfully");
        } catch (IOException e) {
        }
    }

    @Test
    public void writeToFile2() {
        try {
            String content = "Yiibai.com is one the best site in the world";
            File file = new File("/tmp/outfile2.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

            System.out.println("All Job Done!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
