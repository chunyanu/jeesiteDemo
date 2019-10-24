/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test2.student.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.test2.student.entity.DemoStuInfo;

/**
 * 班级信息管理DAO接口
 * @author www
 * @version 2019-10-23
 */
@MyBatisDao
public interface DemoStuInfoDao extends CrudDao<DemoStuInfo> {
	
}