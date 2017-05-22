package com.eagle.entity.po.admin;

import com.huisa.common.reflection.annotations.*;

/*  */
@huisadb_alias("admin_function")
public class AdminFunction {
	@huisadb_ignore
	private java.lang.Integer id;//remark:id，自增;length:10; not null,default:null
	@huisadb_alias("function_name")
	private java.lang.String functionName;//remark:功能名称;length:16; not null,default:null
	private java.lang.String permission;//remark:权限标识;length:200
	@huisadb_alias("create_time")
	private java.util.Date createTime;//remark:创建时间;length:19
	@huisadb_ignore
	@huisadb_alias("update_time")
	private java.util.Date updateTime;//remark:更新时间;length:19; not null,default:CURRENT_TIMESTAMP

	public AdminFunction() {
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.Integer getId() {
		return id;
	}

	public void setFunctionName(java.lang.String functionName) {
		this.functionName = functionName;
	}

	public java.lang.String getFunctionName() {
		return functionName;
	}

	public void setPermission(java.lang.String permission) {
		this.permission = permission;
	}

	public java.lang.String getPermission() {
		return permission;
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