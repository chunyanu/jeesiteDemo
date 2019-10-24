/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test2.student.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.test2.student.entity.DemoStuScore;
import com.thinkgem.jeesite.test2.student.dao.DemoStuScoreDao;

/**
 * 学生成绩Service
 * @author wuchunyuan
 * @version 2019-10-23
 */
@Service
@Transactional(readOnly = true)
public class DemoStuScoreService extends CrudService<DemoStuScoreDao, DemoStuScore> {

	public DemoStuScore get(String id) {
		return super.get(id);
	}
	
	public List<DemoStuScore> findList(DemoStuScore demoStuScore) {
		return super.findList(demoStuScore);
	}
	
	public Page<DemoStuScore> findPage(Page<DemoStuScore> page, DemoStuScore demoStuScore) {
		return super.findPage(page, demoStuScore);
	}
	
	@Transactional(readOnly = false)
	public void save(DemoStuScore demoStuScore) {
		super.save(demoStuScore);
	}
	
	@Transactional(readOnly = false)
	public void delete(DemoStuScore demoStuScore) {
		super.delete(demoStuScore);
	}
	
}