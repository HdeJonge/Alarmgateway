package com.bprocare.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import com.bprocare.domain.Label;

@Repository
public interface LabelRepository extends DataTablesRepository<Label, Long> {

	Label findById(long id);
	
		
}