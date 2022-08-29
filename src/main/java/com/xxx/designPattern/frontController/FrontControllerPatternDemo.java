package com.xxx.designPattern.frontController;

/**
 * 前端控制器模式
 * @See https://www.yiibai.com/design_pattern/front_controller_pattern.html
 */
public class FrontControllerPatternDemo {
    public static void main(String[] args) {
        FrontController frontController = new FrontController();
        frontController.dispatchRequest("HOME");
        frontController.dispatchRequest("STUDENT");
    }
}
