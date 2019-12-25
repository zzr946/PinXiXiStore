<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>拼XiXi商城-个人中心-付款</title>
  
  <!-- 个人中心样式 -->
		<link href="res/static/one/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">
		<link href="res/static/one/basic/css/demo.css" rel="stylesheet" type="text/css">
		<link href="res/static/one/css/cartstyle.css" rel="stylesheet" type="text/css">
		<link href="res/static/one/css/jsstyle.css" rel="stylesheet" type="text/css">
		<script src="res/static/one/AmazeUI-2.4.2/assets/js/jquery.min.js" type="text/javascript"></script>
		<script src="res/static/one/js/address.js" type="text/javascript"></script>
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
	
	.hidden{
		display: none;
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
      <div class="paycont">
					<div class="address">
						<h3>确认收货地址 </h3>
<!-- 						<div class="control"> -->
<!-- 							<div class="tc-btn createAddr theme-login am-btn am-btn-danger">使用新地址</div> -->
<!-- 						</div> -->
						<div class="clear"></div>
						<ul id="addul">
							<div class="per-border"></div>
							<script type="text/javascript">
								$(function(){
									//显示默认地址的方法
									var a=$('.hiddenaddress[value=0]').next().removeClass('hidden');
									//console.info(a);
								})
						</script>
							<!-- 循环标签 begin:开始条件  end:结束条件  step:步径  var:循环变量-->
							<c:forEach var="address" items="${pay_address}" varStatus="s">
							<li class="user-addresslist" ><!-- defaultAddr表示选中的收货地址 -->
								<input type="hidden" id="hiddenaddressdr" value="${address.uadid}">
								<div class="address-left">
									<div class="user DefaultAddr">
										<span class="buy-address-detail">   
                   <span class="buy-user">${address.uadname}</span><!-- 收货人姓名 -->
										<span class="buy-phone">${address.mobile}</span><!-- 收货人电话 -->
										</span>
									</div>
									<div class="default-address DefaultAddr">
										<span class="buy-line-title buy-line-title-type">收货地址：</span>
										<span class="buy--address-detail">
								   <span class="province">${address.province}</span><!-- 省 -->
										<span class="city">${address.city}</span><!-- 市 -->
										<span class="dist">${address.area}</span><!-- 区 -->
										<span class="street">${address.datailaddress}</span><!-- 详细地址 -->
										</span>
									</div>
									<input type="hidden" class="hiddenaddress" value="${address.addressdr}"><!-- 地址的状态值 -->
									<ins class="deftip hidden">默认地址</ins>
										
										
								</div>
								<div class="address-right">
									<a href="../person/address.html">
										<span class="am-icon-angle-right am-icon-lg"></span></a>
								</div>
								<div class="clear"></div>
							</li>
							</c:forEach>
							
							
							<div class="per-border"></div>
<!-- 							<li class="user-addresslist"> -->
<!-- 								<div class="address-left"> -->
<!-- 									<div class="user DefaultAddr"> -->
<!-- 										<span class="buy-address-detail">    -->
<!--                    <span class="buy-user">艾迪 </span> -->
<!-- 										<span class="buy-phone">15871145629</span> -->
<!-- 										</span> -->
<!-- 									</div> -->
<!-- 									<div class="default-address DefaultAddr"> -->
<!-- 										<span class="buy-line-title buy-line-title-type">收货地址：</span> -->
<!-- 										<span class="buy--address-detail"> -->
<!-- 								   <span class="province">湖北</span>省 -->
<!-- 										<span class="city">武汉</span>市 -->
<!-- 										<span class="dist">武昌</span>区 -->
<!-- 										<span class="street">东湖路75号众环大厦2栋9层902</span> -->
<!-- 										</span> -->

<!-- 										</span> -->
<!-- 									</div> -->
<!-- 									<ins class="deftip hidden">默认地址</ins> -->
<!-- 								</div> -->
<!-- 								<div class="address-right"> -->
<!-- 									<span class="am-icon-angle-right am-icon-lg"></span> -->
<!-- 								</div> -->
<!-- 								<div class="clear"></div> -->
<!-- 							</li> -->
						</ul>

						<div class="clear"></div>
					</div>
					<!--物流 -->
					<div class="logistics">
						<h3>选择物流方式</h3>
						<ul class="op_express_delivery_hot">
							<li data-value="yuantong" class="OP_LOG_BTN  "><i class="c-gap-right" style="background-position:0px -468px"></i>圆通<span></span></li>
							<li data-value="shentong" class="OP_LOG_BTN  "><i class="c-gap-right" style="background-position:0px -1008px"></i>申通<span></span></li>
							<li data-value="yunda" class="OP_LOG_BTN  "><i class="c-gap-right" style="background-position:0px -576px"></i>韵达<span></span></li>
							<li data-value="zhongtong" class="OP_LOG_BTN op_express_delivery_hot_last "><i class="c-gap-right" style="background-position:0px -324px"></i>中通<span></span></li>
							<li data-value="shunfeng" class="OP_LOG_BTN  op_express_delivery_hot_bottom"><i class="c-gap-right" style="background-position:0px -180px"></i>顺丰<span></span></li>
						</ul>
					</div>
					<div class="clear"></div>

					<!--支付方式-->
					<div class="logistics">
						<h3>选择支付方式</h3>
						<ul class="pay-list">
							<li class="pay card"><img src="res/static/one/images/wangyin.jpg" />银联<span></span></li>
							<li class="pay qq"><img src="res/static/one/images/weizhifu.jpg" />微信<span></span></li>
							<li class="pay taobao"><img src="res/static/one/images/zhifubao.jpg" />支付宝<span></span></li>
						</ul>
					</div>
					<div class="clear"></div>

					<!--订单 -->
					<div class="concent">
						<div id="payTable">
							<h3>确认订单信息</h3>
							<div class="cart-table-th">
								<div class="wp">

									<div class="th th-item">
										<div class="td-inner">商品信息</div>
									</div>
									<div class="th th-price">
										<div class="td-inner">单价</div>
									</div>
									<div class="th th-amount">
										<div class="td-inner">数量</div>
									</div>
									<div class="th th-sum">
										<div class="td-inner">金额</div>
									</div>
									<div class="th th-oplist">
										<div class="td-inner">配送方式</div>
									</div>

								</div>
							</div>
							<div class="clear"></div>
							
							<c:forEach var="goods" items="${pay_goods}" varStatus="s">
							<tr class="item-list">
								<div class="bundle  bundle-last">
									<div class="bundle-main">
										<ul class="item-content clearfix">
											<div class="pay-phone">
												<li class="td td-item">
													<div class="item-pic">
														<a href="javascript:void(0)" class="J_MakePoint">
															<img src="/userphoto/${goods.goodsimage}" class="itempic J_ItemImg" width="80px" height="80px"></a>
													</div>
													<div class="item-info">
														<div class="item-basic-info">
															<a href="javascript:void(0)" target="_blank" title="" class="item-title J_MakePoint" data-point="tbcart.8.11">${goods.goodsname}</a>
														</div>
													</div>
												</li>
												<li class="td td-info">
													<div class="item-props">
														<span class="sku-line">${goods.reserved1}</span>
													</div>
												</li>
												<li class="td td-price">
													<div class="item-price price-promo-promo">
														<div class="price-content">
															<em class="J_Price price-now">${goods.goodsprice}</em>
														</div>
													</div>
												</li>
											</div>
											<li class="td td-amount">
												<em class="J_Price price-now">${goods.total}</em>
											</li>
											<li class="td td-sum">
												<div class="td-inner">
													<em tabindex="0" class="J_ItemSum number">${goods.sumprice}</em>
												</div>
											</li>
											<li class="td td-oplist">
												<div class="td-inner">
													<span class="phone-title">配送方式</span>
													<div class="pay-logis">
														包邮
													</div>
												</div>
											</li>
										</ul>
										<div class="clear"></div>
									</div>
							</tr>
							</c:forEach>
							<div class="clear"></div>
							</div>

<!-- 							<tr id="J_BundleList_s_1911116345_1" class="item-list"> -->
<!-- 								<div id="J_Bundle_s_1911116345_1_0" class="bundle  bundle-last"> -->
<!-- 									<div class="bundle-main"> -->
<!-- 										<ul class="item-content clearfix"> -->
<!-- 											<div class="pay-phone"> -->
<!-- 												<li class="td td-item"> -->
<!-- 													<div class="item-pic"> -->
<!-- 														<a href="#" class="J_MakePoint"> -->
<!-- 															<img src="res/static/one/images/kouhong.jpg_80x80.jpg" class="itempic J_ItemImg"></a> -->
<!-- 													</div> -->
<!-- 													<div class="item-info"> -->
<!-- 														<div class="item-basic-info"> -->
<!-- 															<a href="#" target="_blank" title="美康粉黛醉美唇膏 持久保湿滋润防水不掉色" class="item-title J_MakePoint" data-point="tbcart.8.11">美康粉黛醉美唇膏 持久保湿滋润防水不掉色</a> -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 												</li> -->
<!-- 												<li class="td td-info"> -->
<!-- 													<div class="item-props"> -->
<!-- 														<span class="sku-line">颜色：10#蜜橘色+17#樱花粉</span> -->
<!-- 													</div> -->
<!-- 												</li> -->
<!-- 												<li class="td td-price"> -->
<!-- 													<div class="item-price price-promo-promo"> -->
<!-- 														<div class="price-content"> -->
<!-- 															<em class="J_Price price-now">39.00</em> -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 												</li> -->
<!-- 											</div> -->
<!-- 											<li class="td td-amount"> -->
<!-- 												<em class="J_Price price-now">3</em> -->
<!-- 											</li> -->
<!-- 											<li class="td td-sum"> -->
<!-- 												<div class="td-inner"> -->
<!-- 													<em tabindex="0" class="J_ItemSum number">117.00</em> -->
<!-- 												</div> -->
<!-- 											</li> -->
<!-- 											<li class="td td-oplist"> -->
<!-- 												<div class="td-inner"> -->
<!-- 													<span class="phone-title">配送方式</span> -->
<!-- 													<div class="pay-logis"> -->
<!-- 														包邮 -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 											</li> -->

<!-- 										</ul> -->
<!-- 										<div class="clear"></div> -->

<!-- 									</div> -->
<!-- 							</tr> -->
							</div>
							<div class="clear"></div>
							<div class="pay-total">
							<div class="clear"></div>
							</div>
							<!--含运费小计 -->
<!-- 							<div class="buy-point-discharge "> -->
<!-- 								<p class="price g_price "> -->
<%-- 									合计（含运费） <span>¥</span><em class="pay-sum">${pay_sum_money}</em> --%>
<!-- 								</p> -->
<!-- 							</div> -->

							<!--信息 -->
							<div class="order-go clearfix">
								<div class="pay-confirm clearfix">
									<div class="">
										<div tabindex="0" id="holyshit267" class="realPay"><em class="t">实付款：</em>
											<span class="price g_price ">
                                    <span>¥</span> <em class="style-large-bold-red " id="J_ActualFee">${pay_sum_money}</em>
											</span>
										</div>
<!-- 										<div id="holyshit268" class="pay-address"> -->
<!-- 											<p class="buy-footer-address"> -->
<!-- 												<span class="buy-line-title buy-line-title-type">寄送至：</span> -->
<!-- 												<span class="buy--address-detail"> -->
<!-- 								   <span class="province">湖北</span>省 -->
<!-- 												<span class="city">武汉</span>市 -->
<!-- 												<span class="dist">洪山</span>区 -->
<!-- 												<span class="street">雄楚大道666号(中南财经政法大学)</span> -->
<!-- 												</span> -->
<!-- 												</span> -->
<!-- 											</p> -->
<!-- 											<p class="buy-footer-address"> -->
<!-- 												<span class="buy-line-title">收货人：</span> -->
<!-- 												<span class="buy-address-detail">    -->
<!--                                          <span class="buy-user">艾迪 </span> -->
<!-- 												<span class="buy-phone">15871145629</span> -->
<!-- 												</span> -->
<!-- 											</p> -->
<!-- 										</div> -->
									</div>
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
									
									
									var e=getUrlSearch("err");
									if(e=='0'){
										layer.msg('系统繁忙');
									}
										
									</script>
									<script type="text/javascript">
										function subOrder(){
											//获取当前登录人id
											var uid=$('uid').val();
											//获取选择的地址id
											var $li=$('#addul li[class!=user-addresslist]');
											if($li.length==0){
												layer.msg('请先选择收货地址');
												return;
											}
											var addressid=$li.children()[0].value;
											//向后台发送请求
											location.href="order?method=success&uid="+uid+"&addressid="+addressid+"&money="+${pay_sum_money};
										}
										
									</script>
									
									<div id="holyshit269" class="submitOrder">
										<div class="go-btn-wrap">
											<a id="J_Go" onclick="subOrder()" href="javascript:void(0)" class="btn-go" tabindex="0" title="点击此按钮，提交订单">提交订单</a>
										</div>
									</div>
									
									
									
									<div class="clear"></div>
								</div>
							</div>
						</div>

						<div class="clear"></div>
					</div>
				</div>
    </div>
  </div>
</body>
</html>