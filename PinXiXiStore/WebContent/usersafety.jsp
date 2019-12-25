<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>拼XiXi商城-个人中心-安全设置</title>
  
  <!-- 个人中心样式 -->
  		<link href="res/static/one/AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
		<link href="res/static/one/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">
		<link href="res/static/one/css/personal.css" rel="stylesheet" type="text/css">
		<link href="res/static/one/css/infstyle.css" rel="stylesheet" type="text/css">
  
  
  
  
  
  <link rel="stylesheet" type="text/css" href="res/static/css/main.css">
  <link rel="stylesheet" type="text/css" href="res/layui/css/layui.css">
  <script type="text/javascript" src="res/JQuery/jquery-3.3.1.js" ></script>
  <script type="text/javascript" src="res/layer/layer.js"></script>
  <script type="text/javascript" src="res/layui/layui.js"></script>
  
  
  <style type="text/css">
  	aside ul li.active a {
    color: #F69;
	}
  	
  	
  </style>
  
  
  
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
  <style type="text/css">
  	.hidden{
  		display:none;
  	}
  </style>
</head>
<body>

  <div class="site-nav-bg">
    <div class="site-nav w1200">
      <p class="sn-back-home">
        <i class="layui-icon layui-icon-home"></i>
        <a href="index.jsp">首页</a>
      </p>
      <div class="sn-quick-menu">
      <input type="hidden" id="uid" name="uid" value="${userlogin.uid}">
      <input type="hidden" id="unickname" name="unickname" value="${userlogin.nickname}">
      <input type="hidden" id="upassword" name="upassword" value="${userlogin.password}">
      <input type="hidden" id="uphone" name="uphone" value="${userlogin.phone}">
      <input type="hidden" id="uphoto" name="uphoto" value="${userlogin.photo}">
      <input type="hidden" id="uname" name="uname" value="${userlogin.name}">
      <input type="hidden" id="usex" name="usex" value="${userlogin.sex}">
      <input type="hidden" id="uuserdr" name="uuserdr" value="${userlogin.userdr}">
      <input type="hidden" id="ureserved1" name="ureserved1" value="${userlogin.reserved1}">
      <input type="hidden" id="ureserved2" name="ureserved2" value="${userlogin.reserved2}">
      <input type="hidden" id="ureserved3" name="ureserved3" value="${userlogin.reserved3}">
      <input type="hidden" id="ureserved4" name="ureserved4" value="${userlogin.reserved4}">
      <input type="hidden" id="urved1ord5" name="ureserved5" value="${userlogin.reserved5}">
      <div class="reg" id="welcome"><span class="layui-icon layui-icon-face-smile"></span><a href="javascript:void(0)" id="weltext"></a></div>
      	<div class="reg" id="info"><a href="userspace.jsp"><span class="layui-icon layui-icon-user"></span>个人中心</a></div>
      	<div class="reg" id="reg"><a href="userreg.jsp">注册</a></div>
        <div class="login" id="login"><a href="login.jsp">登录</a></div>
        <div class="login" id="logout"><a href="user?method=logout"><span class="layui-icon layui-icon-close"></span>退出</a></div>
        <div class="sp-cart" id="cart"><a href="cart?method=cart_no1&uid=${userlogin.uid}"><span class="layui-icon layui-icon-cart"></span>购物车</a></div>
      </div>
    </div>
  </div>
<script type="text/javascript">
//显示用户昵称的方法
//先判断该用户是否设置昵称，如果设置了昵称就直接显示昵称，否则显示手机号码
//从作用域中获取用户昵称和手机号
var phone=$('#uphone').val();
var nickname=$('#unickname').val();
if(!nickname){
	//昵称为空
	$('#weltext').text('欢迎您,'+phone);
// 	console.info('手机号');
}else{
	//昵称为空
	$('#weltext').text('欢迎您,'+nickname);
// 	console.info('昵称');
}


//   	$(function(){
  	//判断当前用户是否登录，若登录则显示个人小中心、购物车 ;若没有登录则显示登录注册
  	  	if('${userlogin}'){
  	  		//已经登录
  	  		$('#login').addClass('hidden');//登录 
  	  		$('#reg').addClass('hidden');//注册
  	  	}else{
  	  		//还未登录
  	  		$('#welcome').addClass('hidden');//欢迎
  	  		$('#info').addClass('hidden');//个人中心
  	  		$('#cart').addClass('hidden');//购物车
  	  		$('#logout').addClass('hidden');//退出
  	  	}
