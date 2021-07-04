package org.closure.MMirror.repositories;

import java.util.Optional;

import org.closure.MMirror.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String>{
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public Optional<User> findByEmail(String email); 
    User findByName(String name); 
    public void deleteByName(String name); 
}
