<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>修改密码--layui后台管理模板</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="css/user.css" media="all" />
</head>
<body class="childrenBody">
	<form class="layui-form changePwd" method="get"  action="admin" >
	 	<!-- 隐藏域 -->
        <input type="hidden" name="method" value="Admin_changeByadminaccount">
		<div class="layui-form-item">
		    <label class="layui-form-label">用户名</label>
		    <div class="layui-input-block">
		    	<input type="text" value="${adminlogin.adminname}" disabled class="layui-input layui-disabled">
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">管理员账号</label>
		    <div class="layui-input-block">
		    	<input type="password" value="" placeholder="请输入管理员账号" name="adminaccount" lay-verify="required|oldPwd" class="layui-input pwd">
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">新密码</label>
		    <div class="layui-input-block">
		    	<input type="password" value="" placeholder="请输入新密码" lay-verify="required|newPwd" id="oldPwd" class="layui-input pwd">
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">确认密码</label>
		    <div class="layui-input-block">
		    	<input type="password" value="" name="adminpassword" placeholder="请确认密码" lay-verify="required|confirmPwd" class="layui-input pwd">
		    </div>
		</div>
		<div class="layui-form-item">
		    <div class="layui-input-block">
				<button type="reset" class="layui-btn">重置</button>
		    	<button class="layui-btn" lay-submit="" lay-filter="changePwd">立即修改</button>
				<a href="adminmain.jsp" target="_blank"><button type="button" class="layui-btn">返回</button></a>
		    </div>
		</div>
	</form>
	<script type="text/javascript" src="layui/layui.js"></script>
</body>
</html>