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
import com.thinkgem.jeesite.test2.student.entity.DemoStuInfo;
import com.thinkgem.jeesite.test2.student.service.DemoStuInfoService;

import java.util.List;
import java.util.Map;

/**
 * 学生信息Controller
 * @author wuchunyuan
 * @version 2019-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/test2/demoStuInfo")
public class DemoStuInfoController extends BaseController {

	@Autowired
	private DemoStuInfoService demoStuInfoService;
	
	@ModelAttribute
	public DemoStuInfo get(@RequestParam(required=false) String id) {
		DemoStuInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = demoStuInfoService.get(id);
		}
		if (entity == null){
			entity = new DemoStuInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("test2:demoStuInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(DemoStuInfo demoStuInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DemoStuInfo> page = demoStuInfoService.findPage(new Page<DemoStuInfo>(request, response), demoStuInfo); 
		model.addAttribute("page", page);
		return "jeesite/test2/student/demoStuInfoList";
	}

	@RequiresPermissions("test2:demoStuInfo:view")
	@RequestMapping(value = "form")
	public String form(DemoStuInfo demoStuInfo, Model model) {
		model.addAttribute("demoStuInfo", demoStuInfo);
		return "jeesite/test2/student/demoStuInfoForm";
	}

	@RequiresPermissions("test2:demoStuInfo:edit")
	@RequestMapping(value = "save")
	public String save(DemoStuInfo demoStuInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, demoStuInfo)){
			return form(demoStuInfo, model);
		}
		demoStuInfoService.save(demoStuInfo);
		addMessage(redirectAttributes, "保存学生信息成功");
		return "redirect:"+Global.getAdminPath()+"/test2/demoStuInfo/?repage";
	}
	
	@RequiresPermissions("test2:demoStuInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(DemoStuInfo demoStuInfo, RedirectAttributes redirectAttributes) {
		demoStuInfoService.delete(demoStuInfo);
		addMessage(redirectAttributes, "删除学生信息成功");
		return "redirect:"+Global.getAdminPath()+"/test2/demoStuInfo/?repage";
	}


}