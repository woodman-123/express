/**
 * 
 */

var myTreeManage = {
	// 树的对象，频繁使用选择器很消耗页面的性能，我们可以把他存起来，下次就不用到页面去搜索了
	$myTree : $("#myTree"),

	initTree : function() {

		myTreeManage.$myTree.tree({
			url : 'right/createmenucurd',
			animate : true,
			lines : true,
			dnd : true,
			onContextMenu : function(e, node) {
				e.preventDefault();
				// 查找节点
				myTreeManage.$myTree.tree('select', node.target);
				// 显示快捷菜单
				myMenuManage.$myMenu.menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			},
			//注意这下面两个事件是放在初始化树的方法里面的
			onBeforeDrag:function(node){      // onBeforeDrag 是事件，isLeaf是方法
				if(myTreeManage.$myTree.tree('isLeaf',node.target)==false){
					return false;
				}
				
			},
			onDragEnter:function(target,source){//source是个源节点node，target是个目标target
				
				var pnode=myTreeManage.$myTree.tree('getNode',target);
				
				if(myTreeManage.$myTree.tree('isLeaf',target)==true){//不能拖到叶子节点
					return false;
				}
				if(pnode.id==0){//不能拖到系统菜单
					return false;
				}
				
				$.post("right/updaterightdnd",{"rightid":source.id,"parentid":pnode.id},function(jsonObj){
					$.messager.alert('system',jsonObj.message,"info");
				},"json");
			
			}

		});
		
		
		
		
		
		
		
		
		
		

	},
	reloadTree:function(){
		
		$.post('right/createmenucurd',null,function(jsondata){
			myTreeManage.$myTree.tree('loadData',jsondata);
		},"json");
		
	}
	

};

var myMenuManage = {
	$myMenu : $("#myMenu"),
	add:function(){
		//获取选中的节点
		var cnode=myTreeManage.$myTree.tree('getSelected');
		//判断选中的节点是不是叶子节点,不是叶子节点就
		if(myTreeManage.$myTree.tree('isLeaf',cnode.target)){
			$.messager.alert('system',"叶子节点不能有下级","info");
			return;
		}
		myDialogManage.$myDialog.dialog("open");
		myDialogManage.$myDialog.dialog("setTitle","添加小窗口");
		myDialogManage.$myDialog.form("clear");
		$("#rightparent").textbox('setValue',cnode.text);
		$("#parentid").val(cnode.id);
		
		
		
		//文件夹没有url，所以不需要验证url
		
		//每次添加先开启验证，防止因为上次关闭了验证，这次用不了
		$("#righturl").textbox("enableValidation");
		if(cnode.id==0){
			$("#righturl").textbox("disableValidation");
		}
		
		
	},
	edit:function(){
		var cnode=myTreeManage.$myTree.tree('getSelected');
		var pnode=myTreeManage.$myTree.tree('getParent',cnode.target);
		myDialogManage.$myDialog.dialog("open");
		myDialogManage.$myDialog.dialog("setTitle","更新小窗口");
		myDialogManage.$myDialog.form("clear");
		$("#rightid").val(cnode.id);
		$("#righturl").textbox('setValue',cnode.attributes.url);
		$("#righttext").textbox('setValue',cnode.text);
		
		$("#rightparent").textbox('setValue',pnode.text);
		$("#parentid").val(pnode.id);
		
		
		//文件夹没有url，所以不需要验证url
		
		//每次修改或者新增先开启验证，防止因为上次关闭了验证，这次用不了
		$("#righturl").textbox("enableValidation");
		if(pnode.id==0){//系统菜单下的是文件夹，不需要url，修改或者新增也不需要验证
			$("#righturl").textbox("disableValidation");
		}
		
	},
	remove:function(){
		var cnode=myTreeManage.$myTree.tree('getSelected');
		if(cnode.id==0){
			$.messager.alert('system',"不能删除系统菜单","info");
		}else if(myTreeManage.$myTree.tree('isLeaf',cnode.target)==false){
			var url="right/deleteFolderRight"
				var data={"rightid":cnode.id};
				$.post(url,data,function(jsonObj){
					$.messager.alert('system',jsonObj.message,"info");
					//myTreeManage.reloadTree();
				},"json");
		}else{
		
			var url="right/deleteOneright"
			var data={"rightid":cnode.id};
			$.post(url,data,function(jsonObj){
				$.messager.alert('system',jsonObj.message,"info");
				myTreeManage.reloadTree();
			},"json");
			
		}
		
	}

};










var myDialogManage = {

	$myDialog : $("#myDialog"),
	
	initDialog : function() {
		myDialogManage.$myDialog.dialog({    
		    title: '添加和修改窗口',    
		    width: 400,    
		    height: 200,    
		    closed: true,    
		    cache: false,    
		   //模式化窗口，为true时，在窗口弹出后，其他不能用
		    modal: true,
		    footer:'#divedit'
		});  
	},
	
	//输入框的验证
	initInput:function(){
		
		$("#righttext").textbox({
			iconCls:'icon-man',
			required:true,
			validType:"remote['right/checkrightname','righttext']",
			//编辑过程，验证延时
			delay:500,
			invalidMessage: "该权利已存在"
		});
		
		$("#righturl").textbox({
			iconCls:'icon-man',
			required:true
			//remote:['right/checkrightname','righttext']
		});
		
		$("#rightparent").textbox({
			iconCls:'icon-man',
			readonly:true
		});
		
	},
	
	
	
	clickOk:function(event){
		event.preventDefault();
		myDialogManage.$myDialog.form('submit', {
			url: 'right/savaorupdateright',
			onSubmit: function(){    
		        // do some check    
		        // return false to prevent submit;  
				//表单验证通过才让你提交
				return myDialogManage.$myDialog.form('validate');
		    }, 
			success:function(data){
		    	var jsonObj=JSON.parse(data);
		    	$.messager.alert('system',jsonObj.message,"info");
		    	myDialogManage.$myDialog.dialog("close");
		    	myTreeManage.reloadTree();////
		    }    
		});  
   
	
	},
	clickCancel:function(event){
		event.preventDefault();
		myDialogManage.$myDialog.dialog("close");
	}


};

