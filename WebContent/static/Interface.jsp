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

<script>
	$(function($) {
		$("#pop").hover(function() {
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
			$("#EditBox").fadeIn("slow");
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
			$("#EditBox").fadeOut("fast");
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

	$(function($) {
		$("#pop_json").hover(function() {
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
			$("#Box_json").fadeIn("slow");
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
			$("#Box_json").fadeOut("fast");
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
</script>


<script>
	function selectAll() {
		var checkbox = document.getElementsByClassName("simple_form");
		if (checkbox[0].checked) {
			for ( var i = 0; i < checkbox.length; i++) {
				checkbox[i].checked = true;
			}
		} else {
			for ( var i = 0; i < checkbox.length; i++) {
				checkbox[i].checked = false;
			}
			;
		}
		;
	}

	function checkAll() {
		var a = document.getElementsByTagName("input");
		var i = 0;
		for (i; i < a.length; i++) {
			if (a[i].type == "checkbox")
				break;
		}
		if (a[i].checked == true) {
			for (i = i + 1; i < a.length; i++) {
				if (a[i].type == "checkbox")
					a[i].checked = true;
			}
		} else {
			for (i = i + 1; i < a.length; i++) {
				if (a[i].type == "checkbox")
					a[i].checked = false;
			}
		}
	}

	function SearchAll() {
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		var url = "../interfaceSearch?param="
				+ document.getElementById("searchText").value;
		xmlhttp.open("GET", url, true);
		xmlhttp.send();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4)
				if (xmlhttp.status == 200)
					document.getElementById("tables").innerHTML = xmlhttp.responseText;

		};
	}

	function pops(id) {
		document.getElementById(id).click();
	}

	function interfaceGetInfo(name, id, ele) {
		if (document.getElementById("cases_Edits") != null)
			alert("您的上一次修改还没保存，请先保存。");
		else {
			var xmlhttp;
			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else {// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			var data = "";
			var url = "";
			url = "../interfaceGetinfo?" + name + "=" + id;
			xmlhttp.open("GET", url, true);
			xmlhttp.send();
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4)
					if (xmlhttp.status == 200) {
						data = xmlhttp.responseText;
						if (name == "nameadd") {
							var obj = eval("(" + data + ")");
							document.getElementById("sceneId1").value = obj["sceneId"];
							document.getElementById("Description").value = obj["Description"];
							document.getElementById("delay").value = obj["delay"];
							document.getElementById("url").value = obj["url"];
							document.getElementById("runStates").value = obj["runStates"];
						} else if (name == "id") {
							ele.parentNode.parentNode.value = ele.parentNode.parentNode.innerHTML;
							//alert(ele.parentNode.value);
							ele.parentNode.style.height = "100px";
							//alert(data);
							ele.parentNode.innerHTML = data;
							if (document.getElementsByName("parameter")[1].value != null) {
								var s = document.getElementsByName("parameter")[1].value;
								while (s.indexOf("'") > 0)
									s = s.replace("'", "\"");
								document.getElementsByName("parameter")[1].value = s;
							}
							if(document.getElementsByName("jsonResult")[1].value!=null){
								var s = document.getElementsByName("jsonResult")[1].value;
								while(s.indexOf("'")>0)
									s = s.replace("'","\"");
								document.getElementsByName("jsonResult")[1].value = s;
							}
						} else if (name == "name") {
							ele.parentNode.value = ele.parentNode.innerHTML;
							//alert(ele.parentNode.value);
							ele.parentNode.style.height = "120px";
							ele.parentNode.style.textIndent = "0px";
							var e=document.getElementsByName("check");
							for(var i=0;i<e.length;i++){
								if(e[i].value==id){
									e[i].style.display="none"
								}
							}
							//alert(data);
							ele.parentNode.innerHTML = data;
						} else {
							var obj = eval("(" + data + ")");
							document.getElementById("sceneId").value = obj["sceneId"];
							document.getElementById("id").value = obj["id"];
							document.getElementById("delay").value = obj["delay"];
							document.getElementById("url").value = obj["url"];
							document.getElementById("parameter").value = obj["parameter"];
						}
					}
			};
		}
	}

	function setInfo(me, ele) {
		if (me == "name") {
			ele.parentNode.style.height = "40px";
			ele.parentNode.style.textIndent = "10px";
			var e=document.getElementsByName("check");
			for(var i=0;i<e.length;i++){
				e[i].style.display=""
			}
			ele.parentNode.innerHTML = ele.parentNode.value;
		} else {
			ele.parentNode.style.height = "25px";
			ele.parentNode.parentNode.innerHTML = ele.parentNode.parentNode.value;
		}
	}

	function delInterfaceCase(name, id) {
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
			if (name == "name")
				url = "../delInterfaceCase?name=" + id;
			else
				url = "../delInterfaceCase?id=" + id;
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

	function runTest() {
		var a = document.getElementsByTagName("input");
		var str = "../runInterfaceTest?";
		for ( var i = 0; i < a.length; i++) {
			if (a[i].type == "checkbox" && a[i].value != "0"
					&& a[i].checked == true)
				str += "check=" + a[i].value + "&";
		}
		str = str.substring(0, str.length - 1);
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.open("GET", str, true);
		xmlhttp.send();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			}
		};
	}

	function Show_json(id) {
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		var data = "";
		var url = "";
		url = "../interfaceGetinfo?getJson=" + id;
		xmlhttp.open("GET", url, true);
		xmlhttp.send();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4)
				if (xmlhttp.status == 200) {
					data = xmlhttp.responseText;
					document.getElementById("jsonText").value = data;
				}
		};
	}
</script>


<body onload="SearchAll()">
	<header class="main_header">
		<div class="wrapper">
			<div class="logo">
				<a href="index.jsp" Title="autoTest框架"> <img
					src="/static/Images/Testlogo.png" alt="Testlogo">
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
				<li class="i_32_charts"><a href="UI.jsp" title="Visual Data">
						<span class="tab_label">UI自动化</span> <span class="tab_info">Edit
							and Run</span>
				</a></li>
				<li class="i_32_inbox"><a href="inbox.jsp"
					title="Your Messages"> <span class="tab_label">用例生成</span> <span
						class="tab_info">Interface Case</span>
				</a></li>
				<li class="active_tab i_32_ui"><a href="Interface.jsp"
					title="Kit elements"> <span class="tab_label">接口测试</span> <span
						class="tab_info">Interface Run</span>
				</a></li>
				<li class="i_32_tables"><a href="tables.jsp" title="Some Rows">
						<span class="tab_label">调试工具</span> <span class="tab_info">Res
							Table</span>
				</a></li>
			</ul>
		</aside>

		<div id="EditBox1">
			<div class="row1">
				添加用例<a href="javascript:void(0)" title="关闭窗口" class="close_btn"
					id="closeBtn">X</a>
			</div>
			<form id="EditForm1" action="../UpdateInterfaceCase" method="post">
				<p class="p_input">
				<div class="p_cli1">
					sceneId:<input id="sceneId1" name="sceneId" type="text"
						value="NULL" class="c_input"
						onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
						onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}'
						readonly />&nbsp Description:<input id="Description"
						name="Description" type="text" value="NULL" class="c_input"
						onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
						onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />&nbsp
					delay:<input id="delay" name="delay" type="text" value="0"
						class="c_input"
						onfocus='if(this.value=="" || this.value=="undefined"){this.value="0"}'
						onblur='if(this.value=="" || this.value=="undefined"){this.value="0"}' />
					url:<input id="url" name="url" type="text" value="NULL"
						class="c_input"
						onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
						onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />
					runStates:<input id="runStates" name="runStates" type="text"
						value="N/A" class="c_input"
						onfocus='if(this.value=="N/A" || this.value=="undefined"){this.value=""}'
						onblur='if(this.value=="" || this.value=="undefined"){this.value="N/A"}' />
				</div>
			</form>
			<div class="row2">
				<a
					href="javascript:document:EditForm1.submit(); javascript:document:closeBtn.click();"
					id="loginbtn">保存</a> <a href="#" id="Cancelbtn"
					onclick='document.getElementById("closeBtn").click();'>取消</a>
			</div>
		</div>

		<div id="EditBox">
			<div class="row1">
				添加用例<a href="javascript:void(0)" title="关闭窗口" class="close_btn"
					id="closeBtn">X</a>
			</div>
			<form id="EditForm" action="../UpdateInterfaceCase" method="post">
				<p class="p_input">
				<div class="p_cli1" name="p_cli1">
					id:&nbsp<input id="id" name="id" type="text" value="NULL"
						class="c_input"
						onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value="";}'
						onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL";}'
						readonly /> sceneId:&nbsp<input id="sceneId" name="sceneId"
						type="text" value="NULL" class="c_input"
						onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value="";}'
						onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />
					order_id:&nbsp<input id="order_id" name="order_id" type="text"
						value="NULL" class="c_input"
						onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
						onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />
					method:<input id="method" name="method" type="text" value="NULL"
						class="c_input"
						onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
						onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />
					url:<input id="url" name="url" type="text" value="NULL"
						class="c_input"
						onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
						onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />
				</div>
				<div class="p_cli2">
					parameter:<input id="parameter" name="parameter" type="text"
						value="NULL" class="c_input"
						onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
						onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />
					checkName:<input id="checkName" name="checkName" type="text"
						value="NULL" class="c_input"
						onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
						onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />
					checkMethod:<input id="checkMethod" name="checkMethod" type="text"
						value="NULL" class="c_input"
						onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
						onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />
					expRes:<input id="expRes" name="expRes" type="text" value="NULL"
						class="c_input"
						onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
						onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />
				</div>
				<div class="p_cli3">
					actualRes:<input id="actualRes" name="actualRes" type="text"
						value="NULL" class="c_input"
						onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
						onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />
					jsonResult:<input id="jsonResult" name="jsonResult" type="text"
						value="NULL" class="c_input"
						onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
						onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />
					Description:<input id="Description" name="Description" type="text"
						value="NULL" class="c_input"
						onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
						onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />
					runState:<input id="runState" name="runState" type="text"
						value="N/A" class="c_input"
						onfocus='if(this.value=="N/A" || this.value=="undefined"){this.value=""}'
						onblur='if(this.value=="" || this.value=="undefined"){this.value="N/A"}' />
				</div>
			</form>
			<div class="row">
				<a href="javascript:document:EditForm.submit();" id="loginbtn">保存</a>
				<a href="#" id="Cancelbtn"
					onclick='document.getElementById("closeBtn").click();'>取消</a>
			</div>
		</div>

		<div id="Box_json">
			<div class="row1">
				Json返回值<a href="javascript:void(0)" title="关闭窗口" class="close_btn"
					id="closeBtn">X</a>
			</div>
			<p class="p_input">
			<div class="p_cli1">
				<textarea id="jsonText"
					style="margin: 0px; width: 671px; height: 150px;">
					请将意见输入此区域
				</textarea>
			</div>
		</div>

	</div>

	<div class="contents">
		<div class="grid_wrapper">

			<div class="g_6 contents_header">
				<h3 class="i_16_message tab_label">Interface Runner</h3>
				<div>
					<span class="label"> Your Interface Cases Search, Add, Edit
						And Run</span>
				</div>
			</div>

			<div class="g_12">
				<input class="checkboxM" type="checkbox" value="0"
					onclick="checkAll()" />
				<div class="widget_header">
					<h4 class="widget_header_title wwIcon i_16_charts">Options:</h4>
					<img title="添加用例场景" src="/static/Images/Icons/16/add.png"
						style="position: relative; top: 2px; left: 20px; cursor: pointer;"
						onclick='pops("pop1");interfaceGetInfo("nameadd",this.name,this);' />
					<img title="执行选中用例" src="/static/Images/Icons/16/run.png"
						style="position: relative; top: 2px; left: 20px; cursor: pointer; width: 18px"
						onclick='runTest()' /> <img title="调试接口"
						src="/static/Images/Icons/16/debug.png"
						style="position: relative; top: 2px; left: 20px; cursor: pointer; width: 18px"
						onclick='debugCase()' /> <input id="searchText" type="text"
						value="Search" class="c_input_Search"
						onkeydown='if(event.keyCode==13){SearchAll()}'
						onfocus='if(this.value=="Search"){this.value=""}'
						onblur='if(this.value==""){this.value="Search"}' /> <img
						id="Search" title="搜索"
						src="/static/Images/Icons/16/Search.png"
						style="position: relative; top: 11px; left: 125px; cursor: pointer; float: right;"
						onclick='SearchAll()' />
				</div>
				<section id="conter">
					<section id="help-left">
						<a href="#" id="pop"> </a> <a href="#" id="pop1"> </a> <a href="#"
							id="pop_json"> </a>
						<div id="tables"
							style="width: 100%; height: 400px; overflow: auto;"></div>
					</section>
				</section>
			</div>
		</div>
	</div>
</body>
</html>