package com.srk.repository.custom.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.srk.domain.Students;
import com.srk.exception.CustomWebException;
import com.srk.repository.custom.StudentsRepositoryCustom;

/**
 * Spring Data JPA repository for the Students entity.
 */
public class StudentsRepositoryImpl implements StudentsRepositoryCustom{

	@PersistenceContext
    private EntityManager entityManager;
	
	

	public List<Students> getStudents(String filter) throws CustomWebException {
		List<Students> studentList = null;
		try {
			StringBuffer sb = new StringBuffer("SELECT s FROM Students s WHERE 1=1 ");
			if (null != filter) {
				sb.append(" AND (s.studentName like '%"+filter+"%' OR s.studentAddr like '%"+filter+"%')");
			}
			Query query = this.entityManager.createQuery(sb.toString());
			
			studentList = query.getResultList();
		} catch(Exception e) {
			e.printStackTrace();
			throw new CustomWebException(422, "Unable to fectch the Student details");
		}
		return studentList;
	}



	public Integer updateStudentInfo(Students student) throws CustomWebException {
		Integer count = 0;
		try {
			String query = "update Students set studentName = :studentName, studentAddr = :studentAddr, studentAge = :studentAge, studentQulaification = :studentQulaification, studentPercent = :studentPercent, studentYearPassword = :studentYearPassword where student_id = :student_id";
			count = this.entityManager.createQuery(query)
							  .setParameter("studentName", student.getStudentName())
							  .setParameter("studentAddr", student.getStudentAddr())
							  .setParameter("studentAge", student.getStudentAge())
							  .setParameter("studentQulaification", student.getStudentQulaification())
							  .setParameter("studentPercent", student.getStudentPercent())
							  .setParameter("studentYearPassword", student.getStudentYearPassword())
							  .setParameter("student_id", student.getStudent_id())
					          .executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			throw new CustomWebException(422, "Failed to update the Student Info");
		}
		return count;
	}

	
}
