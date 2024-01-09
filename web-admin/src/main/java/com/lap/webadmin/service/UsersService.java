package com.lap.webadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lap.common.entity.Users;
import com.lap.webadmin.repository.UsersRepository;

@Service
@Transactional
public class UsersService {

	@Autowired
	private UsersRepository userRepository;
	
	public void addNew(Users user) {
		userRepository.save(user);
	}
	
	public Users findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
}
