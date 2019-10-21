package com.faisal.springbootproject.todolist.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faisal.springbootproject.todolist.dao.IToDoListRepository;
import com.faisal.springbootproject.todolist.entity.ToDoList;

@Service
public class ToDoListService implements IToDoListService
{
	private IToDoListRepository toDoListRepository;
	
	@Autowired
	public ToDoListService(IToDoListRepository toDoListRepository) {
		this.toDoListRepository = toDoListRepository;
	}

	@Transactional
	@Override
	public List<ToDoList> findAll() 
	{
		return toDoListRepository.findAll();
	}

	@Override
	public ToDoList findById(int id) 
	{
		//find by id returns an optional which means if not find it will return empty other 
		//the object
		Optional<ToDoList> result = toDoListRepository.findById(id);
		
		ToDoList toDoList = null;
		
		if (result.isPresent()) 
		{
			toDoList = result.get();
		}
		else
		{
			// we didn't find the employee
			System.out.println("ToDoList id not fount" + id);
		}
		return toDoList;
	}

	@Override
	public void save(ToDoList toDoList) 
	{
		LocalDateTime nowTime = LocalDateTime.now();
		//save new or updated to do list
		if( toDoList.getId() != null ) 
		{
			toDoList.setUpdatedAt(nowTime);
			toDoListRepository.save(toDoList);
		}
		else 
		{
			toDoList.setUpdatedAt(nowTime);
			toDoList.setCreatedAt(nowTime);
			toDoListRepository.save(toDoList);
		}
	}

	@Override
	public void deleteById(int id) 
	{
		toDoListRepository.deleteById(id);
	}

	@Override
	public List<ToDoList> searchBy(String description, String username)
	{
		List<ToDoList> results = null;
		
		if (description != null && (description.trim().length() > 0))
		{
			results = toDoListRepository.findByDescriptionContainsAllIgnoreCaseAndUsernameIgnoreCase(description, username);
		}
		else 
		{
			results = findAllByUsername( username );
		}
		
		return results;
	}

	@Override
	public List<ToDoList> findAllByUsername( String username ) 
	{
		List<ToDoList> results = toDoListRepository.findByUsernameIgnoreCase(username);
		return results;
	}
}
