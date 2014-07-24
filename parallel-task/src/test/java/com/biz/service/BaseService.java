package com.biz.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.parallel.cloud.Cloud;
import com.parallel.exception.CloudException;
import com.parallel.task.IFunc;
import com.parallel.task.IMerge;

public class BaseService {

	private static final Executor executor = Executors.newFixedThreadPool(10);

	private static Cloud<Map<String, Object>> cloud = new Cloud<Map<String, Object>>(
			executor);

	private static IMerge<Map<String, Object>> merge = new DefaultMerge();

	public static Map<String, Object> parallelCompute(
			List<IFunc<Map<String, Object>>> tasks) {
		Map<String, Object> resultMap = null;
		try {
			resultMap = cloud.computeWithTimeOut(tasks, merge, 500L,
					TimeUnit.MILLISECONDS);
		} catch (Exception e) {

		}
		return resultMap;
	}

	private static class DefaultMerge implements IMerge<Map<String, Object>> {

		@Override
		public Map<String, Object> merge(Map<String, Object> a,
				Map<String, Object> b) throws CloudException {
			if (a == null) {
				return b;
			}
			if (b == null || b.isEmpty()) {
				return a;
			}

			a.putAll(b);
			return a;
		}

	}

}
