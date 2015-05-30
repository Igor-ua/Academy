package org.mydomain.academy.SpringBoot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/db/authentication")
public class AuthenticationController {

	@RequestMapping(value = {"", "/"})
	public String authRootPage() {
		return "/fragments/entities/person/not-implemented";
	}
}