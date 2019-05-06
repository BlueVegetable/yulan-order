<!DOCTYPE HTML>
<html>
	<head>
		<title>轮播图管理界面</title>
		<link type="text/css" rel="stylesheet" href="index.css"/>

		<script type="text/javascript">
			window.onload=function(){
				getInfo();
			}
		function getXMLHttp(){
			var xmlhttp;
			if (window.XMLHttpRequest)
			{
				//  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
				xmlhttp=new XMLHttpRequest();
			}
			else
			{
				// IE6, IE5 浏览器执行代码
				xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			}
			return xmlhttp;
		}
		function getInfo(){
			var xmlHttp=getXMLHttp();
			var table=document.getElementById("tb");
			xmlHttp.onreadystatechange=function()
			{
			    var text=xmlHttp.responseText;
			    var images;
				if(text!=""){
			    	table.innerHTML=getInitString();
			    	images=eval("("+text+")");
			    	for (var i = 0; i < images.length; i++) {
			    		var string;
			    		if(i==0)
			    			string=getHTMLString(images[i],true,false);
			    		else if(i==images.length-1)
			    			string=getHTMLString(images[i],false,true);
			    		else
			    			string=getHTMLString(images[i],false,false);
			    		table.innerHTML+=string;
			    	}
			    }
			}
			xmlHttp.open("POST","http://localhost:8080/exper2/user/getAllImages.do",true);
			//false表示是否要异步
			xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			//如果是POST请求要增加这句
			xmlHttp.send(null);
		}
		function getInitString(){
			var string="<thead>"+
							"<tr>"+
								"<th width='16%'>轮播图编号</th>"+
								"<th width='48%'>轮播图</th>"+
								"<th width='16%'>可视</th>"+
								"<th wdith='20%' style='border-right:none'>操作</th>"+
							"</tr>"+
						"</thead>"
			return string;
		}
		function getHTMLString(image,top,bottom) {
			var string="<tr>"+
						"<td width='16%'>"+image.imageName+"</td>"+
						"<td width='48%'>"+
							"<img src='"+image.imagePath+"' style='width:400px;height:300px;'>"+
						"</td>"
				if(image.visible){
					string+="<td width='16%'>"+
								"可视"+
							"</td>"
				}
				else{
					string+="<td width='16%'>"+
								"不可视"+
							"</td>"
				}
				string+="<td width='20%' style='border-right:none'>"+
						"<button class='myButton'>"+
							"删除"+
						"</button></br></br>";
			if(image.visible)
				string+="&nbsp&nbsp<button class='myButton'><a href='"+
							"http://localhost:8080/exper2/user/changeVisible.do?"+
							"imageName="+image.imageName+"&visible=false"+
							"'>"+
							"不可视"+
						"</a></button></br></br>"
			else
				string+="&nbsp&nbsp<button class='myButton'><a href='"+
							"http://localhost:8080/exper2/user/changeVisible.do?"+
							"imageName="+image.imageName+"&visible=true"+
							"'>"+
							"可视"+
						"</a></button></br></br>"
			if(top)
				string+="&nbsp&nbsp<button class='myButton'><a href='"+
							"http://localhost:8080/exper2/user/downImage.do?"+
							"current="+image.position+
							"'>"+
							"下移"+
						"</a></button></br></br>"
			if(bottom)
				string+="&nbsp&nbsp<button class='myButton'><a href='"+
							"http://localhost:8080/exper2/user/upImage.do?"+
							"current="+image.position+
							"'>"+
							"上移"+
						"</a></button></br></br>"
			if(!top&&!bottom)
				string+="&nbsp&nbsp<button class='myButton'><a href='"+
							"http://localhost:8080/exper2/user/upImage.do?"+
							"current="+image.position+
							"'>"+
							"上移"+
						"</a></button></br></br>"+
						"&nbsp&nbsp<button class='myButton'><a href='"+
							"http://localhost:8080/exper2/user/downImage.do?"+
							"current="+image.position+
							"'>"+
							"下移"+
						"</a></button></br></br>"
			string+="</td>"+
					"</tr>"
			return string;
		}
</script>
	<link rel="stylesheet" href="button.css" type="text/css" />
	<link rel="stylesheet" href="upload.css" type="text/css" />
	</head>
	
	<script type="text/javascript">
		function upload(){
			var file=document.getElementById("imageFile");
			if (file.value == "") {    
				alert("请上传图片");    
				return false;    
			} else {
				if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(file.value)) {    
					alert("图片类型必须是.gif,jpeg,jpg,png中的一种");    
					ths.value = "";
					return false;
				}
			}
			return true;
		}
	</script>




	<body>
		<div class="table_div">
		<div class="div_clear">
  		<div class="left_top">
  		</div>
 		<div class="center_top">
			<div style="float:left">
				<img src="./tab/images/tb.gif" width="16px" height="16px" style="vertical-align:middle"/>
				<span style="font-weight:bold">你当前的位置</span>：轮播图管理
			</div>
			<div style="float:right">
				<form onsubmit="return upload()" action="${pageContext.request.contextPath}/user/addImage.do" method="post" enctype="multipart/form-data">
					<input type="file" name="file" class="a-upload" id="imageFile">
					<input type="submit" value="确认上传" class="myButton" >
				</form>
			</div>
		</div>
		<div class="right_top">
		</div>
		</div>
		<div class="div_clear">
			<div class="left_center">
			</div>
			<div class="center_center">
				<div class="table_content">
					<table cellspacing="0px" cellpadding="0px" id="tb" style="width:100%;height:100%">

  					</table>
				</div>
			</div>
			<div class="right_center"></div>
			<div class="div_clear">
				<div class="left_bottom"></div>
				<div class="center_bottom">
					<span>&nbsp;&nbsp;共有 120 条记录，当前第 1/10 页</span>
					<div style="float:right;padding-right:30px">
						<input type="button" value="首页"/>
						<input type="button" value="上页"/>
						<input type="button" value="下页"/>
						<input type="button" value="尾页"/>
						<span>跳转到</span>
						<input type="text" size="1"/>
						<input type="button" value="跳转"/>
					</div>
				</div>
				<div class="right_bottom"></div>
			</div>
		</div>
	</body>
</html>