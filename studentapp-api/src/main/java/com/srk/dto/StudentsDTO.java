package com.srk.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * A DTO for the Students entity.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer student_id;
	private String studentName;
	private String studentAddr;
	private String studentAge;
	private String studentQulaification;
	private String studentPercent;
	private String studentYearPassword;
	
	public StudentsDTO(){}

	public Integer getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentAddr() {
		return studentAddr;
	}

	public void setStudentAddr(String studentAddr) {
		this.studentAddr = studentAddr;
	}

	public String getStudentAge() {
		return studentAge;
	}

	public void setStudentAge(String studentAge) {
		this.studentAge = studentAge;
	}

	public String getStudentQulaification() {
		return studentQulaification;
	}

	public void setStudentQulaification(String studentQulaification) {
		this.studentQulaification = studentQulaification;
	}

	public String getStudentPercent() {
		return studentPercent;
	}

	public void setStudentPercent(String studentPercent) {
		this.studentPercent = studentPercent;
	}

	public String getStudentYearPassword() {
		return studentYearPassword;
	}

	public void setStudentYearPassword(String studentYearPassword) {
		this.studentYearPassword = studentYearPassword;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((student_id == null) ? 0 : student_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentsDTO other = (StudentsDTO) obj;
		if (student_id == null) {
			if (other.student_id != null)
				return false;
		} else if (!student_id.equals(other.student_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StudentsDTO [student_id=" + student_id + ", studentName=" + studentName + ", studentAddr=" + studentAddr
				+ ", studentAge=" + studentAge + ", studentQulaification=" + studentQulaification + ", studentPercent="
				+ studentPercent + ", studentYearPassword=" + studentYearPassword +  "]";
	}	
}
