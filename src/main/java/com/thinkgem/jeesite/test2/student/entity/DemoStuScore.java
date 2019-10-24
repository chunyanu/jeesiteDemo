/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test2.student.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 学生成绩Entity
 * @author wuchunyuan
 * @version 2019-10-23
 */
public class DemoStuScore extends DataEntity<DemoStuScore> {
	
	private static final long serialVersionUID = 1L;
	private String stuId;		// 学生学号
	private String stuScore;		// 分数
	private String stuName;		// 学生姓名
	private String classId;		// 班级号
	
	public DemoStuScore() {
		super();
	}

	public DemoStuScore(String id){
		super(id);
	}

	@Length(min=1, max=64, message="学生学号长度必须介于 1 和 64 之间")
	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	
	@Length(min=0, max=64, message="分数长度必须介于 0 和 64 之间")
	public String getStuScore() {
		return stuScore;
	}

	public void setStuScore(String stuScore) {
		this.stuScore = stuScore;
	}
	
	@Length(min=1, max=64, message="学生姓名长度必须介于 1 和 64 之间")
	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	
	@Length(min=1, max=64, message="班级号长度必须介于 1 和 64 之间")
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}
	
}