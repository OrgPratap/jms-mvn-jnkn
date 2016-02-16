package com.pratap.ex.jmsexample.pojo;

import java.io.Serializable;

public class Emp implements Serializable{
	private String empId;
	private String empName;
	public Emp(String empId, String empName) {
		super();
		this.empId = empId;
		this.empName = empName;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	@Override
	public String toString() {
		return "Emp [empId=" + empId + ", empName=" + empName + "]";
	}

}
