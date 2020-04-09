<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=request.getContextPath()+'/'%>">
<meta charset=UTF-8">
<title>取派员设置</title>
<!-- 导入jquery核心类库 -->
<link rel="stylesheet" type="text/css" href="static/easyui15/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="static/easyui15/themes/icon.css">
<script type="text/javascript" src="static/easyui15/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui15/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/easyui15/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	function doAdd(){
		//alert("增加...");
		$('#addStaffWindow').window("open");
	
		$('#addStaffWindow').window("open");
		$('#addStaffWindow').window("setTitle", "添加小窗口");
		$('#addStaffWindow').form("clear");
		$("#save").text("添加");
		
		
		
		
		
	}
	
	function doView(){
		//alert("查看...");
		$('#addStaffWindow').window("open");
		$('#addStaffWindow').window("setTitle", "查询取派员");
		$('#addStaffWindow').form("clear");
		$("#save").text("查询");
	
		
		
			$("#id").textbox("disableValidation");
		  	$("#name").textbox("disableValidation");
		  	$("#telepahone").textbox("disableValidation");
			$("#station").textbox("disableValidation");
		//	$("#haspda").textbox("disableValidation");

	}
	
	
	function commit(event){
		event.preventDefault();
		if(($("#save").text())=="查询"){
			$('#grid').datagrid('load', {
				'id' : $(":text[name='id']").val(),
				'name' : $(":text[name='name']").val(),
				'telephone' : $(":text[name='telepahone']").val(),
				'station' : $(":text[name='station']").val(),
				'haspda' :$(":checkbox[name='haspda']").val(),
			});
		}else if(($("#save").text())=="添加"){
			$("#myForm").form('submit', {    
			    url:"staff/addstaff",    
			    onSubmit: function(){    
			    	return $('#addStaffWindow').form('validate');
			    },    
			    success:function(data){    
			    	var jsonObj=JSON.parse(data);
			    	$.messager.alert('system',jsonObj.message,"info");
			    	$('#addStaffWindow').form("close");
			    	$('#grid').datagrid('load');
			    }    
			}); 
		}
		
		$('#addStaffWindow').window("close");
	}
	
	
	
	function doDelete(){
		
		event.preventDefault();
		var row = $('#grid').datagrid('getChecked');
		if (row.length == 0) {
			$.messager.alert('警告', '请至少选择一行', "error");
			return;
		}

		$.messager.confirm('确认对话框', '确认删除吗？', function(r) {
			if (r) {
				var ids = "";
				for (var i = 0; i < row.length; i++) {// 必须从0开始遍历，从1开始遍历的话会出现row[i]未定义的错误
					if (i == row.length - 1) {
						ids = ids + row[i].id;
					} else {
						ids = ids + row[i].id + ",";
					}
				}
				alert(ids);
				$.post("staff/delstaffsbycheck", {
					"ids" : ids
				}, function(jsonObj) {
					$.messager.alert('警告', jsonObj.message, "info");
					if (jsonObj.keyCode == 200) {
						dataGridManage.initDataGrid();
					}
				}, "json");

			} else {
				return;
			}
		});
		
		$('#grid').datagrid('load');
		
	}
	
	//工具栏
	var toolbar = [ {
		id : 'button-view',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doView
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-delete',
		text : '作废',
		iconCls : 'icon-cancel',
		handler : doDelete
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	},{
		field : 'name',
		title : '姓名',
		width : 120,
		align : 'center'
	}, {
		field : 'telephone',
		title : '手机号',
		width : 120,
		align : 'center'
	}, {
		field : 'haspda',
		title : '是否有PDA',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="1"){
				return "有";
			}else{
				return "无";
			}
		}
	}, {
		field : 'deltag',
		title : '是否作废',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="0"){
				return "正常使用"
			}else{
				return "已作废";
			}
		}
	}, {
		field : 'standard',
		title : '取派标准',
		width : 120,
		align : 'center'
	}, {
		field : 'station',
		title : '所谓单位',
		width : 200,
		align : 'center'
	} ] ];
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 取派员信息表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList: [2,6,9],
			pagination : true,
			pageSize : 2,
			toolbar : toolbar,
			//url : "static/json/staff.json",
			url:'staff/findstaffbyinput',
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});
		
		// 添加取派员窗口
		$('#addStaffWindow').window({
	        title: '添加取派员',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
	});

	function doDblClickRow(rowIndex, rowData){
		alert("双击表格数据...");
	}
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	<div class="easyui-window" title="对收派员进行添加或者修改" id="addStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" onclick="javascript:commit(event)" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="myForm" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<tr>
						<td>取派员编号</td>
						<td><input type="text" name="id" id="id" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" id="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<td><input type="text" name="telephone" id="telephone" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" id="station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
						<input type="checkbox" name="haspda"  id="haspda" value="1" />
						是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td>
							<input type="text" name="standard"  id="standard" class="easyui-validatebox" required="true"/>  
						</td>
					</tr>
					</table>
			</form>
		</div>
	</div>
</body>
</html>	


