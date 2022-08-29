package com.xxx.designPattern.visitor;

/**
 * 访问者模式
 *
 * @See https://www.yiibai.com/design_pattern/visitor_pattern.html
 */
public class VisitorPatternDemo {
    public static void main(String[] args) {
        ComputerPart computerPart = new Computer();
        computerPart.accept(new ComputerPartDisplayVisitor());
    }
}
