package com.lieq.controller;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.pool.DruidDataSource;
import com.lieq.entity.Student;
import com.lieq.repository.StudenrRepository;

@Controller
public class StudentController {
	@Autowired
	private StudenrRepository studenrRepository;
	
	
	@RequestMapping("/save")
	@ResponseBody
	public String save() {
		System.out.println("Execute --save");
		Student student = new Student();
		student.setName("赵丽银");
		student.setAge(22);
		studenrRepository.save(student);
		return "hello:" + student;
	}
	
	@RequestMapping("/page")
	@ResponseBody
	public String page(final String name) {
		System.out.println("Execute --page");
		/*Sort sort = new Sort(Direction.DESC, "id");
		PageRequest pageable= new PageRequest(2, 3, sort);
		
		Page<Student> page = studenrRepository.findAll(pageable);
		for (Student student : page) {
			System.out.println(student);
		}
		*/
		System.out.println("name = "+name);
		Sort sort = new Sort(Direction.ASC, "id");
		PageRequest pageable = new PageRequest(0, 3, sort);
		Specification<Student> spec = new Specification<Student>() {
			
			@Override
			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("name"), name);
			}
		};
		Page<Student> page = studenrRepository.findAll(spec, pageable);
		System.out.println("总条数"+page.getTotalElements());
		System.out.println("总页数"+page.getTotalPages());
		for (Student student : page) {
			System.out.println(student);
		}
		
		return "查询分页";
	}
}
