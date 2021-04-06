package org.closure.MMirror.repositories;

import java.util.Optional;

import org.closure.MMirror.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String>{
    public Optional<User> findByEmail(String email); 
}
