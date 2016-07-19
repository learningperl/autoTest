<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
<meta charset="UTF-8">
<!-- The Main CSS File -->
<link rel="stylesheet" href="./CSS/style.css">
<script src="./Javascript/jQuery/jquery.min.js"></script>

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
		$("#pop_img").hover(function() {
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
			$("#Box_img").fadeIn("slow");
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
			$("#Box_img").fadeOut("fast");
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
		$("#del").hover(function() {
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
			$("#delBox").fadeIn("slow");
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
			$("#delBox").fadeOut("fast");
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
	
	function operate()
	{
	    setTimeout("disappeare()",3000);
	}
	function disappeare(){
	    document.getElementById('Msg').innerHTML="";
	}
	
	function popdel(id1,id2) {
		document.getElementById("del").click();
		document.getElementById("delForm").name = id1;
		document.getElementById("EditForm").name = id2;
		document.getElementById("delText").innerText="删除操作是不可逆的，你确定删除这个用例？";
	}

	function Show_img(img) {
		document.getElementById("Big_img").src = img;
	}

	function setInfo(me, ele) {
		if (me == "name") {
			ele.parentNode.style.height = "40px";
			ele.parentNode.style.border = "";
			ele.parentNode.style.margin = "";
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

	function getCookie(name)
	{
		var arr=document.cookie.split('; ');
		var i=0;
		for(i=0;i<arr.length;i++)
		{
			//arr2->['username', 'abc']
			var arr2=arr[i].split('=');
			
			if(arr2[0]==name)
			{	
				var getC = decodeURIComponent(arr2[1]);
				return getC;
			}
		}
		
		return '';
	}
	
	function keyOption(){
	 	var keyStr=getCookie("key1");
	 	keyStr = keyStr.replace('"','');
	 	keyStr = keyStr.replace('"','');
	 	var comStr=getCookie("key5");
	 	comStr = comStr.replace('"','');
	 	comStr = comStr.replace('"','');
	 	keyStr=keyStr+","+comStr;
	 	var keySet = keyStr.split(',');
		var str="<select id=\"keyOption\" style=\"width: 110px\"> <option value=\"NULL\">NULL</option>";
		for (i=0;i<keySet.length;i++){
			str+="<option value=\""+keySet[i]+"\">"+keySet[i]+"</option>";
		}
		str+="</select>";
		document.getElementById("keyOption").innerHTML=str;

		keyStr=getCookie("key2");
	 	keyStr = keyStr.replace('"','');
	 	keyStr = keyStr.replace('"','');
	 	keySet = keyStr.split(',');
		str="<select id=\"keyMethod\" style=\"width: 110px\"> <option value=\"NULL\">NULL</option>";
		for (i=0;i<keySet.length;i++){
			str+="<option value=\""+keySet[i]+"\">"+keySet[i]+"</option>";
		}
		str+="</select>";
		document.getElementById("keyMethod").innerHTML=str;
	}
	
	function GetInfo(name, id, ele) {
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
			url = "../GetInfo?" + name + "=" + id;
			xmlhttp.open("GET", url, true);
			xmlhttp.send();
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4)
					if (xmlhttp.status == 200) {
						data = xmlhttp.responseText;
						if (name == "nameadd") {
							var obj = eval("(" + data + ")");
							document.getElementById("casesId1").value = obj["casesId"];
							document.getElementById("casesN").value = obj["casesN"];
							document.getElementById("Browser").value = obj["Browser"];
							document.getElementById("Bpath").value = obj["Bpath"];
							document.getElementById("runStates").value = obj["runStates"];
						} else if (name == "id") {
							ele.parentNode.parentNode.value = ele.parentNode.parentNode.innerHTML;
							//alert(ele.parentNode.value);
							ele.parentNode.style.height = "100px"
							ele.parentNode.style.border = "2px solid #00A4FF";
							ele.parentNode.style.margin = "10px 55px";
							//alert(data);
							ele.parentNode.innerHTML = data;
							if(document.getElementsByName("xPath")[1].value!=null){
								var s = document.getElementsByName("xPath")[1].value;
								while(s.indexOf("'")>0)
									s = s.replace("'","\"");
								document.getElementsByName("xPath")[1].value = s;
							}
						} else if (name == "name") {
							ele.parentNode.value = ele.parentNode.innerHTML;
							//alert(ele.parentNode.value);
							ele.parentNode.style.height = "120px"
							ele.parentNode.style.border = "2px solid #00A4FF";
							ele.parentNode.style.margin = "10px 55px";
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
							document.getElementById("casesId").value = obj["casesId"];
							document.getElementById("id").value = obj["id"];
						}
					}
			};
		}
	}

	function SearchAll() {
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		var url = "../Search?param="
				+ document.getElementById("searchText").value;
		xmlhttp.open("GET", url, true);
		xmlhttp.send();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4)
				if (xmlhttp.status == 200)
					document.getElementById("tables").innerHTML = xmlhttp.responseText;

		};
		
		var xmlhttp1;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp1 = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp1 = new ActiveXObject("Microsoft.XMLHTTP");
		}
		url = "../keySet";
		xmlhttp1.open("GET", url, true);
		xmlhttp1.send();
		xmlhttp1.onreadystatechange = function() {
			if (xmlhttp1.readyState == 4)
				if (xmlhttp1.status == 200)
					document.getElementById("tables").innerHTML = xmlhttp.responseText;
	
		};
	}

	function pops(id) {
		document.getElementById(id).click();
	}

	function loadXMLDoc() {
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				document.getElementById("logs").innerText += xmlhttp.responseText;
			}
		};
		xmlhttp.open("GET", "../Logs", true);
		xmlhttp.send();
	}

	function delCase(name, id) {
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		var url = "";
		document.getElementById("delText").innerText="正在删除，请等待...";
		if (name == "name")
			url = "../delCase?name=" + id;
		else
			url = "../delCase?id=" + id;
		xmlhttp.open("GET", url, true);
		xmlhttp.send();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4)
				if (xmlhttp.status == 200){
					var ret = xmlhttp.responseText;
					if (ret == 1) {
						document.getElementById("Msg").innerText="删除成功！";
						operate();
						document.getElementById("closeBtn").click();
						document.getElementById("searchImg").click();
						}
					else
						document.getElementById("delText").innerText="删除失败，请重试！";
				}else
					document.getElementById("delText").innerText="删除失败，请重试！";
		};
	}

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

	function runTest() {
		var a = document.getElementsByTagName("input");
		var url = "../runTest?";
		var str = "";
		for ( var i = 0; i < a.length; i++) {
			if (a[i].type == "checkbox" && a[i].value != "0"
					&& a[i].checked == true)
				str += "check=" + a[i].value + "&";
		}
		str = str.substring(0, str.length - 1);
		url = url + str;
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		var flag = document.getElementById("load").hidden;
		if (flag == true){
			document.getElementById("Msg").innerText="用例场景："+ str + "正在运行...";
			document.getElementById("load").hidden=false;
			xmlhttp.open("GET", url, true);
			xmlhttp.send();
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					var ret = xmlhttp.responseText;
					document.getElementById("Msg").innerText="运行结果:"+ret;
					document.getElementById("load").hidden=true;
					operate();
				}
			};
		}else{
			alert("当前有用例正在执行，请等待...");
		}
	}

	function debugCase() {
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		var url = "../debug?id=" + document.getElementById("caseId").value;
		var flag = document.getElementById("load").hidden;
		if (flag == true){
			document.getElementById("Msg").innerText="用例正在调试...";
			document.getElementById("load").hidden=false;
			xmlhttp.open("GET", url, true);
			xmlhttp.send();
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4)
					if (xmlhttp.status == 200){
						var ret = xmlhttp.responseText;
						document.getElementById("Msg").innerText="运行结果:"+ret;
						document.getElementById("load").hidden=true;
						operate();
					}
			};
		}else{
			alert("当前有用例正在执行，请等待...");
		}
	}
	
	function AjaxSubmit(Form) {
		Form="#" + Form
		var msg = "";
		if(Form == "#cases_Edits")
			msg = "编辑";
		else
			msg = "添加";
		var AjaxURL = "../Update";
		$.ajax({
			type : "POST",
			dataType : "html",
			url : AjaxURL,
			data : $(Form).serialize(),
			success : function(result) {
				var obj = eval("(" + result + ")");
				if (obj["error"]=="0"){
					document.getElementById("closeBtn").click();
					SearchAll();
					document.getElementById("Msg").innerText = msg + "成功！";
					operate();
				}else{
					document.getElementById("Msg").innerText = msg + "失败，请重试！";
					operate();
					//alert(result);
				}
			},
			error : function(data) {
				document.getElementById("Msg").innerText = "服务器繁忙...";
				operate();
			}
		});
	}
	
