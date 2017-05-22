package com.eagle.dao.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.eagle.entity.po.common.Sysconfig;
import com.huisa.common.database.BaseDao;
import com.huisa.common.exception.ServiceException;

@Repository
public class SysconfigDao extends BaseDao {
	public List<Sysconfig> listSysconfig(String servercode)
			throws ServiceException {
		String sql = "SELECT ckey,cvalue FROM sysconfig WHERE servercode='com'";
		List<Object> params = new ArrayList<Object>();
		if (StringUtils.isNotBlank(servercode)) {
			sql = sql + " OR servercode=?";
			params.add(servercode);
		}
		List<Sysconfig> sysconfigs = list(sql, Sysconfig.class, params);
		return sysconfigs;
	}
	
	public Sysconfig getSysconfigByCkey(String ckey) throws ServiceException
	{
	    return get("SELECT cvalue FROM sysconfig WHERE servercode='com' AND ckey=?",Sysconfig.class,ckey);
	}
	
}
