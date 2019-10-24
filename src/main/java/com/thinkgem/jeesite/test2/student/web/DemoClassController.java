/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test2.student.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.test2.student.entity.DemoClass;
import com.thinkgem.jeesite.test2.student.service.DemoClassService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 班级信息管理Controller
 * @author www
 * @version 2019-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/test2/demoClass")
public class DemoClassController extends BaseController {

	@Autowired
	private DemoClassService demoClassService;
	
	@ModelAttribute
	public DemoClass get(@RequestParam(required=false) String id) {
		DemoClass entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = demoClassService.get(id);
		}
		if (entity == null){
			entity = new DemoClass();
		}
		return entity;
	}
	
	@RequiresPermissions("test2:demoClass:view")
	@RequestMapping(value = {"list", ""})
	public String list(DemoClass demoClass, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DemoClass> page = demoClassService.findPage(new Page<DemoClass>(request, response), demoClass); 
		model.addAttribute("page", page);
		return "jeesite/test2/student/demoClassList";
	}

	@RequiresPermissions("test2:demoClass:view")
	@RequestMapping(value = "form")
	public String form(DemoClass demoClass, Model model) {
		model.addAttribute("demoClass", demoClass);
		return "jeesite/test2/student/demoClassForm";
	}

	@RequiresPermissions("test2:demoClass:edit")
	@RequestMapping(value = "save")
	public String save(DemoClass demoClass, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, demoClass)){
			return form(demoClass, model);
		}
		demoClassService.save(demoClass);
		addMessage(redirectAttributes, "保存班级信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/test2/demoClass/?repage";
	}
	
	@RequiresPermissions("test2:demoClass:edit")
	@RequestMapping(value = "delete")
	public String delete(DemoClass demoClass, RedirectAttributes redirectAttributes) {
		demoClassService.delete(demoClass);
		addMessage(redirectAttributes, "删除班级信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/test2/demoClass/?repage";
	}

	@ResponseBody
	@RequestMapping(value = "treeData")
	public  List<Map<String,Object>> treeData(){
		List<Map<String,Object>> mapList = Lists.newArrayList();
		DemoClass demoClass = new DemoClass();
		List<DemoClass> list=demoClassService.findList(demoClass);
		for (int i=0;i<list.size();i++){
			DemoClass d=list.get(i);
			Map<String,Object> map=Maps.newHashMap();
			map.put("id",d.getClassId());
			map.put("name",d.getClassName());
			mapList.add(map);
		}
		return mapList;
	}
    @ResponseBody
    @RequestMapping(value = "treeData2")
    public  List<Map<String,Object>> treeData2(){
        List<Map<String,Object>> mapList = Lists.newArrayList();
        DemoClass demoClass = new DemoClass();
        List<DemoClass> list=demoClassService.findList(demoClass);
        for (int i=0;i<list.size();i++){
            DemoClass d=list.get(i);
            Map<String,Object> map=Maps.newHashMap();
            map.put("id",d.getClassId());
            map.put("name",d.getClassName());
            mapList.add(map);
        }
        return mapList;
    }
}