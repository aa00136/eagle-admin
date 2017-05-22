package com.eagle.entity.po.admin;

import com.huisa.common.reflection.annotations.*;

/*  */
@huisadb_alias("admin_role_function")
public class AdminlRoleFunction {
	@huisadb_alias("role_id")
	private java.lang.Integer roleId;//remark:角色id;length:10; not null,default:null
	@huisadb_alias("function_id")
	private java.lang.Integer functionId;//remark:功能权限id;length:10; not null,default:null

	public AdminlRoleFunction() {
	}

	public void setRoleId(java.lang.Integer roleId) {
		this.roleId = roleId;
	}

	public java.lang.Integer getRoleId() {
		return roleId;
	}

	public void setFunctionId(java.lang.Integer functionId) {
		this.functionId = functionId;
	}

	public java.lang.Integer getFunctionId() {
		return functionId;
	}
}