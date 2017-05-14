package com.perf.blog.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.perf.blog.mappers.StudentMapper;
import com.perf.blog.model.StudentDetails;

@Component
@Transactional
public class StudentService {

	@Autowired
	private StudentMapper mapper;
	
	public Object listStudent() {
		return mapper.listStudent();
	}

	public void save(StudentDetails studentDetails) {
		mapper.save(studentDetails);
	}
	
	public void delete(String name) {
		mapper.delete(name);
	}

	public Object getList(String studentId) {
		Iterator<StudentDetails> itr = mapper.listStudent().iterator();
		while(itr.hasNext()){
			StudentDetails studentDetails = itr.next();
			if(studentDetails.getId().equals(Long.parseLong(studentId))){
				this.delete(studentDetails.getName());
			}
		}
		return mapper.listStudent();
	}

}
