package org.closure.MMirror.repositories;

import org.closure.MMirror.entities.Mirror;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MirrorRepo extends JpaRepository<Mirror,String>{
    
}
