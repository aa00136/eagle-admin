package com.lgh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huisa.common.database.BaseDao;
import com.huisa.common.exception.ServiceException;

public class Test {
	private static Logger logger = LoggerFactory.getLogger(Test.class);

	public static void main(String[] args) throws Exception {
		// 生成javabean
		try {
			new BaseDao()
					.generateJavaBean(
							"portal_user_role",
							"E:/LGH/mintour-portal/src/main/java/com/mintour/portal/entity/po",
							"com.mintour.portal.entity.po");
		} catch (ServiceException e) {
		}
	}
}
