package com.xxx.player;


import java.util.Scanner;

public class TestDemo {
    public void testSong() {
        Song song1 = new Song("s001", "两只老虎", "小太阳");
        Song song2 = new Song("s002", "小燕子", "风车");
        Song song3 = new Song("s003", "茉莉花", "彩虹");
        Song song4 = new Song("s003", "茉莉花", "彩虹");
        System.out.println(song1);
        System.out.println("song1 == song3 ? " + song1.equals(song3));
        System.out.println("song4 == song3 ? " + song4.equals(song3));
        System.out.println(song4 == song3);
    }


    public void testPlayList() {
        Song song1 = new Song("s001", "两只老虎", "小太阳");
        Song song2 = new Song("s002", "小燕子", "风车");
        Song song3 = new Song("s003", "茉莉花", "彩虹");
        Song song4 = new Song("s003", "茉莉花", "彩虹");
        PlayList mainPlayList = new PlayList("主播放列表");
        mainPlayList.addToPlayList(song1);
        mainPlayList.addToPlayList(song2);
        mainPlayList.addToPlayList(song3);
        mainPlayList.addToPlayList(song4);
        mainPlayList.displayAllSong();

        // 通过id查询
        Song song = mainPlayList.searchSongById("s001");
        if (song != null) {
            System.out.println("通过ID查询到歌曲：");
            System.out.println(song);
        } else {
            System.out.println("该歌曲不存在！");
        }

        // 通过歌曲名称查询
        song = null;
        song = mainPlayList.searchSongByName("小燕子");
        if (song != null) {
            System.out.println("通过名称查询歌曲：");
            System.out.println(song);
        } else {
            System.out.println("该歌曲不存在！");
        }

        // 修改
        Song songUpdate = new Song("s005", "蜗牛与黄鹂鸟", "小太阳");
        mainPlayList.updateSong("s003", songUpdate);
        mainPlayList.displayAllSong();

        // 删除
        mainPlayList.deleteSong("s005");
        mainPlayList.displayAllSong();
    }

    /**
     * 测试播放列表集合类
     */
    public void testPlayListCollection() {
        Song song1 = new Song("s001", "两只老虎", "小太阳");
        Song song2 = new Song("s002", "小燕子", "风车");
        Song song3 = new Song("s003", "茉莉花", "彩虹");

        PlayList mainPlayList = new PlayList("主播放列表");
        mainPlayList.addToPlayList(song1);
        mainPlayList.addToPlayList(song2);
        mainPlayList.addToPlayList(song3);

        PlayList favouritePlayList = new PlayList("最喜欢的歌曲");
        favouritePlayList.addToPlayList(mainPlayList.getMusicList().get(0));
        favouritePlayList.addToPlayList(mainPlayList.getMusicList().get(1));
        favouritePlayList.displayAllSong();

        // 将两个播放列表添加到播放集合中
        PlayListCollection plc = new PlayListCollection();
        plc.addPlayList(mainPlayList);
        plc.addPlayList(favouritePlayList);
        plc.displayListName();

        // 根据播放列表名字查询列表信息，并显示所有歌曲信息
        PlayList playList = plc.searchPlayListByName("最喜欢的歌曲");
        playList.displayAllSong();

        // 删除列表信息
        System.out.println("删除前：");
        plc.displayListName();
        plc.deletePlayList(favouritePlayList);
        System.out.println("删除后：");
        plc.displayListName();

    }

    public void mainMenu() {
        System.out.println("**********************************");
        System.out.println("     **主菜单**                    ");
        System.out.println("     1--播放列表管理                  ");
        System.out.println("     2--播放器管理                  ");
        System.out.println("     0--退出                  ");
        System.out.println("**********************************");
    }

    public void playListMenu() {
        System.out.println("**********************************");
        System.out.println("     **播放列表管理**                    ");
        System.out.println("    1--将歌曲添加到主播放列表         ");
        System.out.println("    2--将歌曲添加到普通播放列表         ");
        System.out.println("    3--通过歌曲id查询播放列表中的歌曲         ");
        System.out.println("    4--通过歌曲名称查询播放列表中的歌曲         ");
        System.out.println("    5--修改播放列表中的歌曲         ");
        System.out.println("    6--删除播放列表中的歌曲         ");
        System.out.println("    7--显示播放列表中的所有歌曲         ");
        System.out.println("    9--返回上一级菜单        ");
        System.out.println("**********************************");
    }

    public void playerMenu() {
        System.out.println("**********************************");
        System.out.println("     **播放器管理**                    ");
        System.out.println("    1--向播放器添加播放列表         ");
        System.out.println("    2--从播放器删除播放列表         ");
        System.out.println("    3--通过名字查询播放列表信息         ");
        System.out.println("    4--显示所有播放列表名称        ");
        System.out.println("    9--返回上一级菜单        ");
        System.out.println("**********************************");
    }

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        TestDemo td = new TestDemo();
        Scanner sc = new Scanner(System.in);
        int input = 0, input1 = 0, input2 = 0;

        // 创建一个播放列表容器
        PlayListCollection plc = new PlayListCollection();
        // 创建主播放列表
        PlayList mainPlayList = new PlayList("主播放列表");
        // 将主播放列表添加到播放器
        plc.addPlayList(mainPlayList);

        PlayList favouritePlayList = null;

