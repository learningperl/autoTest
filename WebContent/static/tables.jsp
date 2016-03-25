<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>AutoTest - A HTML5 Template for Test</title>
<!--[if lt IE 9]>
		<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
		<script src="Javascript/Flot/excanvas.js"></script>
	<![endif]-->
<!-- The Fonts -->
<link href="http://fonts.useso.com/css?family=Oswald|Droid+Sans:400,700"
	rel="stylesheet">
<!-- The Main CSS File -->
<link rel="stylesheet" href="/static/CSS/style.css">
<script src="/static/Javascript/jQuery/jquery.min.js"></script>
<script src="/static/Javascript/jQuery/jquery-form.js"></script>

<script>

function saveReport() { 
	document.getElementById("jsonRes").value="等待响应...";
	// jquery 表单提交 
	$("#debugA").ajaxSubmit(function(message) { 
		setApi(message)// 对于表单提交成功后处理，message为提交页面saveReport.htm的返回内容 
	}); 

	return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转 
	} 

	function setApi(message) {
		var url = "";
		url+=document.getElementById("api_url").value;
		url+="?" + document.getElementById("api_param").value;
		document.getElementById("jsonRes").value=message;
		document.getElementById("Url").value+="\n"+url;
	}
	
function getOptions() {
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var url = "../getOptions";
	var data = "";
	var str = "<option value=\"请求方式\">请求方式</option>";
	xmlhttp.open("GET", url, true);
	xmlhttp.send();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4)
			if (xmlhttp.status == 200)
				data = xmlhttp.responseText;
				if(data !=""){
					while(data.indexOf(",")>0){
						str+="<option value=\"" +data.substring(0,data.indexOf(","))+ "\">"+ data.substring(0,data.indexOf(",")) +"</option>";
						data = data.substring(data.indexOf(",")+1,data.length);
					}
					str+="<option value=\""+data+"\">"+ data +"</option>";
					document.getElementById("selectOp").innerHTML=str;
				}
	};
}
	
</script>

</head>
<body onload="getOptions()">
	<header class="main_header">
		<div class="wrapper">
			<div class="logo">
				<a href="index.jsp" Title="autoTest框架"> <img
					src="/static/Images/Testlogo.png"
					alt="Testlogo">
				</a>
			</div>
		</div>
	</header>

	<div class="wrapper contents_wrapper">

		<aside class="sidebar">
			<ul class="tab_nav">
				<li class="i_32_dashboard"><a href="../index.jsp"
					title="General Info"> <span class="tab_label">Home Page</span>
						<span class="tab_info">Frame Info</span>
				</a></li>
				<li class="i_32_charts"><a href="UI.jsp"
					title="Visual Data"> <span class="tab_label">UI自动化</span> <span
						class="tab_info">Edit and Run</span>
				</a></li>
				<li class="i_32_inbox"><a href="inbox.jsp"
					title="Your Messages"> <span class="tab_label">用例生成</span> <span
						class="tab_info">Interface Case</span>
				</a></li>
				<li class="i_32_ui"><a href="Interface.jsp" title="Kit elements">
						<span class="tab_label">接口测试</span> <span class="tab_info">Interface Run</span>
				</a></li>
				<li class="active_tab i_32_tables"><a href="tables.jsp" title="Some Rows">
						<span class="tab_label">调试工具</span> <span class="tab_info">Res Table</span>
				</a></li>
			</ul>
		</aside>
		<div class="contents">
			<div class="grid_wrapper">
				<a class="i_16_message tab_menu">输入</a>
				<form id="debugA" action="../debugApi" method="post" onsubmit="return saveReport();">
				<div class="tab_div">
					<input id="api_url" name="url" class="tab_input" type="text" value="请输入接口地址"	onfocus='if(this.value=="请输入接口地址"){this.value=""}' onblur='if(this.value==""){this.value="请输入接口地址"}' />
					<input id="api_param" name="param" class="tab_input" type="text" value="请输入接口参数"	onfocus='if(this.value=="请输入接口参数"){this.value=""}' onblur='if(this.value==""){this.value="请输入接口参数"}' />
				</div>
				<div class="tab_div">
					 <select name="selectOp" id="selectOp" class="tab_select">
					 		<option value="1">null</option>
				     </select> 
				     <input type="submit" class="tab_btn" value="调试">
				</div>
				</form>			
				<HR color=#9D9D9D SIZE=1>
				
				<a class="i_16_message tab_menu">结果</a>
				<a class="tab_menuc">记录</a>
				<div class="tab_div">
					<textarea id="jsonRes"	style="margin:5px 5px 5px -15px; width: 500px; height: 330px;">json返回结果</textarea>
					<textarea id="Url"	style="margin:5px 5px 5px 60px; width: 500px; height: 330px;">调试记录</textarea>
				</div>

			</div>
		</div>
	</div>
</body>
</html>