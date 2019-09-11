package com.wrh.springcloud.projectuserprovider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {

	@Value("${name}")
	private String name;
	
	@RequestMapping(value="/returnName",method=RequestMethod.GET)
	private String returnName() {
		return name;
	}
}
