package com.eagle.entity.po.customer;

import java.util.*;
import com.huisa.common.reflection.annotations.*;

/*  */
@huisadb_alias("customer_coupon")
public class CustomerCoupon {
	@huisadb_ignore
	private java.lang.Integer id;//remark:id，自增;length:10; not null,default:null
	@huisadb_alias("customer_id")
	private java.lang.Integer customerId;//remark:客户id;length:10; not null,default:null
	@huisadb_alias("coupon_code")
	private java.lang.String couponCode;//remark:优惠券码;length:16; not null,default:null
	@huisadb_alias("coupon_id")
	private java.lang.Integer couponId;//remark:优惠卷id;length:10; not null,default:null
	@huisadb_alias("coupon_name")
	private java.lang.String couponName;//remark:优惠券名称;length:16; not null,default:null
	@huisadb_alias("expiration_date")
	private java.util.Date expirationDate;//remark:有效截止日期;length:10; not null,default:null
	@huisadb_alias("lock_end_time")
	private java.util.Date lockEndTime;//remark:锁定截止时间;length:19
	@huisadb_alias("been_used")
	private java.lang.Integer beenUsed;//remark:是否已被使用；值域【0：否、1：是】;length:10; not null,default:0
	@huisadb_alias("schedule_id")
	private java.lang.Integer scheduleId;//remark:使用的订单id;length:10; not null,default:0
	@huisadb_alias("create_time")
	private java.util.Date createTime;//remark:创建时间;length:19
	@huisadb_ignore
	@huisadb_alias("update_time")
	private java.util.Date updateTime;//remark:更新时间;length:19; not null,default:CURRENT_TIMESTAMP

	public CustomerCoupon() {
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.Integer getId() {
		return id;
	}

	public void setCustomerId(java.lang.Integer customerId) {
		this.customerId = customerId;
	}

	public java.lang.Integer getCustomerId() {
		return customerId;
	}

	public void setCouponCode(java.lang.String couponCode) {
		this.couponCode = couponCode;
	}

	public java.lang.String getCouponCode() {
		return couponCode;
	}

	public void setCouponId(java.lang.Integer couponId) {
		this.couponId = couponId;
	}

	public java.lang.Integer getCouponId() {
		return couponId;
	}

	public void setCouponName(java.lang.String couponName) {
		this.couponName = couponName;
	}

	public java.lang.String getCouponName() {
		return couponName;
	}

	public void setExpirationDate(java.util.Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public java.util.Date getExpirationDate() {
		return expirationDate;
	}

	public void setLockEndTime(java.util.Date lockEndTime) {
		this.lockEndTime = lockEndTime;
	}

	public java.util.Date getLockEndTime() {
		return lockEndTime;
	}

	public void setBeenUsed(java.lang.Integer beenUsed) {
		this.beenUsed = beenUsed;
	}

	public java.lang.Integer getBeenUsed() {
		return beenUsed;
	}

	public void setScheduleId(java.lang.Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public java.lang.Integer getScheduleId() {
		return scheduleId;
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