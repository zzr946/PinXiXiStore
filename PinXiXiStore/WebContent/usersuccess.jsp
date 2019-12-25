<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>拼XiXi商城-个人中心-交易成功</title>
  
  <!-- 个人中心样式 -->
		<link href="res/static/one/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">
		<link href="res/static/one/AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
		<link href="res/static/one/basic/css/demo.css" rel="stylesheet" type="text/css">
		<link href="res/static/one/css/sustyle.css" rel="stylesheet" type="text/css">
		<script src="res/static/one/basic/js/jquery-1.7.min.js" type="text/javascript"></script>  
  
  
  
  
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
				<i class="layui-icon layui-icon-home"></i> <a href="index.jsp">首页</a>
			</p>
			<div class="sn-quick-menu">
				<div class="reg" id="welcome">
					<a href="javascript:void(0)">欢迎您,${userlogin.phone}</a>
				</div>
				<div class="reg" id="info">
					<a href="userspace.jsp">个人中心</a>
				</div>
				<div class="reg" id="reg">
					<a href="userreg.jsp">注册</a>
				</div>
				<div class="login" id="login">
					<a href="login.jsp">登录</a>
				</div>
				<div class="login" id="logout">
					<a href="user?method=logout">退出</a>
				</div>
				<div class="sp-cart" id="cart">
					<a href="shopcart.jsp">购物车</a><span>2</span>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
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
						<div style="width: 180px; height: 60px;">
							<img src="res/static/img/logo2.PNG" width="180px" height="60px">
						</div>
					</a>
				</h1>
				<div class="mallSearch">
					<form action="goods" class="layui-form" novalidate style="height: 44px">
						<!-- 隐藏域用于区分是什么操作 -->
          				<input type="hidden" name="method" value="goodsbytitle">
						<input type="text" name="title" required lay-verify="required"
							autocomplete="off" class="layui-input" placeholder="请输入需要的商品">
						<button class="layui-btn" lay-submit lay-filter="formDemo">
							<i class="layui-icon layui-icon-search"></i>
						</button>
						<input type="hidden" name="" value="">
					</form>
				</div>
			</div>
		</div>
	</div>

	<hr color="#CFB2F6" style="margin-top: 15px">
	<div class="content content-nav-base  login-content">
		<div class=" order-bg"
			style="height: 500px; background-attachment: rgba(255, 255, 255, 0)">
			<div class="paycont"></div>
			<script type="text/javascript">
				function getUrlSearch(name) {//地址栏取参数的方法
				  // 未传参，返回空
				  if (!name) return null;
				  // 查询参数：先通过search取值，如果取不到就通过hash来取
				  var after = window.location.search;
				  after = after.substr(1) || window.location.hash.split('?')[1];
				  // 地址栏URL没有查询参数，返回空
				  if (!after) return null;
				  // 如果查询参数中没有"name"，返回空
				  if (after.indexOf(name) === -1) return null;
				  var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)');
				  // 当地址栏参数存在中文时，需要解码，不然会乱码
				  var r = decodeURI(after).match(reg);
				  // 如果url中"name"没有值，返回空
				  if (!r) return null;
				  return r[2];
				}
				//获取地址栏的信息
				var money=getUrlSearch("money");
				$('#money').text('¥'+money);
				
			</script>
				<!-- 主体部分 -->
				<div class="take-delivery">
					 <div class="status">
					   <h2>您已成功付款</h2>
					   <div class="successInfo">
						     <ul>
						       <li>付款金额<em id="money">¥${countmoney}</em></li>
						       <div class="user-info">
						         <p>收货人：${successaddress.uadname}</p>
						         <p>联系电话：${successaddress.mobile}</p>
						         <p>收货地址：${successaddress.datailaddress}</p>
						       </div>
						           	  请认真核对您的收货信息，如有错误请联系客服
						     </ul>
<!-- 					     <div class="option"> -->
<!-- 					       <span class="info">您可以</span> -->
<!-- 					        <a href="userorder.jsp" class="J_MakePoint">查看<span>已买到的宝贝</span></a> -->
<!-- 					        <a href="userorderinfo.jsp" class="J_MakePoint">查看<span>交易详情</span></a> -->
<!-- 					     </div> -->
					    </div>
					  </div>
				</div>
				
				
			<div class="clear"></div>
		</div>
	</div>

</body>
</html>