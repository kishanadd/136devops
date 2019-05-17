package com.srk.repository.custom;

import java.util.List;

import com.srk.domain.Students;
import com.srk.exception.CustomWebException;

/**
 * Spring Data JPA repository for the Students entity.
 */
public interface StudentsRepositoryCustom {
	public Integer updateStudentInfo(Students students) throws CustomWebException;
	public List<Students> getStudents(String filter) throws CustomWebException;
}
