package com.basicEcommerceII.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.basicEcommerceII.Controller.UserController;
import com.basicEcommerceII.model.User;
import com.basicEcommerceII.repository.IUserRepository;


@Service
public class UserServiceImpl implements IUserService {
	
	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	IUserRepository userRepository;

	@Override
	public Optional<User> findById(Integer id) {
		return Optional.ofNullable(userRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %s not found ", id))));
	}

	@Override
	public User saveUser(User user) {
		logger.info("Usuario dentro de saveUser en el servicio:  {}", user);
		return userRepository.save(user);

	}

	@Override
	public Optional<User> findByMail(String mail) {
		return userRepository.findByMail(mail);
		
		
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	
	
}
