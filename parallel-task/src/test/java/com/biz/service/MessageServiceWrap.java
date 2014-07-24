package com.biz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.parallel.exception.CloudException;
import com.parallel.task.IFunc;

public class MessageServiceWrap implements IFunc<Map<String, Object>> {

	private final IMessageService messageService;

	private final Integer userId;

	public MessageServiceWrap(IMessageService messageService, Integer userId) {
		this.messageService = messageService;
		this.userId = userId;
	}

	@Override
	public Map<String, Object> method() throws CloudException {

		Map<String, Object> result = new HashMap<String, Object>();

		try {
			if (messageService != null) {
				List<String> list = messageService.list(userId);
				result.put(KEY, list);
			}

		} catch (Exception e) {

		}

		return result;
	}

	public static final String KEY = MessageServiceWrap.class.getName();

}
