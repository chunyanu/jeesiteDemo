/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test2.student.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.test2.student.entity.DemoStuInfo;
import com.thinkgem.jeesite.test2.student.dao.DemoStuInfoDao;

/**
 * 学生信息Service
 * @author wuchunyuan
 * @version 2019-10-23
 */
@Service
@Transactional(readOnly = true)
public class DemoStuInfoService extends CrudService<DemoStuInfoDao, DemoStuInfo> {

	public DemoStuInfo get(String id) {
		return super.get(id);
	}
	
	public List<DemoStuInfo> findList(DemoStuInfo demoStuInfo) {
		return super.findList(demoStuInfo);
	}
	
	public Page<DemoStuInfo> findPage(Page<DemoStuInfo> page, DemoStuInfo demoStuInfo) {
		return super.findPage(page, demoStuInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(DemoStuInfo demoStuInfo) {
		super.save(demoStuInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(DemoStuInfo demoStuInfo) {
		super.delete(demoStuInfo);
	}
	
}