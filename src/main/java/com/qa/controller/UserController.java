package com.qa.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qa.model.User;
import com.qa.repository.UserRepository;

@RestController
@RequestMapping("/api/user/")

public class UserController {
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "user", method = RequestMethod.POST)
    public User create(@RequestBody User user){
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        return userRepository.saveAndFlush(user);
    }
	
	@RequestMapping(value = "user/{username}", method = RequestMethod.GET)
    public int get(@PathVariable String username){
		User exists = userRepository.findByUsername(username);
        if (exists != null) {
        	return 1;
        }
		return 0;
    }
	
	@RequestMapping(value = "user/{username}", method = RequestMethod.POST)
    public int get(@RequestBody User user){
		User login = userRepository.findByUsername(user.getUsername());
		if (BCrypt.checkpw(user.getPassword(), login.getPassword())) {
			return 1;
		}
        return 0;
    }
	
	@RequestMapping(value = "user/{username}", method = RequestMethod.PUT)
    public User update(@PathVariable String username, @RequestBody User user){
		User existingUser = userRepository.findByUsername(username);
        BeanUtils.copyProperties(user, existingUser);
        return userRepository.saveAndFlush(user);
    }
}
