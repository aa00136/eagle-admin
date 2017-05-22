package com.eagle.web.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eagle.entity.po.admin.PortalCustomer;
import com.eagle.entity.vo.QueryCustomerVo;
import com.eagle.service.user.PortalCustomerService;
import com.eagle.web.common.MVCUtil;
import com.huisa.common.database.model.PageBean;
import com.huisa.common.exception.ServiceException;

@Controller
public class ManageCustomerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ManageCustomerController.class);
    private static final String CUSTOMER_SEARCH = "CUSTOMER_SEARCH";
    private static final int PAGESIZE = 15;
    @Autowired
	private PortalCustomerService portalCustomerService;
	    
	@RequiresPermissions("portal:customer:manage")
	@RequestMapping(value = "portal_customer/show_customer_list",method = RequestMethod.GET)
	public String showCustomerList(Model model) throws ServiceException{
		
		int pageid = MVCUtil.getIntParam("pageid");
		QueryCustomerVo queryCustomerVo = null;
		if (pageid <= 0) {
            pageid = 1;
            MVCUtil.removeSessionAttribute(CUSTOMER_SEARCH);
        } else {
            Object obj = MVCUtil
                    .getSessionAttribute(CUSTOMER_SEARCH);
            if (obj != null) {
            	queryCustomerVo = (QueryCustomerVo) obj;
            }
        }
        PageBean pageBean = new PageBean(pageid, PAGESIZE);
        List<PortalCustomer> portalCustomers = portalCustomerService.queryPortalCustomerListByPage(queryCustomerVo, pageBean);
        model.addAttribute("customerVoList", portalCustomers);
        model.addAttribute("queryCustomerVo", queryCustomerVo);
        model.addAttribute("pagebean", pageBean);
        model.addAttribute("pageuri", "/portal_customer/show_customer_list?");
        return "portal_customer/show_customer_list";
		
	}
	
	
	@RequiresPermissions("portal:customer:manage")
	@RequestMapping(value = "portal_customer/search", method = RequestMethod.POST)
    public String searchUser(QueryCustomerVo queryCustomerVo,Model model) throws ServiceException {
	    int pageid = 0;
//	    LOGGER.debug(queryCustomerVo.getEmail());
//	    LOGGER.debug(queryCustomerVo.getPhoneNum());
//	    LOGGER.debug(queryCustomerVo.getUsername());
        if (queryCustomerVo != null) {
            MVCUtil.setSessionAttribute(CUSTOMER_SEARCH, queryCustomerVo);
            pageid = 1;
        }
        return "redirect:/portal_customer/show_customer_list?pageid=" + pageid;
    }
}
