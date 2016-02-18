<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>

<!DOCTYPE HTML>
<html lang="en-US">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Test Frame</title>
<!--[if lt IE 9]>
		<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
		<script src="Javascript/Flot/excanvas.js"></script>
	<![endif]-->
<!-- The Fonts -->
<link href="http://fonts.useso.com/css?family=Oswald|Droid+Sans:400,700"
	rel="stylesheet" />
<!-- The Main CSS File -->
<link rel="stylesheet" href="static/CSS/style.css" />	
<!-- jQuery -->

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body onload="autoFlush()">
	<!-- Top Panel -->

	<script>	
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
			}
			xmlhttp.open("GET", "./Logs", true);
			xmlhttp.send();
		}
		
		function autoFlush() {
				setInterval("loadXMLDoc()",2000);
		}
	</script>

	<header class="main_header">
		<div class="wrapper">
			<div class="logo">
				<a href="#" title="Kanrisha Home"> <img
					src="static/Images/kanrisha_logo.png" alt="kanrisha_logo" />
				</a>
			</div>
		</div>
	</header>

	<div class="wrapper small_menu">
		<ul class="menu_small_buttons">
			<li><a title="General Info" class="i_22_dashboard smActive"
				href="index.jsp"></a></li>
			<li><a title="Your Messages" class="i_22_inbox"
				href="static/inbox.jsp"></a></li>
			<li><a title="Visual Data" class="i_22_charts"
				href="static/charts.jsp"></a></li>
			<li><a title="Kit elements" class="i_22_ui"
				href="static/ui.html"></a></li>
			<li><a title="Some Rows" class="i_22_tables"
				href="static/tables.jsp"></a></li>
			<li><a title="Some Fields" class="i_22_forms"
				href="static/forms.html"></a></li>
		</ul>
	</div>

	<div class="wrapper contents_wrapper">

		<aside class="sidebar">
			<ul class="tab_nav">
				<li class="active_tab i_32_dashboard"><a href="index.jsp"
					title="General Info"> <span class="tab_label">Dashboard</span>
						<span class="tab_info">General Info</span>
				</a></li>
				<li class="i_32_inbox"><a href="static/inbox.jsp"
					title="Your Messages"> <span class="tab_label">Inbox</span> <span
						class="tab_info">Your Messages</span>
				</a></li>
				<li class="i_32_charts"><a href="static/charts.jsp"
					title="Visual Data"> <span class="tab_label">编辑/执行</span> <span
						class="tab_info">Edit and Run</span>
				</a></li>
				<li class="i_32_ui"><a href="static/ui.html"
					title="Kit elements"> <span class="tab_label">UI</span> <span
						class="tab_info">Kit elements</span>
				</a></li>
				<li class="i_32_tables"><a href="static/tables.jsp"
					title="Some Rows"> <span class="tab_label">Tables</span> <span
						class="tab_info">Some Rows</span>
				</a></li>
			</ul>
		</aside>

		<div class="contents">
			<div class="grid_wrapper">

				<div class="g_6 contents_header">
					<h3 class="i_16_dashboard tab_label">Dashboard</h3>
					<div>
						<span class="label">General Informations and Resume</span>
					</div>
				</div>

				<div class="g_12 separator">
					<span></span>
				</div>

				<form id="testForm" method="get" action="./Main">
					<a id="Test1" href="javascript:document:testForm.submit();">
						<div class="g_3 quick_stats">
							<h1 class="stats_info">编辑用例</h1>
						</div>
					</a>
				</form>

				<form id="Logs1" onsubmit="loadXMLDoc()">
					<a id="Test2" href="javascript:document:Logs.submit();">
						<div class="g_3 quick_stats">
							<h1 id="log1" class="stats_info">初始化</h1>
						</div>
					</a>
				</form>

				
				<form id="Logs2" onsubmit="autoFlush()" >
					<a id="Test3" href="javascript:document:Logs.submit();">
						<div class="g_3 quick_stats">
							<h1 id="log2" class="stats_info">执行结果</h1>
						</div>
					</a>
				</form>

				<div class="g_12 separator">
					<span></span>
				</div>
				<form id="Logs" onsubmit="loadXMLDoc()" >
					<a id="Test3" href="javascript:document:Logs.submit();">
						<div class="g_12 quick_stats">
							<h1 id="logs" class="stats_info">日志</h1>
						</div>
					</a>
				</form>
			</div>
		</div>
	</div>

	<footer>
		
	</footer>
	<div style="display: none">
		<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
			language='JavaScript' charset='gb2312'></script>
	</div>
</body>
</html>