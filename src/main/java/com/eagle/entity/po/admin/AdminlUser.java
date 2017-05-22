package com.eagle.entity.po.admin;

import java.io.Serializable;

import com.huisa.common.reflection.annotations.huisadb_alias;
import com.huisa.common.reflection.annotations.huisadb_ignore;

/*  */
@huisadb_alias("admin_user")
public class AdminlUser implements Serializable{
	@huisadb_ignore
	private java.lang.Integer id;//remark:id，自增;length:10; not null,default:null
	private java.lang.String account;//remark:账号;length:16; not null,default:null
	private java.lang.String passwd;//remark:密码，MD5加密;length:32; not null,default:null
	@huisadb_alias("user_name")
	private java.lang.String userName;//remark:姓名;length:16; not null,default:null
	@huisadb_alias("create_time")
	private java.util.Date createTime;//remark:创建时间;length:19
	@huisadb_ignore
	@huisadb_alias("update_time")
	private java.util.Date updateTime;//remark:更新时间;length:19; not null,default:CURRENT_TIMESTAMP

	public AdminlUser() {
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.Integer getId() {
		return id;
	}

	public void setAccount(java.lang.String account) {
		this.account = account;
	}

	public java.lang.String getAccount() {
		return account;
	}

	public void setPasswd(java.lang.String passwd) {
		this.passwd = passwd;
	}

	public java.lang.String getPasswd() {
		return passwd;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getUserName() {
		return userName;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}
}