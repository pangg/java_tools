create table t_category
(
    category_id    int auto_increment
        primary key,
    category_name  varchar(100) default '' not null,
    parent_id      int          default 0  not null,
    category_level int          default 0  not null,
    category_order int          default 0  not null
) comment '分类表';

create table t_goods
(
    id               int auto_increment
        primary key,
    goods_id         int            default 0    not null,
    title            varchar(150)                not null,
    sub_title        varchar(256)                not null comment '子标题',
    original_cost    decimal(10, 2) default 0.00 not null comment '原始价格',
    current_price    decimal(10, 2) default 0.00 not null comment '当前价格',
    discount         decimal(10, 2) default 0.00 not null comment '折扣率',
    is_free_delivery int            default 0    not null comment '是否包邮，1-包邮，0-不包邮',
    category_id      int            default 0    not null comment '分类编号',
    constraint t_goods_goods_id_uindex
        unique (goods_id)
) comment '商品表';

create table t_goods_detail
(
    gd_id      int auto_increment
        primary key,
    goods_id   int          default 0  not null,
    gd_pic_url varchar(500) default '' not null,
    gd_order   int          default 0  not null
);

create table t_order
(
    id   int auto_increment
        primary key,
    code varchar(32) default '' not null
) comment '订单表';

create table t_order_item
(
    id       int auto_increment
        primary key,
    oid      int default 0 not null,
    goods_id int default 0 not null,
    number   int default 0 not null
) comment '订单详情表';