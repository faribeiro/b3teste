package br.com.b3teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.b3teste.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
