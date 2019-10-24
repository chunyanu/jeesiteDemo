<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学生信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/test2/demoStuInfo/">学生信息列表</a></li>
		<shiro:hasPermission name="test2:demoStuInfo:edit"><li><a href="${ctx}/test2/demoStuInfo/form">学生信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="demoStuInfo" action="${ctx}/test2/demoStuInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>stu_name：</label>
				<form:input path="stuName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>更新时间</th>
				<th>备注信息</th>
				<th>stu_name</th>
				<th>stu_id</th>
				<th>stu_classname</th>
				<th>stu_sex</th>
				<th>stu_age</th>
				<th>stu_classid</th>
				<shiro:hasPermission name="test2:demoStuInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="demoStuInfo">
			<tr>
				<td><a href="${ctx}/test2/demoStuInfo/form?id=${demoStuInfo.id}">
					<fmt:formatDate value="${demoStuInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${demoStuInfo.remarks}
				</td>
				<td>
					${demoStuInfo.stuName}
				</td>
				<td>
					${demoStuInfo.stuId}
				</td>
				<td>
					${demoStuInfo.stuClassname}
				</td>
				<td>
					${fns:getDictLabel(demoStuInfo.stuSex, 'sex', '')}
				</td>
				<td>
					${demoStuInfo.stuAge}
				</td>
				<td>
					${demoStuInfo.stuClassid}
				</td>
				<shiro:hasPermission name="test2:demoStuInfo:edit"><td>
    				<a href="${ctx}/test2/demoStuInfo/form?id=${demoStuInfo.id}">修改</a>
					<a href="${ctx}/test2/demoStuInfo/delete?id=${demoStuInfo.id}" onclick="return confirmx('确认要删除该学生信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>