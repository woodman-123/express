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

<ul id="tt"></ul>

</body>
<script src="static/easyui15/jquery.min.js"></script>
<script src="static/easyui15/jquery.easyui.min.js"></script>
<script src="static/easyui15/locale/easyui-lang-zh_CN.js"></script>

<script>

$("#tt").tree({
	
	
	url:"right/createMenuForCurd?&roleid=1",
	checkbox:true,
	cascadeCheck:false,
	onLoadSuccess:function(node,data){
		$("#tt").tree("expandAll");
	},
	onCheck:function(node,checked){
		
		var pnode=$("#tt").tree("getParent",node.target);
		var cnodes=$("#tt").tree("getChildren",node.target);
		
		
		if($("#tt").tree("isLeaf",node.target)==false){
			for(var i=0;i<cnodes.length;i++){
				$('#tt').tree('update', {
					target: cnodes[i].target,
					checked:checked
				});
			}
		}else{
			if(checked){
				$('#tt').tree('update', {
					target: pnode.target,
					checked:true
				});
			}else{
				
				var xdnodes=$("#tt").tree("getChildren",pnode.target);
				var flag=false;
				for(var i=0;i<xdnodes.length;i++){
					if(xdnodes[i].checked==true){
						flag=true;
						break;
					}
				}
				$('#tt').tree('update', {
					target: pnode.target,
					checked:flag
				});
				
			}
		}
	}
	
});
  
</script>
</html>












