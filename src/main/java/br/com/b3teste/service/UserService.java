package br.com.b3teste.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.b3teste.exception.UsuarioDuplicadoException;
import br.com.b3teste.model.User;
import br.com.b3teste.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void save(User user) {
		try {
			userRepository.save(user);
		} catch (DataIntegrityViolationException e) {
			throw new UsuarioDuplicadoException();
		}
	}

	public Optional<User> findById(Integer id) {
		return userRepository.findById(id);
	}

	public List<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public List<User> findByCompanyId(String companyId) {
		return userRepository.findByCompanyId(companyId);
	}

	public void deleteById(Integer id) {
		userRepository.deleteById(id);
	}

	@Scheduled(initialDelay = 0, fixedRate = 30000)
	public void importCSVFile() {
		System.out.println("The time is now " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
	}
}
