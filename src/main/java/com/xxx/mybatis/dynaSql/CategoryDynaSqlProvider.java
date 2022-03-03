package com.xxx.mybatis.dynaSql;

import org.apache.ibatis.jdbc.SQL;

public class CategoryDynaSqlProvider {
    public String list() {
        return new SQL()
                .SELECT("*")
                .FROM("t_category")
                .toString();
    }

    public String get() {
        return new SQL().SELECT("*")
                .FROM("t_category")
                .WHERE("category_id=#{categoryId}")
                .toString();
    }

    public String add() {
        return new SQL()
                .INSERT_INTO("t_category")
                .VALUES("category_name", "#{categoryName}")
                .toString();
    }

    public String update() {
        return new SQL().UPDATE("t_category")
                .SET("category_name=#{categoryName}")
                .WHERE("category_id=#{categoryId}")
                .toString();
    }

    public String delete() {
        return new SQL()
                .DELETE_FROM("t_category")
                .WHERE("category_id=#{categoryId}")
                .toString();
    }
}
