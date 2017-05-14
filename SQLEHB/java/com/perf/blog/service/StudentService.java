package com.perf.blog.service;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.perf.blog.constant.Constant;
import com.perf.blog.model.StudentDetails;


@Repository
public class StudentService {

	@Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;

	public String save(StudentDetails studentDetails){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(studentDetails);
		tx.commit(); 
		return Constant.RESPONSE_SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public List<StudentDetails> listStudent() {
		String sqlQuery = " from StudentDetails";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(sqlQuery);
		return (List<StudentDetails>) query.list();
	}
	
	public void delete(StudentDetails studentDetails){
		String sqlQuery = "delete from StudentDetails where id=:id";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(sqlQuery);
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
