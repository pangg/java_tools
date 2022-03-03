package com.xxx.mybatis.dao;

import com.xxx.mybatis.entity.OrderItem;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderItemDAO {
    @Select("select * from t_order_item where oid = #{oid}")
    @Results({
            @Result(property = "goods", column = "goods_id", one = @One(select = "com.xxx.mybatis.dao.GoodsDAO.getSingleGoods"))
    })
    public List<OrderItem> listByOrder(int oid);
}
