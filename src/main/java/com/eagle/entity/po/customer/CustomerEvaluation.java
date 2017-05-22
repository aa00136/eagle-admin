package com.eagle.entity.po.customer;

import java.util.*;
import com.huisa.common.reflection.annotations.*;

/*  */
@huisadb_alias("customer_evaluation")
public class CustomerEvaluation {
	@huisadb_ignore
	private java.lang.Integer id;//remark:id，自增;length:10; not null,default:null
	@huisadb_alias("customer_id")
	private java.lang.Integer customerId;//remark:客户id;length:10; not null,default:null
	@huisadb_alias("guide_id")
	private java.lang.Integer guideId;//remark:导购员id;length:10; not null,default:null
	@huisadb_alias("order_id")
	private java.lang.Integer orderId;//remark:订单id;length:10; not null,default:null
	private java.lang.Integer score;//remark:评分；值域【1-5】;length:10; not null,default:null
	@huisadb_alias("create_time")
	private java.util.Date createTime;//remark:创建时间;length:19
	@huisadb_ignore
	@huisadb_alias("update_time")
	private java.util.Date updateTime;//remark:更新时间;length:19; not null,default:CURRENT_TIMESTAMP

	public CustomerEvaluation() {
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

	public void setGuideId(java.lang.Integer guideId) {
		this.guideId = guideId;
	}

	public java.lang.Integer getGuideId() {
		return guideId;
	}

	public void setOrderId(java.lang.Integer orderId) {
		this.orderId = orderId;
	}

	public java.lang.Integer getOrderId() {
		return orderId;
	}

	public void setScore(java.lang.Integer score) {
		this.score = score;
	}

	public java.lang.Integer getScore() {
		return score;
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