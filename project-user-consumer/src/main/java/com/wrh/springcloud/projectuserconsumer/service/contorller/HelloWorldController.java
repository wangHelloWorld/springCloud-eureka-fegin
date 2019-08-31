package com.wrh.springcloud.projectuserconsumer.service.contorller;

import com.wrh.springcloud.projectcommon.common.User;
import com.wrh.springcloud.projectuserconsumer.service.interface1.HelloWorldIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

	@Autowired
	private HelloWorldIn helloWorld;

	@RequestMapping(value = "/loginC", method = RequestMethod.GET)
	public String loginC() {

		return helloWorld.login();

	}

	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	public String loginCUser(@RequestParam("name") String name, @RequestParam("password") String password) {
		System.out.println("name " + name + " password " + password);
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		return helloWorld.loginUser(user);

	}

	@RequestMapping(value = "/loginUserBody", method = RequestMethod.POST)
	public String loginCUserBody(@RequestBody User user) {
		return helloWorld.loginUser(user);

	}

}
