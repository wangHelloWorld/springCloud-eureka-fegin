package com.wrh.springcloud.projectuserconsumer.service.contorller.fallback;

import org.springframework.stereotype.Component;

import com.wrh.springcloud.projectcommon.common.User;
import com.wrh.springcloud.projectuserconsumer.service.interface1.HelloWorldIn;

@Component
public class HelloWorldFallback implements HelloWorldIn{

	@Override
	public String login() {
		// TODO Auto-generated method stub
		return "login exception";
	}

	@Override
	public String loginUser(User user) {
		// TODO Auto-generated method stub
		return "loginUser exception";
	}

	@Override
	public String loginRibbon(int count) {
		// TODO Auto-generated method stub
		return "loginRibbon Exception";
	}

}
