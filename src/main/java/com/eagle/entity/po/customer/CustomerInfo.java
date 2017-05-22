package com.eagle.entity.po.customer;

import java.util.*;
import com.huisa.common.reflection.annotations.*;

/*  */
@huisadb_alias("customer_info")
public class CustomerInfo {
	@huisadb_ignore
	private java.lang.Integer id;//remark:id，自增;length:10; not null,default:null
	@huisadb_alias("phone_num")
	private java.lang.String phoneNum;//remark:手机号;length:16
	private java.lang.String email;//remark:邮箱;length:40
	private java.lang.String passwd;//remark:密码，MD5加密;length:32; not null,default:null
	@huisadb_alias("user_name")
	private java.lang.String userName;//remark:姓名/昵称;length:24; not null,default:null
	@huisadb_alias("user_portrait")
	private java.lang.String userPortrait;//remark:头像uri，不带域名的文件存储路径;length:120
	private java.lang.Integer sex;//remark:性别；值域【1:男，2：女】;length:10; not null,default:null
	@huisadb_alias("total_score")
	private java.lang.Integer totalScore;//remark:总评分;length:10; not null,default:0
	@huisadb_alias("score_num")
	private java.lang.Integer scoreNum;//remark:评分人数;length:10; not null,default:0
	@huisadb_alias("push_token")
	private java.lang.String pushToken;//remark:推送系统token;length:64
	@huisadb_alias("im_user_id")
	private java.lang.String imUserId;//remark:IM系统的用户id;length:32
	private java.lang.String sessionid;//remark:sessionid;length:32
	@huisadb_alias("last_login_time")
	private java.util.Date lastLoginTime;//remark:最后一次登录时间;length:19
	@huisadb_alias("last_sys_type")
	private java.lang.Integer lastSysType;//remark:最后一次登录的手机系统类型，值域【0:未知，1:ios，2：android】;length:10; not null,default:0
	@huisadb_alias("create_time")
	private java.util.Date createTime;//remark:注册时间;length:19
	@huisadb_ignore
	@huisadb_alias("update_time")
	private java.util.Date updateTime;//remark:更新时间;length:19; not null,default:CURRENT_TIMESTAMP

	public CustomerInfo() {
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.Integer getId() {
		return id;
	}

	public void setPhoneNum(java.lang.String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public java.lang.String getPhoneNum() {
		return phoneNum;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public java.lang.String getEmail() {
		return email;
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

	public void setUserPortrait(java.lang.String userPortrait) {
		this.userPortrait = userPortrait;
	}

	public java.lang.String getUserPortrait() {
		return userPortrait;
	}

	public void setSex(java.lang.Integer sex) {
		this.sex = sex;
	}

	public java.lang.Integer getSex() {
		return sex;
	}

	public void setTotalScore(java.lang.Integer totalScore) {
		this.totalScore = totalScore;
	}

	public java.lang.Integer getTotalScore() {
		return totalScore;
	}

	public void setScoreNum(java.lang.Integer scoreNum) {
		this.scoreNum = scoreNum;
	}

	public java.lang.Integer getScoreNum() {
		return scoreNum;
	}

	public void setPushToken(java.lang.String pushToken) {
		this.pushToken = pushToken;
	}

	public java.lang.String getPushToken() {
		return pushToken;
	}

	public void setImUserId(java.lang.String imUserId) {
		this.imUserId = imUserId;
	}

	public java.lang.String getImUserId() {
		return imUserId;
	}

	public void setSessionid(java.lang.String sessionid) {
		this.sessionid = sessionid;
	}

	public java.lang.String getSessionid() {
		return sessionid;
	}

	public void setLastLoginTime(java.util.Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public java.util.Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastSysType(java.lang.Integer lastSysType) {
		this.lastSysType = lastSysType;
	}

	public java.lang.Integer getLastSysType() {
		return lastSysType;
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