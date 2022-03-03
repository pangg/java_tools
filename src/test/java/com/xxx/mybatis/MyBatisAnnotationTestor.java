package com.xxx.mybatis;

import com.xxx.mybatis.dao.CategoryDAO;
import com.xxx.mybatis.dao.GoodsDAO;
import com.xxx.mybatis.dao.OrderDAO;
import com.xxx.mybatis.entity.Category;
import com.xxx.mybatis.entity.Goods;
import com.xxx.mybatis.entity.Order;
import com.xxx.mybatis.entity.OrderItem;
import com.xxx.mybatis.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MyBatisAnnotationTestor {
    private static SqlSession session = MyBatisUtils.openSession();

    @Before
    public void testBefore() throws Exception {
        if (session == null) {
            throw new Exception("MyBatis初始化错误");
        }
    }

    @After
    public void testAfter() {
        MyBatisUtils.closeSession(session);
    }

    @Test
    public void testInsertCategory() {
        CategoryDAO c = session.getMapper(CategoryDAO.class);
        Category category = new Category();
        category.setCategoryName("category1");
        category.setParentId(0);
        category.setCategoryLevel(3);
        category.setCategoryOrder(3);
        int num = c.add(category);
        session.commit();
        System.out.println(num);
    }

    @Test
    public void testUpdateCategory() {
        CategoryDAO categoryDAO = session.getMapper(CategoryDAO.class);
        Category category = categoryDAO.get(6);
        System.out.println(category);
        category.setCategoryName("测试类目1");
        int num = categoryDAO.update(category);
        session.commit();
        System.out.println(num);
    }

    @Test
    public void testGetCategory() {
        CategoryDAO categoryDAO = session.getMapper(CategoryDAO.class);
        Category category = categoryDAO.get(6);
        System.out.println(category.getCategoryName());
    }

    @Test
    public void testDeleteCategory() {
        CategoryDAO categoryDAO = session.getMapper(CategoryDAO.class);
        int num = categoryDAO.delete(5);
        session.commit();
        System.out.println(num);
    }

    @Test
    public void testListAllCategory() {
        CategoryDAO categoryDAO = session.getMapper(CategoryDAO.class);
        List<Category> list = categoryDAO.list();
        for (Category category : list) {
            System.out.println(category.getCategoryName());
        }
    }

    /**
     * 一对多关系获取测试
     */
    @Test
    public void testListCategory2() {
        CategoryDAO categoryDAO = session.getMapper(CategoryDAO.class);
        List<Category> list = categoryDAO.listCategory();
        for (Category category : list) {
            System.out.println("类目名称：" + category.getCategoryName());
            List<Goods> goods = category.getGoods();
            for (Goods g : goods) {
                System.out.println("     商品标题：" + g.getTitle());
            }
        }
    }

    /**
     * 多对一关系查询
     */
    @Test
    public void testManyGoodsToCategory() {
        GoodsDAO goodsDAO = session.getMapper(GoodsDAO.class);
        List<Goods> goods = goodsDAO.list();
        for (Goods goods1 : goods) {
            System.out.println(goods1.getTitle() + " \t 对应分类是： \t " + goods1.getCategory().getCategoryName());
        }
    }

    /**
     * 多对多关系查询
     */
    @Test
    public void testManyOrderToManyGoods() {
        OrderDAO orderDAO = session.getMapper(OrderDAO.class);
        List<Order> os = orderDAO.listOrder();
        for (Order o : os) {
            System.out.println(o.getCode());
            List<OrderItem> ois = o.getOrderItems();
            if (ois != null) {
                for (OrderItem orderItem : ois) {
                    System.out.format("\t%s\t%f\t%d\n", orderItem.getGoods().getTitle(), orderItem.getGoods().getCurrentPrice(), orderItem.getNumber());
                }
            }
        }
    }
}