        while (true) {
            td.mainMenu();
            System.out.println("请输入对应数字进行操作：");
            input = sc.nextInt();
            if (input == 0) {
                break;
            }
            switch (input) {
                case 1:  // 播放列表管理
                    while (true) {
                        td.playListMenu();
                        System.out.println("请输入对应的数字对播放列表进行管理：");
                        input1 = sc.nextInt();
                        if (input1 == 9) {
                            break;
                        }
                        switch (input1) {
                            case 1:
                                System.out.println("将歌曲添加到主播放列表");
                                System.out.println("请输入要添加歌曲的数量：");
                                int count = sc.nextInt();
                                for (int i = 1; i<= count; i++) {
                                    System.out.println("请输入第" + i + "首歌曲：");
                                    System.out.println("请输入歌曲的id：");
                                    String strId = sc.next();
                                    System.out.println("请输入歌曲的名称：");
                                    String strName = sc.next();
                                    System.out.println("请输入歌曲的演唱者：");
                                    String strSinger = sc.next();

                                    Song song = new Song(strId, strName, strSinger);
                                    mainPlayList.addToPlayList(song);
                                    // mainPlayList.displayAllSong();
                                }
                                break;
                            case 2:
                                System.out.println("将歌曲添加到普通播放列表");
                                System.out.println("请输入要添加的播放器列表名称：");
                                String sName = sc.next();
                                favouritePlayList = plc.searchPlayListByName(sName);
                                if(favouritePlayList ==null) {
                                    System.out.println("该播放列表不存在，请先将播放列表添加到播放器中！");
                                } else {
                                    System.out.println("请输入要添加歌曲的数量：");
                                    int count1 = sc.nextInt();
                                    for (int i = 1; i<=count1; i++) {
                                        System.out.println("请输入第" + i + "首歌曲：");
                                        System.out.println("请输入歌曲id：");
                                        String strId = sc.next();
                                        Song song = mainPlayList.searchSongById(strId);
                                        if (song == null) {
                                            System.out.println("该歌曲在主播放列表中不存在，继续输入歌曲的其他信息！");
                                            System.out.println("请输入歌曲名称：");
                                            String strName = sc.next();
                                            System.out.println("请输入歌曲演唱者：");
                                            String strSinger = sc.next();
                                            song = new Song(strId, strName, strSinger);
                                            favouritePlayList.addToPlayList(song);
                                            mainPlayList.addToPlayList(song);
                                        } else {
                                            favouritePlayList.addToPlayList(song);
                                            System.out.println("歌曲插入成功");
                                        }
                                    }
                                    System.out.println("主播放列表：");
                                    mainPlayList.displayAllSong();
                                    System.out.println("普通播放列表：");
                                    favouritePlayList.displayAllSong();
                                }
                                break;
                            case 3:
                                System.out.println("通过歌曲id查询播放列表中的歌曲");
                                System.out.println("请输入要查询的播放列表名称：");
                                String strPlayListName1 = sc.next();
                                PlayList pl = plc.searchPlayListByName(strPlayListName1);
                                if (pl == null) {
                                    System.out.println("该播放列表不存在！");break;
                                } else {
                                    System.out.println("请输入要查询的歌曲id：");
                                    String strId1 = sc.next();
                                    Song s = pl.searchSongById(strId1);
                                    if (s == null) {
                                        System.out.println("该歌曲在播放列表" + strPlayListName1 + "中不存在！");
                                    } else {
                                        System.out.println("该歌曲的信息为：");
                                        System.out.println(s);
                                    }
                                }
                                break;
                            case 4:
                                System.out.println("通过歌曲名称查询播放列表中的歌曲");
                                break;
                            case 5:
                                System.out.println("修改播放列表中的歌曲");
                                break;
                            case 6:
                                System.out.println("删除播放列表中的歌曲");
                                break;
                            case 7:
                                System.out.println("显示播放列表中的所有歌曲");
                                break;
                            default:
                                System.out.println("该歌曲没有对应的操作！");
                                break;
                        }
                    }
                    break;
                case 2: // 播放器管理
                    while (true) {
                        td.playerMenu();
                        System.out.println("请输入对应的数字对播放器进行管理：");
                        input2 = sc.nextInt();
                        if (input2 == 9) {
                            break;
                        }
                        switch (input2) {
                            case 1:
                                System.out.println("向播放器添加播放列表");
                                System.out.println("请输入要添加的播放列表名称：");
                                String playerName = sc.next();
                                favouritePlayList = new PlayList(playerName);
                                plc.addPlayList(favouritePlayList);
                                break;
                            case 2:
                                System.out.println("从播放器删除播放列表");
                                System.out.println("请输入要删除的播放器列表名称：");
                                String strPlayListName = sc.next();
                                if (strPlayListName.equals("主播放列表")) {
                                    System.out.println("主播放列表不能删除");
                                    break;
                                }
                                PlayList playList = plc.searchPlayListByName(strPlayListName);
                                if (playList == null) {
                                    System.out.println("该播放列表不存在！");
                                } else {
                                    plc.deletePlayList(playList);
                                }
                                break;
                            case 3:
                                System.out.println("通过名字查询播放器播放列表信息");
                                System.out.println("请输入要查询的播放列表名称：");
                                String strPlayList1 = sc.next();
                                PlayList playList2 = plc.searchPlayListByName(strPlayList1);
                                if (playList2 == null) {
                                    System.out.println("该播放列表不存在！");
                                } else {
                                    System.out.println("该播放列表的名称为：" + strPlayList1);
                                    playList2.displayAllSong();
                                }
                                break;
                            case 4:
                                System.out.println("显示所有播放列表名称");
                                plc.displayListName();
                                break;
                            default:
                                System.out.println("该歌曲没有对应的操作!");
                                break;
                        }
                    }
                    break;
                default:
                    System.out.println("该数字没有对应的操作！");
                    break;
            }
        }
    }
}
