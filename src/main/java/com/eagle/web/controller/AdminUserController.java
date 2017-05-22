package com.eagle.web.controller;

import java.util.Date;
import java.util.List;

import com.eagle.entity.po.admin.AdminlUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eagle.entity.vo.QueryUserVo;
import com.eagle.service.user.AdminUserService;
import com.eagle.web.common.AjaxData;
import com.eagle.web.common.MVCUtil;
import com.huisa.common.database.model.PageBean;
import com.huisa.common.exception.ServiceException;

@Controller
public class AdminUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminUserController.class);
    private static final String USER_SEARCH = "USER_SEARCH";
    private static final int PAGESIZE = 15;
    
	@Autowired
	private AdminUserService adminUserService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
//		PaymentUser paymentUser = (PaymentUser) SecurityUtils.getSubject()
//				.getPrincipal();
//		if (paymentUser != null) {
//			return "redirect:/index";
//		}
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String check(Model model) throws ServiceException {
		String uname = MVCUtil.getParam("uname");
		String passwd = MVCUtil.getParam("passwd");
		if (StringUtils.isBlank(uname) || StringUtils.isBlank(passwd)) {
			model.addAttribute("msg", "用户名或密码不能为空!");
			return "login";
		}
		passwd = DigestUtils.md5Hex(passwd);

		UsernamePasswordToken token = new UsernamePasswordToken(uname, passwd);
		token.setRememberMe(true);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return "redirect:/index";
		} catch (UnknownAccountException e) {
			model.addAttribute("msg", "用户名或密码错误");
			return "login";
		} catch (IncorrectCredentialsException e) {
			model.addAttribute("msg", "用户名或密码错误");
			return "login";
		}
	}

	@RequestMapping(value = "/logout")
	public String logout(Model model) {
		SecurityUtils.getSubject().logout();
		return "redirect:/login";
	}

	@RequestMapping(value = "/index")
	public String index(Model model) {
		AdminlUser adminUser = (AdminlUser) SecurityUtils.getSubject()
				.getPrincipal();
		model.addAttribute("loginInfo", adminUser);
		return "index";
	}

	@RequestMapping(value = "/home")
	public String home(Model model) {
		return "home";
	}
	
	/**
	 * 获取用户列表
	 */
	@RequiresPermissions("admin:user:manage")
	@RequestMapping(value = "admin_user/show_user_list",method = RequestMethod.GET)
    public String showUserList(Model model) throws ServiceException {
        int pageid = MVCUtil.getIntParam("pageid");
        QueryUserVo queryUserVo = null;
        if (pageid <= 0) {
            pageid = 1;
            MVCUtil.removeSessionAttribute(USER_SEARCH);
        } else {
            Object obj = MVCUtil
                    .getSessionAttribute(USER_SEARCH);
            if (obj != null) {
                queryUserVo = (QueryUserVo) obj;
            }
        }
        PageBean pagebean = new PageBean(pageid, PAGESIZE);
        List<AdminlUser> admintUserList = adminUserService
                .queryAdminlUserListByPage(queryUserVo, pagebean);
        List<QueryUserVo> userVoList=adminUserService
                .getQueryUserVoList(admintUserList);
        model.addAttribute("userVoList", userVoList);
        model.addAttribute("queryUserVo", queryUserVo);
        model.addAttribute("pagebean", pagebean);
        model.addAttribute("pageuri", "/admin_user/show_user_list?");
        return "admin_user/show_user_list";
    }
	
	/**
	 * 查询用户
	 */
	@RequiresPermissions("admin:user:manage")
	@RequestMapping(value = "admin_user/search", method = RequestMethod.POST)
    public String searchUser(QueryUserVo queryUserVo,Model model) throws ServiceException {
	    int pageid = 0;
        if (queryUserVo != null) {
            MVCUtil.setSessionAttribute(USER_SEARCH, queryUserVo);
            pageid = 1;
        }
        return "redirect:/admin_user/show_user_list?pageid=" + pageid;
    }
	@RequiresPermissions("admin:user:manage")
	@RequestMapping(value = "admin_user/delete", method = RequestMethod.GET)
    public String deletePaymentUser(Model model) throws ServiceException {
       int id = MVCUtil.getIntParam("id");
       if(id>0)
           adminUserService.deleteAdminUser(id);
       return "redirect:/admin_user/show_user_list";
    }
	
	/**
	 * 添加新用户
	 */
	@RequiresPermissions("admin:user:manage")
    @RequestMapping(value = "admin_user/addAdminUser", method = RequestMethod.POST)
	@ResponseBody
    public void addAdminUser(AdminlUser adminUser, Model model){
	   AjaxData ajaxdata;
	   String msg = null;
	   String rePasswd=MVCUtil.getParam("rePasswd");
	   String role=MVCUtil.getParam("role");
      
       if (StringUtils.isBlank(adminUser.getAccount())) {
           msg = "账号不能为空！";
       } else if (StringUtils.isBlank(adminUser.getUserName())) {
           msg = "用户姓名不能为空！";
       } else if(StringUtils.isBlank(role)){
           msg = "请选择用户的角色！";
       } else if (StringUtils.isBlank(adminUser.getPasswd())) {
           msg = "密码不能为空！";       
       } else if (StringUtils.isBlank(rePasswd)) {
           msg = "新密码没有重复输入！";
       } else if (!adminUser.getPasswd().equals(rePasswd)) {
           msg = "两次输入的密码不相同！";
       }

       if (StringUtils.isNotBlank(msg)) {
           ajaxdata = new AjaxData(false, null, msg);
           MVCUtil.ajaxJson(ajaxdata);
           return;
       }
       try {
           adminUser.setCreateTime(new Date());
           adminUserService.addAdminUser(adminUser,role);
           ajaxdata = new AjaxData(true, null, null);
       } catch (ServiceException e){
           ajaxdata = new AjaxData(false, null, "该帐号已经存在！");
           LOGGER.error(e.getMessage());
       }
       MVCUtil.ajaxJson(ajaxdata);
    }
}
