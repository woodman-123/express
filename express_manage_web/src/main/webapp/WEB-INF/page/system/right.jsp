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

<!-- 树 -->
<ul id="myTree"></ul>
<!-- 弹出的操作菜单 -->
<div id="myMenu" class="easyui-menu" style="width:120px;">
	<div   onclick="myMenuManage.add()" data-options="iconCls:'icon-add'">追加</div>
	<div onclick="myMenuManage.edit()"   data-options="iconCls:'icon-edit'">修改</div>
	<div  class="menu-sep"></div>
	<div onclick="myMenuManage.remove()" data-options="iconCls:'icon-remove'">移除</div>
</div>
<!-- 弹出的修改窗口 -->
<form id="myDialog" method="post" style="text-align: center;">							
		<input type="hidden" id="rightid" name="rightid"/> 					
		<p>					
			<label for="righttext">节点文本:</label>
			<input class="easyui-textbox" data-options="iconCls:'icon-search'" id="righttext" name="righttext" style="width: 140px"> 						
		</p>									
		<p>					
			<label for="righturl">节点链接:</label>				
			<input type="text" id="righturl"				
				class="easyui-textbox" data-options="iconCls:'icon-search'" name="righturl" style="width: 140px"/> 			
		</p>
		<p>					
			<label for="rightparent">父节点:</label>				
			<input type="text" id="rightparent"				
				class="easyui-textbox" data-options="iconCls:'icon-search'"
				 name="rightparent" style="width: 140px" readonly="readonly"/> 
			<input type="hidden" id="parentid"				
			 name="parentid"/> 			
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
<script src="static/js/right.js"></script>
</html>












