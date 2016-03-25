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
<body onload="">
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

	<div class="wrapper small_menu">
		<ul class="menu_small_buttons">
			<li><a title="General Info" class="i_22_dashboard"
				href="../index.jsp"></a></li>
			<li><a title="Your Messages" class="i_22_inbox smActive"
				href="inbox.jsp"></a></li>
			<li><a title="Visual Data" class="i_22_charts"
				href="UI.jsp"></a></li>
			<li><a title="Kit elements" class="i_22_ui" href="Interface.jsp"></a></li>
			<li><a title="Some Rows" class="i_22_tables" href="tables.jsp"></a></li>
		</ul>
	</div>

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
				<li class="active_tab i_32_inbox"><a href="inbox.jsp"
					title="Your Messages"> <span class="tab_label">用例生成</span> <span
						class="tab_info">Interface Case</span>
				</a></li>
				<li class="i_32_ui"><a href="Interface.jsp" title="Kit elements">
						<span class="tab_label">接口测试</span> <span class="tab_info">Interface Run</span>
				</a></li>
				<li class="i_32_tables"><a href="tables.jsp" title="Some Rows">
						<span class="tab_label">调试工具</span> <span class="tab_info">Res Table</span>
				</a></li>
			</ul>
		</aside>
		<div class="contents">
			<div class="grid_wrapper">

				<div class="g_6 contents_header">
					<h3 class="i_16_message tab_label">生成用例</h3>
					<div>
						<span class="label">Generate Your Interface Cases</span>
					</div>
				</div>

				<div class="g_12 separator">
					<span></span>
				</div>

				
				<div class="g_12">

					<h1>待实现</h1>
				</div>
			</div>
		</div>
	</div>
</body>
</html>