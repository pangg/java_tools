package com.xxx.oaSystem.dao;

import com.xxx.oaSystem.entity.Notice;
import com.xxx.oaSystem.utils.MybatisUtils;
import org.junit.Test;

import java.util.Date;

public class NoticeDaoTest {

    @Test
    public void insert() {
        MybatisUtils.executeUpdate(sqlSession -> {
            NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
            Notice notice = new Notice();
            notice.setReceiverId(2L);
            notice.setContent("测试消息");
            notice.setCreateTime(new Date());
            dao.insert(notice);
            return null;
        });
    }
}