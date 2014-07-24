package com.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.biz.service.BaseService;
import com.biz.service.IMessageService;
import com.biz.service.IUserService;
import com.biz.service.MessageServiceWrap;
import com.biz.service.UserServiceWrap;
import com.biz.service.impl.MessageServiceImpl;
import com.biz.service.impl.UserServiceImpl;
import com.parallel.task.IFunc;

public class ParallelTaskTest {

	IUserService userService = null;
	IMessageService messageService = null;

	@Before
	public void setUp() throws Exception {
		userService = new UserServiceImpl();
		messageService = new MessageServiceImpl();
	}

	@Test
	public void test() {

		Integer userId = 1;

		// 添加任务
		List<IFunc<Map<String, Object>>> tasks = new ArrayList<IFunc<Map<String, Object>>>(
				2);
		tasks.add(new UserServiceWrap(userService, userId));
		tasks.add(new MessageServiceWrap(messageService, userId));

		// 并行计算
		Map<String, Object> resultMap = BaseService.parallelCompute(tasks);
		assertEquals(2, resultMap.size());
	}

}
