<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>班级信息管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/student/demoClass/">班级信息管理列表</a></li>
		<li class="active"><a href="${ctx}/student/demoClass/form?id=${demoClass.id}">班级信息管理<shiro:hasPermission name="student:demoClass:edit">${not empty demoClass.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="student:demoClass:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="demoClass" action="${ctx}/student/demoClass/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">班级名称：</label>
			<div class="controls">
				<form:input path="className" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">班级号：</label>
			<div class="controls">
				<form:input path="classId" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">学生信息：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>备注信息</th>
								<th>stu_name</th>
								<th>stu_id</th>
								<th>stu_classname</th>
								<th>stu_sex</th>
								<th>stu_age</th>
								<shiro:hasPermission name="student:demoClass:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="demoStuInfoList">
						</tbody>
						<shiro:hasPermission name="student:demoClass:edit"><tfoot>
							<tr><td colspan="8"><a href="javascript:" onclick="addRow('#demoStuInfoList', demoStuInfoRowIdx, demoStuInfoTpl);demoStuInfoRowIdx = demoStuInfoRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="demoStuInfoTpl">//<!--
						<tr id="demoStuInfoList{{idx}}">
							<td class="hide">
								<input id="demoStuInfoList{{idx}}_id" name="demoStuInfoList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="demoStuInfoList{{idx}}_delFlag" name="demoStuInfoList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<textarea id="demoStuInfoList{{idx}}_remarks" name="demoStuInfoList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<td>
								<input id="demoStuInfoList{{idx}}_stuName" name="demoStuInfoList[{{idx}}].stuName" type="text" value="{{row.stuName}}" maxlength="64" class="input-small required"/>
							</td>
							<td>
								<input id="demoStuInfoList{{idx}}_stuId" name="demoStuInfoList[{{idx}}].stuId" type="text" value="{{row.stuId}}" maxlength="64" class="input-small required"/>
							</td>
							<td>
								<input id="demoStuInfoList{{idx}}_stuClassname" name="demoStuInfoList[{{idx}}].stuClassname" type="text" value="{{row.stuClassname}}" maxlength="64" class="input-small required"/>
							</td>
							<td>
								<select id="demoStuInfoList{{idx}}_stuSex" name="demoStuInfoList[{{idx}}].stuSex" data-value="{{row.stuSex}}" class="input-small required">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('sex')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<input id="demoStuInfoList{{idx}}_stuAge" name="demoStuInfoList[{{idx}}].stuAge" type="text" value="{{row.stuAge}}" maxlength="64" class="input-small required"/>
							</td>
							<shiro:hasPermission name="student:demoClass:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#demoStuInfoList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var demoStuInfoRowIdx = 0, demoStuInfoTpl = $("#demoStuInfoTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(demoClass.demoStuInfoList)};
							for (var i=0; i<data.length; i++){
								addRow('#demoStuInfoList', demoStuInfoRowIdx, demoStuInfoTpl, data[i]);
								demoStuInfoRowIdx = demoStuInfoRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="student:demoClass:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>