//   	})
  	
  </script>


  <div class="header">
    <div class="headerLayout w1200">
      <div class="headerCon">
        <h1 class="mallLogo">
          <a href="index.jsp" title="拼XiXi商城">
            <img src="res/static/img/logo2.PNG" width="180px" height="60px">
          </a>
        </h1>
        <div class="mallSearch">
          <form action="goods" class="layui-form" novalidate style="height: 44px">
          <!-- 隐藏域用于区分是什么操作 -->
          <input type="hidden" name="method" value="goodsbytitle">
            <input type="text" name="title" required  lay-verify="required" autocomplete="off" class="layui-input" placeholder="请输入需要的商品">
            <button class="layui-btn" lay-submit lay-filter="formDemo">
                <i class="layui-icon layui-icon-search"></i>
            </button>
            <input type="hidden" name="" value="">
          </form>
        </div>
      </div>
    </div>
  </div>


  <div class="content content-nav-base  login-content">
    <div class="login-bg" style="height: 780px">
      <div class="login-cont w1200">
        
        <div class="center">
			<div class="col-main" style="">
				<div class="main-wrap" style="background-color: rgba(255,255,255,0.9);">
					<!-- 显示部分 -->
					<div class="user-safety">
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">账户安全</strong> / <small>Set&nbsp;up&nbsp;Safety</small></div>
						</div>
						<hr/>

						<!--头像 -->
						<div class="user-infoPic">

							<div class="filePic">
								<img id="select_btn" style="width=100px;height: 100px" class="am-circle am-img-thumbnail" src="res/static/one/images/getAvatar.do.jpg" alt="" />
							</div>
								<script type="text/javascript">
										//从数据库中获取头像并显示头像
										if('${userlogin.photo}'){
										//如果有头像就显示头像    userphoto/${userlogin.photo}
										document.getElementById('select_btn').src='/userphoto/${userlogin.photo}';
										}
										console.info('${userlogin.photo}')
								</script>

							<p class="am-form-help">头像</p>

							<div class="info-m">
								<div><b>用户名：<b id="view_name">路人甲</b></b></div>
								<div class="u-level">
									<span class="rank r2">
							             <a class="classes" href="javascript:void(0)">拼XiXi会员</a>
						            </span>
								</div>
							</div>
						</div>
						<script type="text/javascript">
									//显示用户昵称的方法
									//先判断该用户是否设置昵称，如果设置了昵称就直接显示昵称，否则显示手机号码
									//从作用域中获取用户昵称和手机号
									var phone=$('#uphone').val();
									var nickname=$('#unickname').val();
									if(!nickname){
										//昵称为空
										$('#view_name').text('用户名:'+phone);
	//						 			console.info('手机号');
									}else{
										//昵称为空
										$('#view_name').text('用户名:'+nickname);
	//						 			console.info('昵称');
									}
						</script>
						
						
						

						<div class="check">
							<ul>
								<li>
									<i class="i-safety-lock"></i>
									<div class="m-left">
										<div class="fore1">登录密码</div>
										<div class="fore2"><small>为保证您购物安全，建议您定期更改密码以保护账户安全。</small></div>
									</div>
									<div class="fore3">
										<a href="modifypwd.jsp">
											<div class="am-btn am-btn-secondary">修改</div>
										</a>
									</div>
								</li>
								<li>
									<i class="i-safety-wallet"></i>
									<div class="m-left">
										<div class="fore1">支付密码</div>
										<div class="fore2"><small>启用支付密码功能，为您资产账户安全加把锁。</small></div>
									</div>
									<div class="fore3">
										<a href="javascript:void(0)">
											<div class="am-btn am-btn-secondary">立即启用</div>
										</a>
									</div>
								</li>
								<li>
									<i class="i-safety-iphone"></i>
									<div class="m-left">
										<div class="fore1">手机验证</div>
										<div class="fore2"><small>您验证的手机：您的登录手机号若已丢失或停用，请立即更换</small></div>
									</div>
									<div class="fore3">
										<a href="javascript:void(0)">
											<div class="am-btn am-btn-secondary">换绑</div>
										</a>
									</div>
								</li>
								<li>
									<i class="i-safety-mail"></i>
									<div class="m-left">
										<div class="fore1">邮箱验证</div>
										<div class="fore2"><small>您验证的邮箱：绑定邮箱可用于快速找回登录密码</small></div>
									</div>
									<div class="fore3">
										<a href="javascript:void(0)">
											<div class="am-btn am-btn-secondary">换绑</div>
										</a>
									</div>
								</li>
								<li>
									<i class="i-safety-idcard"></i>
									<div class="m-left">
										<div class="fore1">实名认证</div>
										<div class="fore2"><small>用于提升账号的安全性和信任级别，认证后不能修改认证信息。</small></div>
									</div>
									<div class="fore3">
										<a href="javascript:void(0)">
											<div class="am-btn am-btn-secondary">认证</div>
										</a>
									</div>
								</li>
								<li>
									<i class="i-safety-security"></i>
									<div class="m-left">
										<div class="fore1">安全问题</div>
										<div class="fore2"><small>保护账户安全，验证您身份的工具之一。</small></div>
									</div>
									<div class="fore3">
										<a href="javascript:void(0)">
											<div class="am-btn am-btn-secondary">认证</div>
										</a>
									</div>
								</li>
							</ul>
						</div>

					</div>
					
				</div>
			</div>



			<aside class="menu">
				<ul>
					<li class="person">
						<a href="userspace.jsp"><span style="font-size: 25px">个人中心</span></a>
					</li>
					<li class="person">
						<a href="javascript:void(0)"><span style="font-size: 18px">个人资料</span></a>
						<ul>
							<li > <a href="userinfo.jsp">个人信息</a></li>
							<li class="active"> <a href="usersafety.jsp">安全设置</a></li>
							<li > <a href="useraddress.jsp">收货地址</a></li>
						</ul>
					</li>
					<li class="person">
						<a href="javascript:void(0)"><span style="font-size: 18px">我的交易</span></a>
						<ul>
							<li><a href="userorder.jsp">订单管理</a></li>
							<li> <a href="javascript:void(0)">退款售后</a></li>
						</ul>
					</li>

					<li class="person">
						<a href="javascript:void(0)"><span style="font-size: 18px">我的小窝</span></a>
						<ul>
							<li > <a href="collection?method=selectAll&uid=${userlogin.uid}">收藏</a></li>
						</ul>
					</li>

				</ul>
			</aside>
		</div>
        
        
        
        
        
      </div>
    </div>
  </div>

</body>
</html>