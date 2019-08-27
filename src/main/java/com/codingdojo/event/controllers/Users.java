package com.codingdojo.event.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.event.models.Event;
import com.codingdojo.event.models.EventUser;
import com.codingdojo.event.models.User;
import com.codingdojo.event.services.AppService;
import com.codingdojo.event.validator.UserValidator;

@Controller
public class Users {
    @Autowired
	private AppService userService;
    @Autowired
	private UserValidator userValidator;
       
    
/////////////        INDEX          //////////////////
    
    @GetMapping("/")
    public String registerForm(@ModelAttribute("newUserObj") User user) {
        return "/users/index.jsp";
    }
    
///////////// REGISTRATION FUNCTION //////////////////  
    
    @PostMapping("/registration")
    public String registerUser(@Valid @ModelAttribute("newUserObj") User user, BindingResult result, HttpSession session) {
    	userValidator.validate(user, result);
    	if(result.hasErrors()) {
    		return "/users/index.jsp";
    	} 
		  User u = userService.registerUser(user);
		  session.setAttribute("userId", u.getId());
          return "redirect:/home";
    }
    
/////////////   LOGIN FUNCTION    /////////////////// 
    
    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
    	boolean isAuthenticated = userService.authenticateUser(email, password);
    	if(isAuthenticated) {
    		User u = userService.findByEmail(email);
  		  	session.setAttribute("userId", u.getId());
            return "redirect:/home";
    	} else {
    		model.addAttribute("error", "Invalid credentials.Please try again.");
    		return "redirect:/";
    	}
    }

///////////// HOME SHOW FUNCTION ///////////////////
    
    @GetMapping("/home")
    public String home(HttpSession session, Model model, @ModelAttribute("newEventObj") Event event) {
    	Long userId = (Long) session.getAttribute("userId");
    	User u = userService.findUserById(userId);
    	List<Event> allEvents = userService.getAllEvents();
    	model.addAttribute("allEvents", allEvents);
    	model.addAttribute("user", u);
    	return "/users/homePage.jsp";	
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
   
//////////// ADD NEW EVENT ////////////////////////
    
    @PostMapping("/events/new")
    public String createEvent(@Valid @ModelAttribute("newEventObj") Event event, BindingResult result) {
        if(result.hasErrors()) {
        	return "/users/homePage.jsp";
        }
        userService.createNewEvent(event);
    	return "redirect:/home";
    }
    
////////////DELETE ROUTE /////////////////////////
    
    @PostMapping("/events/{id}/delete")
    public String deleteEvent(@PathVariable("id") Long id) {
        userService.deleteEvent(id);
        return "redirect:/home";
    }

//////////// SHOW ROUTE /////////////////////////
    
    @GetMapping("/events/{id}/show")
    public String show(Model model, @PathVariable("id") Long id) {
        Event event = userService.findEvent(id);
        List<User> allUsers = userService.getAllUsers();
		model.addAttribute("allUsers", allUsers);
        model.addAttribute("event", event);
        return "/events/show.jsp";
    }
    
/////////// UPDATE ROUTE ////////////////////////
    
    @GetMapping("/events/{id}/edit")
    public String editEvent(HttpSession session,Model model, @PathVariable("id") Long id) {
    	Long userId = (Long) session.getAttribute("userId");
    	User u = userService.findUserById(userId);
        Event event = userService.findEvent(id);
        model.addAttribute("editEventObj", event);
        model.addAttribute("user", u);
        return "/events/edit.jsp";
    }
    
////////// UPDATE FUNCTION ///////////////////////

    @RequestMapping(value="/events/{id}", method=RequestMethod.POST)
    public String update(@Valid @ModelAttribute("editEventObj") Event event, @PathVariable("id") Long id, BindingResult result) {
        if (result.hasErrors()) {
            return "/events/edit.jsp";
        } else {
            userService.updateEvent(event);
            return "redirect:/home";
        }
    }
    
////////// JOIN EVENT ///////////////////////////
    
	@PostMapping("/{id}/join")
	public String joinEvent(Model model,  @PathVariable("id")Long id, HttpSession session) {
		Event event = userService.findEvent(id);
		Long userId = (Long) session.getAttribute("userId");
    	User user = userService.findUserById(userId);
    	model.addAttribute("user", user);
		EventUser joinEvent = new EventUser(user, event);
		userService.joinEvent(joinEvent);
		return "redirect:/home";
	}
}
