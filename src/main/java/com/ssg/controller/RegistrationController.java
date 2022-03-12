package com.ssg.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ssg.model.UserRegistration;
import com.ssg.model.UserRegistrationEntity;
import com.ssg.repository.UserRegistrationRepository;

@Controller
public class RegistrationController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRegistrationRepository userRegistrationRepository;

	@RequestMapping("/welcome")
	public ModelAndView firstPage() {
		return new ModelAndView("welcome");
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		return new ModelAndView("registration", "user", new UserRegistration());
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView processRegister(@ModelAttribute("user") UserRegistration userRegistrationObject) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		String encodedPassword = bCryptPasswordEncoder.encode(userRegistrationObject.getPassword());
		UserRegistrationEntity userEntity = new UserRegistrationEntity(userRegistrationObject.getUsername(),encodedPassword);
		userRegistrationRepository.save(userEntity);

		return new ModelAndView("redirect:/welcome");
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("errorMsg", "Your username and password are invalid.");

		if (logout != null)
			model.addAttribute("msg", "You have been logged out successfully.");

		return "login";
	}

}
