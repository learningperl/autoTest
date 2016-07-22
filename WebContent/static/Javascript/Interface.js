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

$(function($) {
	$("#pop_msg").hover(function() {
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
		$("#msgBox").fadeIn("slow");
	});
	$(".close_btn_msg").hover(function() {
		$(this).css({
			color : 'black'
		});
	}, function() {
		$(this).css({
			color : '#999'
		});
	}).on('click', function() {
		$("#msgBox").fadeOut("fast");
		$("#mask").css({
			display : 'none'
		});
	});
});

function operate() {
	setTimeout("disappeare()", 3000);
}
function disappeare() {
	document.getElementById('Msg').innerHTML = "";
}

function popdel(id1, id2) {
	document.getElementById("del").click();
	document.getElementById("delForm").name = id1;
	document.getElementById("EditForm").name = id2;
	document.getElementById("delText").innerText = "删除操作是不可逆的，你确定删除这个用例？";
}

function selectAll() {
	var checkbox = document.getElementsByClassName("simple_form");
	if (checkbox[0].checked) {
		for (var i = 0; i < checkbox.length; i++) {
			checkbox[i].checked = true;
		}
	} else {
		for (var i = 0; i < checkbox.length; i++) {
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

function getCookie(name) {
	var arr = document.cookie.split('; ');
	var i = 0;
	for (i = 0; i < arr.length; i++) {
		// arr2->['username', 'abc']
		var arr2 = arr[i].split('=');

		if (arr2[0] == name) {
			var getC = decodeURIComponent(arr2[1]);
			return getC;
		}
	}

	return '';
}

function keyOption() {
	var keyStr = getCookie("key3");
	comStr = comStr.replace(/ /g, '');
	keyStr = keyStr.replace('"', '');
	keyStr = keyStr.replace('"', '');
	var comStr = getCookie("key5");
	comStr = comStr.replace(/ /g, '');
	comStr = comStr.replace('"', '');
	comStr = comStr.replace('"', '');
	keyStr = keyStr + "," + comStr;
	var keySet = keyStr.split(',');
	var str = "<select id=\"keyOption\" style=\"width: 110px\"> <option value=\"NULL\">NULL</option>";
	for (i = 0; i < keySet.length; i++) {
		str += "<option value=\"" + keySet[i] + "\">" + keySet[i] + "</option>";
	}
	str += "</select>";
	document.getElementById("keyOption").innerHTML = str;

	keyStr = getCookie("key4");
	comStr = comStr.replace(/ /g, '');
	keyStr = keyStr.replace('"', '');
	keyStr = keyStr.replace('"', '');
	keySet = keyStr.split(',');
	str = "<select id=\"keyMethod\" style=\"width: 110px\"> <option value=\"NULL\">NULL</option>";
	for (i = 0; i < keySet.length; i++) {
		str += "<option value=\"" + keySet[i] + "\">" + keySet[i] + "</option>";
	}
	str += "</select>";
	document.getElementById("keyMethod").innerHTML = str;
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

function interfaceGetInfo(name, id, ele) {
	if (document.getElementById("cases_Edits") != null)
		document.getElementById("msgText").innerText = "您的上一次修改还没保存，请先保存。";
	else {
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera,
									// Safari
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
						if (document.getElementById("delay").value == "undefined")
							document.getElementById("delay").value = "0";
						document.getElementById("url").value = obj["url"];
						document.getElementById("runStates").value = obj["runStates"];
						if (document.getElementById("runStates").value == "undefined")
							document.getElementById("runStates").value = "N/A";
					} else if (name == "id") {
						ele.parentNode.parentNode.value = ele.parentNode.parentNode.innerHTML;
						// alert(ele.parentNode.value);
						ele.parentNode.style.height = "100px";
						ele.parentNode.style.border = "2px solid #00A4FF";
						ele.parentNode.style.margin = "10px 55px";
						// alert(data);
						ele.parentNode.innerHTML = data;
						if (document.getElementsByName("parameter")[1].value != null) {
							var s = document.getElementsByName("parameter")[1].value;
							while (s.indexOf("'") > 0)
								s = s.replace("'", "\"");
							document.getElementsByName("parameter")[1].value = s;
						}
						if (document.getElementsByName("jsonResult")[1].value != null) {
							var s = document.getElementsByName("jsonResult")[1].value;
							while (s.indexOf("'") > 0)
								s = s.replace("'", "\"");
							document.getElementsByName("jsonResult")[1].value = s;
						}
					} else if (name == "name") {
						ele.parentNode.value = ele.parentNode.innerHTML;
						// alert(ele.parentNode.value);
						ele.parentNode.style.height = "120px";
						ele.parentNode.style.border = "2px solid #00A4FF";
						ele.parentNode.style.margin = "10px 55px";
						ele.parentNode.style.textIndent = "0px";
						var e = document.getElementsByName("check");
						for (var i = 0; i < e.length; i++) {
							if (e[i].value == id) {
								e[i].style.display = "none"
							}
						}
						// alert(data);
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
		ele.parentNode.style.border = "";
		ele.parentNode.style.margin = "";
		var e = document.getElementsByName("check");
		for (var i = 0; i < e.length; i++) {
			e[i].style.display = ""
		}
		ele.parentNode.innerHTML = ele.parentNode.value;
	} else {
		ele.parentNode.style.height = "25px";
		ele.parentNode.parentNode.innerHTML = ele.parentNode.parentNode.value;
	}
}

function delInterfaceCase(name, id) {
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var url = "";
	document.getElementById("delText").innerText = "正在删除，请等待...";
	if (name == "name")
		url = "../delInterfaceCase?name=" + id;
	else
		url = "../delInterfaceCase?id=" + id;
	xmlhttp.open("GET", url, true);
	xmlhttp.send();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4)
			if (xmlhttp.status == 200) {
				var ret = xmlhttp.responseText;
				if (ret == 1) {
					document.getElementById("Msg").innerText = "删除成功！";
					operate();
					document.getElementById("closeBtn").click();
					document.getElementById("searchImg").click();
				} else
					document.getElementById("delText").innerText = "删除失败，请重试！";
			} else {
				document.getElementById("delText").innerText = "删除失败，请重试！";
			}
	};
}

function runTest() {
	var a = document.getElementsByTagName("input");
	var url = "../runInterfaceTest?";
	var str = "";
	for (var i = 0; i < a.length; i++) {
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
	if (flag == true) {
		document.getElementById("Msg").innerText = "用例场景：" + str + "正在运行...";
		document.getElementById("load").hidden = false;
		xmlhttp.open("GET", url, true);
		xmlhttp.send();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				var ret = xmlhttp.responseText;
				document.getElementById("Msg").innerText = "运行结果:" + ret;
				document.getElementById("load").hidden = true;
				operate();
			}
		};
	} else {
		document.getElementById("msgText").innerText = "当前有用例正在执行，请等待...";
	}
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

function AjaxSubmit(Form) {
	Form = "#" + Form
	var msg = "";
	if (Form == "#cases_Edits")
		msg = "编辑";
	else
		msg = "添加";
	var AjaxURL = "../UpdateInterfaceCase";
	$.ajax({
		type : "POST",
		dataType : "html",
		url : AjaxURL,
		data : $(Form).serialize(),
		success : function(result) {
			var obj = eval("(" + result + ")");
			if (obj["error"] == "0") {
				document.getElementById("closeBtn").click();
				SearchAll();
				document.getElementById("Msg").innerText = msg + "成功！";
				operate();
			} else {
				document.getElementById("Msg").innerText = msg + "失败，请重试！";
				operate();
				if (obj["msg"] != null) {
					pops("pop_msg");
					document.getElementById("msgText").innerText = obj["msg"];
				}
			}
		},
		error : function(data) {
			document.getElementById("msgText").innerText = "服务器忙...";
		}
	});
}