package com.wrh.springcloud.projectuserprovider.controller;

import com.wrh.springcloud.projectcommon.common.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
      return "Hello World Pro" ;
  }
    @RequestMapping(value = "/loginUser",method = RequestMethod.POST)
    public String loginUser(@RequestBody  User user){
        return "Hello World Pro " + user.toString();
    }

}

