package com.biz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.parallel.exception.CloudException;
import com.parallel.task.IFunc;

public class UserServiceWrap implements IFunc<Map<String, Object>> {

	private final IUserService userService;

	private final Integer userId;

	public UserServiceWrap(IUserService userService, Integer userId) {
		this.userService = userService;
		this.userId = userId;
	}

	@Override
	public Map<String, Object> method() throws CloudException {

		Map<String, Object> result = new HashMap<String, Object>();

		try {
			List<String> list = userService.getUser(userId);
			if (list != null && list.size() > 0) {
				result.put(KEY, list);
			}

		} catch (Exception e) {

		}

		return result;
	}

	public static final String KEY = UserServiceWrap.class.getName();

}
