package com.codingdojo.event.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

//////////////////// TABLE ///////////////////////////

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	@Size(min=3, message = "Firstname must be greater than 3 characters!")
    private String firstname;
	@Size(min=3, message = "Lastname must be greater than 3 characters!")
    private String lastname;
	@Size(min=1, message = "Location is required!")
    private String location;
	@Size(min=1, message = "State is required!")
	private String state;
	@Email(message = "Email must be valid!")
    private String email;
	@Size(min=5, message = "Password must be greater than 5 characters!")
    private String password;
    @Transient
    private String passwordConfirmation;
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-DD")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-DD")
    private Date updatedAt;
    
    //////////// RELATIONSHIP ///////////////
    
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Event> event;
    
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "events_users",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "event_id")
	)
	private List<Event> events;
    
    
    ////////////////////////////////////////////
    
    public User() {}

	public User(Long id, @Size(min = 3, message = "Firstname must be greater than 3 characters!") String firstname,
			@Size(min = 3, message = "Lastname must be greater than 3 characters!") String lastname,
			@Size(min = 1, message = "Location is required!") String location,
			@Size(min = 1, message = "State is required!") String state,
			@Email(message = "Email must be valid!") String email,
			@Size(min = 5, message = "Password must be greater than 5 characters!") String password,
			String passwordConfirmation, Date createdAt, Date updatedAt, List<Event> event, List<Event> events) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.location = location;
		this.state = state;
		this.email = email;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.event = event;
		this.events = events;
	}



	public List<Event> getEvent() {
		return event;
	}

	public void setEvent(List<Event> event) {
		this.event = event;
	}

	public List<Event> getEvents() {
		return event;
	}

	public void setEvents(List<Event> events) {
		this.event = events;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	
}
