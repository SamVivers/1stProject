package com.qa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String username;
	private String description;
	private String priority;
	private String status;
	private String do_by;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDo_by() {
		return do_by;
	}
	public void setDo_by(String do_by) {
		this.do_by = do_by;
	}
	public Task(String username, String description, String priority, String status, String do_by) {
		this.username = username;
		this.description = description;
		this.priority = priority;
		this.status = status;
		this.do_by = do_by;
	}
	public Task() {		
	}
}
