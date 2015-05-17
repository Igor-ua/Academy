package org.mydomain.academy.SpringBoot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World")
						   String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

//    @RequestMapping("/user/{username:[a-z]{0,}}")
//    public String userPage(@PathVariable String username, ModelMap modelMap) {
//        String msg = "Hello, " + username;
//        modelMap.addAttribute("message", msg);
//        return "hello";
//    }

}