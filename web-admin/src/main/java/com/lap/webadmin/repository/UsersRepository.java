package com.lap.webadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lap.common.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
	public Users findByUserName(String userName);
}
