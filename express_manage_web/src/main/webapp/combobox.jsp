<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 用restclient中文参数不行，我想是因为jsp页面已经设置好了请求头，而restclient没有设置请求头，中文编码要进行转码,没转码就是乱码，数据库找不到这个乱码当然返回来的是空的 -->
<html>
<head>
<base href="<%=request.getContextPath() + '/'%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="static/easyui15/themes/bootstrap/easyui.css">
    <link rel="stylesheet" href="static/easyui15/themes/icon.css">
</head>
<body>
<!-- class渲染 -->

	<select id="sl" class="easyui-combobox"
		data-options="
			url:'region/findregionbykey',
 			valueField:'id',
   			width:160,
    		mode:'remote',
   			 textField:'regionStr'">
	</select>

</body>
<script src="static/easyui15/jquery.min.js"></script>
<script src="static/easyui15/jquery.easyui.min.js"></script>
<script src="static/easyui15/locale/easyui-lang-zh_CN.js"></script>

<script>
	<!--jquery渲染  
	$("#sl").combobox({    
	    url:'region/findregionbykey',    
	    valueField:'id',
	    width:160,
	    mode:'remote',
	    textField:'regionStr'   
	});-->
	
  
</script>
</html>












