/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test2.student.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.test2.student.entity.DemoStuScore;
import com.thinkgem.jeesite.test2.student.service.DemoStuScoreService;

/**
 * 学生成绩Controller
 * @author wuchunyuan
 * @version 2019-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/test2/demoStuScore")
public class DemoStuScoreController extends BaseController {

	@Autowired
	private DemoStuScoreService demoStuScoreService;
	
	@ModelAttribute
	public DemoStuScore get(@RequestParam(required=false) String id) {
		DemoStuScore entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = demoStuScoreService.get(id);
		}
		if (entity == null){
			entity = new DemoStuScore();
		}
		return entity;
	}
	
	@RequiresPermissions("test2:demoStuScore:view")
	@RequestMapping(value = {"list", ""})
	public String list(DemoStuScore demoStuScore, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DemoStuScore> page = demoStuScoreService.findPage(new Page<DemoStuScore>(request, response), demoStuScore); 
		model.addAttribute("page", page);
		return "jeesite/test2/student/demoStuScoreList";
	}

	@RequiresPermissions("test2:demoStuScore:view")
	@RequestMapping(value = "form")
	public String form(DemoStuScore demoStuScore, Model model) {
		model.addAttribute("demoStuScore", demoStuScore);
		return "jeesite/test2/student/demoStuScoreForm";
	}

	@RequiresPermissions("test2:demoStuScore:edit")
	@RequestMapping(value = "save")
	public String save(DemoStuScore demoStuScore, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, demoStuScore)){
			return form(demoStuScore, model);
		}
		demoStuScoreService.save(demoStuScore);
		addMessage(redirectAttributes, "保存学生成绩管理成功");
		return "redirect:"+Global.getAdminPath()+"/test2/demoStuScore/?repage";
	}
	
	@RequiresPermissions("test2:demoStuScore:edit")
	@RequestMapping(value = "delete")
	public String delete(DemoStuScore demoStuScore, RedirectAttributes redirectAttributes) {
		demoStuScoreService.delete(demoStuScore);
		addMessage(redirectAttributes, "删除学生成绩管理成功");
		return "redirect:"+Global.getAdminPath()+"/test2/demoStuScore/?repage";
	}

}