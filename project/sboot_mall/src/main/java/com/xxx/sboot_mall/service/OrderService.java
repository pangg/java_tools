package com.xxx.sboot_mall.service;

import com.github.pagehelper.PageInfo;
import com.xxx.sboot_mall.model.request.CreateOrderReq;
import com.xxx.sboot_mall.model.vo.OrderVO;
import org.springframework.transaction.annotation.Transactional;

public interface OrderService {
    //数据库事物
    @Transactional(rollbackFor = {Exception.class})
    String create(CreateOrderReq createOrderReq);

    OrderVO detail(String orderNo);

    PageInfo listForCustomer(Integer pageNum, Integer pageSize);

    void cancel(String orderNo);

    String qrcode(String orderNo);

    void pay(String orderNo);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    void deliver(String orderNo);

    void finish(String orderNo);
}
