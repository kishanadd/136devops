package com.srk.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.srk.domain.Students;
import com.srk.repository.custom.StudentsRepositoryCustom;

/**
 * Spring Data JPA repository for the Students entity.
 */

public interface StudentsRepository extends JpaRepository<Students,Integer>, StudentsRepositoryCustom {

}
