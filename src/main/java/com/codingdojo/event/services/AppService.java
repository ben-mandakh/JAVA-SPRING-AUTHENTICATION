package com.codingdojo.event.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.event.models.Event;
import com.codingdojo.event.models.EventUser;
import com.codingdojo.event.models.User;
import com.codingdojo.event.repositories.EventRepository;
import com.codingdojo.event.repositories.EventUserRepository;
import com.codingdojo.event.repositories.UserRepository;

@Service
public class AppService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private EventRepository eventRepo;
	@Autowired
	private EventUserRepository eventUserRepo;
	
    // register user and hash their password
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepo.save(user);
    }
    
    // find user by email
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> u = userRepo.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepo.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
	
	public Event createNewEvent(@Valid Event event) {
		return eventRepo.save(event);
	}
	
	public List<Event> getAllEvents() {
		return eventRepo.findAll();
	}
	
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	public void deleteEvent(Long id) {
		eventRepo.deleteById(id);
	}

	public Event findEvent(Long id) {
	        Optional<Event> optionalEvent = eventRepo.findById(id);
	        if(optionalEvent.isPresent()) {
	            return optionalEvent.get();
	        } else {
	            return null;
	        }
	    }

	public void updateEvent(@Valid Event event) {
		eventRepo.save(event);
		
	}
	
	public EventUser joinEvent(EventUser joinEvent) {
		return eventUserRepo.save(joinEvent);
	}

	
	

	
}
