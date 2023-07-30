package com.basicEcommerceII.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.basicEcommerceII.model.User;




@Service
public interface IUserService {
	
	//Obtiene usuario según el id provisto
	public Optional <User> findById(Integer id);

	public User saveUser(User user);
	
	Optional<User> findByMail(String mail);
	
	public List<User> findAll();
}
