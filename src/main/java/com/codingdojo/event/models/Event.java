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
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

//////////////////// TABLE ///////////////////////////

@Entity
@Table(name="events")
public class Event {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	@Size(min=1, message = "Name is required!")
    private String name;
    private Date date;
	@Size(min=1, message = "Location is required!")
    private String location;
	@Size(min=1, message = "State is required!")
	private String state;
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-DD")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-DD")
    private Date updatedAt;
    
    //////////// RELATIONSHIP  ///////////////
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "events_users",
		joinColumns = @JoinColumn(name = "event_id"),
		inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private List<User> users;
    
    ////////////////////////////////////////////
    
    public Event() {}

	public Event(Long id, @Size(min = 1, message = "Name is required!") String name, Date date,
			@Size(min = 1, message = "Location is required!") String location,
			@Size(min = 1, message = "State is required!") String state, Date createdAt, Date updatedAt, User user,
			List<User> users) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.location = location;
		this.state = state;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.user = user;
		this.users = users;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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
