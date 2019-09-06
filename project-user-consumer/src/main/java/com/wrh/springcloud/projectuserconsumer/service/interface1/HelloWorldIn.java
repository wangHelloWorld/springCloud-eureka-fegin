package com.wrh.springcloud.projectuserconsumer.service.interface1;

import com.wrh.springcloud.projectcommon.common.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "project-user-provider")
public interface HelloWorldIn {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login();
    @RequestMapping(value = "/loginUser",method = RequestMethod.POST)
    public String loginUser(@RequestBody User user);
    @RequestMapping(value = "/loginRibbon" , method = RequestMethod.POST)
    public String loginRibbon(@RequestParam("count") int count);
}
