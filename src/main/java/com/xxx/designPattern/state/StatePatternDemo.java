package com.xxx.designPattern.state;

/**
 * 状态模式:
 *      类行为根据其状态而改变
 *
 * 实现：
 *      我们将创建一个State接口来定义一个动作并实现State接口的具体状态类。Context是一个载有一个状态的类。
 *      演示类StatePatternDemo将使用Context和状态对象来演示上下文行为基于它所处的状态类型的变化。
 */
public class StatePatternDemo {
    public static void main(String[] args) {
        Context context = new Context();

        StartState startState = new StartState();
        startState.doAction(context);

        System.out.println(context.getState().toString());

        StopState stopState = new StopState();
        stopState.doAction(context);

        System.out.println(context.getState().toString());
    }
}
