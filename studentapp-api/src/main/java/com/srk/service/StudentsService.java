package com.srk.service;

import java.util.List;

import com.srk.dto.StudentsDTO;
import com.srk.exception.CustomWebException;


/**
 * Service Interface for managing Students.
 */
public interface StudentsService {

    /**
     * Save a Students.
     * 
     * @param StudentsDTO the entity to save
     * @return the persisted entity
     */
    Integer save(StudentsDTO studentsDTO) throws CustomWebException, Exception;

    /**
     *  Get all the Students.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    List<StudentsDTO> findAll(String filter) throws CustomWebException, Exception;

    /**
     *  Get the "id" Students.
     *  
     *  @param id the id of the entity
     *  @return the entity
     */
    StudentsDTO findOne(Integer id) throws CustomWebException, Exception;

    /**
     *  Delete the "id" Students.
     *  
     *  @param id the id of the entity
     */
    void delete(Integer id) throws CustomWebException, Exception;
}
