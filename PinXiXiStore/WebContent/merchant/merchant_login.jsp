<%@page import="com.softeem.servlet.MerchantServlet"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>商家登录</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<style>
body{height:100%;background:#16a085;overflow:hidden;}
canvas{z-index:-1;position:absolute;}
</style>
<script src="js/jquery.js"></script>
<script src="js/verificationNumbers.js"></script>
<script src="js/Particleground.js"></script>
<script type="text/javascript" src="layer/layer.js"></script>
<link rel="stylesheet" href="layui/css/layui.css" />
<script type="text/javascript" src="layui/layui.js" ></script>
<script>

$(document).ready(function() {
  //粒子背景特效
  $('body').particleground({
    dotColor: '#5cbdaa',
    lineColor: '#5cbdaa'
  })
 
})
</script>
</head>
<body>
	<form  class="layui-form" action="merchant" method="get">
	<input type="hidden" name="method" value="login">
			<dl class="admin_login ">
				 <dt>
				  <strong>PinXiXi商家管理系统</strong>
				  <em>PinXiXi Merchant Management System</em>
				 </dt>
				 <dd class="user_icon">
				  <input type="text" placeholder="手机号" name="login_phone"  lay-verify="required" lay-reqtext="电话号码是必填项，岂能为空" class="login_txtbx" id="login_phone"  />
				 </dd>
				 <dd class="pwd_icon">
				  <input type="password" placeholder="密码" name="login_password" lay-verify="required" lay-reqtext="密码不能为空"  class="login_txtbx" id="login_password"/>
				 </dd>
<!-- 				 <dd class="val_icon"> -->
<!-- 				  <div class="checkcode"> -->
<!-- 				    <input type="text" id="J_codetext" placeholder="验证码" maxlength="4" class="login_txtbx"> -->
<%-- 				    <canvas class="J_codeimg" id="myCanvas" onclick="createCode()">对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas>	 --%>
<!-- 				  </div> -->
<!-- 				 </dd> -->
				 <dd>
				  <input type="submit" value="立即登陆" class="submit_btn_log" lay-submit="" onclick="login_btn();"/>
				 </dd>
				  <dd>
						  <a href="merchant_reg.jsp" class="a1">免费注册</a>
						  <a href="forgetpwd.jsp" class="a2">忘记密码？</a>
				 </dd>
				 <dd>
				  <p>© PinXiXi商城版权所有</p>
				 </dd>
			</dl>
</form>
	 <script>
	 $(function(){
		 
		if('${logerror}'){
			layer.msg('手机号或者密码错误')
		}
	})
		layui.use(['form'], function(){
		  var form = layui.form
		});
</script>
</body>
</html>
