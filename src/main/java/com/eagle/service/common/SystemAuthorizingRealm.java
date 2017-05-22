package com.eagle.service.common;

import java.util.Collection;
import java.util.List;

import com.eagle.dao.user.AdminUserDao;
import com.eagle.entity.po.admin.AdminRole;
import com.eagle.entity.po.admin.AdminlUser;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eagle.entity.po.admin.AdminFunction;
import com.huisa.common.exception.ServiceException;

@Service
// @DependsOn({"userDao","roleDao","menuDao"})
public class SystemAuthorizingRealm extends AuthorizingRealm {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private AdminUserDao portalUserDao;

	/**
	 * 认证回调函数, 登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		// 校验用户名密码
		AdminlUser portalUser = null;
		try {
		    portalUser = portalUserDao.getUserByAccount(token.getUsername());
		} catch (ServiceException e) {
			logger.error(
					"SystemAuthorizingRealm.doGetAuthenticationInfo faild!", e);
		}
		if (portalUser != null) {
			return new SimpleAuthenticationInfo(portalUser,
			        portalUser.getPasswd(), getName());
		} else {
			return null;
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
	    AdminlUser portalUser = (AdminlUser) getAvailablePrincipal(principals);
		if (portalUser != null) {

			try {
				SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
				List<AdminRole> PortalUserRoles = portalUserDao
						.listAdminUserRoles(portalUser.getId());
				if (PortalUserRoles != null && PortalUserRoles.size() > 0) {
					for (AdminRole portalUserRole : PortalUserRoles) {
						info.addRole(portalUserRole.getRoleCode());
					}
				}
				List<AdminFunction> portalUserFunctions = portalUserDao
						.listAdminUserFunctions(portalUser.getId());
				if (portalUserFunctions != null
						&& portalUserFunctions.size() > 0) {
					for (AdminFunction portalUserFunction : portalUserFunctions) {
						info.addStringPermission(portalUserFunction
								.getPermission());
					}
				}
				return info;
			} catch (ServiceException e) {
				logger.error(
						"SystemAuthorizingRealm.doGetAuthorizationInfo faild!",
						e);
			}
			return null;
		} else {
			return null;
		}
	}

	@Override
	protected void checkPermission(Permission permission, AuthorizationInfo info) {
		authorizationValidate(permission);
		super.checkPermission(permission, info);
	}

	@Override
	protected boolean[] isPermitted(List<Permission> permissions,
			AuthorizationInfo info) {
		if (permissions != null && !permissions.isEmpty()) {
			for (Permission permission : permissions) {
				authorizationValidate(permission);
			}
		}
		return super.isPermitted(permissions, info);
	}

	@Override
	public boolean isPermitted(PrincipalCollection principals,
			Permission permission) {
		authorizationValidate(permission);
		return super.isPermitted(principals, permission);
	}

	@Override
	protected boolean isPermittedAll(Collection<Permission> permissions,
			AuthorizationInfo info) {
		if (permissions != null && !permissions.isEmpty()) {
			for (Permission permission : permissions) {
				authorizationValidate(permission);
			}
		}
		return super.isPermittedAll(permissions, info);
	}

	/**
	 * 授权验证方法
	 * 
	 * @param permission
	 */
	private void authorizationValidate(Permission permission) {
		// 模块授权预留接口
	}

}
