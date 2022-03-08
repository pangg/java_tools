package com.xxx.oaSystem.dao;

import com.xxx.oaSystem.entity.ProcessFlow;
import com.xxx.oaSystem.utils.MybatisUtils;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ProcessFlowDaoTest {

    @Test
    public void testInsert() {
        MybatisUtils.executeUpdate(sqlSession -> {
            ProcessFlowDao dao = sqlSession.getMapper(ProcessFlowDao.class);
            ProcessFlow flow = new ProcessFlow();
            flow.setFormId(1L);
            flow.setOperatorId(2L);
            flow.setAction("audit");
            flow.setReason("同意");
            flow.setResult("approved");
            flow.setCreateTime(new Date());
            flow.setAuditTime(new Date());
            flow.setOrderNo(1);
            flow.setState("ready");
            flow.setIsLast(1);
            dao.insert(flow);
            return null;
        });
    }
}