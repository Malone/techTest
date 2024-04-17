
package com.malone;

public class Todo {
	
	private Long id;
	private String description;
	private String completionStatus;
	
	public Todo() {

	}

	public Todo(Long id, String description, String completionStatus) {
		super();
		this.id = id;
		this.description = description;
		this.completionStatus = completionStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCompletionStatus() {
		return completionStatus;
	}

	public void setCompletionStatus(String completionStatus) {
		this.completionStatus = completionStatus;
	}
	
	
}
