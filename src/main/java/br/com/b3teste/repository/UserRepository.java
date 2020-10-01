package br.com.b3teste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.b3teste.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.email = ?1")
	List<User> findByEmail(String email);

	@Query("select u from User u where u.companyId = ?1")
	List<User> findByCompanyId(String companyId);

}
