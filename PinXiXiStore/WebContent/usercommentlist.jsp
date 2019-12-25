<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>拼XiXi商城-个人中心-评论</title>
  
  <!-- 个人中心样式 -->
  		<link href="res/static/one/AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
		<link href="res/static/one/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">
		<link href="res/static/one/css/personal.css" rel="stylesheet" type="text/css">
		<link href="res/static/one/css/appstyle.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="res/JQuery/jquery-1.7.2.js"></script>
		<script src="res/static/one/AmazeUI-2.4.2/assets/js/amazeui.js" type="text/javascript"></script>
<!-- 		<script type="text/javascript" src="res/jQuery/jquery-3.3.1.js" ></script> -->
  
  
  
  
  
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
          <div style="width: 180px;height: 60px">
            <img src="res/static/img/logo2.PNG" width="180px" height="60px">
            </div>
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
							<script type="text/javascript">
								function subcomment() {
									//获取用户id
									var uid = $('#uid').val();
									//获取商品id
									var gid = $
									{
										goodscomment.gid
									}
									;
									//获取评论内容
									var content = $('#cont').val();
									console.info(uid)
									console.info(gid)
									console.info(content)
									//location.href="comment?method="
								}
							</script>
							<div class="user-comment">
						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">发表评论</strong> / <small>Make&nbsp;Comments</small></div>
						</div>
						<hr/>

						<div class="comment-main">
							<div class="comment-list">
								<div class="item-pic">
									<a href="javascript:void(0)" class="J_MakePoint">
										<img src="/userphoto/${goodscomment.goodsimage}" width="148px" height="148px" class="itempic">
									</a>
								</div>

								<div class="item-title">

									<div class="item-name">
										<a href="javascript:void(0)">
											<p class="item-basic-info">${goodscomment.goodsname}</p>
										</a>
									</div>
									<div class="item-info">
										<div class="info-little">
											<span>${goodscomment.reserved1 }</span>
										</div>
										<div class="item-price">
											价格：<strong>${goodscomment.discountprice }</strong>
										</div>										
									</div>
								</div>
								<div class="clear"></div>
								<div class="item-comment">
									<textarea id="cont" placeholder="请写下对宝贝的感受吧，对他人帮助很大哦！"></textarea>
								</div>
								<div class="item-opinion">
									<li><i class="op1"></i>好评</li>
									<li><i class="op2"></i>中评</li>
									<li><i class="op3"></i>差评</li>
								</div>
								<div class="info-btn">
								<input type="hidden" id="goodsid" value="${goodscomment.gid}">
									<a onclick="subcomment()"><button class="am-btn am-btn-danger">发表评论</button></a>
								</div>
							</div>
							<script type="text/javascript">
								function subcomment() {
									//获取用户id
									var uid = $('#uid').val();
									//获取商品id
									var gid = $('#goodsid').val();
									//获取评论内容
									var content = $('#cont').val();
									console.info(uid)
									console.info(gid)
									console.info(content)
									location.href="comment?method=publishcomment&uid="+uid+"&gid="+gid+"&content="+content;
								}
							</script>
								
								
								
								
								
								
								
								
													
					<script type="text/javascript">
						$(document).ready(function() {
							$(".comment-list .item-opinion li").click(function() {	
								$(this).prevAll().children('i').removeClass("active");
								$(this).nextAll().children('i').removeClass("active");
								$(this).children('i').addClass("active");
								
							});
				     })
					</script>					
					
												
							
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
							<li > <a href="usersafety.jsp">安全设置</a></li>
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
							<li class="active"> <a href="collection?method=selectAll&uid=${userlogin.uid}">收藏</a></li>
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