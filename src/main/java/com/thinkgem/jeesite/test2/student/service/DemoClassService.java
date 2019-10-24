/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test2.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.test2.student.entity.DemoClass;
import com.thinkgem.jeesite.test2.student.dao.DemoClassDao;
import com.thinkgem.jeesite.test2.student.entity.DemoStuInfo;
import com.thinkgem.jeesite.test2.student.dao.DemoStuInfoDao;

/**
 * 班级信息管理Service
 * @author www
 * @version 2019-10-23
 */
@Service
@Transactional(readOnly = true)
public class DemoClassService extends CrudService<DemoClassDao, DemoClass> {

	@Autowired
	private DemoStuInfoDao demoStuInfoDao;
	
	public DemoClass get(String id) {
		DemoClass demoClass = super.get(id);
		demoClass.setDemoStuInfoList(demoStuInfoDao.findList(new DemoStuInfo(demoClass)));
		return demoClass;
	}
	
	public List<DemoClass> findList(DemoClass demoClass) {
		return super.findList(demoClass);
	}
	
	public Page<DemoClass> findPage(Page<DemoClass> page, DemoClass demoClass) {
		return super.findPage(page, demoClass);
	}
	
	@Transactional(readOnly = false)
	public void save(DemoClass demoClass) {
		super.save(demoClass);
		for (DemoStuInfo demoStuInfo : demoClass.getDemoStuInfoList()){
			if (demoStuInfo.getId() == null){
				continue;
			}
			if (DemoStuInfo.DEL_FLAG_NORMAL.equals(demoStuInfo.getDelFlag())){
				if (StringUtils.isBlank(demoStuInfo.getId())){
					demoStuInfo.setStuClassid(demoClass);
					demoStuInfo.preInsert();
					demoStuInfoDao.insert(demoStuInfo);
				}else{
					demoStuInfo.preUpdate();
					demoStuInfoDao.update(demoStuInfo);
				}
			}else{
				demoStuInfoDao.delete(demoStuInfo);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(DemoClass demoClass) {
		super.delete(demoClass);
		demoStuInfoDao.delete(new DemoStuInfo(demoClass));
	}
	
}