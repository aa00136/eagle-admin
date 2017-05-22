package com.eagle.dao.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.eagle.entity.po.admin.PortalCustomer;
import com.huisa.common.database.BaseDao;
import com.huisa.common.database.model.PageBean;
import com.huisa.common.exception.ServiceException;

@Repository
public class PortalCustomerDao  extends BaseDao{
	
    public List<PortalCustomer> queryCustomersByPage(PageBean pageBean, String sql,
            List<Object> params) throws ServiceException {
        return listByPage(pageBean, sql, PortalCustomer.class, params);
    }
}
