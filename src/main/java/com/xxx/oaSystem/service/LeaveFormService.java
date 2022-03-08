package com.xxx.oaSystem.service;

import com.xxx.oaSystem.dao.EmployeeDao;
import com.xxx.oaSystem.dao.LeaveFormDao;
import com.xxx.oaSystem.dao.ProcessFlowDao;
import com.xxx.oaSystem.entity.Employee;
import com.xxx.oaSystem.entity.LeaveForm;
import com.xxx.oaSystem.entity.ProcessFlow;
import com.xxx.oaSystem.utils.MybatisUtils;

import java.util.Date;

public class LeaveFormService {
    public LeaveForm createLeaveForm(LeaveForm form) {
        LeaveForm saveForm = (LeaveForm) MybatisUtils.executeUpdate(sqlSession -> {
            // 1. 持久化form表单数据
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            Employee employee = employeeDao.selectById(form.getEmployeeId());
            if (employee.getLevel() == 8) {
                form.setState("approved");
            } else {
                form.setState("processing");
            }
            LeaveFormDao leaveFormDao = sqlSession.getMapper(LeaveFormDao.class);
            leaveFormDao.insert(form);

            // 2. 增加第一条流程数据，说明表单已经提交，状态为complete
            ProcessFlowDao processFlowDao  = sqlSession.getMapper(ProcessFlowDao.class);
            ProcessFlow flow1 = new ProcessFlow();
            flow1.setFormId(form.getFormId());
            flow1.setOperatorId(employee.getEmployeeId());
            flow1.setAction("apply");
            flow1.setCreateTime(new Date());
            flow1.setOrderNo(1);
            flow1.setState("complete");
            flow1.setIsLast(0);
            processFlowDao.insert(flow1);

            // 3. 分情况创建其他流程数据
            // 3.1 7级以下员工，生成部门经理审批任务，请假大于36小时，还需要生成总经理审批任务
            if (employee.getLevel() < 7) {
                Employee dmanager = employeeDao.selectLeader(employee);
                ProcessFlow flow2 = new ProcessFlow();
                flow2.setFormId(form.getFormId());
                flow2.setOperatorId(dmanager.getEmployeeId());
                flow2.setAction("audit");
                flow2.setCreateTime(new Date());
                flow2.setOrderNo(2);
                flow2.setState("process");
                long diff = form.getEndTime().getTime() - form.getStartTime().getTime();
                float hours = diff / (1000*60*60) * 1f;
                if (hours >= BusinessConstants.MANAGER_AUDIT_HOURS) {
                    flow2.setIsLast(0);
                    processFlowDao.insert(flow2);
                    Employee manager = employeeDao.selectLeader(dmanager);
                    ProcessFlow flow3 = new ProcessFlow();
                    flow3.setFormId(form.getFormId());
                    flow3.setOperatorId(manager.getEmployeeId());
                    flow3.setAction("audit");
                    flow3.setCreateTime(new Date());
                    flow3.setState("ready");
                    flow3.setOrderNo(3);
                    flow3.setIsLast(1);
                    processFlowDao.insert(flow3);
                } else {
                    flow2.setIsLast(1);
                    processFlowDao.insert(flow2);
                }
            } else if (employee.getLevel() == 7) {  //部门经理
                // 3.2 7级员工，生成总经理审批任务
                Employee manager = employeeDao.selectLeader(employee);
                ProcessFlow flow = new ProcessFlow();
                flow.setFormId(form.getFormId());
                flow.setOperatorId(manager.getEmployeeId());
                flow.setAction("audit");
                flow.setCreateTime(new Date());
                flow.setState("process");
                flow.setOrderNo(1);
                flow.setIsLast(1);
                processFlowDao.insert(flow);
            } else if (employee.getLevel() == 8) {
                ProcessFlow flow = new ProcessFlow();
                flow.setFormId(form.getFormId());
                flow.setOperatorId(employee.getEmployeeId());
                flow.setAction("audit");
                flow.setResult("approved");
                flow.setReason("自动通过");
                flow.setCreateTime(new Date());
                flow.setAuditTime(new Date());
                flow.setState("complete");
                flow.setOrderNo(2);
                flow.setIsLast(1);
                processFlowDao.insert(flow);
            }
            return form;
        });
        return saveForm;
    }
}
