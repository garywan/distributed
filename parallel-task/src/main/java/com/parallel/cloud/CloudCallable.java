package com.parallel.cloud;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import org.apache.log4j.Logger;
import com.parallel.Processor;
import com.parallel.task.IFunc;

public class CloudCallable<O> implements Callable<Void> {

    private static final Logger logger = Logger.getLogger(CloudCallable.class);

    private CountDownLatch doneSignal;

    private Processor<O> processor;

    private IFunc<O> func;

    public CloudCallable(CountDownLatch doneSignal, Processor<O> processor, IFunc<O> func) {
        super();
        this.doneSignal = doneSignal;
        this.processor = processor;
        this.func = func;
    }

    public Void call() throws Exception {
        O o = null;
        try {
            o = func.method();
            processor.doResult(o);
        } catch (Exception e) {
            logger.error("call error: " + e.getMessage(), e);
        } finally {
            doneSignal.countDown();
        }
        return null;
    }

}
