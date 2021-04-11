package org.closure.MMirror.repositories;

import java.util.List;

import org.closure.MMirror.entities.Event;
import org.closure.MMirror.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepo extends PagingAndSortingRepository<Event, String> {
    public List<Event> findAllByUser(User user);
}
