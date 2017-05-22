package com.eagle.service.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.eagle.dao.user.AdminUserDao;
import com.eagle.entity.po.admin.AdminRole;
import com.eagle.entity.po.admin.AdminlUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eagle.entity.po.admin.AdminUserRole;
import com.eagle.entity.vo.QueryUserVo;
import com.huisa.common.database.model.PageBean;
import com.huisa.common.exception.ServiceException;

@Service
public class AdminUserService {
    @Autowired
    private AdminUserDao adminUserDao;
    
    /**
     * 根据account,user_name,role来分页查询用户
     */
    public List<AdminlUser> queryAdminlUserListByPage(QueryUserVo queryUserVo,
                                                      PageBean pageBean) throws ServiceException {
        StringBuffer querySqlBuffer = new StringBuffer();
        List<Object> queryParams = new ArrayList<Object>();

        querySqlBuffer.append("SELECT au.* FROM admin_user AS au JOIN admin_user_role AS aur ON aur.user_id=au.id WHERE 1=1");
        if (queryUserVo != null) {
            if (StringUtils.isNotBlank(queryUserVo.getUser_name())) {
                querySqlBuffer.append(" AND user_name LIKE ?");
                queryParams.add("%" + queryUserVo.getUser_name() + "%");
            }
            if (StringUtils.isNotBlank(queryUserVo.getAccount())) {
                querySqlBuffer.append(" AND account LIKE ?");
                queryParams.add("%" + queryUserVo.getAccount() + "%");
            }
            if (StringUtils.isNotBlank(queryUserVo.getRole())) {
                querySqlBuffer.append(" AND role_id=?");
                queryParams.add(queryUserVo.getRole());
            }
        }
        List<AdminlUser> adminUserList = adminUserDao.queryUserByPage(pageBean, querySqlBuffer.toString(), queryParams);

        return adminUserList;
    }

    /**
     * 获取用于页面显示的QueryUserVo列表
     */
    public List<QueryUserVo> getQueryUserVoList(
            List<AdminlUser> adminUserList) throws ServiceException {
        List<QueryUserVo> queryUserVoList = new ArrayList<QueryUserVo>();
        for (AdminlUser adminUser : adminUserList) {
            List<AdminRole> roleList = adminUserDao.listAdminUserRoles(adminUser.getId());
            StringBuffer roleStr = new StringBuffer();
            for (int i = 0; i < roleList.size(); i++) {
                AdminRole adminRole = roleList.get(i);
                if (i == roleList.size() - 1)
                    roleStr.append(adminRole.getRoleName());
                else
                    roleStr.append(adminRole.getRoleName() + ",");
            }
            QueryUserVo quv = new QueryUserVo();
            quv.setId(adminUser.getId());
            quv.setAccount(adminUser.getAccount());
            quv.setUser_name(adminUser.getUserName());
            quv.setRole(roleStr.toString());
            queryUserVoList.add(quv);
        }
        return queryUserVoList;
    }

    /**
     * 添加新用户
     */
    public void addAdminUser(AdminlUser adminUser, String role)
            throws ServiceException {
        String passwd = adminUser.getPasswd();
        passwd = DigestUtils.md5Hex(passwd);
        adminUser.setPasswd(passwd);
        int userId = adminUserDao.addAdminUser(adminUser);
        
        AdminUserRole pur = new AdminUserRole();
        pur.setRoleId(Integer.parseInt(role));
        pur.setUserId(userId);
        pur.setCreateTime(new Date());
        adminUserDao.addAdminUserRole(pur);
    }

    /**
     * 删除用户
     */
    public void deleteAdminUser(int id) throws ServiceException {
        adminUserDao.deleteUserById(id);
    }
}
