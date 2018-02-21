package com.mjamsek.lozigorbox.controllers.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AngularRouter {
	
	@GetMapping(value = "/**/{path:[^\\.]*}")
	public String forward(@PathVariable("path") String path) {
		return "forward:/";
	}
	
}
