<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>拼XiXi商城-个人中心-订单管理</title>
  
  <!-- 个人中心样式 -->
  		<link href="res/static/one/AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
		<link href="res/static/one/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">
		<link href="res/static/one/css/personal.css" rel="stylesheet" type="text/css">
		<link href="res/static/one/css/orstyle.css" rel="stylesheet" type="text/css">
		<script src="res/static/one/AmazeUI-2.4.2/assets/js/jquery.min.js" type="text/javascript"></script>
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
								
				var e=getUrlSearch("result");
				if(e=='0'){
					//退货申请提交成功
					layer.alert('退货申请提交成功');
				}
				if(e=='1'){
					//退货申请提交失败
					layer.alert('系统繁忙,请稍后再试')
				}
	
	
  	
  </script>


  <div class="header">
    <div class="headerLayout w1200">
      <div class="headerCon">
        <h1 class="mallLogo">
          <a href="index.jsp" title="拼XiXi商城">
          	<div style="width:180px;height:60px;">
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

	<hr color="#CFB2F6" style="margin-top: 15px">
  <div class="content content-nav-base  login-content">
    <div class=" order-bg" style="height: 780px; background-attachment: rgba(255,255,255,0)">
      <div class="login-cont w1200">
        <div class="center">
			<div class="col-main" style="">
				<div class="main-wrap" style="background-color: rgba(255,255,255,0.9);">
					<!-- 显示部分 -->
					<div class="user-order">

						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">订单管理</strong> / <small>Order</small></div>
						</div>
						<hr/>

						<div class="am-tabs am-tabs-d2 am-margin" data-am-tabs>

							<ul class="am-avg-sm-5 am-tabs-nav am-nav am-nav-tabs">
								<li class="am-active"><a href="#tab1">所有订单</a></li>
								<li><a href="#tab2">待付款</a></li>
								<li><a href="#tab3">待发货</a></li>
								<li><a href="#tab4">待收货</a></li>
								<li><a href="#tab5">待评价</a></li>
							</ul>
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
							//var page=getUrlSearch("page");
// 							if(page=='1'){
// 								$(t).addClass('am-active');
// 								$(t).prev().remove('am-active');
// 							}
							
							
							
