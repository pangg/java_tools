package com.xxx.future;

import java.util.concurrent.Callable;

public class RealData implements Data, Callable {
    protected final String result;

    public RealData(String para) {
        StringBuffer sb = new StringBuffer(para);
        // 假设这里很慢很慢，构造RealData不是一个容易的事
        this.result = sb.toString();
    }

    @Override
    public String getResult() {
        return result;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public String call() throws Exception {
        return result;
    }
}
