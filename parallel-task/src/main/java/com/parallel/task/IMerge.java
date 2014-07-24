package com.parallel.task;

import com.parallel.exception.CloudException;

public interface IMerge<O> {

    public O merge(O a, O b) throws CloudException;
}
