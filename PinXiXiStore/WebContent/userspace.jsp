<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>拼XiXi商城-个人中心</title>
  
  <!-- 个人中心样式 -->
  		<link href="res/static/one/AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
		<link href="res/static/one/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">
		<link href="res/static/one/css/personal.css" rel="stylesheet" type="text/css">
		<link href="res/static/one/css/systyle.css" rel="stylesheet" type="text/css">
  
  
  
  
  
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
// 			console.info('手机号');
		}else{
			//昵称不为空
			$('#weltext').text('欢迎您,'+nickname);
// 			console.info('昵称');
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
					<div class="wrap-left">
						<div class="wrap-list">
							<div class="m-user">
								<!--个人信息 -->
								<div class="m-bg"></div>
								<div class="m-userinfo">
									<div class="m-baseinfo">
										<a href="userinfo.jsp">
											<img id="select_btn" src="res/static/one/images/getAvatar.do.jpg">
										</a>
										
										<script type="text/javascript">
											//从数据库中获取头像并显示头像
											if('${userlogin.photo}'){
												//如果有头像就显示头像    userphoto/${userlogin.photo}
												document.getElementById('select_btn').src='/userphoto/${userlogin.photo}';
											}
											console.info('${userlogin.photo}')
										</script>
										
										<em class="s-name" id="view_name">(小叮当)</em>
										<script type="text/javascript">
											//显示用户昵称的方法
											//先判断该用户是否设置昵称，如果设置了昵称就直接显示昵称，否则显示手机号码
											//从作用域中获取用户昵称和手机号
											var phone=$('#uphone').val();
											var nickname=$('#unickname').val();
											if(!nickname){
												//昵称为空
												$('#view_name').text(phone);
	//								 			console.info('手机号');
											}else{
												//昵称为空
												$('#view_name').text(nickname);
	//								 			console.info('昵称');
											}
										</script>
										
										
										<div class="s-prestige am-btn am-round">
											拼XiXi会员</div>
									</div>
									<div class="m-right">
										<div class="m-address">
											<a href="useraddress.jsp" class="i-trigger">管理<br>我的收货地址</a>
										</div>
									</div>
								</div>
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
								layer.msg('系统繁忙!请稍后再试');
							}
								
							</script>

							<!--订单 -->
							<div class="m-order">
								<div class="s-bar">
									<i class="s-icon"></i>我的订单
									<a class="i-load-more-item-shadow" href="order?method=find&uid=${userlogin.uid}">全部订单</a>
								</div>
								<ul>
									<li><a href="userorder.jsp"><i><img src="res/static/one/images/pay.png"/></i><span>待付款</span></a></li>
									<li><a href="userorder.jsp"><i><img src="res/static/one/images/send.png"/></i><span>待发货<em class="m-num"></em></span></a></li>
									<li><a href="userorder.jsp"><i><img src="res/static/one/images/receive.png"/></i><span>待收货</span></a></li>
									<li><a href="userorder.jsp"><i><img src="res/static/one/images/comment.png"/></i><span>待评价<em class="m-num"></em></span></a></li>
									<li><a href="userorder.jsp"><i><img src="res/static/one/images/refund.png"/></i><span>退换货</span></a></li>
								</ul>
							</div>
							
							<!--物流 -->
							<div class="m-logistics">
								<div class="s-bar">
									<i class="s-icon"></i>我的物流
								</div>
								<div class="s-content">
									<div style="width:250px;height:50px; margin-left: 300px">
										<h1 style="font-size: 25px">暂无物流信息</h1>
									</div>
								</div>

							</div>

						</div>
					</div>
					<div class="wrap-right">

						<!-- 日历-->
						<%
							Calendar calendar=Calendar.getInstance();
							//将date类型转为calendar类型
							calendar.setTime(new Date());
							//获取年
							String year=calendar.get(Calendar.YEAR)+"";
							//获取月
							String month=calendar.get(Calendar.MONTH)+1+"";
							//获取日
							String day=calendar.get(Calendar.DAY_OF_MONTH)+"";
							//获取星期几
							int week=calendar.get(Calendar.DAY_OF_WEEK)-1;
							String weekday="";
							switch(week){
							case 0:weekday="星期天"; break;
							case 1:weekday="星期一"; break;
							case 2:weekday="星期二"; break;
							case 3:weekday="星期三"; break;
							case 4:weekday="星期四"; break;
							case 5:weekday="星期五"; break;
							case 6:weekday="星期六"; break;
							}
							
						%>
						<div class="day-list">
							<div class="s-bar">
								<a class="i-history-trigger s-icon" href="#"></a>我的日历
								<a class="i-setting-trigger s-icon" href="#"></a>
							</div>
							<div class="s-care s-care-noweather">
								<div class="s-date">
									<em><%=day %></em>
									<span><%=weekday %></span>
									<span><%=year %>年<%=month %>月</span>
								</div>
							</div>
						</div>
						<!--热卖推荐 -->
						<div class="new-goods">
							<div class="s-bar">
								<i class="s-icon"></i>推荐商品
							</div>
							<div class="new-goods-info">
								<a class="shop-info" href="#" target="_blank">
									<div >
										<img src="res/static/one/images/imgsearch1.jpg" alt="">
									</div>
                                    <span class="one-hot-goods">￥9.20</span>
								</a>
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