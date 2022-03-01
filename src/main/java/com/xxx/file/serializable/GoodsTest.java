package com.xxx.file.serializable;

import java.io.*;

public class GoodsTest {
    public static void main(String[] args) {
        String name = "./src/main/resources/test/score.txt";
        Goods goods1 = new Goods("gd001", "电脑", 3000);
        try {
            FileOutputStream fos = new FileOutputStream(name);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            FileInputStream fis = new FileInputStream(name);
            ObjectInputStream ois = new ObjectInputStream(fis);
            // 将Goods对象写入文件
            oos.writeObject(goods1);
            oos.writeBoolean(true);
            oos.flush();
            // 读取对象
            Goods goods = (Goods) ois.readObject();
            System.out.println(goods);
            System.out.println(ois.readBoolean());
            fos.close();
            oos.close();
            fis.close();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
