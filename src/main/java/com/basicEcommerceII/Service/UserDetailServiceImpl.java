	package com.basicEcommerceII.Service;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.basicEcommerceII.model.User;

@Service
	public class UserDetailServiceImpl implements UserDetailsService   {
		
		@Autowired
		private IUserService IUserService;
		
		@Autowired
		private BCryptPasswordEncoder bCrypt;
		
		@Autowired
		HttpSession session;
		
		private org.slf4j.Logger log=	LoggerFactory.getLogger(UserDetailServiceImpl.class);
		
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			log.info("Este es el userrname en el userdetailservice {}", username);
			Optional<User> optionalUser= IUserService.findByMail(username);
	

		if (optionalUser.isPresent()) {
			session.setAttribute("idusuario", optionalUser.get().getId());
			User user = optionalUser.get();
			// Comento la siguiente línea porque hace la autenticación pasando password que
			// era un texto plano a password encriptado
			// con bCrypt
			// return
			// org.springframework.security.core.userdetails.User.builder().username(user.getNombre()).password(bCrypt.encode(user.getPassword())).roles(user.getTipo()).build()
			return org.springframework.security.core.userdetails.User.builder().username(user.getNombre())
					.password(user.getPassword()).roles(user.getTipo()).build();
		} else {
			throw new UsernameNotFoundException("User not found");

		}
	
		
	}

}