package com.parallel;

import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import com.parallel.exception.CloudException;
import com.parallel.task.IMerge;

public class Processor<O> {

    private static Logger logger = Logger.getLogger(Processor.class);

    private O result;

    private IMerge<O> merge;

    private final ReentrantLock lock = new ReentrantLock();

    public Processor(IMerge<O> merge) {
        this.merge = merge;
    }

    public O getResult() {
        return result;
    }

    public void doResult(O o) {
        if (o == null) {
            return;
        }
        lock.lock();
        try {
            if (merge != null) {
                this.result = merge.merge(this.result, o);
            }
        } catch (CloudException e) {
            logger.error("doResult: " + e.getMessage(), e);
        } finally {
            lock.unlock();
        }
    }
}
