/**
 * 
 */
var dataGridManage = {
	$myDataGrid : $("#myDataGrid"),
	initDataGrid : function() {
		dataGridManage.$myDataGrid.datagrid({
			url : 'users/findusersbyinput',
			title : '',
			fitColumns : true,
			scrollbarSize : 0,
			rownumbers : true,
			toolbar : '#DGTools',
			columns : [ [ {
					field : 'uid',
					title : '用户id',
					width : 2,
					checkbox : true
				},{
				field : 'uname',
				title : '用户名称',
				width : 2
				
			}, {
				field : 'utruename',
				title : '真实姓名',
				width : 2
			}, {
				field : 'upassword',
				title : '密码',
				width : 2
			},{
				field : 'roleid',
				hidden:true
			},{
				field : 'rolename',
				title : '角色名称',
				width : 5
				
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
			'uname' : $("#searchname").val()
		});

	},

	add : function(event) {
		event.preventDefault();

		myDialogManage.$myDialog.dialog("open");
		myDialogManage.$myDialog.dialog("setTitle", "添加小窗口");
		 myDialogManage.$myDialog.form("clear");
		myDialogManage.initCombobox();
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
		$("#uid").val(row.uid);

		$("#uname").textbox('setValue', row.uname);
		$("#utruename").textbox('setValue', row.utruename);
		$("#upassword").textbox('setValue', row.upassword);
		$("#roles").val(row.roleid);
		myDialogManage.initCombobox();

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
				var uids = "";
				for (var i = 0; i < row.length; i++) {// 必须从0开始遍历，从1开始遍历的话会出现row[i]未定义的错误
					if (i == row.length - 1) {
						uids = uids + row[i].uid;
					} else {
						uids = uids + row[i].uid + ",";
					}
				}
				alert(uids);
				$.post("users/delusersbycheck", {
					"uids" : uids
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

	clickOk : function(event) {
		event.preventDefault();
		myDialogManage.$myDialog.form('submit', {
			url : '/users/savaorupdateusers',
			success : function(data) {
				var jsonObj = JSON.parse(data);
				$.messager.alert('system', jsonObj.message, "info");

				if ($("#uid").val() == null || $("#uid").val() == '') {
					dataGridManage.$myDataGrid.datagrid('insertRow', {
						index : 0, // 索引从0开始
						row : {
							uid : jsonObj.data.uid,// 这个data并不是success:function(data)里面的data，是AppResult里面的data
							uname : jsonObj.data.uname,
							utruename : jsonObj.data.utruename,
							upassword : jsonObj.data.upassword,
							rolename : jsonObj.data.rolename
						}
					});

					dataGridManage.$myDataGrid.datagrid('highlightRow', 0);

				} else {

					var crows = dataGridManage.$myDataGrid
							.datagrid("getChecked");
					var indexChecked = dataGridManage.$myDataGrid.datagrid(
							"getRowIndex", crows[0]);

					dataGridManage.$myDataGrid.datagrid('updateRow', {
						index : indexChecked,
						row : {
							uid : jsonObj.data.uid,// 这个data并不是success:function(data)里面的data，是AppResult里面的data
							uname : jsonObj.data.uname,
							utruename : jsonObj.data.utruename,
							upassword : jsonObj.data.upassword,
							rolename : jsonObj.data.rolename
						}
					});

				}

				myDialogManage.$myDialog.dialog("close");
				// myTreeManage.reloadTree();////
			}
		});

	},
	clickCancel : function(event) {
		event.preventDefault();
		myDialogManage.$myDialog.dialog("close");
	},

	initCombobox : function() {
		
		$('#roles').combobox({    
		    url:'users/findAllRolesForCombobox',    
		    valueField:'roleid',    
		    textField:'rolename',
		    onChange:function(newValue, oldValue){
		    	$('#roles').combobox('setValue', newValue);
		    	//$('#roles').val(newValue);
		    	windowManage.roleid=newValue;
		    }
		});  
		
	}

}

var windowManage = {

	//roleid : null,
	//rolename : '',

	initWindow : function() {
		dataGridManage.initDataGrid();
		myDialogManage.initDialog();
	}
}

windowManage.initWindow();
