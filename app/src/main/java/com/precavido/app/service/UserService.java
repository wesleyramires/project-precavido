package com.precavido.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.precavido.app.dto.PasswordDTO;
import com.precavido.app.dto.ProfileDTO;
import com.precavido.app.dto.UserDTO;
import com.precavido.app.model.Payments;
import com.precavido.app.model.User;
import com.precavido.app.repository.PaymentsRepository;
import com.precavido.app.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PaymentsRepository paymentsRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	public User addUser(UserDTO userDTO) {
		User user = new User();		
		user.setName(userDTO.getName());
		user.setLastName(userDTO.getLastName());
		user.setBirthday(userDTO.getBirthday());
		user.setEmail(userDTO.getEmail());
		user.setUsername(userDTO.getUsername());
		user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
		return userRepository.save(user);
	}
	
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email); 
	}
	
	public ProfileDTO findById(long id) {
		ProfileDTO profileDTO = new ProfileDTO();
		User user = userRepository.findById(id);
		profileDTO.setId(user.getId());
		profileDTO.setImage(user.getImage());
		profileDTO.setName(user.getName());
		profileDTO.setLastName(user.getLastName());
		profileDTO.setBirthday(user.getBirthday());
		return profileDTO;
	}

	public User editUser(ProfileDTO profileDTO, long id) {
		User user = userRepository.findById(id);	
		user.setName(profileDTO.getName());
		user.setLastName(profileDTO.getLastName());
		user.setBirthday(profileDTO.getBirthday());
		user.setImage(profileDTO.getImage());
		return userRepository.save(user);
	}
	
	public User deleteImage(long id) {
		User user = userRepository.findById(id);
		byte[] image = null;
		user.setImage(image);
		return userRepository.save(user);
	}
	
	public void deleteUser(long id) {
		User user = userRepository.findById(id);
		
		User userOnline = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username =((UserDetails)userOnline).getUsername();
		List<Payments> payments = paymentsRepository.findByUsername(username);
		
		for (Payments payment : payments) {
			paymentsRepository.delete(payment);
		}
		
		userRepository.delete(user);		
	}

	public PasswordDTO findPassword(long id) {
		PasswordDTO passwordDTO = new PasswordDTO();
		User user = userRepository.findById(id);
		passwordDTO.setId(user.getId());
		passwordDTO.setImage(user.getImage());
		return passwordDTO;
	}
	
	
	public User editPassword(PasswordDTO passwordDTO, long id) throws Exception {
		User user = userRepository.findById(id);
		
		String confirmPassword = passwordDTO.getCurrentPassword();
		String hashPassword = user.getPassword();
		boolean autenticationPassword = BCrypt.checkpw(confirmPassword, hashPassword);
		
		try {
			if (autenticationPassword == true && passwordDTO.getPassword().equals(passwordDTO.getConfirmPassword())) {
				user.setPassword(bCryptPasswordEncoder.encode(passwordDTO.getPassword()));
				userRepository.save(user);
			} 
		} catch (ExceptionInInitializerError e) {
			throw new Exception("Os dados não conferem com a do usuário logado!");
		}
		
		System.out.println(autenticationPassword);
		return user;
	}
	
}

