package com.eagle.entity.po.admin;

import com.huisa.common.reflection.annotations.*;

/*  */
@huisadb_alias("admin_user_role")
public class AdminUserRole {
	@huisadb_alias("user_id")
	private java.lang.Integer userId;//remark:用户id;length:10; not null,default:null
	@huisadb_alias("role_id")
	private java.lang.Integer roleId;//remark:角色id;length:10; not null,default:null
	@huisadb_alias("create_time")
	private java.util.Date createTime;//remark:创建时间;length:19
	@huisadb_ignore
	@huisadb_alias("update_time")
	private java.util.Date updateTime;//remark:更新时间;length:19; not null,default:CURRENT_TIMESTAMP

	public AdminUserRole() {
	}

	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}

	public java.lang.Integer getUserId() {
		return userId;
	}

	public void setRoleId(java.lang.Integer roleId) {
		this.roleId = roleId;
	}

	public java.lang.Integer getRoleId() {
		return roleId;
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