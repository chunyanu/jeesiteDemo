<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>班级信息管理管理</title>
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
		<li class="active"><a href="${ctx}/test2/demoClass/">班级信息管理列表</a></li>
		<shiro:hasPermission name="test2:demoClass:edit"><li><a href="${ctx}/test2/demoClass/form">班级信息管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="demoClass" action="${ctx}/test2/demoClass/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>班级名称：</label>
				<form:input path="className" htmlEscape="false" maxlength="64" class="input-medium"/>
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
				<th>班级名称</th>
				<th>班级号</th>
				<shiro:hasPermission name="test2:demoClass:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="demoClass">
			<tr>
				<td><a href="${ctx}/test2/demoClass/form?id=${demoClass.id}">
					<fmt:formatDate value="${demoClass.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${demoClass.remarks}
				</td>
				<td>
					${demoClass.className}
				</td>
				<td>
					${demoClass.classId}
				</td>
				<shiro:hasPermission name="test2:demoClass:edit"><td>
    				<a href="${ctx}/test2/demoClass/form?id=${demoClass.id}">修改</a>
					<a href="${ctx}/test2/demoClass/delete?id=${demoClass.id}" onclick="return confirmx('确认要删除该班级信息管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>