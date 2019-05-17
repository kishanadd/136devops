package com.srk.rest.resource;

import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.srk.dto.StudentsDTO;
import com.srk.exception.CustomWebException;
import com.srk.rest.response.ResponseData;
import com.srk.rest.response.SuccessResponse;
import com.srk.service.StudentsService;




/**
 * REST controller for managing Students.
 */
@RestController
@RequestMapping("/student")
public class StudentsResource {

    private final Logger log = LoggerFactory.getLogger(StudentsResource.class);
    
    private SuccessResponse<StudentsDTO> successResponse;
    private ResponseData<StudentsDTO> responseData;
        
    @Autowired
    private StudentsService studentsService;
    
   /**
     * POST  /student : Create a new Student.
     *
     * @param StudentsDTO the studentsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new StudentsDTO, or with status 400 (Bad Request) if the Student has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public <T> ResponseEntity<SuccessResponse<StudentsDTO>> createStudent(@RequestBody StudentsDTO studentsDTO) throws URISyntaxException {
        log.debug("REST request to save Students : {}", studentsDTO);
        successResponse = new SuccessResponse<StudentsDTO>();
        responseData = new ResponseData<StudentsDTO>();
        try {
        	Integer count = studentsService.save(studentsDTO);
			if(count!=null && count>0){
				successResponse.setStatus(Boolean.TRUE);
				successResponse.setHttpStatus(HttpStatus.CREATED.value());
				responseData.setMessage("Student Saved Successfully");
				studentsDTO.setStudent_id(count);
				responseData.setObject(studentsDTO);
			} else {
				successResponse.setStatus(Boolean.FALSE);
				successResponse.setHttpStatus(HttpStatus.BAD_REQUEST.value());
				responseData.setMessage("Student details are failed to Save");
			}
		} catch (CustomWebException e) {
			successResponse.setStatus(Boolean.FALSE);
			successResponse.setHttpStatus(e.getErrorCode());
			responseData.setMessage(e.getErrorMessage());
		} catch (Exception e) {
			successResponse.setStatus(Boolean.FALSE);
			successResponse.setHttpStatus(HttpStatus.PRECONDITION_REQUIRED.value());
			if (e.getMessage().contains("Name_UNIQUE")) {
				responseData.setMessage("Student already exists");
			} else {
				responseData.setMessage("Failed to Save Student");
			}
		}
        successResponse.setData(responseData);
        return new ResponseEntity<SuccessResponse<StudentsDTO>>(successResponse, HttpStatus.CREATED);
    }

    /**
     * PUT  /student : Updates an existing Student.
     *
     * @param StudentsDTO the studentsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated StudentsDTO,
     * or with status 400 (Bad Request) if the StudentsDTO is not valid,
     * or with status 500 (Internal Server Error) if the StudentsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public <T> ResponseEntity<SuccessResponse<StudentsDTO>> updateStudent( @RequestBody StudentsDTO studentsDTO) throws URISyntaxException {
        log.debug("REST request to update Studnets : {}", studentsDTO);
        successResponse = new SuccessResponse<StudentsDTO>();
        responseData = new ResponseData<StudentsDTO>();
        try {
        	Integer count = studentsService.save(studentsDTO);
			if(count!=null && count>0){
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setStatus(Boolean.TRUE);
				responseData.setMessage("Student Updated Successfully");
				responseData.setObject(studentsDTO);
			} else {
				successResponse.setHttpStatus(HttpStatus.BAD_REQUEST.value());
				successResponse.setStatus(Boolean.FALSE);
				responseData.setMessage("failed to update Student information");
				responseData.setObject(studentsDTO);
			}
		} catch (CustomWebException e) {
			successResponse.setHttpStatus(e.getErrorCode());
			successResponse.setStatus(Boolean.FALSE);
			responseData.setMessage(e.getErrorMessage());
		} catch (Exception e) {
			successResponse.setHttpStatus(HttpStatus.PRECONDITION_REQUIRED.value());
			successResponse.setStatus(Boolean.FALSE);
			responseData.setMessage("Failed to update the Student information");
		}
        successResponse.setData(responseData);
        return new ResponseEntity<SuccessResponse<StudentsDTO>>(successResponse, HttpStatus.OK);
    }

    /**
     * GET  /student/list : get all students.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of Students in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/list",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public <T> ResponseEntity<SuccessResponse<StudentsDTO>> getAllStudents(@RequestHeader(required = false, value="filter") String filter)
        throws URISyntaxException {
        log.debug("REST request to get a page of Students");
        successResponse = new SuccessResponse<StudentsDTO>();
        responseData = new ResponseData<StudentsDTO>();
        List<StudentsDTO> studentList = null;
		try {
			studentList = studentsService.findAll(filter);
			if (null != studentList && studentList.size()>0) {
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setStatus(Boolean.TRUE);
				responseData.setObjects(studentList);
			} else {
				successResponse.setHttpStatus(HttpStatus.NO_CONTENT.value());
				successResponse.setStatus(Boolean.FALSE);
				responseData.setMessage("There are no students available");
			}
		} catch (CustomWebException e) {
			successResponse.setHttpStatus(e.getErrorCode());
			successResponse.setStatus(Boolean.FALSE);
			responseData.setMessage(e.getErrorMessage());
		} catch (Exception e) {
			successResponse.setHttpStatus(HttpStatus.PRECONDITION_REQUIRED.value());
			successResponse.setStatus(Boolean.FALSE);
			responseData.setMessage("Failed to fetch the Students information");
		}
		successResponse.setData(responseData);
        return new ResponseEntity<SuccessResponse<StudentsDTO>>(successResponse, HttpStatus.OK);
    }

    /**
     * GET  /student/:id : get the "id" Student.
     *
     * @param id the id of the StudentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the StudentDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public <T> ResponseEntity<SuccessResponse<StudentsDTO>> getStudent(@PathVariable Integer id) throws URISyntaxException {
        log.debug("REST request to get Students : {}", id);
        successResponse = new SuccessResponse<StudentsDTO>();
        responseData = new ResponseData<StudentsDTO>();
        StudentsDTO studentsDTO = null;
		try {
			studentsDTO = studentsService.findOne(id);
			if (null != studentsDTO) {
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setStatus(Boolean.TRUE);
				responseData.setObject(studentsDTO);
			} else {
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setStatus(Boolean.TRUE);
				responseData.setMessage("There is no student exists with this student id : "+id);
			}
		} catch (CustomWebException e) {
			successResponse.setHttpStatus(e.getErrorCode());
			successResponse.setStatus(Boolean.FALSE);
			responseData.setMessage(e.getErrorMessage());
		} catch (Exception e) {
			successResponse.setHttpStatus(HttpStatus.PRECONDITION_REQUIRED.value());
			successResponse.setStatus(Boolean.FALSE);
			responseData.setMessage("Failed to fetch the Students information");
		}
		successResponse.setData(responseData);
        return new ResponseEntity<SuccessResponse<StudentsDTO>>(successResponse, HttpStatus.OK);
    }

    /**
     * DELETE  /student/:id : delete the "id" students.
     *
     * @param id the id of the StudentDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public <T> ResponseEntity<SuccessResponse<StudentsDTO>> deleteStudent( @PathVariable Integer id) throws URISyntaxException {
        log.debug("REST request to delete Student : {}", id);
        successResponse = new SuccessResponse<StudentsDTO>();
        responseData = new ResponseData<StudentsDTO>();
        try {
        	studentsService.delete(id);
        	successResponse.setHttpStatus(HttpStatus.OK.value());
			successResponse.setStatus(Boolean.TRUE);
			responseData.setMessage("Student deleted successfully...!");
        } catch (CustomWebException e) {
			successResponse.setHttpStatus(e.getErrorCode());
			successResponse.setStatus(Boolean.FALSE);
			responseData.setMessage(e.getErrorMessage());
		} catch (Exception e) {
			successResponse.setHttpStatus(HttpStatus.PRECONDITION_REQUIRED.value());
			successResponse.setStatus(Boolean.FALSE);
			responseData.setMessage("Failed to fetch the Students information");
		}
		successResponse.setData(responseData);
        return new ResponseEntity<SuccessResponse<StudentsDTO>>(successResponse, HttpStatus.OK);
    }

}
