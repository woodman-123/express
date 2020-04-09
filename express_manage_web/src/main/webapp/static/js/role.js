/**
 * 
 */
var dataGridManage = {
	$myDataGrid : $("#myDataGrid"),
	initDataGrid : function() {
		dataGridManage.$myDataGrid.datagrid({
			url : 'role/findrolesbyinput',
			title : '',
			fitColumns : true,
			scrollbarSize : 0,
			rownumbers : true,
			toolbar : '#DGTools',
			columns : [ [ {
				field : 'roleid',
				title : '角色ID',
				width : 2,
				checkbox : true
			}, {
				field : 'rolename',
				title : '角色名称',
				width : 2
			}, {
				field : 'rightNames',
				title : '拥有的权限',
				width : 10,
				formatter : function(value, row, index) {
					/*
					 * row.roleid
					 */
					return "<strong>[" + value + "]</strong>";
				}

			} ] ],
			pagination : true,
			pageSize : 3,
			pageList : [ 3, 5, 10, 20 ],
			fit : true
		});

	}
}

var DGToolsManage = {
	$DGTools : $("#DGTools"),
	search : function(event) {
		event.preventDefault();
		dataGridManage.$myDataGrid.datagrid('load', {
			'rolename' : $("#rname").val()
		});

	},

	add : function(event) {
		event.preventDefault();

		myDialogManage.$myDialog.dialog("open");
		myDialogManage.$myDialog.dialog("setTitle", "添加小窗口");
		// myDialogManage.$myDialog.form("clear");
		myDialogManage.initTree(-1);
	},
	upd : function(event) {
		event.preventDefault();
		var rows = dataGridManage.$myDataGrid.datagrid('getChecked');

		if (rows.length != 1) {
			$.messager.alert("system", "请选中并且只能选中一行", "info");
			return;
		}

		myDialogManage.$myDialog.dialog("open");
		myDialogManage.$myDialog.dialog("setTitle", "更新小窗口");
		// myDialogManage.$myDialog.form("clear");
		var row = rows[0];
		$("#roleid").val(row.roleid);

		$("#rolename").textbox('setValue', row.rolename);

		myDialogManage.initTree(row.roleid);

	},
	del : function(event) {
		event.preventDefault();
		var row = dataGridManage.$myDataGrid.datagrid('getChecked');
		if (row.length == 0) {
			$.messager.alert('警告', '请至少选择一行', "error");
			return;
		}

		$.messager.confirm('确认对话框', '确认删除吗？', function(r) {
			if (r) {
				var rolesid = "";
				for (var i = 0; i < row.length; i++) {// 必须从0开始遍历，从1开始遍历的话会出现row[i]未定义的错误
					if (i == row.length - 1) {
						rolesid = rolesid + row[i].roleid;
					} else {
						rolesid = rolesid + row[i].roleid + ",";
					}
				}
				alert(rolesid);
				$.post("role/delrolesbycheck", {
					"rolesid" : rolesid
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
	},

}

var myDialogManage = {

	$myDialog : $("#myDialog"),

	initDialog : function() {
		myDialogManage.$myDialog.dialog({
			title : '添加和修改窗口',
			width : 400,
			height : 250,
			closed : true,
			cache : false,
			// 模式化窗口，为true时，在窗口弹出后，其他不能用
			modal : true,
			footer : '#divedit'
		});
	},
	
	
	
	clickOk:function(event){
		event.preventDefault();
		myDialogManage.$myDialog.form('submit', {
			url: 'right/savaorupdaterole',
			success:function(data){
		    	var jsonObj=JSON.parse(data);
		    	$.messager.alert('system',jsonObj.message,"info");
		    	
		    	if($("#roleid").val()==null||$("#roleid").val()==''){
		    		dataGridManage.$myDataGrid.datagrid('insertRow',{
		    			index: 0,	// 索引从0开始
		    			row: {
		    				roleid: jsonObj.data.roleid,//这个data并不是success:function(data)里面的data，是AppResult里面的data
		    				rolename: jsonObj.data.rolename,
		    				rightNames: windowManage.rightnames
		    			}
		    		});
		    		
		    		dataGridManage.$myDataGrid.datagrid('highlightRow',0);

		    	}else{
		    		
		    		var crows=dataGridManage.$myDataGrid.datagrid("getChecked");
		    		var indexChecked=dataGridManage.$myDataGrid.datagrid("getRowIndex",crows[0]);
		    		
		    		
		    		
		    		dataGridManage.$myDataGrid.datagrid('updateRow',{
		    			index: indexChecked,
		    			row: {
		    				roleid: jsonObj.data.roleid,//这个data并不是success:function(data)里面的data，是AppResult里面的data
		    				rolename: jsonObj.data.rolename,
		    				rightNames: windowManage.rightnames
		    			}
		    		});
		    		
		    	}

		    	
		    	myDialogManage.$myDialog.dialog("close");
		    	//myTreeManage.reloadTree();////
		    }    
		});  
   
	
	},
	clickCancel:function(event){
		event.preventDefault();
		myDialogManage.$myDialog.dialog("close");
	},
	
	

	initTree : function(roleid) {

		$("#rights").combotree({
			url : "right/createMenuForCurd?roleid=" + roleid,
			checkbox : true,
			// 多选
			multiple : true,
			cascadeCheck : false,
			onLoadSuccess : function(node, data) {
				// 获得下拉菜单的树控件
				var $tree = $("#rights").combotree("tree");
				$tree.tree("expandAll");
			},
			onCheck : function(node, checked) {
				var $tree = $("#rights").combotree("tree");
				var pnode = $tree.tree("getParent", node.target);
				var cnodes = $tree.tree("getChildren", node.target);

				if ($tree.tree("isLeaf", node.target) == false) {
					for (var i = 0; i < cnodes.length; i++) {
						$tree.tree('update', {
							target : cnodes[i].target,
							checked : checked
						});
					}
				} else {
					if (checked) {
						$tree.tree('update', {
							target : pnode.target,
							checked : true
						});
					} else {

						var xdnodes = $tree.tree("getChildren", pnode.target);
						var flag = false;
						for (var i = 0; i < xdnodes.length; i++) {
							if (xdnodes[i].checked == true) {
								flag = true;
								break;
							}
						}
						$tree.tree('update', {
							target : pnode.target,
							checked : flag
						});

					}
				}
				
				windowManage.rightids=new Array();
				windowManage.rightnames='';
				
				var nodes=$tree.tree("getChecked");
				
				for(var i=0;i<nodes.length;i++){
					windowManage.rightids.push(nodes[i].id);
					if(i!=nodes.length){
						windowManage.rightnames+=nodes[i].text+",";
					}else{
						windowManage.rightnames+=nodes[i].text;
					}
				}
				//alert(windowManage.rightids);
				//alert(windowManage.rightnames);
				$("#rights").combotree("setText",windowManage.rightnames);
				$("#rights").combotree("setValue",windowManage.rightids);
						
			}

		});

	}



}

var windowManage = {
		
		rightids:null,
		rightnames:'',
		
		
	initWindow : function() {
		dataGridManage.initDataGrid();
		myDialogManage.initDialog();
	}
}

windowManage.initWindow();
