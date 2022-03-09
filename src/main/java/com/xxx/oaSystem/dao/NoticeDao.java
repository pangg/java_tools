package com.xxx.oaSystem.dao;

import com.xxx.oaSystem.entity.Notice;

import java.util.List;

public interface NoticeDao {
    public void insert(Notice notice);

    public List<Notice> selectByReceiverId(Long receiverId);
}
