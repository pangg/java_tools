package com.xxx.designPattern.serviceLocator;

/**
 * 服务定位器模式
 * @See https://www.yiibai.com/design_pattern/service_locator_pattern.html
 */
public class ServiceLocatorPatternDemo {
    public static void main(String[] args) {
        Service service = ServiceLocator.getService("Service1");
        service.execute();
        service = ServiceLocator.getService("Service2");
        service.execute();
        service = ServiceLocator.getService("Service1");
        service.execute();
        service = ServiceLocator.getService("Service2");
        service.execute();
    }
}
