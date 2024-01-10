package com.lap.webadmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lap.common.entity.Users;
import com.lap.webadmin.repository.UsersRepository;

@Service
@Transactional
public class UsersService {

	@Autowired
	private UsersRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Users findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public void save(Users user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole("ADMIN");
		userRepository.save(user);
	}

	public List<Users> findAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}
}