</script>

<body onload="SearchAll()">
	<header>
		<div class="logo">
			<a href="../index.jsp" Title="autoTest框架"> <img
				src="./Images/Testlogo.png"
				alt="Testlogo">
			</a>
		</div>
	</header>

	<div class="wrapper small_menu">
		<ul class="menu_small_buttons">
			<li><a title="General Info" class="i_22_dashboard"
				href="./index.jsp"></a></li>
			<li><a title="Your Messages" class="i_22_inbox smActive"
				href="./inbox.jsp"></a></li>
			<li><a title="Visual Data" class="i_22_charts" href="./UI.jsp"></a></li>
			<li><a title="Kit elements" class="i_22_ui" href="./Interface.jsp"></a></li>
			<li><a title="Some Rows" class="i_22_tables" href="./tables.jsp"></a></li>
		</ul>
	</div>
	
		<aside class="sidebar">
			<ul class="tab_nav">
				<li class="i_32_dashboard"><a href="../index.jsp"
					title="General Info"> <span class="tab_label">Home Page</span>
						<span class="tab_info">Frame Info</span>
				</a></li>
				<li class="active_tab i_32_charts"><a href="./UI.jsp"
					title="Visual Data"> <span class="tab_label">UI自动化</span> <span
						class="tab_info">Edit and Run</span>
				</a></li>
				<li class="i_32_inbox"><a href="./inbox.jsp"
					title="Your Messages"> <span class="tab_label">用例生成</span> <span
						class="tab_info">Interface Case</span>
				</a></li>
				<li class="i_32_ui"><a href="./Interface.jsp" title="Kit elements">
						<span class="tab_label">接口测试</span> <span class="tab_info">Interface
							Run</span>
				</a></li>
				<li class="i_32_tables"><a href="./tables.jsp" title="Some Rows">
						<span class="tab_label">调试工具</span> <span class="tab_info">Res
							Table</span>
				</a></li>
			</ul>
		</aside>
		
		<div class="contents2">
			<div class="grid_wrapper">
				<h3 id="Msg" style=" position: absolute; top: 3px;  left: 42%; color: red;"></h3>
				<img id="load" title="正在运行" src="./Images/Loading.gif" style="position: absolute;left: 43%; top: 50px;" hidden>
				<div class="g_6 contents_header">
					<h3 class="i_16_message tab_label">UI &nbsp; Tester</h3>
					<div>
						<span class="label">Your UI Cases Search, Add, Edit And Run</span>
					</div>
				</div>

				<div id="Box_img">
					<a href="javascript:void(0)" title="关闭窗口" class="close_btn"
						id="closeBtn">X</a> <img id="Big_img" class="Big_img"
						src="./imgs/1.jpg"
						onclick='document.getElementById("closeBtn").click();'>
				</div>

				<div id="EditBox1">
					<div class="row1">
						添加用例场景<a href="javascript:void(0)" title="关闭窗口" class="close_btn"
							id="closeBtn">X</a>
					</div>
					<form id="EditForm1" method="post" onsubmit="AjaxSubmit('EditForm1')">
						<p class="p_input">
						<div class="p_cli1">
							casesId:<input id="casesId1" name="casesId" type="text"
								value="NULL" class="c_input"
								onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
								onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}'
								readonly />&nbsp;casesN:<input id="casesN" name="casesN"
								type="text" value="NULL" class="c_input"
								onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
								onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />
							Browser:<input id="Browser" name="Browser" type="text"
								value="NULL" class="c_input"
								onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
								onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />
							Bpath:<input id="Bpath" name="Bpath" type="text" value="NULL"
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
						<a	href="javascript:AjaxSubmit('EditForm1'); " id="loginbtn">保存</a> 
						<a href="#" id="Cancelbtn"	onclick='document.getElementById("closeBtn").click();'>取消</a>
					</div>
				</div>

				<div id="EditBox">
					<div class="row1">
						添加用例<a href="javascript:void(0)" title="关闭窗口" class="close_btn"
							id="closeBtn">X</a>
					</div>
					<form id="EditForm" name="EditForm" onsubmit="AjaxSubmit('EditForm')" method="post">
						<p class="p_input">
						<div class="p_cli1" name="p_cli1">
							id:&nbsp;<input id="id" name="id" type="text" value="NULL"
								class="c_input"
								onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value="";}'
								onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL";}'
								readonly /> casesId:&nbsp;<input id="casesId" name="casesId"
								type="text" value="NULL" class="c_input"
								onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value="";}'
								onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />
							order_id:&nbsp;<input id="order_id" name="order_id" type="text"
								value="NULL" class="c_input"
								onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
								onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />
							option:<select id="keyOption" style="width: 110px"><option value="NULL">NULL</option></select>
							xPath:<input id="xPath" name="xPath" type="text" value="NULL"
								class="c_input"
								onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
								onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />
						</div>
						<div class="p_cli2">
							datas:<input id="datas" name="datas" type="text" value="NULL"
								class="c_input"
								onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
								onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />
							checkMethod:<select id="keyMethod" style="width: 110px"><option value="NULL">NULL</option></select>
							checkName:<input id="checkName" name="checkName" type="text"
								value="NULL" class="c_input"
								onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
								onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />
							expectedRes:<input id="expectedRes" name="expectedRes"
								type="text" value="NULL" class="c_input"
								onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
								onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />
						</div>
						<div class="p_cli3">
							actualRes:<input id="actualRes" name="actualRes" type="text"
								value="NULL" class="c_input"
								onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
								onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />
							imgName:<input id="imgName" name="imgName" type="text"
								value="NULL" class="c_input"
								onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
								onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />
							Description:<input id="Description" name="Description"
								type="text" value="NULL" class="c_input"
								onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
								onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />
							runState:<input id="runState" name="runState" type="text"
								value="N/A" class="c_input"
								onfocus='if(this.value=="N/A" || this.value=="undefined"){this.value=""}'
								onblur='if(this.value=="" || this.value=="undefined"){this.value="N/A"}' />
						</div>
					</form>
					<div class="row">
						<a href="javascript:AjaxSubmit('EditForm');" id="loginbtn">保存</a>
						<a href="#" id="Cancelbtn"
							onclick='document.getElementById("closeBtn").click();'>取消</a>
					</div>
				</div>

				<div id="delBox">
					<div class="row1">
						删除测试用例<a href="javascript:void(0)" title="关闭窗口" class="close_btn"
							id="closeBtn">X</a>
					</div>
					<form id="delForm" name="delForm" action="" method="post">
						<h3 style="text-align: center;">
							<br>
						  <font id="delText" color="red">
							  删除操作是不可逆的，你确定删除这条测试用例？
 						</font></h3>
					</form>
					<div class="row2">
						<a  href="javascript:document:closeBtn.click();"
							id="loginbtn" onclick='delCase(document.getElementById("delForm").name,document.getElementById("EditForm").name)'>删除</a> <a href="#" id="Cancelbtn"
							onclick='document.getElementById("closeBtn").click();'>取消</a>
					</div>
				</div>

				<div class="g_t">
					<input class="checkboxM" type="checkbox" value="0"
						onclick="checkAll()" />
					<div class="widget_header">
						<h4 class="widget_header_title wwIcon i_16_charts">Options:</h4>
						<img
							title="添加用例场景" src="./Images/Icons/16/add.png"
							style="position: relative; top: 2px; left: 20px; cursor: pointer;"
							onclick='pops("pop1");GetInfo("nameadd",this.name,this);' />
						<img
							title="执行选中用例" src="./Images/Icons/16/run.png"
							style="position: relative; top: 2px; left: 20px; cursor: pointer; width: 18px"
							onclick='runTest()' />
						<input id="caseId" type="text" value="1"
							style="position: relative; left: 30px; width: 50px;"
							onkeydown='if(event.keyCode==13){debugCase()}'
							onblur = 'this.value = this.value.replace(/\D+/g, "");if(this.value==""){this.value="1";}' />
						<img
							title="调试选中用例" src="./Images/Icons/16/debug.png"
							style="position: relative; top: 2px; left: 28px; cursor: pointer; width: 18px"
							onclick='debugCase()' />
						<input id="searchText" type="text" value="Search"
							class="c_input_Search"
							onkeydown='if(event.keyCode==13){SearchAll()}'
							onfocus='if(this.value=="Search"){this.value=""}'
							onblur='if(this.value==""){this.value="Search"}' />
						<img
							id="searchImg" title="搜索" src="./Images/Icons/16/Search.png"
							style="position: relative; top: 11px; left: 125px; cursor: pointer; float: right;"
							onclick='SearchAll()' />
					</div>
					<section id="conter">
						<section id="help-left">
							<a href="#" id="pop"> </a> <a href="#" id="pop1"> </a> <a
								href="#" id="pop_img"> </a>
								<a href="#" id="del"> </a>
							<div id="tables"
								style="width: 100%;position: absolute;overflow: auto;height: 98%;"></div>
						</section>

						<section id="help-right"></section>

					</section>

				</div>
			</div>
		</div>
</body>
</html>