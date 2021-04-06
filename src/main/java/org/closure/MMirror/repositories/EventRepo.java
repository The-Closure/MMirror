package org.closure.MMirror.repositories;

import org.closure.MMirror.entities.Event;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepo extends PagingAndSortingRepository<Event, String> {
    
}
