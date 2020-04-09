<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<base href="<%=request.getContextPath()+'/'%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>注册页面</h1>
	<hr>
	
	<form action="">
		name:<input type="text" name="name"/><br>
		telephone:<input type="text" name="telephone"/><br>
		address:<input type="text" name="address"/><br>
		<input type="text" name="inputcode"/><a href="javascript:void(0);" id="getcode">获取验证码</a><br>
		<a href="javascript:void(0);" id="register">注册</a>
	</form>
	
</body>
<script src="/static/easyui15/jquery.min.js"></script>
	
	<script type="text/javascript">
	$("#getcode").on("click",function(){
		
		$.post("customer/getcheckcode",{"tel":$(":text[name='telephone']").val()},function(jsonObj){
			alert(jsonObj.message);
		},"json");
		
	})
		
	$("#register").on("click",function(){
		
		$.post("customer/register",$("form").serialize(),function(jsonObj){
			alert(jsonObj.message);
		},"json");
		
	})
	
	</script>

</html>











