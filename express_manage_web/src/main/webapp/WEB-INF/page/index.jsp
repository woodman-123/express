<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=request.getContextPath() + '/'%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="static/easyui15/themes/bootstrap/easyui.css">
<link rel="stylesheet" href="static/easyui15/themes/icon.css">
<link rel="stylesheet" href="static/css/index.css">
<title>首页</title>
</head>
<body class="easyui-layout">


	<div data-options="region:'north'"
		style="height: 100px; background-color: gray;">
		<div class="logo">快递后台管理系统</div>

		<div class="logout">
			<span>欢迎，<span id="spauser">${userinfo.uname}</span></span>|<a href="users/logout"
				style="color: #fff">退出</a>
		</div>
	</div>

	<div data-options="region:'south'" style="height: 70px;"></div>


	<!--  
    <div data-options="region:'west',title:'系统菜单',split:true,iconCls:'icon-world'" style="width:200px;">
    	<p><a class="system" href="system/tousers">用户管理</a></p>
    	<p><a class="system" href="system/torole">角色管理</a></p>
    	<p><a class="system" href="system/toright">权限管理</a></p>
    </div>   
    -->

	<div data-options="region:'west',title:'系统菜单',split:true,iconCls:'icon-world'" style="width: 200px;">
		<div id="divacd" class="easyui-accordion" style="width: 200px;">
			
		</div>
	</div>


	<div data-options="region:'center'"
		style="padding: 5px; background: #eee;">

		<div id="cts" class="easyui-tabs" data-options="fit:true">
			<div title="首页" data-options="iconCls:'icon-login'"
				style="background-image: url('static/img/index.png'); background-repeat: no-repeat; background-size: 100%">
				tab1</div>
		</div>

	</div>


</body>
<script src="static/easyui15/jquery.min.js"></script>
<script src="static/easyui15/jquery.easyui.min.js"></script>
<script src="static/easyui15/locale/easyui-lang-zh_CN.js"></script>
<script src="static/js/index.js"></script>
<script src="static/easyui15/plugins/jquery.layout.js"></script>

<!--  在需要使用GoEasy的html页面引入goeasy.js。-->

<!--[if lte IE 8]>//如果需要支持低版本的IE8及以下
<script  type="text/javascript" src="https://cdn.goeasy.io/json2.js"></script>
<![endif]-->

<script type="text/javascript" src="https://cdn.goeasy.io/goeasy.js"></script>




<script type="text/javascript">

var goEasy = new GoEasy({
    host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
    appkey: "BS-253f312d4d8347528d706f2bf098d0ea", //替换为您的应用appkey
});

goEasy.subscribe({
    channel: "ts_qj_"+${ userinfo.roleid}, //替换为您自己的channel
    onMessage: function (message) {
        //控制台打印console.log("Channel:" + message.channel + " content:" + message.content);
   		 // alert(message.content);
        	//$.messager.show("sysytem",message.content,"info");
        	
        	if(confirm(message.content)){
        		
        		if($("#cts").tabs("exists","手动派单")==false){
        		
        		$("#cts").tabs('add',{    
        		    title:"手动派单",      
        		    selected:true,
        		    closable:true,   
        		    content:'<iframe src="page/work/torg" frameborder="0"  style="width: 100%;height: 100%;" scrolling="no" ></iframe>'
        		});
        		
        		}else{
        			$("#cts").tabs("select","手动派单");
        		}
        	}
    }
});

</script>











</html>