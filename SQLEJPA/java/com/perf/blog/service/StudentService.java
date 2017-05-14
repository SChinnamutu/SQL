package com.perf.blog.service;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.perf.blog.constant.Constant;
import com.perf.blog.model.StudentDetails;
import com.perf.blog.util.ConnectionUtil;


@Component
@Transactional
public class StudentService extends ConnectionUtil {

	
	public String save(StudentDetails studentDetails){
		getManager().persist(studentDetails);
		return Constant.RESPONSE_SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public List<StudentDetails> listStudent() {
		String sqlQuery = " from StudentDetails";
		Query query = getManager().createQuery(sqlQuery);
		return (List<StudentDetails>) query.getResultList();
	}
	
	public void delete(StudentDetails studentDetails){
		String sqlQuery = "delete from StudentDetails where id=:id";
		Query query = getManager().createQuery(sqlQuery);
		query.setParameter("id", studentDetails.getId());
		query.executeUpdate();
	}
	
	public List<StudentDetails> getList(String studentId) {
		Iterator<StudentDetails> itr = this.listStudent().iterator();
		while(itr.hasNext()){
			StudentDetails details = itr.next();
			if(details.getId().equals(Long.valueOf(studentId))){
				this.delete(details);
			}
		}
		return this.listStudent();
	}
	
}
