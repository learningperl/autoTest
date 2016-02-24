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
	<link href="http://fonts.useso.com/css?family=Oswald|Droid+Sans:400,700" rel="stylesheet">
	<!-- The Main CSS File -->
	<link rel="stylesheet" href="CSS/style.css">
	<!-- jQuery -->
	<script src="Javascript/jQuery/jquery-1.7.2.min.js"></script>
	<!-- DataTables -->
	<script src="Javascript/DataTables/jquery.dataTables.min.js"></script>
	<!-- ColResizable -->
	<script src="Javascript/ColResizable/colResizable-1.3.js"></script>
	<!-- jQuryUI -->
	<script src="Javascript/jQueryUI/jquery-ui-1.8.21.min.js"></script>
	<!-- Uniform -->
	<script src="Javascript/Uniform/jquery.uniform.js"></script>
	<!-- ColorPicker -->
	<script src="Javascript/ColorPicker/colorpicker.js"></script>
	<!-- UISpinner -->
	<script src="Javascript/UISpinner/ui.spinner.js"></script>
	<!-- ClEditor -->
	<script src="Javascript/ClEditor/jquery.cleditor.js"></script>
	<!-- Color Box -->
	<script src="Javascript/ColorBox/jquery.colorbox.js"></script>
	<!-- Kanrisha Script -->
	<script src="Javascript/kanrisha.js"></script>
</head>
<body onload="../Main">
	<header class="main_header">
		<div class="wrapper">
			<div class="logo">
				<a href="#" Title="Kanrisha Home">
					<img src="Images/kanrisha_logo.png" alt="kanrisha_logo">
				</a>
			</div>
	</header>

	<div class="wrapper small_menu">
		<ul class="menu_small_buttons">
			<li><a title="General Info" class="i_22_dashboard" href="../index.jsp"></a></li>
			<li><a title="Your Messages" class="i_22_inbox smActive" href="inbox.jsp"></a></li>
			<li><a title="Visual Data" class="i_22_charts" href="charts.html"></a></li>
			<li><a title="Kit elements" class="i_22_ui" href="ui.html"></a></li>
			<li><a title="Some Rows" class="i_22_tables" href="tables.jsp"></a></li>
		</ul>
	</div>

	<div class="wrapper contents_wrapper">
		
		<aside class="sidebar">
			<ul class="tab_nav">
				<li class="i_32_dashboard">
					<a href="../index.jsp" title="General Info">
						<span class="tab_label">Dashboard</span>
						<span class="tab_info">General Info</span>
					</a>
				</li>
				<li class="active_tab i_32_inbox">
					<a href="inbox.jsp" title="Your Messages">
						<span class="tab_label">Inbox</span>
						<span class="tab_info">Your Messages</span>
					</a>
				</li>
				<li class="i_32_charts">
					<a href="charts.html" title="Visual Data">
						<span class="tab_label">Charts</span>
						<span class="tab_info">Visual Data</span>
					</a>
				</li>
				<li class="i_32_ui">
					<a href="ui.html" title="Kit elements">
						<span class="tab_label">UI</span>
						<span class="tab_info">Kit elements</span	>
					</a>
				</li>
				<li class="i_32_tables">
					<a href="tables.jsp" title="Some Rows">
						<span class="tab_label">Tables</span>
						<span class="tab_info">Some Rows</span>
					</a>
				</li>
			</ul>
		</aside>
		<div class="contents">
			<div class="grid_wrapper">

				<div class="g_6 contents_header">
					<h3 class="i_16_message tab_label">Inbox</h3>
					<div><span class="label">Your Tickets, Chats, Email</span></div>
				</div>

				<div class="g_12">
					<div class="widget_contents noPadding twCheckbox" style="width:1130px;height:460px">
						<div class="table-body">
							<table class="tables datatable noObOLine">
								<thead>
									<tr>
										<th>
											<input type="checkbox" class="simple_form tMainC">
										</th>
										<th>ID</th>
										<th>Title</th>
										<th>Priority</th>
										<th>Status</th>
									</tr>
								</thead>
								<tbody>
									<% 
										ArrayList myList = (ArrayList)session.getAttribute("list");
										Map<String,String> map = new HashMap<String,String>();
										for (int i=0;i<myList.size();i++){
											map = (Map)myList.get(i);
									%>
										<tr class="status_open" id="tb_first">
											<td><input type="checkbox" class="simple_form"></td>
											<td><%=map.get("ID")%></td>
											<td><%=map.get("Title")%></td>
											<td><%=map.get("Priority")%></td>
											<td><%=map.get("Status")%></td>
										</tr>
									<% } %>
									</table>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>