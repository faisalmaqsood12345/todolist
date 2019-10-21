package com.faisal.springbootproject.todolist.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faisal.springbootproject.todolist.entity.ToDoList;

public interface IToDoListRepository extends JpaRepository< ToDoList, Integer >
{
	/**
	 * 
	 * @param description
	 * @param username
	 * @return
	 */
	public List<ToDoList> findByDescriptionContainsAllIgnoreCaseAndUsernameIgnoreCase( String description, String username );
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	public List<ToDoList> findByUsernameIgnoreCase(String username);
}
