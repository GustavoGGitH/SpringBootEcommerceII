package com.basicEcommerceII.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.basicEcommerceII.model.User;



@Repository
public interface IUserRepository extends JpaRepository<User,Integer>{
	
	Optional<User> findByMail(String mail);

}
