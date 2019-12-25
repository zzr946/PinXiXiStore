<%@page import="com.softeem.servlet.MerchantServlet"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>商家注册</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<style>
body{height:100%;background:#6A5ACD;overflow:hidden;}
canvas{z-index:-1;position:absolute;}
</style>
<script src="js/jquery.js"></script>
<script src="js/verificationNumbers.js"></script>
<script src="js/Particleground.js"></script>
 <script type="text/javascript" src="layer/layer.js"></script>
<link rel="stylesheet" href="layui/css/layui.css" />
  <script type="text/javascript" src="layui/layui.js" ></script>
 
<script>
$(function(){
	if('${checkerror}' == 1){
		layer.msg('验证码输入错误');
	}
})
$(document).ready(function() {
  //粒子背景特效
  $('body').particleground({
    dotColor: '#5cbdaa',
    lineColor: '#5cbdaa'
  });
});
</script>
</head>
<body>
	<form class="layui-form" action="merchant" method="get">
	<input type="hidden" name="method" value="reg">
		<dl class="admin_reg">
			 <dt>
			  <strong>PinXiXi商家管理系统</strong>
			  <em>PinXiXi Merchant Management System</em>
			 </dt>
			 <dd class="user_icon">
			  <input type="text" placeholder="手机号" name="reg_phone" lay-verify="required" lay-reqtext="电话号码是必填项，不能为空" class="login_txtbx" id="phone"  onblur="checkphone(this);"/>
			 </dd>
			 <dd class="pwd_icon">
			  <input type="password" name="reg_password" lay-verify="required" lay-reqtext="密码不能为空" placeholder="密码" class="login_txtbx"/>
			 </dd>
			 <dd class="val_icon">
			  <div class="checkcode">
			    <input type="text" id="J_codetext" name="reg_yzm" lay-verify="required" lay-reqtext="验证码不能为空" placeholder="验证码" maxlength="6" class="login_txtbx">
			  </div>
			  <input type="button" id="sendyzm" name="send_yzm" value="发送验证码" class="ver_btn" ">
			 </dd>
			 <dd>
			  <input type="submit" value="注册" class="submit_btn_reg" lay-submit="" onclick="reg_btn();"/>
			 </dd>
			  <dd>
						  <a href="merchant_login.jsp" class="a1">去登陆</a>
			 </dd>
			 <dd>
			  <p>© PinXiXi商城版权所有</p>
			 </dd>
		</dl>
	</form>
<script>
layui.use(['form'], function(){
  var form = layui.form
});
//1.获取XMLHttpRequest对象
var xhr;//用于接收XMLHttpRequest对象
function getXhr(){
	if(window.XMLHttpRequest){
		//非IE浏览器创建对象
		xhr = new XMLHttpRequest();
	}else{
		//是IE浏览器(IE5,IE6)
		xhr = new ActiveXObject("microsoft.XMLHttp");
	}
}
document.getElementById('sendyzm').addEventListener('click', function(){
	//验证手机号
	if(/^1[3,5,6,7,8,9]{1}\d{9}$/.test($("#phone").val())){
	//获取输入框中的电话
	var phone=document.getElementById('phone').value;
	//获取XMLHttpRequest对象
	getXhr();
//2.打开连接
	xhr.open('get', 'merchant?method=phonecode&phoneNumber='+phone,true);
//3.发送请求
	xhr.send();
//4.当请求状态发生改变且请求成功时回调
	xhr.onreadystatechange=function(){
	//请求成功的回调
	if(xhr.status==200&&xhr.readyState==4){
//5.获取回调响应数据	
		layer.msg("请查收验证码")	
		}
	}
	
}else{
	layer.msg("请输入正确的手机号")
}
})

</script>
</body>
</html>

