package com.eagle.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eagle.common.AppContext;
import com.eagle.service.common.FileService;
import com.eagle.web.common.AjaxData;
import com.eagle.web.common.MVCUtil;
import com.google.gson.JsonObject;

@Controller
@RequestMapping(value = "/common")
public class CommonController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);
    
	@RequestMapping(value = "/fileupload", method = RequestMethod.POST)
	@ResponseBody
	public String fileupload(HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		AjaxData ajaxdata = null;
		try {
			MultipartFile mf = multipartRequest.getFile("file");
			String fileuri = FileService.saveFile(mf, mf.getOriginalFilename());
			JsonObject data = new JsonObject();
			data.addProperty("fileuri", fileuri);
			data.addProperty("fileurl", AppContext.getFileUrl(fileuri));
			ajaxdata = new AjaxData(true, data, null);
		} catch (Exception e) {
			ajaxdata = new AjaxData(false, null, "文件上传失败！" + e.getMessage());
			LOGGER.error(e.getMessage());
		}

		MVCUtil.ajaxJson(ajaxdata);
		return null;
	}
}
