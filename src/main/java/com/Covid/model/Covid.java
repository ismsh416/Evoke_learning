package com.Covid.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "covid")
public class Covid {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	
	@Column(name = "state_name")
	private String state;
	
	
	@Column(name = "state_cases")
	private long cases;

	public Covid() {
		super();
	}

	public Covid(long id, String state, long cases) {
		super();
		this.id = id;
		this.state = state;
		this.cases = cases;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getCases() {
		return cases;
	}

	public void setCases(long cases) {
		this.cases = cases;
	}

}
