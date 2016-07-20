<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
<meta charset="UTF-8">
<!-- The Main CSS File -->
<link rel="stylesheet" href="./CSS/style.css">
<script src="./Javascript/jquery.min.js"></script>
<script src="./Javascript/Interface.js"></script>
</head>

<body onload="SearchAll()">
	<header>
		<div class="logo">
			<a href="../index.jsp" Title="autoTest框架"> <img
				src="./Images/Testlogo.png" alt="Testlogo">
			</a>
		</div>
	</header>

		<aside class="sidebar">
			<ul class="tab_nav">
				<li class="i_32_dashboard"><a href="../index.jsp"
					title="General Info"> <span class="tab_label">Home Page</span>
						<span class="tab_info">Frame Info</span>
				</a></li>
				<li class="i_32_charts"><a href="./UI.jsp" title="Visual Data">
						<span class="tab_label">UI自动化</span> <span class="tab_info">Edit
							and Run</span>
				</a></li>
				<li class="i_32_inbox"><a href="./inbox.jsp"
					title="Your Messages"> <span class="tab_label">用例生成</span> <span
						class="tab_info">Interface Case</span>
				</a></li>
				<li class="active_tab i_32_ui"><a href="./Interface.jsp"
					title="Kit elements"> <span class="tab_label">接口测试</span> <span
						class="tab_info">Interface Run</span>
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
					<h3 class="i_16_message tab_label">Interface Runner</h3>
					<div>
						<span class="label"> Your Interface Cases Search, Add, Edit
							And Run</span>
					</div>
				</div>
				
			<div id="EditBox1">
				<div class="row1">
					添加用例<a href="javascript:void(0)" title="关闭窗口" class="close_btn"
						id="closeBtn">X</a>
				</div>
				<form id="EditForm1" onsubmit="AjaxSubmit('EditForm1')" method="post">
					<p class="p_input">
					<div class="p_cli1">
						sceneId:<input id="sceneId1" name="sceneId" type="text"
							value="NULL" class="c_input"
							onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
							onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}'
							readonly /> Description:<input id="Description"
							name="Description" type="text" value="NULL" class="c_input"
							onfocus='if(this.value=="NULL" || this.value=="undefined"){this.value=""}'
							onblur='if(this.value=="" || this.value=="undefined"){this.value="NULL"}' />
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
						href="javascript:AjaxSubmit('EditForm1');"
						id="loginbtn">保存</a> <a href="#" id="Cancelbtn"
						onclick='document.getElementById("closeBtn").click();'>取消</a>
				</div>
			</div>
	
			<div id="EditBox">
				<div class="row1">
					添加用例<a href="javascript:void(0)" title="关闭窗口" class="close_btn"
						id="closeBtn">X</a>
				</div>
				<form id="EditForm" onsubmit="AjaxSubmit('EditForm')" method="post">
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
						method:<select id="keyOption" style="width: 110px"><option value="NULL">NULL</option></select>
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
						checkMethod:<select id="keyMethod" style="width: 110px"><option value="NULL">NULL</option></select>
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
					<a href="javascript:AjaxSubmit('EditForm');" id="loginbtn">保存</a>
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
			
			<div id="msgBox">
					<div class="row1">
						异常信息！<a href="javascript:void(0)" title="关闭窗口" class="close_btn_msg"
							id="closeBtn_msg">X</a>
					</div>
					<form id="msgForm" name="msgForm" action="" method="post">
						<h3 style="text-align: center;">
							<br>
						  <font id="msgText" color="red">
							  服务器忙...
 						</font></h3>
					</form>
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
							id="loginbtn" onclick='delInterfaceCase(document.getElementById("delForm").name,document.getElementById("EditForm").name)'>删除</a> <a href="#" id="Cancelbtn"
							onclick='document.getElementById("closeBtn").click();'>取消</a>
					</div>
				</div>
	
				<div class="g_t">
					<input class="checkboxM" type="checkbox" value="0"
						onclick="checkAll()" />
					<div class="widget_header">
						<h4 class="widget_header_title wwIcon i_16_charts">Options:</h4>
						<img title="添加用例场景" src="./Images/Icons/16/add.png"
							style="position: relative; top: 2px; left: 20px; cursor: pointer;"
							onclick='pops("pop1");interfaceGetInfo("nameadd",this.name,this);' />
						<img title="执行选中用例" src="./Images/Icons/16/run.png"
							style="position: relative; top: 2px; left: 20px; cursor: pointer; width: 18px"
							onclick='runTest()' /> <input id="searchText" type="text"
							value="Search" class="c_input_Search"
							onkeydown='if(event.keyCode==13){SearchAll()}'
							onfocus='if(this.value=="Search"){this.value=""}'
							onblur='if(this.value==""){this.value="Search"}' /> <img
							id="searchImg" title="搜索"
							src="./Images/Icons/16/Search.png"
							style="position: relative; top: 11px; left: 125px; cursor: pointer; float: right;"
							onclick='SearchAll()' />
					</div>
					<section id="conter">
						<section id="help-left">
							<a href="#" id="pop">
							</a> <a href="#" id="pop1">
							 </a> <a href="#" id="del"> </a>
							  <a href="#" id="pop_json"> </a>
							  <a href="#" id="pop_msg"> </a>
							<div id="tables"
								style="width: 100%;position: absolute;overflow: auto;height: 98%;"></div>
						</section>
					</section>
				</div>
			</div>
		</div>
</body>
</html>