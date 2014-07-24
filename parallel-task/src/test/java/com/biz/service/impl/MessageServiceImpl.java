package com.biz.service.impl;

import java.util.Arrays;
import java.util.List;

import com.biz.service.IMessageService;

public class MessageServiceImpl implements IMessageService {

	@Override
	public List<String> list(Integer userId) {
		return Arrays.asList("你好", "收到");
	}

}
