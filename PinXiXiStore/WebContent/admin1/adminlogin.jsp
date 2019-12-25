<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script src="js1/jquery-1.7.1.min.js"></script>
		<script src="js1/login-xin.js"></script>
		<link href="css/login.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="layer/layer.js"></script>
	</head>
	<body onLoad="createCode();">      
<div class="login">
			<div class="login-head">
				<div class="login-head-a">
					<img src="img/a.jpg" title="" alt=""  width="60px" height="60px"/>
						
				</div>
				<div class="login-head-b">
					管理员后台
				</div>
			</div>
			<div class="login-con">
				<div class="login-a">
					<form id="loginForm" method="get" action="admin" onsubmit="return validate()">
                   	  <!-- 隐藏域 -->
                      <input type="hidden" name="method" value="Admin_loginByadminaccount">
                      <div class="login-b">
                      	<div class="login-b-a">管理员登陆</div>
                      	<div class="login-b-b">
                      		<ul>
                      			<li>
                      				<label for="userName" >用户名：</label><input type="text" id="mobile" name="adminaccount"/>
                      				<h1 id="m-tip"></h1>
                      			</li>
                      			<li>
                      				<label for="password">密 &nbsp;&nbsp;码：</label><input type="password" id="password" name="adminpassword"/>
                      				<h1 id="p-tip"></h1>
                      			</li>
                      			<li>
                      				<label >验证码：</label><input type="text" id="input1" />      
                                    <input type="text" id="checkCode" class="code" style="width: 55px" /> <a href="#" onClick="createCode()">看不清楚</a>      
                                    <h1 id="c-tip"></h1>
                      					
                      				<!--输入错误时的输入框样式<input type="text" name="textfield" id="textfield" class="receSum-error" />-->
                                   <!--当输入的手机号码发生错误时，出现如下提示信息
                                   <p class="error-tips-1 spanRed">在此处显示错误的信息提示——如：对不起，您输入的手机号码格式有误，请重新输入！</p>-->
                      				
                      			</li>
                      			
                      			<li class="login-x">
		                          <div class="login-x-a"><input type="submit" value="登录"id="Button1" onClick="validate();" /><div class="x"></div></div>
		                          <!--<div class="login-x-a"><input type="submit" name="订单查询"value="订单查询"onclick="pay_query();"/><div class="x"></div></div>-->
	                            </li>
                      			
                      		</ul>
                      	</div>
                      	<div class="login-b-c">
                      		<a href="forgetpassword.jsp">忘记登录密码？</a>
                      	</div>
                      </div>
					</form>
				</div>
			</div>
			
		</div>
		 
		<script>
		$(function(){
			if('${loginByadminaccounterror}'){
				layer.msg("账号或者密码错误")
			}
		})
		</script>
		
</body>    
</html>
