package com.xxx.oaSystem.service;

import com.xxx.oaSystem.dao.NoticeDao;
import com.xxx.oaSystem.entity.Notice;
import com.xxx.oaSystem.utils.MybatisUtils;

import java.util.List;

public class NoticeService {
    /**
     * 查询指定员工的系统消息
     * @param receiverId
     * @return 最近100条消息列表
     */
    public List<Notice> getNoticeList(Long receiverId) {
        return (List<Notice>) MybatisUtils.executeQuery(sqlSession -> {
            NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
            return dao.selectByReceiverId(receiverId);
        });
    }
}
