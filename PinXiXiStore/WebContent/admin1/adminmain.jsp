<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>拼xixi后台管理</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="Access-Control-Allow-Origin" content="*">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="css/font_eolqem241z66flxr.css" media="all" />
	<link rel="stylesheet" href="css/main.css" media="all" />
</head>
<body class="main_body">
	<div class="layui-layout layui-layout-admin">
		<!-- 顶部 -->
		<div class="layui-header header">
			<div class="layui-main">
				<a onclick="shuaxin(this)" class="logo">拼XiXi后台管理</a>
			    <!-- 顶部右侧菜单 -->
			    <ul class="layui-nav top_menu">
			    	<li class="layui-nav-item showNotice" id="showNotice" >
						<a href="javascript:;"><i class="iconfont icon-gonggao"></i><cite>系统公告</cite></a>
					</li>
					<li class="layui-nav-item lockcms" >
						<a href="javascript:;"><i class="iconfont icon-lock1"></i><cite>锁屏</cite></a>
					</li>
					<li class="layui-nav-item" >
						<a href="javascript:;">
							<!-- 头像 -->
							<img src="/userphoto/${adminlogin.reserved3}" class="layui-circle" width="35" height="35">
							<cite>${adminlogin.adminname}</cite>
						</a>
						<dl class="layui-nav-child">
							<dd><a href="page/changephoto.jsp" ><i class="iconfont icon-edit"></i><cite>更改头像</cite></a></dd>
							<dd><a href="changePwd.jsp" data-url="changePwd.jsp"><i class="iconfont icon-shezhi1" data-icon="icon-shezhi1"></i><cite>修改密码</cite></a></dd>
							<dd><a href="javascript:void(0)"  onclick="tuichu(this)"><i class="iconfont icon-loginout"></i><cite>退出</cite></a></dd>
						</dl>
					</li>
				</ul>
			</div>
		</div>
		<!-- 左侧导航 -->
		<div class="layui-side layui-bg-black">
			
			<div class="navBar layui-side-scroll">
			<!-- 导航列表 -->
			<ul class="layui-nav layui-nav-tree">
				<li class="layui-nav-item layui-this">
					<a target="iframe" href="page/main.html"  >
					<i class="iconfont icon-computer" data-icon="icon-computer"></i>
					<cite>后台首页</cite>
					</a>
				</li>
				<li class="layui-nav-item">
				<a target="iframe" href="page/checkjoin.jsp"  >
				<i class="iconfont icon-text" data-icon="icon-text"></i>
				<cite>商家入住申请审核</cite>
				</a>
				</li>
				
				<li class="layui-nav-item">
				<a target="iframe" href="page/checkchange.jsp" >
				<i class="iconfont icon-text" data-icon="icon-text"></i>
				<cite>商品信息修改审核</cite>
				</a>
				</li>
				
				<li class="layui-nav-item">
				<a target="iframe" href="page/banthemerchant.jsp"  >
				<i class="iconfont icon-text" data-icon="icon-text"></i>
				<cite>强制封禁违规商家</cite>
				</a>
				</li>
				
				<li class="layui-nav-item">
				<a target="iframe" href="page/downthegoods.jsp" >
				<i class="iconfont icon-text" data-icon="icon-text"></i>
				<cite>强制下架违规商品</cite>
				</a>
				</li>
				
				<span class="layui-nav-bar" style="top: 22.5px; height: 0px; opacity: 0;"></span>
				</ul>
			
			</div>
		</div>
		<!-- 右侧内容 -->
		<div class="layui-body layui-form">
			<div class="layui-tab marg0">
				<div class="layui-tab-content clildFrame">
					<div class="layui-tab-item layui-show">
						<iframe name="iframe"  src="page/main.html"></iframe>
					</div>
				</div>
			</div>
		</div>
	</div>



	<!-- 锁屏 -->
	<div class="admin-header-lock" id="lock-box" style="display: none;">
		<div class="admin-header-lock-img"><img src="images/face.jpg"/></div>
		<div class="admin-header-lock-name" id="lockUserName">管理员</div>
		<div class="input_btn">
			<input type="password" class="admin-header-lock-input layui-input" placeholder="请输入密码解锁.." name="lockPwd" id="lockPwd" />
			<button class="layui-btn" id="unlock">解锁</button>
		</div>
	</div>
	


	<script type="text/javascript" src="layui/layui.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
	<script type="text/javascript">
		function tuichu(){
			location.href="admin?method=Admin_exit";
		}
		
		function shuaxin(){
			location.href="admin?method=Admin_loginByadminaccount&adminaccount="+${adminlogin.adminaccount}+"&adminpassword="+${adminlogin.adminpassword};
		}
		
	</script>
</body>
</html>