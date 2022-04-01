package com.xxx.reader.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.reader.entity.Test;

public interface TestMapper extends BaseMapper<Test> {
    public Integer insertSample();
}
