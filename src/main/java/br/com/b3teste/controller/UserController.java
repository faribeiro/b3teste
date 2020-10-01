package br.com.b3teste.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.b3teste.model.User;
import br.com.b3teste.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation("Salva um usuário")
	@PostMapping
	public ResponseEntity<User> save(@RequestBody @Valid User user) {
		userService.save(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@ApiOperation("Consulta um usuário por id")
	@GetMapping(path = "/{id}")
	public ResponseEntity<Optional<User>> findById(@PathVariable Integer id) {
		Optional<User> user = userService.findById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@ApiOperation("Consulta os usuários por email")
	@GetMapping(path = "/email/{email}")
	public ResponseEntity<List<User>> findByEmail(@PathVariable String email) {
		List<User> users = userService.findByEmail(email);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@ApiOperation("Consulta os usuários por companyId")
	@GetMapping(path = "/company/{companyId}")
	public ResponseEntity<List<User>> findByCompanyId(@PathVariable String companyId) {
		List<User> users = userService.findByCompanyId(companyId);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@ApiOperation("Excluir o usuário por id")
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Integer> deleteById(@PathVariable Integer id) {
		userService.deleteById(id);
		return new ResponseEntity<>(id, HttpStatus.OK);
	}

}
