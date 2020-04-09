<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<base href="<%=request.getContextPath() + '/'%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="static/easyui15/themes/bootstrap/easyui.css">
    <link rel="stylesheet" href="static/easyui15/themes/icon.css">
</head>
<body>



<form action="" method="post" style="text-align: center;padding-top: 100px">
    <p>
        <label for="uname">用户姓名:</label>
        <input type="text" name="uname" id="uname">
    </p>

    <p>
        <label for="upass">用户密码:</label>
        <input type="password" name="upassword" id="upass">
    </p>
</form>
<div id="tt" style="text-align: right">
    <a href="#"  class="easyui-linkbutton" data-options="iconCls:'icon-login',text:'登录'" id="alogin"></a>
    <a href="#"  class="easyui-linkbutton" data-options="iconCls:'icon-cancel',text:'取消'" id="acancel"></a>
</div>

</body>
<script src="static/easyui15/jquery.min.js"></script>
<script src="static/easyui15/jquery.easyui.min.js"></script>
<script src="static/easyui15/locale/easyui-lang-zh_CN.js"></script>

<script>

   $("form").window({
       title:'登录界面',
       iconCls:'icon-login',
       width:600,
       height:300,
		footer:'#tt' 
   });
   
   $("#acancel").on("click",function (event) {
       event.preventDefault();
       $("#uname").val("");
       $("#upass").val("");
   });
   
   $("#alogin").on("click",function(event){
	   event.preventDefault();
	   var url="users/login";
	   var pdata=$("form").serialize();
	   $.post(url,pdata,function(jsonobj){
		   if(jsonobj.keyCode==200){
			   window.location.href="toindex";
		   }else{
			   //alert(jsonobj.message);
			   $.messager.alert("系统信息",jsonobj.message,"error");
		   }
	   },"json");
	   
   });
</script>
</html>












