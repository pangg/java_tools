package com.xxx.mybatis;


import com.xxx.mybatis.dto.GoodsDTO;
import com.xxx.mybatis.entity.Goods;
import com.xxx.mybatis.utils.MyBatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBatisTestor {

    @Test
    public void testSqlSessionFactory() throws IOException {
        // 利用Reader加载classpath下的mybatis-config.xml核心配置文件
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        // 初始化SqlSessionFactory对象，同时解析mybatis-config.xml文件
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        System.out.println("SessionFactory加载成功");

        SqlSession sqlSession = null;
        try {
            // 创建SqlSession对象，SqlSession是JDBC的扩展类，用于与数据库交互
            sqlSession = sqlSessionFactory.openSession();
            // 创建数据库连接（测试用，实际生产不需要）
            Connection connection = sqlSession.getConnection();
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                // 如果type="POOLED"，代表使用连接池，close则是将连接回收到连接池
                // 如果type="UNPOOLED"，代表直连，close则会调用Connection.close()方法关闭连接
                sqlSession.close();
            }
        }
    }

    @Test
    public void testMyBatisUtils() throws Exception {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            Connection connection = sqlSession.getConnection();
            System.out.println(connection);
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    /**
     * MyBatis数据查询步骤
     * 1. 创建实体类（Entity）
     * 2. 创建Mapper XML
     * 3. 编写<select>Sql标签
     * 4. mybatis-config.xml中开启驼峰命名映射
     * 5. mybatis-config.xml中新增<mapper>
     * 6. SqlSession执行select语句
     */
    @Test
    public void testSelectAll() throws Exception {
        SqlSession session = null;
        try {
            session = MyBatisUtils.openSession();
            List<Goods> list = session.selectList("goods.selectAll");
            for (Goods goods : list) {
                System.out.println(goods.getTitle());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(session);
        }
    }

    @Test
    public void testSelectById() throws Exception {
        SqlSession session = null;
        try {
            session = MyBatisUtils.openSession();
            Goods good = session.selectOne("goods.selectById", 3400);
            System.out.println(good.getTitle());
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(session);
        }
    }

    /**
     * 多参数sql查询
     * @throws Exception
     */
    @Test
    public void testSelectByPriceRange() throws Exception {
        SqlSession session = null;
        try {
            session = MyBatisUtils.openSession();
            Map<String, Integer> param = new HashMap<>();
            param.put("min", 20);
            param.put("max", 2000);
            param.put("limt", 10);
            List<Goods> list = session.selectList("goods.selectByPriceRange", param);
            for (Goods goods : list) {
                System.out.println(goods.getTitle() + ":" + goods.getCurrentPrice());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(session);
        }
    }

    /**
     * 获取连表查询结果
     * @throws Exception
     */
    @Test
    public void testSelectGoodsMap() throws Exception {
        SqlSession session = null;
        try {
            session = MyBatisUtils.openSession();
            List<Map> list = session.selectList("goods.selectGoodsMap");
            for (Map map : list) {
                System.out.println(map);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(session);
        }
    }

    /**
     * 数据传输对象测试
     */
    @Test
    public void testSelectGoodsDTO() {
        SqlSession session = null;
        try {
            session = MyBatisUtils.openSession();
            List<GoodsDTO> list = session.selectList("goods.selectGoodsDTO");
            for (GoodsDTO g : list) {
                System.out.println(g.getGoods().getTitle());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(session);
        }
    }

    /**
     * 通过事物插入数据
     * @throws Exception
     */
    @Test
    public void testInsert() throws Exception {
        SqlSession session = null;
        try {
            session = MyBatisUtils.openSession();
            Goods goods = new Goods();
            goods.setGoodsId(1007);
            goods.setTitle("洗衣机2");
            goods.setSubTitle("西门子2");
            goods.setOriginalCost(5000f);
            goods.setCurrentPrice(4599f);
            goods.setDiscount(8.5f);
            goods.setCategoryId(3);
            goods.setIsFreeDelivery(1);
            // int num = session.insert("goods.insetNew", goods); // insert() 返回值代表成功插入的记录数
            int num = session.insert("goods.insetNewTwo", goods); // insert() 返回值代表成功插入的记录数
            System.out.println(num);
            session.commit(); // 提交事物
            System.out.println(goods.getId());
        } catch (Exception e) {
            if (session != null) {
                session.rollback();
            }
            throw e;
        } finally {
            MyBatisUtils.closeSession(session);
        }
    }

    @Test
    public void testUpdate() throws Exception {
        SqlSession session = null;
        try {
            session = MyBatisUtils.openSession();
            Goods goods = session.selectOne("goods.selectById", 1006);
            goods.setTitle("空调");
            goods.setSubTitle("格力");
            int num = session.update("goods.update", goods);
            System.out.println(num);
            session.commit();
        } catch (Exception e) {
            if (session != null) {
                session.rollback();
            }
            throw e;
        } finally {
            MyBatisUtils.closeSession(session);
        }
    }

    @Test
    public void testDelete() throws Exception {
        SqlSession session = null;
        try {
            session = MyBatisUtils.openSession();
            int num = session.delete("goods.delete", 8);
            System.out.println(num);
            session.commit();
        } catch (Exception e) {
            if (session != null) {
                session.rollback();
            }
            throw e;
        } finally {
            MyBatisUtils.closeSession(session);
        }
    }

    /**
     * SQL注入
     * @throws Exception
     */
    @Test
    public void testSelectByTitle() throws Exception {
        SqlSession session = null;
        try {
            session = MyBatisUtils.openSession();
            Map<String, String> param = new HashMap<>();
            param.put("title", "'' or 1=1 or title='电视'");
            List<Goods> list = session.selectList("goods.selectByTitle", param);
            for (Goods g:list) {
                System.out.println(g.getTitle()+" : " + g.getCurrentPrice());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(session);
        }
    }

    @Test
    public void testDynamicSQL() throws Exception {
        SqlSession session = null;
        try {
            session = MyBatisUtils.openSession();
            Map<String, Integer> param = new HashMap<>();
            // param.put("categoryId", 2);
            param.put("currentPrice", 5000);
            List<Goods> list = session.selectList("goods.dynamicSQL", param);
            for (Goods g:list) {
                System.out.println(g.getTitle()+" : " + g.getCurrentPrice());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(session);
        }
    }

    /**
     * MyBatis二级缓存
     *      一级缓存默认开启，缓存范围SqlSession会话；
     *      二级缓存手动开启，属于范围Mapper Namespace；
     *
     * 二级缓存运行规则：
     *      二级缓存开启后默认所有查询操作均使用缓存；
     *      写操作commit提交时对该namespace缓存强制清空；
     *      配置useCache=false可以不用缓存；
     *      配置flushCache=true代表强制清空缓存；
     */
    /**
     * 一级缓存测试
     * @throws Exception
     */
    @Test
    public void testLv1Cache() throws Exception {
        SqlSession session = null;
        try {
            session = MyBatisUtils.openSession();
            Goods good = session.selectOne("goods.selectById", 3400);
            Goods good1 = session.selectOne("goods.selectById", 3400);
            System.out.println(good.hashCode() + " : " + good1.hashCode());
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(session);
        }

        System.out.println("--------------------------------");

        try {
            session = MyBatisUtils.openSession();
            Goods good = session.selectOne("goods.selectById", 3400);
            session.commit(); // commit提交时对该namespace缓存强制清空
            Goods good1 = session.selectOne("goods.selectById", 3400);
            System.out.println(good.hashCode() + " : " + good1.hashCode());
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(session);
        }
    }

    /**
     * 二级缓存缓存测试
     * @throws Exception
     */
    @Test
    public void testLv2Cache() throws Exception {
        SqlSession session = null;
        try {
            session = MyBatisUtils.openSession();
            Goods good = session.selectOne("goods.selectById", 3400);
            System.out.println(good.hashCode());
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(session);
        }

        System.out.println("--------------------------------");

        try {
            session = MyBatisUtils.openSession();
            Goods good = session.selectOne("goods.selectById", 3400);
            session.commit(); // commit提交时对该namespace缓存强制清空
            System.out.println(good.hashCode());
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(session);
        }
    }

    /**
     * oneToMany一对多查询
     * @throws Exception
     */
    @Test
    public void testOneToMany() throws Exception {
        SqlSession session = null;
        try {
            session = MyBatisUtils.openSession();
            List<Goods> list = session.selectList("goods.selectOneToMany");
            for (Goods g:list) {
                System.out.println(g.getTitle() + " : " + g.getGoodsDetails().size());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(session);
        }
    }
}
