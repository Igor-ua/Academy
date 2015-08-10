package org.mydomain.academy.SpringBoot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HeaderController {

	//костыль
	@RequestMapping(value = {"**/db/db"})
	String home() {
		return "redirect:/db";
	}
}