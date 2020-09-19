package com.precavido.app.controller;

import java.io.IOException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.precavido.app.dto.PasswordDTO;
import com.precavido.app.dto.ProfileDTO;
import com.precavido.app.model.User;
import com.precavido.app.repository.UserRepository;
import com.precavido.app.service.UserService;


@Controller
public class ProfileController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/profile")
	public ModelAndView getProfile() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username =((UserDetails)user).getUsername();
		
		User userProfile = userService.findUserByUsername(username);
		ModelAndView view = new ModelAndView("Profile-show");
		view.addObject("user", userProfile);
		return view;
	}
	
	@RequestMapping(value = "/profile/edit/{id}",  method = RequestMethod.POST)
	public ModelAndView editProfile(@PathVariable("id") String id, @Valid ProfileDTO profileDTO,
			BindingResult result, RedirectAttributes attributes,
			@RequestParam("fileUser") MultipartFile file) throws IOException {
		
		User user = userRepository.findById(Long.parseLong(id));
		if (file.isEmpty()) {
			profileDTO.setImage(user.getImage() );
		} else {
			profileDTO.setImage(file.getBytes());
		}
		
		
		ModelAndView errors = new ModelAndView("Profile-edit");		
		if (result.hasErrors()) {
			profileDTO.setImage(file.getBytes());
			return errors;
		}
		
		
		ModelAndView view = new ModelAndView("redirect:/profile/edit/{id}");
		userService.editUser(profileDTO, Long.parseLong(id));
		attributes.addFlashAttribute("mensagem", "Usuário editado com sucesso!");
		return view;
	}
	
	@RequestMapping(value = "/profile/edit/{id}", method = RequestMethod.GET)
	public ModelAndView informationProfile(@PathVariable("id") String id) {
		ModelAndView view = new ModelAndView("Profile-edit");		
		view.addObject("profileDTO", userService.findById(Long.parseLong(id)));
		return view;
	}
	
	@GetMapping("/image/{id}")
	@ResponseBody
	public byte[] showImage(@PathVariable("id") long id) {
		User user = userRepository.getOne(id);
		return user.getImage();
	}
		
	
	@RequestMapping(value = "/profile/delete/image/{id}")
	public ModelAndView deleteImage(@PathVariable("id") long id, RedirectAttributes attributes) {
		ModelAndView view = new ModelAndView("redirect:/profile/edit/{id}");
		userService.deleteImage(id);
		attributes.addFlashAttribute("mensagem", "Imagem deletada com sucesso!");
		return view;
	}
		
	@RequestMapping(value = "/profile/user/{id}", method = RequestMethod.GET)
	public ModelAndView deleteUser(@PathVariable("id") String id) {
		ModelAndView view = new ModelAndView("redirect:/logout");
		userService.deleteUser(Long.parseLong(id));
		return view;
	}
	
	
	@RequestMapping(value = "/profile/password/{id}", method = RequestMethod.GET)
	public ModelAndView passwordProfile(@PathVariable("id") String id) {
		ModelAndView view = new ModelAndView("Profile-password");		
		view.addObject("passwordDTO", userService.findPassword(Long.parseLong(id)));
		return view;
	}
	
	
	@RequestMapping(value = "/profile/password/{id}", method = RequestMethod.POST)
	public ModelAndView editPassword(@PathVariable("id") String id, @Valid PasswordDTO passwordDTO,
			BindingResult result, RedirectAttributes attributes) throws Exception {
		
		ModelAndView errors = new ModelAndView("Profile-password");
		if (result.hasErrors()) {
			User user = userRepository.findById(Long.parseLong(id));
			passwordDTO.setImage(user.getImage());
			attributes.addFlashAttribute("mensagem","Senha atual incorreta!");
			return errors;
		}
		
		ModelAndView view = new ModelAndView("redirect:/profile/password/{id}");
		userService.editPassword(passwordDTO, Long.parseLong(id));
		attributes.addFlashAttribute("mensagem","Sua senha foi alterada com sucesso!");
		return view;
	}
}
