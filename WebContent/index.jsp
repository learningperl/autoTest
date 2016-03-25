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

</head>

<script type="text/javascript">
	$(function($) {
		$("#pop1").hover(function() {
			$(this).stop().animate({
				opacity : '1'
			}, 600);
		}, function() {
			$(this).stop().animate({
				opacity : '0.6'
			}, 1000);
		}).on('click', function() {
			$("body").append("<div id='mask'></div>");
			$("#mask").addClass("mask").fadeIn("slow");
			$("#EditBox1").fadeIn("slow");
		});
		$(".close_btn").hover(function() {
			$(this).css({
				color : 'black'
			});
		}, function() {
			$(this).css({
				color : '#999'
			});
		}).on('click', function() {
			$("#EditBox1").fadeOut("fast");
			$("#mask").css({
				display : 'none'
			});
		});
		$("#loginbtn").hover(function() {
			$(this).stop().animate({
				opacity : '1'
			}, 600);
		}, function() {
			$(this).stop().animate({
				opacity : '0.8'
			}, 1000);
		});
	});

	function pops(id) {
		document.getElementById(id).click();
	}

	function delKey(name, id) {
		var flag = confirm("删除操作是不可逆的，你确定删除这条用例？");
		var ret = 2;
		if (flag > 0) {
			var xmlhttp;
			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else {// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			var url = "";
			url = "./delKey?id=" + id;
			xmlhttp.open("GET", url, true);
			xmlhttp.send();
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4)
					if (xmlhttp.status == 200)
						ret = xmlhttp.responseText;
				if (ret == 1) {
					alert("删除成功！");
					document.getElementById("Search").click();
				}

			};
		}
		if (ret == 0)
			alert("删除失败请重试！");
	}

	function AllKeyWords() {
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		var url = "./SearchKey?param="
				+ document.getElementById("searchText").value;
		xmlhttp.open("GET", url, true);
		xmlhttp.send();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4)
				if (xmlhttp.status == 200)
					document.getElementById("tables").innerHTML = xmlhttp.responseText;

		};
	}

	function GetKey(name, id) {
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		var data = "";
		var url = "";
		url = "./GetKey?" + name + "=" + id;
		xmlhttp.open("GET", url, true);
		xmlhttp.send();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4)
				if (xmlhttp.status == 200) {
					data = xmlhttp.responseText;
					var obj = eval("(" + data + ")");
					if (name == "id") {
						document.getElementById("id").value = obj["id"];
						document.getElementById("type").value = obj["type"];
						document.getElementById("keyName").value = obj["keyName"];
						document.getElementById("describes").value = obj["describes"];
						document.getElementById("useCase").value = obj["useCase"];
					} else {
						document.getElementById("id").value = obj["id"];
					}
				}
		};
	}
</script>

<body onload="AllKeyWords()">
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
				<li class="active_tab i_32_dashboard"><a href="index.jsp"
					title="General Info"> <span class="tab_label">Home Page</span>
						<span class="tab_info">Frame Info</span>
				</a></li>
				<li class="i_32_charts"><a href="static/UI.jsp"
					title="Visual Data"> <span class="tab_label">UI自动化</span> <span
						class="tab_info">Edit and Run</span>
				</a></li>
				<li class="i_32_inbox"><a href="static/inbox.jsp"
					title="Your Messages"> <span class="tab_label">用例生成</span> <span
						class="tab_info">Interface Case</span>
				</a></li>
				<li class="i_32_ui"><a href="static/Interface.jsp"
					title="Kit elements"> <span class="tab_label">接口测试</span> <span
						class="tab_info">Interface Run</span>
				</a></li>
				<li class="i_32_tables"><a href="static/tables.jsp"
					title="Some Rows"> <span class="tab_label">调试工具</span> <span
						class="tab_info">Res Table</span>
				</a></li>
			</ul>
		</aside>
		<div class="contents">
			<div class="grid_wrapper">

				<div class="g_6 contents_header">
					<h3 class="i_16_message tab_label">KeyWords</h3>
					<div>
						<span class="label">Your KeyWord Discribe And Use Demo</span>
					</div>
				</div>

				<div id="EditBox1">
					<div class="row1">
						添加关键字<a href="javascript:void(0)" title="关闭窗口" class="close_btn"
							id="closeBtn">X</a>
					</div>
					<form id="EditForm1" action="./updateKeyWords" method="post">
						<p class="p_input">
						<div class="p_cli1">
							Id:<input id="id" name="id" type="text" value="NULL"
								class="c_input"
								onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
								onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}'
								readonly />&nbsp type:<input id="type" name="type" type="text"
								value="NULL" class="c_input"
								onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
								onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />&nbsp
							keyName:<input id="keyName" name="keyName" type="text"
								value="NULL" class="c_input"
								onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
								onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />&nbsp
							describes:<input id="describes" name="describes" type="text"
								value="NULL" class="c_input"
								onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
								onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />
							useCase:<input id="useCase" name="useCase" type="text"
								value="NULL" class="c_input"
								onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
								onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />
						</div>
					</form>
					<div class="row2">
						<a
							href="javascript:document:EditForm1.submit(); javascript:document:closeBtn.click();"
							id="loginbtn">保存</a> <a href="#" id="Cancelbtn"
							onclick='document.getElementById("closeBtn").click();'>取消</a>
					</div>
				</div>

				<div class="g_12">
					<form id="CheckForm" method="get">
						<div class="widget_header">
							<h4 class="widget_header_title wwIcon i_16_charts">Options:&nbsp&nbsp</h4>
							<img title="添加用例场景"
								src="/static/Images/Icons/16/add.png"
								style="position: relative; top: 2px; left: 20px; cursor: pointer;"
								onclick='pops("pop1");GetKey("idadd",this.name);' /> <input
								id="searchText" type="text" value="Search"
								class="c_input_Search"
								onkeydown='if(event.keyCode==13){AllKeyWords()}'
								onfocus='if(this.value=="Search"){this.value=""}'
								onblur='if(this.value==""){this.value="Search"}' /> <img
								title="搜索 " src="/static/Images/Icons/16/Search.png"
								style="position: relative; top: 11px; left: 125px; cursor: pointer; float: right;"
								onclick='AllKeyWords()' />
						</div>
						<section id="conter">
							<section id="help-left">
								<a href="#" id="pop1"> </a>
								<div id="tables"
									style="width: 100%; height: 400px; overflow: auto;"></div>
							</section>

							<section id="help-right"></section>

						</section>

					</form>
				</div>
			</div>
		</div>
	</div>

	<footer> </footer>
</body>
</html>