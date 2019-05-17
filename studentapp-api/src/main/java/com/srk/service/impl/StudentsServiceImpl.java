package com.srk.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.srk.domain.Students;
import com.srk.dto.StudentsDTO;
import com.srk.exception.CustomWebException;
import com.srk.repository.StudentsRepository;
import com.srk.service.StudentsService;


/**
 * Service Implementation for managing Students.
 */
@Service
@Transactional
public class StudentsServiceImpl implements StudentsService {

    private final Logger log = LoggerFactory.getLogger(StudentsServiceImpl.class);
    
    @Autowired
    private StudentsRepository studentsRepository;
    
    /**
     * Save a Students.
     * 
     * @param StudentsDTO the entity to save
     * @return the persisted entity
     */
    public Integer save(StudentsDTO studentsDTO) throws CustomWebException, Exception {
        log.debug("Request to save Students : {}", studentsDTO);
        Integer check = 0;
        Students student = new Students();
        
        student.setStudent_id(studentsDTO.getStudent_id());
        student.setStudentName(studentsDTO.getStudentName());
        student.setStudentAddr(studentsDTO.getStudentAddr());
        student.setStudentAge(studentsDTO.getStudentAge());
        student.setStudentQulaification(studentsDTO.getStudentQulaification());
        student.setStudentPercent(studentsDTO.getStudentPercent());
        student.setStudentYearPassword(studentsDTO.getStudentYearPassword());


        if(studentsDTO.getStudent_id()!=null) {
        	check = studentsRepository.updateStudentInfo(student);	
        } else {
        	student = studentsRepository.save(student);	
            if(student.getStudent_id()!=null){
            	check = student.getStudent_id();
            } else {
            	check = 1;
            }
        }
        return check;
    }

    /**
     *  Get all the Students.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<StudentsDTO> findAll(String filter) throws CustomWebException, Exception {
        log.debug("Request to get all Students");
        List<Students> studentList = null;
        if (null != filter) {
        	studentList = studentsRepository.getStudents(filter);
        } else {
        	studentList = studentsRepository.findAll(); 
        }
        
        List<StudentsDTO> studentsDTOList = new ArrayList<StudentsDTO>();
        for (Students students : studentList) {
        	
        	StudentsDTO studentsDTO = new StudentsDTO();
            studentsDTO.setStudent_id(students.getStudent_id());
            studentsDTO.setStudentName(students.getStudentName());
            studentsDTO.setStudentAddr(students.getStudentAddr());
            studentsDTO.setStudentAge(students.getStudentAge());
            studentsDTO.setStudentQulaification(students.getStudentQulaification());
            studentsDTO.setStudentPercent(students.getStudentPercent());
            studentsDTO.setStudentYearPassword(students.getStudentYearPassword());
            
            studentsDTOList.add(studentsDTO);
		}
        return studentsDTOList;
    }

    /**
     *  Get one Students by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public StudentsDTO findOne(Integer id) throws CustomWebException, Exception {
        log.debug("Request to get Students : {}", id);
        Students students = studentsRepository.findOne(id);
        StudentsDTO studentsDTO = new StudentsDTO();
        studentsDTO.setStudent_id(students.getStudent_id());
        studentsDTO.setStudentName(students.getStudentName());
        studentsDTO.setStudentAddr(students.getStudentAddr());
        studentsDTO.setStudentAge(students.getStudentAge());
        studentsDTO.setStudentQulaification(students.getStudentQulaification());
        studentsDTO.setStudentPercent(students.getStudentPercent());
        studentsDTO.setStudentYearPassword(students.getStudentYearPassword());
           	
        return studentsDTO;
    }

    /**
     *  Delete the  Students by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(Integer id) throws CustomWebException, Exception {
        log.debug("Request to delete Students : {}", id);
        studentsRepository.delete(id);
    }
}
