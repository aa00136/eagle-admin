package com.eagle.service.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eagle.dao.user.PortalCustomerDao;
import com.eagle.entity.po.admin.PortalCustomer;
import com.eagle.entity.vo.QueryCustomerVo;
import com.huisa.common.database.model.PageBean;
import com.huisa.common.exception.ServiceException;

@Service
public class PortalCustomerService {
	
    @Autowired
    private PortalCustomerDao portalCustomerDao;
    
    /**
     * 根据username,email,phoneNum分页查询用户
     */
    public List<PortalCustomer> queryPortalCustomerListByPage(QueryCustomerVo queryCustomerVo,
            PageBean pageBean) throws ServiceException {
        StringBuffer querySqlBuffer = new StringBuffer();
        List<Object> queryParams = new ArrayList<Object>();

        querySqlBuffer.append("SELECT cus.* FROM customer_info cus WHERE 1=1");
        if (queryCustomerVo != null) {
            if (StringUtils.isNotBlank(queryCustomerVo.getUsername())) {
                querySqlBuffer.append(" AND user_name LIKE ?");
                queryParams.add("%" + queryCustomerVo.getUsername() + "%");
            }
            if (StringUtils.isNotBlank(queryCustomerVo.getPhoneNum())) {
                querySqlBuffer.append(" AND phone_num LIKE ?");
                queryParams.add("%" +queryCustomerVo.getPhoneNum() + "%");
            }
            if (StringUtils.isNotBlank(queryCustomerVo.getEmail())) {
                querySqlBuffer.append(" AND email LIKE ?");
                queryParams.add("%" +queryCustomerVo.getEmail() + "%");
            }
        }
        List<PortalCustomer> portalCustomerList = portalCustomerDao.queryCustomersByPage(pageBean, querySqlBuffer.toString(), queryParams);

        return portalCustomerList;
    }

    /**
     * 获取用于页面显示的QueryUserVo列表
     */
    /*public List<QueryUserVo> getQueryUserVoList(
            List<AdminlUser> PortalUserList) throws ServiceException {
        List<QueryUserVo> queryUserVoList = new ArrayList<QueryUserVo>();
        for (AdminlUser portalUser : PortalUserList) {
            List<AdminRole> roleList = portalUserDao.listPortalUserRoles(portalUser.getId());
            StringBuffer roleStr = new StringBuffer();
            for (int i = 0; i < roleList.size(); i++) {
                AdminRole portalRole = roleList.get(i);
                if (i == roleList.size() - 1)
                    roleStr.append(portalRole.getRoleName());
                else
                    roleStr.append(portalRole.getRoleName() + ",");
            }
            QueryUserVo quv = new QueryUserVo();
            quv.setId(portalUser.getId());
            quv.setAccount(portalUser.getAccount());
            quv.setUser_name(portalUser.getUserName());
            quv.setRole(roleStr.toString());
            queryUserVoList.add(quv);
        }
        return queryUserVoList;
    }
*/
    /**
     * 添加新用户
     */
    /*public void addPortalUser(AdminlUser AdminlUser, String role)
            throws ServiceException {
        String passwd = AdminlUser.getPasswd();
        passwd = DigestUtils.md5Hex(passwd);
        AdminlUser.setPasswd(passwd);
        int userId = portalUserDao.addPortalUser(AdminlUser);
        
        AdminUserRole pur = new AdminUserRole();
        pur.setRoleId(Integer.parseInt(role));
        pur.setUserId(userId);
        pur.setCreateTime(new Date());
        portalUserDao.addPortalUserRole(pur);
    }*/

    /**
     * 删除用户
     */
   /* public void deletePortalUser(int id) throws ServiceException {
        portalUserDao.deleteUserById(id);
    }*/
}
