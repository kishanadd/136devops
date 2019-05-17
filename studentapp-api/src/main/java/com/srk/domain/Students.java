package com.srk.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the students database table.
 * 
 */
@Entity
@Table(name="students")
public class Students implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer student_id;

	@Column(name = "student_name")
	private String studentName;
	
	@Column(name = "student_addr")
	private String studentAddr;
	
	@Column(name = "student_age")
	private String studentAge;
	
	@Column(name = "student_qual")
	private String studentQulaification;
	
	@Column(name = "student_percent")
	private String studentPercent;
	
	@Column(name = "student_year_passed")
	private String studentYearPassword;
	
    public Students() {}

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}    
}