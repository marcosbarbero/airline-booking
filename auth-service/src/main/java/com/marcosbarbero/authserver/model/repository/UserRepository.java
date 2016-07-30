package com.marcosbarbero.authserver.model.repository;

import com.marcosbarbero.authserver.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Marcos Barbero
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
