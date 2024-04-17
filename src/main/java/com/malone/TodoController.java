package com.malone;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	

	@GetMapping(path="/todos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Todo>> getUsers(){
		List<Todo> usersList = todoService.getTodos();
		return new ResponseEntity<List<Todo>>(usersList, HttpStatus.OK);
	}	

	
	@PostMapping(path = "/todos",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Todo> createUser(@RequestBody Todo todo){
		todoService.createTodo(todo);		
		return new ResponseEntity<Todo>(todo, HttpStatus.CREATED);
		
	}
	
	@PutMapping(path = "/todos/{id}")
	public ResponseEntity<Todo> updateUser(@PathVariable Integer id, @RequestBody Todo todo){
		if(todoService.getTodo(id) != null) {
			todoService.updateTodo(todo, id);
			return new ResponseEntity<Todo>(todo, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<Todo>(todo, HttpStatus.NO_CONTENT);
		
	}
	
	@DeleteMapping(path = "/todos/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer id){
		todoService.deleteTodo(id);
		return new ResponseEntity<String>("Deleted todo " + id, HttpStatus.ACCEPTED);
	}
	
	@PatchMapping(path = "/todos/{id}")
	public ResponseEntity<Todo> patchTodo(@PathVariable Integer id, @RequestBody Todo todo){
		if(todoService.getTodo(id) != null) {
			todoService.patchTodo(todo, id);
			return new ResponseEntity<Todo>(todo, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<Todo>(todo, HttpStatus.NO_CONTENT);
		
	}

}
