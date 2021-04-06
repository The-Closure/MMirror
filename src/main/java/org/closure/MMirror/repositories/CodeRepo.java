package org.closure.MMirror.repositories;

import java.util.Optional;

import org.closure.MMirror.entities.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepo extends JpaRepository<Code, Long>{
    public Optional<Code> findByCode(Long code); 
}
