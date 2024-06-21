package com.webserver.siatwiki.user.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.webserver.siatwiki.user.dto.UserDTO.UserRequestDTO;
import com.webserver.siatwiki.user.entity.Role;
import com.webserver.siatwiki.user.entity.User;
import com.webserver.siatwiki.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	@Autowired
	private final UserRepository userRepository;
//	@Transactional
//	public void userTest(User user) {
//		userRepository.save(user);
//	}

	@Transactional
	public void createUser(User user) {
		userRepository.save(user);
	}

	@Transactional
	public boolean findUserLogin(String email, String password) {
		User user = userRepository.findByEmail(email);
		return user != null && user.getPassword().equals(password);
	}

	@Transactional
	public List<User> findUser() {
		return userRepository.findAll();
	}

	@Transactional
	public boolean findUserLogout(String email) {
		User user = userRepository.findByEmail(email);
		return user != null;
	}

	@Transactional
	public User getByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User DtoToEntity(UserRequestDTO requestDTO) {
		User user = User.builder()
				.name(requestDTO.getName())
				.email(requestDTO.getEmail())
				.password(requestDTO.getPassword())
				.role(Role.USER)
				.createDate(LocalDateTime.now())
				.build();

		return user;

	}


}
