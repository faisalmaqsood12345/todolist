package com.faisal.springbootproject.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.faisal.springbootproject.todolist.entity.ToDoList;
import com.faisal.springbootproject.todolist.service.IToDoListService;

@Controller
@RequestMapping( "/todolists" )
public class ToDoListController
{
	private IToDoListService toDoListService;
	
	@Autowired
	public ToDoListController( IToDoListService toDoListService )
	{
		this.toDoListService = toDoListService;
	}
	//get current username from spring security
	public String getCurrentUserName()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name="";
		if ( !(authentication instanceof AnonymousAuthenticationToken ) ) {
		    String currentUserName = authentication.getName();
		    name  = currentUserName;
		}
		return name;
	}
	
	@GetMapping("/list")
	public String listToDoList( Model theModel )
	{
		// get lists from db
		String username = getCurrentUserName();
		List<ToDoList> toDoLists = toDoListService.findAllByUsername( username );
		
		// add lists to model and bind the data
		theModel.addAttribute( "todolists", toDoLists );
		
		return "todolist/list";
	}
	
	@GetMapping("/toDoListFormAdd")
	public String toDoListFormAdd( Model theModel )
	{	
		// create model attribute to bind form data
		ToDoList toDoList = new ToDoList();
		
		theModel.addAttribute( "toDoList", toDoList );
		
		return "todolist/form";
	}
	
	@GetMapping("/toDoListFormUpdate")
	public String toDoListFormAddAndUpdate( @RequestParam ( "todolistId" ) int id,
									Model theModel ) 
	{
		//get todolist if id exists otherwise empty form
		ToDoList toDoList = toDoListService.findById( id );
		
		// set model with its object either empty or with data
		theModel.addAttribute( "toDoList", toDoList );
		
		// send over to our form
		return "todolist/form";			
	}
	
	@PostMapping("/saveform")
	public String saveToDoList( @ModelAttribute  ( "toDoList" ) ToDoList toDoList )
	{
	    String username = getCurrentUserName();
		toDoList.setUsername( username );
		toDoListService.save( toDoList );
		// use a redirect to prevent duplicate submissions
		return "redirect:/todolists/list";
	}
	
	@GetMapping("/delete")
	public String delete( @RequestParam ( "todolistId" ) int id ) {
		
		// delete a todolist
		toDoListService.deleteById( id );
		
		// redirect to main list
		return "redirect:/todolists/list";
	}
	
	@GetMapping("/search")
	public String delete( @RequestParam ( "taskDescription" ) String taskDescription,
						 Model theModel ) 
	{
		// search todolist by description
	    String username = getCurrentUserName();
		List<ToDoList> toDoLists = toDoListService.searchBy( taskDescription, username );
		
		theModel.addAttribute( "todolists", toDoLists );
		// open the list model
		return "todolist/list";
	}
}
