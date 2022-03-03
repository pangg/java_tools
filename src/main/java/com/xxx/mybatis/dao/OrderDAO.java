package com.xxx.mybatis.dao;

import com.xxx.mybatis.entity.Order;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderDAO {

    /**
     * 多对多关系查询
     * @return
     */
    @Select("select * from t_order")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "orderItems", javaType = List.class, column = "id", many = @Many(select = "com.xxx.mybatis.dao.OrderItemDAO.listByOrder"))
    })
    public List<Order> listOrder();
}
