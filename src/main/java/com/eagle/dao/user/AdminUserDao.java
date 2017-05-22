package com.eagle.dao.user;

import java.util.List;

import com.eagle.entity.po.admin.AdminlUser;
import org.springframework.stereotype.Repository;

import com.eagle.entity.po.admin.AdminFunction;
import com.eagle.entity.po.admin.AdminRole;
import com.huisa.common.database.BaseDao;
import com.huisa.common.database.model.PageBean;
import com.huisa.common.exception.ServiceException;

@Repository
public class AdminUserDao extends BaseDao {
    public AdminlUser getUserByAccount(String username)
            throws ServiceException {
        return get("SELECT * FROM admin_user WHERE account=?",
                AdminlUser.class, username);
    }

    public List<AdminRole> listAdminUserRoles(Integer id)
            throws ServiceException {
        return list(
                "SELECT pr.* FROM admin_role AS pr JOIN admin_user_role AS pur ON pr.id=pur.role_id AND pur.user_id=?",
                AdminRole.class, id);
    }

    public List<AdminFunction> listAdminUserFunctions(Integer id)
            throws ServiceException {
        return list(
                "SELECT pf.* FROM admin_function AS pf JOIN admin_role_function AS prf ON pf.id=prf.function_id"
                        + " JOIN admin_user_role AS pur ON prf.role_id=pur.role_id AND pur.user_id=?",
                        AdminFunction.class, id);
    }
    
    public List<AdminlUser> queryUserByPage(PageBean pageBean, String sql,
                                            List<Object> params) throws ServiceException {
        return listByPage(pageBean, sql, AdminlUser.class, params);
    }
    
    public void deleteUserById(int id) throws ServiceException {
            delete("DELETE admin_user,admin_user_role from admin_user LEFT JOIN admin_user_role "
                    + "ON admin_user.id=admin_user_role.user_id WHERE admin_user.id=?", id);
    }

    public int addAdminUser(Object object) throws ServiceException {
        return addReturnGeneratedKey(object);
    }
    public void addAdminUserRole(Object object) throws ServiceException {
        add(object);
    }
}
