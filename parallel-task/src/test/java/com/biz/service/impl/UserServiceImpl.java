package com.biz.service.impl;

import java.util.Arrays;
import java.util.List;

import com.biz.service.IUserService;

public class UserServiceImpl implements IUserService {

	@Override
	public List<String> getUser(Integer userId) {
		return Arrays.asList("张三", "男");
	}

}
