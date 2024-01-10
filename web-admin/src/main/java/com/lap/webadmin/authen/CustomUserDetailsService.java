package com.lap.webadmin.authen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lap.common.entity.Users;
import com.lap.webadmin.service.UsersService;

@Service
public class CustomUserDetailsService implements UserDetailsService  {
	
	@Autowired
	private UsersService usersService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users user = usersService.findByUserName(userName);

        if (user != null) {
            return User.withUsername(user.getUserName())
		        	.password(user.getPassword())
		        	.roles(user.getRole())
		        	.build();
        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

}
