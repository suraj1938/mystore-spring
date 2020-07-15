package com.app.mystore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.mystore.dto.User;
import com.app.mystore.service.LoginControllerService;


@RestController
@RequestMapping("/api/myStore")
public class LoginController {

	@Autowired
	private  LoginControllerService loginControllerService;

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	/*
	 * @RequestMapping(value="/login",method={RequestMethod.POST},
	 * consumes="application/json")
	 */
	@PostMapping("/login")
	public @ResponseBody String login (@RequestBody User  loginUser){


		User user= (User)loginControllerService.login(loginUser);

		if(user!=null) {
			return "success";
		}
		return "failed";

	}


	@RequestMapping(value = "/resetPassword/{email}", method = RequestMethod.GET)
	@ResponseBody
	public String resetPasswordToken(
	  @PathVariable("email") String email) {
		String token = null;
		token = loginControllerService.getResetToken(email);
		if(token==null) {
			token = "Email Id is not registered, please provide an registered email Id";
		}
		return "token";
	}
}
