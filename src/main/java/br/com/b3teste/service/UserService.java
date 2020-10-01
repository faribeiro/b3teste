package br.com.b3teste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.b3teste.repository.UserRepository;

@Component
public class UserService {

	@Autowired
	private UserRepository userRepository;

}
