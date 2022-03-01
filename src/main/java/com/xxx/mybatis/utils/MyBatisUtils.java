package com.xxx.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.Reader;

/**
 * 工具类，创建全局唯一的SqlSessionFactory对象
 */
public class MyBatisUtils {
    // 利用static（静态）属于类不属于对象，且全局唯一
    private static SqlSessionFactory sqlSessionFactory = null;

    // 利用静态块在初始化类时实例化sqlSessionFactory
    static {
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
            // 抛出初始化异常
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * 创建一个新的SqlSession对象
     * @return
     */
    public static SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }

    /**
     * 释放一个有效的SqlSession对象
     * @param session
     */
    public static void closeSession(SqlSession session) {
        if (session != null) {
            session.close();
        }
    }
}
