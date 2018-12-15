package com.lieq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.lieq.entity.Student;

public interface StudenrRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student>{
	

}
