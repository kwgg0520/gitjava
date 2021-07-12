package com.kosta.vo;

import java.sql.Date;

public class MemberDTO {
	private int memberno;
	private String id;
	private String password;
	private String name;
	private String email;
	private Date sdate;
	
	public MemberDTO(int memberno, String id, String password, String name, String email, Date sdate) {
		super();
		this.memberno = memberno;
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.sdate = sdate;
	}
	
	
	public MemberDTO(String id, String password, String email) {
		super();
		this.id = id;
		this.password = password;
		this.email = email;
	}


	public MemberDTO(String password, String email) {
		super();
		this.password = password;
		this.email = email;
	}


	public MemberDTO(String id, String password, String name, String email) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
	}


	public MemberDTO() {
		super();
	}


	public int getMemberno() {
		return memberno;
	}

	public void setMemberno(int memberno) {
		this.memberno = memberno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	
	
	
}
