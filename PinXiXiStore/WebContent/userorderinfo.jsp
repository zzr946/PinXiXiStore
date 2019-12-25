<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>拼XiXi商城-个人中心-订单详情</title>
  
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
					<div class="user-orderinfo">

						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">订单详情</strong> / <small>Order&nbsp;details</small></div>
						</div>
						<hr/>
						<!--进度条-->
						
						<div class="order-infoaside">
							<div class="order-logistics">
								<a href="#"><!-- 查看物流的连接 -->
									<div class="icon-log">
										<i><img src="res/static/one/images/receive.png"></i>
									</div>
									<div class="latest-logistics">
										<p class="text">暂无物流信息</p>
										<div class="time-list">
<!-- 											<span class="date">2015-12-19</span><span class="week">周六</span><span class="time">15:35:42</span> -->
										</div>
<!-- 										<div class="inquire"> -->
<!-- 											<span class="package-detail">物流：天天快递</span> -->
<!-- 											<span class="package-detail">快递单号: </span> -->
<!-- 											<span class="package-number">373269427868</span> -->
<!-- 											<a href="#">查看</a>查看物流的连接 -->
<!-- 										</div> -->
									</div>
									<span class="am-icon-angle-right icon"></span>
								</a>
								<div class="clear"></div>
							</div>
							<div class="order-addresslist">
								<div class="order-address">
									<div class="icon-add">
									</div>
									<p class="new-tit new-p-re">
										<span class="new-txt">小叮当</span>
										<span class="new-txt-rd2">159****1622</span>
									</p>
									<div class="new-mu_l2a new-p-re">
										<p class="new-mu_l2cw">
											<span class="title">收货地址：</span>
											<span class="province">湖北</span>省
											<span class="city">武汉</span>市
											<span class="dist">洪山</span>区
											<span class="street">雄楚大道666号(中南财经政法大学)</span></p>
									</div>
								</div>
							</div>
						</div>
						<div class="order-infomain">

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

								<div class="order-status3">
									<div class="order-title">
										<div class="dd-num">订单编号：<a href="javascript:;">1601430</a></div>
										<span>成交时间：2015-12-20</span>
										<!--    <em>店铺：小桔灯</em>-->
									</div>
									<div class="order-content">
										<div class="order-left">
											<ul class="item-list">
												<li class="td td-item">
													<div class="item-pic">
														<a href="#" class="J_MakePoint">
															<img src="res/static/one/images/kouhong.jpg_80x80.jpg" class="itempic J_ItemImg">
														</a>
													</div>
													<div class="item-info">
														<div class="item-basic-info">
															<a href="#">
																<p>美康粉黛醉美唇膏 持久保湿滋润防水不掉色</p>
																<p class="info-little">颜色：12#川南玛瑙
																	<br/>包装：裸装 </p>
															</a>
														</div>
													</div>
												</li>
												<li class="td td-price">
													<div class="item-price">
														333.00
													</div>
												</li>
												<li class="td td-number">
													<div class="item-number">
														<span>×</span>2
													</div>
												</li>
												<li class="td td-operation">
													<div class="item-operation">
														退款/退货
													</div>
												</li>
											</ul>

											<ul class="item-list">
												<li class="td td-item">
													<div class="item-pic">
														<a href="#" class="J_MakePoint">
															<img src="../images/62988.jpg_80x80.jpg" class="itempic J_ItemImg">
														</a>
													</div>
													<div class="item-info">
														<div class="item-basic-info">
															<a href="#">
																<p>礼盒袜子女秋冬 纯棉袜加厚 韩国可爱 </p>
																<p class="info-little">颜色分类：李清照
																	<br/>尺码：均码</p>
															</a>
														</div>
													</div>
												</li>
												<li class="td td-price">
													<div class="item-price">
														333.00
													</div>
												</li>
												<li class="td td-number">
													<div class="item-number">
														<span>×</span>2
													</div>
												</li>
												<li class="td td-operation">
													<div class="item-operation">
														退款/退货
													</div>
												</li>
											</ul>

										</div>
										<div class="order-right">
											<li class="td td-amount">
												<div class="item-amount">
													合计：676.00
													<p>含运费：<span>10.00</span></p>
												</div>
											</li>
											<div class="move-right">
												<li class="td td-status">
													<div class="item-status">
														<p class="Mystatus">卖家已发货</p>
														<p class="order-info"><a href="#">查看物流</a></p>
														<p class="order-info"><a href="#">延长收货</a></p>
													</div>
												</li>
												<li class="td td-change">
													<div class="am-btn am-btn-danger anniu">
														确认收货</div>
												</li>
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
							<li><a href="userorder.jsp">订单管理</a></li>
							<li> <a href="change.html">退款售后</a></li>
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