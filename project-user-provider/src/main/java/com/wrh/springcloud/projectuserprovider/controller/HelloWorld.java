package com.wrh.springcloud.projectuserprovider.controller;

import com.wrh.springcloud.projectcommon.common.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
	private Logger logger  = LoggerFactory.getLogger(HelloWorld.class);
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
      return "Hello World Pro" ;
  }
    @RequestMapping(value = "/loginUser",method = RequestMethod.POST)
    public String loginUser(@RequestBody  User user){
        return "Hello World Pro " + user.toString();
    }
    @RequestMapping(value = "/loginRibbon" , method = RequestMethod.POST)
    public String loginRibbon(@RequestParam("count") int count) {
    	logger.info(count + "product-user-provider2");
    	return "product-user-provider2";
    }
}

