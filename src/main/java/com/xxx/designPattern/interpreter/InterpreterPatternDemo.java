package com.xxx.designPattern.interpreter;

/**
 * 解释器模式
 * 提供了一种评估计算语言语法或表达式的方法。 这种类型的模式属于行为模式。 这种模式涉及实现一个表达式接口，它告诉解释一个指定的上下文。 此模式用于SQL解析，符号处理引擎等。
 *
 * @See https://www.yiibai.com/design_pattern/interpreter_pattern.html
 */
public class InterpreterPatternDemo {

    //Rule: Robert and John are male
    public static Expression getMaleExpression() {
        Expression robert = new TerminalExpression("Robert");
        Expression john = new TerminalExpression("John");
        return new OrExpression(robert, john);
    }

    //Rule: Julie is a married women
    public static Expression getMarriedWomanExpression(){
        Expression julie = new TerminalExpression("Julie");
        Expression married = new TerminalExpression("Married");
        return new AndExpression(julie, married);
    }

    public static void main(String[] args) {
        Expression isMale = getMaleExpression();
        Expression isMarriedWoman = getMarriedWomanExpression();

        System.out.println("John is male? " + isMale.interpret("John"));
        System.out.println("Julie is a married women? " + isMarriedWoman.interpret("Married Julie"));
    }

}
