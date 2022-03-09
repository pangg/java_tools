package com.xxx.oaSystem.dao;

import com.xxx.oaSystem.entity.LeaveForm;
import com.xxx.oaSystem.utils.MybatisUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class LeaveFormDaoTest {

    @Test
    public void testInsert() {
        MybatisUtils.executeUpdate(sqlSession -> {
            LeaveFormDao dao = sqlSession.getMapper(LeaveFormDao.class);
            LeaveForm form = new LeaveForm();
            form.setEmployeeId(4L);
            form.setFormType(1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startTime = null;
            Date endTime = null;
            try {
                startTime = sdf.parse("2020-03-25 08:00:00");
                endTime = sdf.parse("2020-04-01 18:00:00");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            form.setStartTime(startTime);
            form.setEndTime(endTime);
            form.setReason("回家探亲");
            form.setCreateTime(new Date());
            form.setState("processing");
            dao.insert(form);
            return null;
        });
    }

    @Test
    public void testSelectByParams(){
        MybatisUtils.executeQuery(sqlSession -> {
            LeaveFormDao dao = sqlSession.getMapper(LeaveFormDao.class);
            List<Map> list = dao.selectByParams("process", 2l);
            System.out.println(list);
            return list;
        });
    }
}