// 								function await(t){
// 									//向后台请求
// 									location.href="order?method=awaitpay&uid=${userlogin.uid}";
// 								}
								
								var e=getUrlSearch("ere");
								if(e=='0'){
									layer.msg('失败');
								}
								if(e=='1'){
									layer.msg('收货成功');
								}
								
								
							</script>

							<div class="am-tabs-bd">
								<div class="am-tab-panel am-fade am-in am-active" id="tab1">
									<div class="order-top">
										<div class="th th-item">
											<td class="td-inner">商品</td>
										</div>
										<div class="th th-price">
											<td class="td-inner">单价</td>
										</div>
										<div class="th th-number">
											<td class="td-inner">数量</td>
										</div>
										<div class="th th-operation">
											<td class="td-inner">商品操作</td>
										</div>
										<div class="th th-amount">
											<td class="td-inner">合计</td>
										</div>
										<div class="th th-status">
											<td class="td-inner">交易状态</td>
										</div>
										<div class="th th-change">
											<td class="td-inner">交易操作</td>
										</div>
									</div>

									<div class="order-main">
										<div class="order-list">
											
											<!--全部订单-->
											<div class="order-status5">
												<c:forEach var="orderitem" items="${showOrderAll}" varStatus="s">
												<div class="order-content">
													<div class="order-left">
														<ul class="item-list">
															<li class="td td-item">
																<div class="item-pic">
																	<a href="#" class="J_MakePoint">
																		<img src="/userphoto/${orderitem.goodsimage}" width="80px" height="80px" class="itempic J_ItemImg">
																	</a>
																</div>
																<div class="item-info">
																	<div class="item-basic-info">
																		<a href="#">
																			<p>${orderitem.goodsname}</p>
																			<p class="info-little">${orderitem.reserved1}
																		</a>
																	</div>
																</div>
															</li>
															<li class="td td-price">
																<div class="item-price">
																	${orderitem.goodsprice}
																</div>
															</li>
															<li class="td td-number">
																<div class="item-number">
																	<span>×</span>${orderitem.total}
																</div>
															</li>
															<li class="td td-operation">
																<div class="item-operation">
																</div>
															</li>
														</ul>
													</div>
													<div class="order-right">
														<li class="td td-amount">
															<div class="item-amount">
																合计：${orderitem.sumprice}
															</div>
														</li>
														<div class="move-right">
															<li class="td td-status">
																<div class="item-status">
																	<p class="Mystatus">&nbsp;</p>
																</div>
															</li>
															<li class="td td-change">
																<div class="am-btn am-btn-danger anniu">
																	删除订单</div>
															</li>
														</div>
													</div>
												</div>
												</c:forEach>
											</div>
										</div>
									</div>

								</div>
								<div class="am-tab-panel am-fade" id="tab2">

									<div class="order-top">
										<div class="th th-item">
											<td class="td-inner">商品</td>
										</div>
										<div class="th th-price">
											<td class="td-inner">单价</td>
										</div>
										<div class="th th-number">
											<td class="td-inner">数量</td>
										</div>
										<div class="th th-operation">
											<td class="td-inner">商品操作</td>
										</div>
										<div class="th th-amount">
											<td class="td-inner">合计</td>
										</div>
										<div class="th th-status">
											<td class="td-inner">交易状态</td>
										</div>
										<div class="th th-change">
											<td class="td-inner">交易操作</td>
										</div>
									</div>

									<div class="order-main">
										<div class="order-list">
											<div class="order-status1">
											<!-- 待付款 -->
												<c:forEach var="goods" items="${showawaitOrder}" varStatus="s">
												<div class="order-content">
													<div class="order-left">
														<ul class="item-list">
															<li class="td td-item">
																<div class="item-pic">
																	<a href="javascript:void(0)" class="J_MakePoint">
																		<img src="/userphoto/${goods.goodsimage}" class="itempic J_ItemImg">
																	</a>
																</div>
																<div class="item-info">
																	<div class="item-basic-info">
																		<a href="#">
																			<p>${goods.goodsname}</p>
																			<p class="info-little">${goods.reserved1}
																		</a>
																	</div>
																</div>
															</li>
															<li class="td td-price">
																<div class="item-price">
																	${goods.goodsprice}
																</div>
															</li>
															<li class="td td-number">
																<div class="item-number">
																	<span>×</span>${goods.total}
																</div>
															</li>
															<li class="td td-operation">
																<div class="item-operation">
																</div>
															</li>
														</ul>
													</div>
													<div class="order-right">
														<li class="td td-amount">
															<div class="item-amount">
																合计：${goods.sumprice}
															</div>
														</li>
														<div class="move-right">
															<li class="td td-status">
																<div class="item-status">
																	<p class="Mystatus">等待付款</p>
																</div>
															</li>
															<li class="td td-change">
															<input type="hidden" value="${goods.gid}">
																<a  href="javascript:void(0)"><!-- onclick="pay(this)" -->
																<div class="am-btn am-btn-danger ">
																	一键支付</div></a>
															</li>
														</div>
													</div>
												</div>
												</c:forEach>
												<script type="text/javascript">
													function pay(t){
														//获取商品id
														var gid=$(t).prev().val();
														//console.info(gid);
														//获取用户id
														var uid=$('#uid').val();
														//获取商品总价
														var money=$($(t).parent().parent().prev().children()[0]).text().trim();
														money=money.substring(3,money.length);
														console.info(money);
														//请求后台跳转
														location.href="order?method=order_pay&uid="+uid+"&gid="+gid+"&money="+money;
														
													}
													
												</script>
											</div>
										</div>

									</div>
								</div>
								<div class="am-tab-panel am-fade" id="tab3">
									<div class="order-top">
										<div class="th th-item">
											<td class="td-inner">商品</td>
										</div>
										<div class="th th-price">
											<td class="td-inner">单价</td>
										</div>
										<div class="th th-number">
											<td class="td-inner">数量</td>
										</div>
										<div class="th th-operation">
											<td class="td-inner">商品操作</td>
										</div>
										<div class="th th-amount">
											<td class="td-inner">合计</td>
										</div>
										<div class="th th-status">
											<td class="td-inner">交易状态</td>
										</div>
										<div class="th th-change">
											<td class="td-inner">交易操作</td>
										</div>
									</div>

									<div class="order-main">
										<div class="order-list">
											<div class="order-status2">
												<c:forEach var="goods" items="${showawaitSendOrder}" varStatus="s">
												<div class="order-content">
													<div class="order-left">
														<ul class="item-list">
															<li class="td td-item">
																<div class="item-pic">
																	<a href="javascript:void(0)" class="J_MakePoint">
																		<img src="/userphoto/${goods.goodsimage}" class="itempic J_ItemImg">
																	</a>
																</div>
																<div class="item-info">
																	<div class="item-basic-info">
																		<a href="javascript:void(0)">
																			<p>${goods.goodsname} </p>
																			<p class="info-little">${goods.reserved1}
																		</a>
																	</div>
																</div>
															</li>
															<li class="td td-price">
																<div class="item-price">
																	${goods.goodsprice}
																</div>
															</li>
															<li class="td td-number">
																<div class="item-number">
																	<span>×</span>${goods.total}
																</div>
															</li>
															<li class="td td-operation">
																<div class="item-operation">
																	<a href="order?method=returnmoney&uid=${userlogin.uid}&gid=${goods.gid}">退款</a>
																</div>
															</li>
														</ul>
													</div>
													<div class="order-right">
														<li class="td td-amount">
															<div class="item-amount">
																合计：${goods.sumprice}
															</div>
														</li>
														<div class="move-right">
															<li class="td td-status">
																<div class="item-status">
																	<p class="Mystatus">买家已付款</p>
																</div>
															</li>
															<li class="td td-change">
																<div onclick="remind()" class="am-btn am-btn-danger anniu">
																	提醒发货</div>
															</li>
														</div>
													</div>
												</div>
												</c:forEach>
												<script type="text/javascript">
													//提醒发货的方法
													function remind(){
														layer.alert('已经提醒商家尽早发货');
													}
													
												</script>
											</div>
										</div>
									</div>
								</div>
								<div class="am-tab-panel am-fade" id="tab4">
									<div class="order-top">
										<div class="th th-item">
											<td class="td-inner">商品</td>
										</div>
										<div class="th th-price">
											<td class="td-inner">单价</td>
										</div>
										<div class="th th-number">
											<td class="td-inner">数量</td>
										</div>
										<div class="th th-operation">
											<td class="td-inner">商品操作</td>
										</div>
										<div class="th th-amount">
											<td class="td-inner">合计</td>
										</div>
										<div class="th th-status">
											<td class="td-inner">交易状态</td>
										</div>
										<div class="th th-change">
											<td class="td-inner">交易操作</td>
										</div>
									</div>

									<div class="order-main">
										<div class="order-list">
											<div class="order-status3">
												<c:forEach var="goods" items="${showawaittake}" varStatus="s">
												<div class="order-content">
													<div class="order-left">
														

														<ul class="item-list">
															<li class="td td-item">
																<div class="item-pic">
																	<a href="javascript:void(0)" class="J_MakePoint">
																		<img src="/userphoto/${goods.goodsimage}" class="itempic J_ItemImg">
																	</a>
																</div>
																<div class="item-info">
																	<div class="item-basic-info">
																		<a href="javascript:void(0)">
																			<p>${goods.goodsname}</p>
																			<p class="info-little">${goods.reserved1 }
																		</a>
																	</div>
																</div>
															</li>
															<li class="td td-price">
																<div class="item-price">
																	${goods.goodsprice}
																</div>
															</li>
															<li class="td td-number">
																<div class="item-number">
																	<span>×</span>${goods.total}
																</div>
															</li>
															<li class="td td-operation">
																<div class="item-operation">
																	<a href="userrefund.jsp">退款/退货</a>
																</div>
															</li>
														</ul>

													</div>
													<div class="order-right">
														<li class="td td-amount">
															<div class="item-amount">
																合计：${goods.sumprice}
															</div>
														</li>
														<div class="move-right">
															<li class="td td-status">
																<div class="item-status">
																	<p class="Mystatus">卖家已发货</p>
																</div>
															</li>
															<li class="td td-change">
															<input type="hidden" value="${goods.gid}">
																<div onclick="confirm(this)" class="am-btn am-btn-danger anniu">
																	确认收货</div>
															</li>
														</div>
													</div>
												</div>
												</c:forEach>
												<script type="text/javascript">
													function confirm(t){
														//获取用户id
														var uid=$('#uid').val();
														//获取商品id
														var gid=$(t).prev().val();
														//请求后台
														location.href="order?method=take&uid="+uid+"&gid="+gid;
													}
												</script>
											</div>
										</div>
									</div>
								</div>

								<div class="am-tab-panel am-fade" id="tab5">
									<div class="order-top">
										<div class="th th-item">
											<td class="td-inner">商品</td>
										</div>
										<div class="th th-price">
											<td class="td-inner">单价</td>
										</div>
										<div class="th th-number">
											<td class="td-inner">数量</td>
										</div>
										<div class="th th-operation">
											<td class="td-inner">商品操作</td>
										</div>
										<div class="th th-amount">
											<td class="td-inner">合计</td>
										</div>
										<div class="th th-status">
											<td class="td-inner">交易状态</td>
										</div>
										<div class="th th-change">
											<td class="td-inner">交易操作</td>
										</div>
									</div>

									<div class="order-main">
										<div class="order-list">
											<!--不同状态的订单	-->
										<div class="order-status4">
										<c:forEach var="goods" items="${showawaitevaluate}" varStatus="s">
												<div class="order-content">
													<div class="order-left">
														<ul class="item-list">
															<li class="td td-item">
																<div class="item-pic">
																	<a href="#" class="J_MakePoint">
																		<img src="/userphoto/${goods.goodsimage}" class="itempic J_ItemImg">
																	</a>
																</div>
																<div class="item-info">
																	<div class="item-basic-info">
																		<a href="javascript:void(0)">
																			<p>${goods.goodsname}</p>
																			<p class="info-little">${goods.reserved1}
																		</a>
																	</div>
																</div>
															</li>
															<li class="td td-price">
																<div class="item-price">
																	${goods.goodsprice}
																</div>
															</li>
															<li class="td td-number">
																<div class="item-number">
																	<span>×</span>${goods.total}
																</div>
															</li>
															<li class="td td-operation">
																<div class="item-operation">
																	<a href="userrefund.jsp">退款/退货</a>
																</div>
															</li>
														</ul>

													</div>
													<div class="order-right">
														<li class="td td-amount">
															<div class="item-amount">
																合计：${goods.sumprice}
															</div>
														</li>
														<div class="move-right">
															<li class="td td-status">
																<div class="item-status">
																	<p class="Mystatus">交易成功</p>
																</div>
															</li>
															<li class="td td-change">
																<a href="comment?method=subcomment&uid=${userlogin.uid}&gid=${goods.gid}&sumprice=${goods.sumprice}">
																	<div class="am-btn am-btn-danger anniu">
																		评价商品</div>
																</a>
															</li>
														</div>
													</div>
												</div>
												</c:forEach>
											</div>
											
										</div>

									</div>

								</div>
							</div>

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
							<li class="active"><a href="userorder.jsp">订单管理</a></li>
							<li> <a href="javascript:void(0)">退款售后</a></li>
						</ul>
					</li>

					<li class="person">
						<a href="javascript:void(0)"><span style="font-size: 18px">我的小窝</span></a>
						<ul>
							<li> <a href="collection?method=selectAll&uid=${userlogin.uid}">收藏</a></li>
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