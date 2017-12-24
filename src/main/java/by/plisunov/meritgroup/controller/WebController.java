package by.plisunov.meritgroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Basic spring MVC Controller
 * 
 * @author Andrey
 *
 */
@Controller
public class WebController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String start(Model model) {
		return "index";
	}
}