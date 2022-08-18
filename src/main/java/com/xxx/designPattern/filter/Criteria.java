package com.xxx.designPattern.filter;

import java.util.List;

/**
 * 条件接口
 */
public interface Criteria {
    public List<Person> meetCriteria(List<Person> persons);
}
