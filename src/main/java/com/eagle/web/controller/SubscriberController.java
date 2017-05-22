package com.eagle.web.controller;

import com.eagle.entity.po.Subscriber;
import com.eagle.entity.po.admin.AdminlUser;
import com.eagle.entity.vo.QuerySubscriberVo;
import com.eagle.entity.vo.QueryUserVo;
import com.eagle.service.subscriber.SubscriberService;
import com.eagle.service.user.AdminUserService;
import com.eagle.web.common.MVCUtil;
import com.huisa.common.database.model.PageBean;
import com.huisa.common.exception.ServiceException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class SubscriberController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriberController.class);
    private static final String SUBCRIBER_SEARCH = "SUBCRIBER_SEARCH";
    private static final int PAGESIZE = 15;
    
	@Autowired
	private SubscriberService subscriberService;

	/**
	 * 获取订阅者列表
	 */
	@RequiresPermissions("subscriber:manage")
	@RequestMapping(value = "subscriber/show_subscriber_list",method = RequestMethod.GET)
    public String showUserList(Model model) throws ServiceException {
        int pageid = MVCUtil.getIntParam("pageid");
        QuerySubscriberVo querySubscriberVo = null;
        if (pageid <= 0) {
            pageid = 1;
            MVCUtil.removeSessionAttribute(SUBCRIBER_SEARCH);
        } else {
            Object obj = MVCUtil
                    .getSessionAttribute(SUBCRIBER_SEARCH);
            if (obj != null) {
                querySubscriberVo = (QuerySubscriberVo) obj;
            }
        }
        PageBean pagebean = new PageBean(pageid, PAGESIZE);
        List<Subscriber> subscriberList = subscriberService
                .querySubscriberListByPage(querySubscriberVo, pagebean);
        model.addAttribute("subscriberVoList", subscriberList);
        model.addAttribute("querySubscriberVo", querySubscriberVo);
        model.addAttribute("pagebean", pagebean);
        model.addAttribute("pageuri", "/subscriber/show_subscriber_list?");
        return "subscriber/show_subscriber_list";
    }
}
