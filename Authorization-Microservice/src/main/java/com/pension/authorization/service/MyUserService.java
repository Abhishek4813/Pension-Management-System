package com.pension.authorization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pension.authorization.dao.UserDao;
import com.pension.authorization.model.MyUserDetail;
import com.pension.authorization.model.User;

@Service
public class MyUserService implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//Returning UserDetails Object if your is present in database.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user= userDao.getUserByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("No Users Available With "+username+" !!!....");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		MyUserDetail myUser= new MyUserDetail(user);
		return myUser;
	}

}