var windowManage = {

	initWindow : function() {
		myTreeManage.initTree();
		myDialogManage.initDialog();
		//初始化输入框
		myDialogManage.initInput();
	}

};

windowManage.initWindow();






















/*
 * //1 树形的管理类
var mtreeManage={
	//树的对象
	$mtree:$("#mtree"),
	//初始化树
	initTree:function(){
		mtreeManage.$mtree.tree({
			url:'user/createmenucurd',
			animate:true,
			lines:true,
			dnd:true,
			onContextMenu: function(e, node){
				e.preventDefault();
				// 选中点击的节点
				mtreeManage.$mtree.tree('select', node.target);
				// 显示快捷菜单
				mmManage.$mm.menu('show', {
					left: e.pageX,
					top: e.pageY
				});
			},
			//拖动之前触发的事件
			onBeforeDrag:function(node){
				//判断托送的节点 是否是一个 父亲节点 是 ：拒绝  否。。。。。
				if(mtreeManage.$mtree.tree('isLeaf',node.target)==false){
					return false;
				}
			},
			//target 放进去的目标节点 （）  source:被拖动的节点  node
			onDragEnter:function(target, source){
				var pnode = mtreeManage.$mtree.tree('getNode',target);
				//如果放进去的节点 是一个儿子节点 拒绝
				if(mtreeManage.$mtree.tree('isLeaf',target)==true){
					return false;
				}
				if(pnode.id==0){
					return false;
				}
				//把拖动更新到数据库
				$.post('user/updateRightDnd',{"rightid":source.id,"parentid":pnode.id},function(jsonData){
					$.messager.alert("system",jsonData.message,'info');
				},"json");
			}

		});
	},
	//刷新树的方法
	reloadTree:function(){
		$.post('user/createmenucurd',null,function(jsonDate){
			mtreeManage.$mtree.tree('loadData',jsonDate);
		},'json');
		
	}
}

//2 弹出的menu的管理类
var mmManage={
	$mm:$("#mm"),
	//点击添加
	add:function(){
		//1 判断点击的树上的节点 是不是子节点
		var cnode = mtreeManage.$mtree.tree('getSelected');
		//2 是子节点 就浸膏 
		if(mtreeManage.$mtree.tree("isLeaf",cnode.target)){
			$.messager.alert('system','子节点不能有下级','info');
			return;
		}
		//3 不是 弹出 form
		myDialogManage.$myDialog.dialog('open');
		//3.1 修改form的title
		myDialogManage.$myDialog.dialog('setTitle','添加小窗口');
		//3.2 清空信息
		myDialogManage.$myDialog.form('clear');
		//3.3 设置父节点
		$("#rightparent").textbox('setValue',cnode.text);
		$("#parentid").val(cnode.id);
	},
	//点击修改
	edit:function(){
		//0.1 活儿当前的节点
		var cnode = mtreeManage.$mtree.tree('getSelected');
		//0.2 获得当前节点的爸爸节点
		var pnode = mtreeManage.$mtree.tree('getParent',cnode.target);
		//1 弹出form
		myDialogManage.$myDialog.dialog('open');
		//2 修改title
		myDialogManage.$myDialog.dialog('setTitle','更新小窗口');
		//3 清空信息
		myDialogManage.$myDialog.form('clear');
		//4 设置值
		$("#rightid").val(cnode.id);
		$("#righturl").textbox('setValue',cnode.attributes.url);
		$("#righttext").textbox('setValue',cnode.text);
		
		$("#rightparent").textbox('setValue',pnode.text);
		$("#parentid").val(pnode.id);
		
	},
	//点击删除
	remove:function(){
		
	}
}

//3 弹出的form的管理类
var myDialogManage={
	$myDialog:$("#myDialog"),
	//初始化form
	initForm:function(){
		myDialogManage.$myDialog.dialog({    
		    title: '添加和修改窗口',    
		    width: 400,    
		    height: 250,    
		    closed: true,    
		    cache: false,    
		    modal: true,
		    footer:"#divedit"
		});  
	},
	//点击ok
	clickOk:function(event){
		event.preventDefault();
		myDialogManage.$myDialog.form('submit', {    
		    url:'user/saveorupdateright',    
		    success:function(data){
		    	//把json 字符串转成  json对象
		    	var jsonObj = JSON.parse(data);
		        $.messager.alert('system',jsonObj.message,'info'); 
		        myDialogManage.$myDialog.dialog('close');
		        //刷新树
		        mtreeManage.reloadTree();
		        
		    }    
		});  
	},
	//点击取消
	clickCancel:function(event){
		event.preventDefault();
		myDialogManage.$myDialog.dialog('close');
	}
	
}

//4 整个窗体的管理类
var windowManage={
	//初始化整个窗体
	initWindow:function(){
		//初始书
		mtreeManage.initTree();
		//初始化form
		myDialogManage.initForm();
	}
}

windowManage.initWindow();
 */
