package com.xxx.player;

import java.util.ArrayList;
import java.util.List;

public class PlayList {
    private String playListName; // 播放列表名称
    private List<Song> musicList; // 播放列表的歌曲集合

    public PlayList(String playListName) {
        this.playListName = playListName;
        musicList = new ArrayList<Song>();
    }

    /**
     * 将歌曲添加到播放列表
     * @param song
     */
    public void addToPlayList(Song song) {
        boolean flag = false; //判断播放列表中的歌曲是否存在
        for (Song song1 : musicList) {
            if (song1.equals(song)) {
                flag = true;
                break;
            }
        }
        if (flag) {
            System.out.println("该歌曲已经存在于播放列表中，添加失败！");
        } else {
            musicList.add(song);
        }
    }

    /**
     * 通过歌曲ID查询
     * @param id 歌曲id
     * @return Song
     */
    public Song searchSongById(String id) {
        Song song = null;
        for (Song song1:musicList) {
            if (song1.getId().equals(id)) {
                song = song1;
                break;
            }
        }
        return song;
    }

    /**
     * 按照歌曲名称查询
     * @param name
     * @return
     */
    public Song searchSongByName(String name) {
        Song song = null;
        for (Song song1 : musicList) {
            if (song1.getName().equals(name)) {
                song = song1;
                break;
            }
        }
        return song;
    }

    /**
     * 显示播放列表中的所有歌曲
     */
    public void  displayAllSong() {
        System.out.println("播放列表中的所有歌曲为：");
        for (Song song : musicList) {
            System.out.println(song);
        }
    }

    /**
     * 修改播放列表中的歌曲信息
     * @param id
     * @param song
     */
    public void updateSong(String id, Song song) {
        Song song1 = searchSongById(id);
        if (song1 == null) {
            System.out.println("没找到ID为" + id + "对应的歌曲信息！");
        } else {
            musicList.remove(song1);
            musicList.add(song);
            System.out.println("修改成功！");
        }
    }

    /**
     * 删除播放列表中指定的歌曲信息
     * @param id
     */
    public void deleteSong(String id) {
        Song song = searchSongById(id);
        if (song != null) {
            musicList.remove(song);
        } else {
            System.out.println("没找到ID为" + id + "对应的歌曲信息！");
        }
    }

    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }

    public List<Song> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<Song> musicList) {
        this.musicList = musicList;
    }
}
