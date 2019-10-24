/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test2.student.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 班级信息管理Entity
 * @author www
 * @version 2019-10-23
 */
public class DemoStuInfo extends DataEntity<DemoStuInfo> {
	
	private static final long serialVersionUID = 1L;
	private String stuName;		// stu_name
	private String stuId;		// stu_id
	private String stuClassname;		// stu_classname
	private String stuSex;		// stu_sex
	private String stuAge;		// stu_age
	private DemoClass stuClassid;		// stu_classid 父类
	
	public DemoStuInfo() {
		super();
	}

	public DemoStuInfo(String id){
		super(id);
	}

	public DemoStuInfo(DemoClass stuClassid){
		this.stuClassid = stuClassid;
	}

	@Length(min=1, max=64, message="stu_name长度必须介于 1 和 64 之间")
	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	
	@Length(min=1, max=64, message="stu_id长度必须介于 1 和 64 之间")
	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	
	@Length(min=1, max=64, message="stu_classname长度必须介于 1 和 64 之间")
	public String getStuClassname() {
		return stuClassname;
	}

	public void setStuClassname(String stuClassname) {
		this.stuClassname = stuClassname;
	}
	
	@Length(min=1, max=64, message="stu_sex长度必须介于 1 和 64 之间")
	public String getStuSex() {
		return stuSex;
	}

	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}
	
	@Length(min=1, max=64, message="stu_age长度必须介于 1 和 64 之间")
	public String getStuAge() {
		return stuAge;
	}

	public void setStuAge(String stuAge) {
		this.stuAge = stuAge;
	}
	
	@Length(min=1, max=64, message="stu_classid长度必须介于 1 和 64 之间")
	public DemoClass getStuClassid() {
		return stuClassid;
	}

	public void setStuClassid(DemoClass stuClassid) {
		this.stuClassid = stuClassid;
	}
	
}