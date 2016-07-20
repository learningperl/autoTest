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
	$("#pop2").hover(function() {
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
		$("#EditBox2").fadeIn("slow");
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
		$("#EditBox2").fadeOut("fast");
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
	setTimeout("disappeare()", 2000);
}
function disappeare() {
	document.getElementById('Msg').innerHTML = "";
}

function pops(id) {
	document.getElementById(id).click();
}

function popdel(id, id1) {
	document.getElementById(id).click();
	document.getElementById("delText").innerText = "删除操作是不可逆的，你确定删除这个用例？";
	document.getElementById("delForm").name = id1;
}

function delKey(id) {
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var url = "";
	document.getElementById("delText").innerText = "正在删除，请等待...";
	url = "./delKey?id=" + id;
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
	document.getElementById("delText").innerText = "删除操作是不可逆的，你确定删除这个关键字？";
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
					document.getElementById("keyName2").innerHTML = obj["keyName"];
					document.getElementById("Describe2").value = obj["describes"];
					document.getElementById("useCase2").value = obj["useCase"];
				} else {
					document.getElementById("id").value = obj["id"];
					document.getElementById("type").value = id;
				}
			}
	};
}

function AjaxSubmit() {
	var AjaxURL = "./updateKeyWords";
	$.ajax({
		type : "POST",
		dataType : "html",
		url : AjaxURL,
		data : $('#EditForm1').serialize(),
		success : function(result) {
			var obj = eval("(" + result + ")");
			if (obj["error"] == "0") {
				document.getElementById("closeBtn").click();
				AllKeyWords();
				document.getElementById("Msg").innerText = "添加成功！";
				operate();
			} else {
				document.getElementById("Msg").innerText = "添加失败，请重试！";
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