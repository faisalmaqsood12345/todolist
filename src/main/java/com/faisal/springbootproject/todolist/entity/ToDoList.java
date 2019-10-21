package com.faisal.springbootproject.todolist.entity;

import javax.persistence.Table;

import org.jboss.logging.FormatWith;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name="ToDoList")
public class ToDoList 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="description")
	private String description;
	
	@Column(name="createdAt")
	private LocalDateTime createdAt;
	
	@Column(name="updatedAt")
	private LocalDateTime updatedAt;
	
	//with real usr table this would be relationship with user table
	@Column(name="username")
	private String username;
	
	@Column(name="taskDone")
	private Boolean taskDone = Boolean.FALSE;
	
	public ToDoList(){
		
	}
	
	public ToDoList(Integer id, String description, LocalDateTime createdAt, LocalDateTime updatedAt, String username,
			Boolean taskDone) {
		super();
		this.id = id;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.username = username;
		this.taskDone = taskDone;
	}
	
	public ToDoList(String description, LocalDateTime createdAt, LocalDateTime updatedAt, String username,
			Boolean taskDone) {
		super();
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.username = username;
		this.taskDone = taskDone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public Boolean getTaskDone() {
		return taskDone;
	}

	public void setTaskDone(Boolean taskDone) {
		this.taskDone = taskDone;
	}

	@Override
	public String toString() {
		return "ToDoList [id=" + id + ", description=" + description + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", username=" + username + ", taskDone=" + taskDone + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((taskDone == null) ? 0 : taskDone.hashCode());
		result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToDoList other = (ToDoList) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (taskDone == null) {
			if (other.taskDone != null)
				return false;
		} else if (!taskDone.equals(other.taskDone))
			return false;
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
