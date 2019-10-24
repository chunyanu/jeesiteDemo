/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test2.student.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 班级信息管理Entity
 * @author www
 * @version 2019-10-23
 */
public class DemoClass extends DataEntity<DemoClass> {
	
	private static final long serialVersionUID = 1L;
	private String className;		// 班级名称
	private String classId;		// 班级号
	private List<DemoStuInfo> demoStuInfoList = Lists.newArrayList();		// 子表列表
	
	public DemoClass() {
		super();
	}

	public DemoClass(String id){
		super(id);
	}

	@Length(min=1, max=64, message="班级名称长度必须介于 1 和 64 之间")
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	@Length(min=1, max=64, message="班级号长度必须介于 1 和 64 之间")
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}
	
	public List<DemoStuInfo> getDemoStuInfoList() {
		return demoStuInfoList;
	}

	public void setDemoStuInfoList(List<DemoStuInfo> demoStuInfoList) {
		this.demoStuInfoList = demoStuInfoList;
	}
}