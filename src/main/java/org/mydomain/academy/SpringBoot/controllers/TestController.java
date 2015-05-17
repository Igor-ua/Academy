package org.mydomain.academy.SpringBoot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@ResponseBody
	@RequestMapping(value = "/test1", method = RequestMethod.GET)
	public String reply1() {
		return "test1 ResponseBody";
	}

	//ResponseBody
	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	public String reply2() {
		return "/tmp/test2";
	}

}