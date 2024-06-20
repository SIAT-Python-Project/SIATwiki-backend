package com.webserver.siatwiki.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webserver.siatwiki.user.dto.UserDTO.UserRequestDTO;
import com.webserver.siatwiki.user.entity.User;
import com.webserver.siatwiki.user.repository.UserRepository;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	@Transactional
	public void userTest(User user) {
		userRepository.save(user);
	}
	
//	@Transactional
//	public void createUser(User user) {
//		userRepository.insert(user);
//	}

	@Transactional
	public boolean findUserLogin(String email, String password, UserRequestDTO loginUserRequestDTO) {

		
		if (email.equals(loginUserRequestDTO.getEmail()) && password.equals(loginUserRequestDTO.getPassword())) {
			userRepository.findByEmailAndPassword(email, password);
			
			return true;
		}
		return false;
	}

	@Transactional
	public List<User> findUser() {
		return userRepository.findAll();
	}

	@Transactional
	public User findUserLogout(String email) {
		return userRepository.findByEmail(email);
	}

}
