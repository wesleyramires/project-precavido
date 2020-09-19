package com.precavido.app.controller;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.precavido.app.dto.UserDTO;

import com.precavido.app.model.User;
import com.precavido.app.service.UserService;

@Controller
public class UsersController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView view = new ModelAndView();
		view.setViewName("Login");
		return view;
	}
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public ModelAndView cadastro() {
		ModelAndView view = new ModelAndView("Register");
		view.addObject("userDTO", new UserDTO());
		return view;
	}
	
	@PostMapping(value = "/cadastro")
	public ModelAndView addUsers(@Valid UserDTO userDTO, BindingResult result, RedirectAttributes attributes) throws ParseException {
		User userExist = userService.findUserByUsername(userDTO.getUsername());
		User emailExist = userService.findUserByEmail(userDTO.getEmail());
		if (userExist != null) {
			result.rejectValue("username", "hasError.user" , "Nome de usuário indisponivel!");
		} 
		if (emailExist != null) {
			result.rejectValue("email", "hasError.user" , "Email ja cadastrado!");
		}
		ModelAndView errors = new ModelAndView("Register");
		if (result.hasErrors()) {
			return errors;
		} 
		ModelAndView view = new ModelAndView("redirect:/login");
		userService.addUser(userDTO);
		attributes.addFlashAttribute("mensagem", "Cadastro efetuado com sucesso!");
		return view;
		
	}
	
}
