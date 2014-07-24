package com.parallel.task;

import com.parallel.exception.CloudException;

public interface IFunc<R>
{
	public R method() throws CloudException;
}
