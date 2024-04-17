package com.malone;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

	@Autowired
	private TodoDao todoDao;
	
	public List<Todo> getTodos(){
		return todoDao.getTodos();
	}
	
	public Todo getTodo(Integer userId) {
		return todoDao.getTodo(userId);
	}
	
	public Integer createTodo(Todo todo) {
		return todoDao.createTodo(todo);
	}
	
	public Integer updateTodo(Todo todo, Integer id) {
		return todoDao.updateTodo(todo, id);
	}
	
	public Integer deleteTodo(Integer id) {
		return todoDao.deleteTodo(id);
	}
	
	public Todo patchTodo(Todo todo, Integer id) {
		return todoDao.patchTodo(id, todo);
	}
}
