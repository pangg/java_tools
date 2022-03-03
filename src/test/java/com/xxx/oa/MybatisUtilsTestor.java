package com.xxx.oa;

import com.xxx.oaSystem.utils.MybatisUtils;
import org.junit.Test;

public class MybatisUtilsTestor {
    @Test
    public void testCase1() {
        String result = (String) MybatisUtils.executeQuery(sqlSession -> {
            String out = sqlSession.selectOne("test.sample");
            return out;
        });
        System.out.println(result);
    }

    @Test
    public void testCase2() {
        String result = (String) MybatisUtils.executeQuery(sqlSession -> sqlSession.selectOne("test.sample"));
        System.out.println(result);
    }
}
