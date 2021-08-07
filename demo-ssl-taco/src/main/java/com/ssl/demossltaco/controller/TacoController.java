package com.ssl.demossltaco.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TacoController {

	@GetMapping("/tacos")
	public String tacos() { 
		return "tacos :[ Beef Taco, Steak Taco, Cheese Taco]";
	}
}
