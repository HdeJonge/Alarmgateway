package com.bprocare.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name = "Label")
@Table(name = "label")
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    @Column(name = "label_id")
	private Long id ;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;
	
	
	@ManyToMany(mappedBy = "labels") 
	private List<Location> posts = new ArrayList<>();
	 	
	
}
