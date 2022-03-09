package com.xxx.oaSystem.dao;

import com.xxx.oaSystem.entity.ProcessFlow;

import java.util.List;

public interface ProcessFlowDao {

    public void insert(ProcessFlow processFlow);

    public void update(ProcessFlow processFlow);

    public List<ProcessFlow> selectByFormId(Long formId);
}
