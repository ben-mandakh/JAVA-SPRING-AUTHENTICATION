package com.codingdojo.event.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.event.models.EventUser;

@Repository
public interface EventUserRepository extends CrudRepository<EventUser, Long> {


}
