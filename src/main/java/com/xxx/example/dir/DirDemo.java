package com.xxx.example.dir;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class DirDemo {
    /**
     * 使用File类的file.mkdirs()方法来递归创建一个或多个目录
     */
    @Test
    public void createDirectories() {
        String directories = "/tmp/dir1/dir2";
        File file = new File(directories);
        boolean result = file.mkdirs();
        System.out.println("Status = " + result);
    }

    /**
     * 使用File类的dir.isDirectory()，dir.list()和deleteDir()方法来删除其文件和子目录后，再删除该目录
     */
    @Test
    public void deleteDirectory() {
        String directories = "/tmp/dir1/dir2";
        deleteDir(new File(directories));
    }

    public boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            if (children == null) {
                return false;
            }
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));

                if (!success) {
                    return false;
                }
            }
        }
        System.out.println("The directory is deleted.");
        return dir.delete();
    }

    /**
     * 使用File类的file.isDirectory()，file.list()和file.getPath()方法获取目录的大小
     */
    @Test
    public void emptyDirectory() {
        String dir = "/tmp/dir1/dir2";
        File file = new File(dir);
        if (file.isDirectory()) {
            String[] files = file.list();
            if (files.length > 0) {
                System.out.println("The " + file.getPath() + " is not empty!");
                for(String str : files){
                    System.out.println("Exsits file: " + str);
                }
            }else{
                System.out.println("The " + file.getPath() + " is empty!");
            }
        }
    }

    /**
     * 使用File类的file.isHidden()方法获取文件是否被隐藏
     */
    @Test
    public void hiddenDirectory() {
        File file = new File("/tmp/dir1/dir2/t1.txt");
        if (file.isHidden()) {
            System.out.println("This file is hidden");
        } else {
            System.out.println("This file is not hidden");
        }
    }

    /**
     * 使用File类的file.getName()和file.listFiles()方法打印指定目录的层次结构
     */
    @Test
    public void directoryHierarchy() throws Exception {
        showDir(1, new File("/tmp/dir1/"));
    }

    private void showDir(int indent, File file) throws IOException {
        for (int i = 0; i < indent; i++) {
            System.out.print("-");
        }
        System.out.println(file.getName());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                showDir(indent + 2, files[i]);
            }
        }
    }

    /**
     * 使用File类的file.lastModified()方法来获取目录的最后修改时间
     */
    @Test
    public void directoryModificationTime() {
        File f1 = new File("/tmp/dir1/dir2/t1.txt");
        System.out.println("Before Format : " + f1.lastModified());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println("After Format : " + sdf.format(f1.lastModified()));
    }

    /**
     * 使用File类的file.getParent()方法获取文件的父目录
     */
    @Test
    public void parentDirectory() {
        File file = new File("/tmp/dir1/dir2/t1.txt");
        String strParentDirectory = file.getParent();
        System.out.println("Parent directory is : " + strParentDirectory);
    }

    /**
     * 使用File类的dir.list()方法搜索并获取指定目录下的所有文件的列表
     */
    @Test
    public void searchingFiles() {
        File dir = new File("/tmp/dir1/dir2");
        String[] children = dir.list();

        if (children == null) {
            System.out.println("does not exist or  is not a directory");
        } else {
            for (int i = 0; i < children.length; i++) {
                String filename = children[i];
                System.out.println(filename);
            }
        }
    }

    @Test
    public void searchingFiles2() throws Exception {
        System.out.println("Enter the path to folder to search for files=> ");
        Scanner s1 = new Scanner(System.in);
        String folderPath = s1.next();
        File folder = new File(folderPath);

        if (folder.isDirectory()) {
            File[] listOfFiles = folder.listFiles();
            if (listOfFiles.length < 1)
                System.out.println("There is no File inside Folder");
            else
                System.out.println("List of Files & Folder");
            for (File file : listOfFiles) {
                if (!file.isDirectory())
                    System.out.println(file.getCanonicalPath().toString());
            }
        } else
            System.out.println("There is no Folder @ given path :" + folderPath);
    }

    /**
     * 通过FileUtils类的FileUtils.sizeofDirectory(File Name)方法获取目录的大小
     */
    @Test
    public void directorySize() {
        String dir = "/tmp/";
        long size = FileUtils.sizeOfDirectory(new File(dir));
        System.out.println("Size of " + dir + ": " + size + " bytes");

        System.out.println("-------------------");
        long folderSize = findSize(dir);
        System.out.println("Size of " + dir + " in byte :" + folderSize);
    }

    public long findSize(String path) {
        long totalSize = 0;
        ArrayList<String> directory = new ArrayList<String>();
        File file = new File(path);

        if (file.isDirectory()) {
            directory.add(file.getAbsolutePath());
            while (directory.size() > 0) {
                String folderPath = directory.get(0);
                System.out.println("Size of this :" + folderPath);
                directory.remove(0);
                File folder = new File(folderPath);
                File[] filesInFolder = folder.listFiles();
                int noOfFiles = filesInFolder.length;

                for (int i = 0; i < noOfFiles; i++) {
                    File f = filesInFolder[i];
                    if (f.isDirectory()) {
                        directory.add(f.getAbsolutePath());
                    } else {
                        totalSize += f.length();
                    }
                }
            }
        } else {
            totalSize = file.length();
        }
        return totalSize;
    }

    /**
     * 通过File类的dir.isDirectory()和dir.list()方法遍历目录
     */
    @Test
    public void traversingDirectory() throws Exception {
        System.out.println("The Directory is traversed.");
        String dir = "/tmp/";
        File file = new File(dir);
        visitAllDirsAndFiles(file, 0);
    }

    private void visitAllDirsAndFiles(File file, int indent) throws IOException {
        for (int i = 0; i <= indent; i++) {
            System.out.print('-');
        }
        System.out.println(file.getName());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++)
                visitAllDirsAndFiles(files[i], indent + 2);
        }
    }

    /**
     * 使用getProperty()方法获取当前目录
     */
    @Test
    public void findCurrentDirectory() {
        String curDir = System.getProperty("user.dir");
        System.out.println("You currently working in :" + curDir + " Directory");
    }

    /**
     * 使用File类的listRoots()方法在系统中查找根目录
     */
    @Test
    public void displayRootDirectories() {
        File[] roots = File.listRoots();
        System.out.println("Root directories in your system are:");

        for (int i = 0; i < roots.length; i++) {
            System.out.println(roots[i].toString());
        }
    }

    /**
     * 创建Filefiter来搜索目录中的特定文件。以下示例显示文件名以't'开头的所有文件
     */
    @Test
    public void searchFileInDirectory() {
        File dir = new File("/tmp/dir1/dir2/");
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.startsWith("t");
            }
        };
        String[] children = dir.list(filter);
        if (children == null) {
            System.out.println("Either dir does not exist or is not a directory: ");
        } else {
            for (int i = 0; i < children.length; i++) {
                String filename = children[i];
                System.out.println(filename);
            }
        }
    }

    /**
     * 使用File类的list()方法显示目录中包含的所有文件
     */
    @Test
    public void displayFilesInDirectory() {
        File dir = new File("/tmp/dir1/dir2/");
        String[] children = dir.list();

        if (children == null) {
            System.out.println( "Either dir does not exist or is not a directory");
        } else {
            for (int i=0; i< children.length; i++) {
                String filename = children[i];
                System.out.println(filename);
            }
        }
    }

    static String temp = "";
    public static File folder = new File("/tmp/dir1/dir2/");
    @Test
    public void displayFilesInDirectory2() {
        System.out.println("Reading files under the folder "+ folder.getAbsolutePath());
        listFilesForFolder(folder);
    }

    public void listFilesForFolder(File folder) {
        for (File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                if (fileEntry.isFile()) {
                    temp = fileEntry.getName();
                    if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("txt"))
                        System.out.println("" + folder.getAbsolutePath()+ "\\" + fileEntry.getName());
                }
            }
        }
    }

    /**
     * 显示目录中的所有目录
     */
    @Test
    public void displayDirectories() {
        File dir = new File("/tmp/");
        File[] files = dir.listFiles();
        FileFilter fileFilter = new FileFilter() {
            public boolean accept(File file) {
                return file.isDirectory();
            }
        };
        files = dir.listFiles(fileFilter);
        System.out.println(files.length +" directories found.");

        if (files.length == 0) {
            System.out.println("Either dir does not exist or is not a directory");
        } else {
            for (int i = 0; i < files.length; i++) {
                File filename = files[i];
                System.out.println(filename.toString());
            }
        }
    }

    @Test
    public void displayDirectories2() {
        File currentDir = new File("/tmp/");
        displayDirectoryContents(currentDir);
    }

    public void displayDirectoryContents(File dir) {
        try {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println("--directory:" + file.getCanonicalPath());
                    displayDirectoryContents(file);
                } else {
                    System.out.println("----file:" + file.getCanonicalPath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
