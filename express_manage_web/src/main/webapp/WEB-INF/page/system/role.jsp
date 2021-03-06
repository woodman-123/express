<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<base href="<%=request.getContextPath() + '/'%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="static/easyui15/themes/bootstrap/easyui.css">
<link rel="stylesheet" href="static/easyui15/themes/icon.css">
</head>
<body>

<!-- 表格工具栏 -->
<div id="DGTools">
	<div class='easyui-panel' title='查询条件' iconCls='icon-search' collapsible=true collapsed=true style='padding: 5px'>
		<div style="float:left;height: 30px">
			<label>角色名称:</label><input type='text' id='rname' class='easyui-textbox' />&nbsp;
		</div>
		<a href='#' id="" onclick='DGToolsManage.search(event)' class='easyui-linkbutton' plain=true iconCls='icon-search'>查询</a>
	</div>
		<a class='easyui-linkbutton' plain=true iconCls='icon-add' onclick='DGToolsManage.add(event)'>添加</a>
		<a class='easyui-linkbutton' plain=true iconCls='icon-edit' onclick='DGToolsManage.upd(event)'>修改</a>
		<a class='easyui-linkbutton' plain=true iconCls='icon-remove' onclick='DGToolsManage.del(event)'>删除</a>
</div>
<!-- 表格 -->
<table id="myDataGrid"></table>
<!-- 弹出框 -->

<form id="myDialog" style="height: 300px;text-align: center;" method='post'>														
	<input type="hidden" id="roleid" name="roleid"/> 													
	<p>													
		<label for="roletext">角色名称:</label>												
		<input type="text" id="rolename"												
			class="easyui-textbox" name="rolename" style="width:200px;"/>											
	</p>													
	<p>													
		<label for="rights">赋予权限:</label>												
		<select id="rights" name='rights' style="width:200px;"></select>  												
	</p>													
</form>
<div id="divedit" style="text-align: center;">
	<a class="easyui-linkbutton" onclick="javascript:myDialogManage.clickOk(event)">确认</a>
	<a class="easyui-linkbutton" onclick="javascript:myDialogManage.clickCancel(event)">取消</a>
</div>  



</body>
</body>
<script src="static/easyui15/jquery.min.js"></script>
<script src="static/easyui15/jquery.easyui.min.js"></script>
<script src="static/easyui15/locale/easyui-lang-zh_CN.js"></script>
<script src="static/js/role.js"></script>
</html>












