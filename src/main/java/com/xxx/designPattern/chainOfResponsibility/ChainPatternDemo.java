package com.xxx.designPattern.chainOfResponsibility;

/**
 * 责任链模式
 * 责任模式链为请求创建一系列接收者对象。 此模式基于请求的类型将请求的发送方和接收方分离。 这种模式是行为模式。
 * 在这种模式中，通常每个接收器包含对另一个接收器的引用。如果一个对象不能处理请求，则它将相同的对象传递给下一个接收者等等。
 */
public class ChainPatternDemo {
    private static AbstractLogger getChainOfLoggers() {
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);
        return errorLogger;
    }

    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainOfLoggers();

        loggerChain.logMessage(AbstractLogger.INFO, "This is an information.");
        loggerChain.logMessage(AbstractLogger.DEBUG,
                "This is an debug level information.");

        loggerChain.logMessage(AbstractLogger.ERROR,
                "This is an error information.");
   }
}
