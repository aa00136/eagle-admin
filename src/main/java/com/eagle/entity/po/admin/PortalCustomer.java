package com.eagle.entity.po.admin;

import java.io.Serializable;

import com.huisa.common.reflection.annotations.huisadb_alias;
import com.huisa.common.reflection.annotations.huisadb_ignore;

@huisadb_alias("customer_info")
public class PortalCustomer implements Serializable{

	@huisadb_ignore
	private int id;
	@huisadb_alias("user_name")
	private String username;
	private String email;
	@huisadb_alias("phone_num")
	private String phoneNum;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	
	
}
