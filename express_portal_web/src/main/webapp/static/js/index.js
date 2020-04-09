/**
 * 
 */

var windowManage = {

	initWindow : function() {
		
		$.messager.show({
			title : '系统消息',
			//${user.uname}这里不是jsp页面不能从四大作用域取值，可以通过选择器找到uname
			msg : '欢迎'+ $("#spauser").text()+'登录系统',
			timeout : 5000,
			showType : 'slide'
		});
		
	$.post("right/createmenu",null,function(jsonObj){
		
		$(jsonObj.data).each(function(index,right){
			var content="";
			$(right.child).each(function(index,obj){
				content+='<p class="pitem"><a class="easyui-linkbutton" data-options="plain:true" href="'+obj.righturl+'">'+obj.righttext+'</a></p>';
			});
			
			
			
			if(index==0){
				$('#divacd').accordion('add', {
					title: right.righttext,
					content:content,
					selected: true
				});
			}else{
				$('#divacd').accordion('add', {
					title: right.righttext,
					content:content,
					selected: false
				});
			}

			
			
			
		});
		
	
	},"json");	
		
		

	}


};



//事件的委派，把爸爸的时间委派给儿子，因为"p.pitem a"是要在数据库查完回来之后才有的，如果用事件的绑定
//$("#divacd p.pitem a").on("click",function(event){
//当没有a（数据库还没有查回来）的话，事件是绑定不了的，但是爸爸#divacd是一值存在，不管a是否存在他可以把事件委派给a
//上面是异步提交，(子线程），当到数据库查完返回，页面其他的东西已经加载完毕，
$("#divacd").on("click","p.pitem a",function(event){
	event.preventDefault();
	
	if($("#cts").tabs("exists",$(this).text())==false){
	
	$("#cts").tabs('add',{    
	    title:$(this).text(),      
	    selected:true,
	    closable:true,   
	    content:'<iframe src="'+this.href+'" frameborder="0"  style="width: 100%;height: 100%;" scrolling="no" ></iframe>'
	});
	
	}else{
		$("#cts").tabs("select",$(this).text());
	}
	
});



windowManage.initWindow();