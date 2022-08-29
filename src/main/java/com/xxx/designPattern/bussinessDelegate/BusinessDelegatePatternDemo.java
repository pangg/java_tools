package com.xxx.designPattern.bussinessDelegate;

/**
 * 业务代理模式
 *
 * @See https://www.yiibai.com/design_pattern/business_delegate_pattern.html
 */
public class BusinessDelegatePatternDemo {
    public static void main(String[] args) {
        BusinessDelegate businessDelegate = new BusinessDelegate();
        businessDelegate.setServiceType("EJB");

        Client client = new Client(businessDelegate);
        client.doTask();

        businessDelegate.setServiceType("JMS");
        client.doTask();
    }
}
