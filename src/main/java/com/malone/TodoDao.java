package com.malone;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TodoDao {

	
	@Autowired
	private JdbcTemplate template;	
	
	public List<Todo> getTodos(){
		String sql = "select * from todo";
		return template.query(sql, (rs, rowNum) -> 
				new Todo(rs.getLong("id"), rs.getString("description"),
						rs.getString("completion_status")));
	}
	
	public Todo getTodo(Integer id) {
		String sql = "select * from todo where id = ?";
		return template.query(sql, BeanPropertyRowMapper.newInstance(Todo.class), id).get(0);
	}
	
	public Integer createTodo(Todo todo) {
		String sql = "insert into todo values( ?, ?,?)";
		return template.update(sql, todo.getId(), todo.getDescription(),
				todo.getCompletionStatus()); 
		
	}
	
	public Integer updateTodo(Todo todo, Integer id) {
		String sql = "update todo set description = ?, completion_status = ?"
				+ " where id = ?";
		return template.update(sql, todo.getDescription(),
				todo.getCompletionStatus(), id); 
	}
	
	public Integer deleteTodo(Integer id) {
		String sql = "delete from todo where id = ?";
		return template.update(sql, id);
	}
	
    public Todo patchTodo(Integer id, Todo todo) {
        String sql = "update todo set ";
        boolean isFirstField = true;
        
        if (todo.getDescription() != null) {
            sql += "description = ?";
            isFirstField = false;
        }
        
        if (todo.getCompletionStatus() != null) {
            if (!isFirstField) {
                sql += ", ";
            }
            sql += "completion_status = ?";
        }

        sql += " where id = ?";

        Object[] params;
        if (todo.getDescription() != null && todo.getCompletionStatus() != null) {
            params = new Object[]{todo.getDescription(), todo.getCompletionStatus(), id};
        } else if (todo.getDescription() != null) {
            params = new Object[]{todo.getDescription(), id};
        } else if (todo.getCompletionStatus() != null) {
            params = new Object[]{todo.getCompletionStatus(), id};
        } else {
            return null; // No fields to update
        }

        int rowsUpdated = template.update(sql, params);
        if (rowsUpdated > 0) {
            return getTodo(id);
        } else {
            return null; 
        }
    }
	
}
