package org.closure.MMirror.repositories;

import org.closure.MMirror.entities.Pics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PicsRepo extends JpaRepository<Pics,String> {
    
}
