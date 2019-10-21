package com.faisal.springbootproject.todolist.service;

import java.util.List;

import com.faisal.springbootproject.todolist.entity.ToDoList;


public interface IToDoListService 
{
	/**
	 * 
	 * @return
	 */
    public List<ToDoList> findAll();
	/**
	 * 
	 * @param id
	 * @return
	 */
	public ToDoList findById(int id);
	/**
	 * 
	 * @param toDoList
	 */
	public void save(ToDoList toDoList);
	/**
	 * 
	 * @param id
	 */
	public void deleteById(int id);
	/**
	 * 
	 * @param description
	 * @param username
	 * @return
	 */
	public List<ToDoList> searchBy(String description, String username);
	/**
	 * 
	 * @param username
	 * @return
	 */
	public List<ToDoList> findAllByUsername( String username);
}
