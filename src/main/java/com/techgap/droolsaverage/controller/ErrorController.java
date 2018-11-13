package com.techgap.droolsaverage.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

	@GetMapping("/error")
	public ModelAndView getErrorPage(HttpServletRequest httpRequest) {
		
		ModelAndView errorPage = new ModelAndView("error");
		String error = "";
		int errorCode = getErrorCode(httpRequest);

		switch (errorCode) {
		case 400: {
			error = "Http Error Code: 400. Malformed Request :(";
			break;
		}
		case 403: {
			error = "Http Error Code: 403 You're not allowed in here";
			break;
		}
		case 404: {
			error = "Http Error Code: 404. Resource not found";
			break;
		}
		case 500: {
			error = "Http Error Code: 500. Internal Server Error";
			break;
		}
		}
		errorPage.addObject("errorMsg", error);
		return errorPage;
	}


	private int getErrorCode(HttpServletRequest httpRequest) {
		return (int) httpRequest.getAttribute("javax.servlet.error.status_code");
	}
}
