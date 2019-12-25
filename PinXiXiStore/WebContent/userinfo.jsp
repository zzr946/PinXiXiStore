<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>拼XiXi商城-个人中心-个人信息</title>
  
  <!-- 个人中心样式 -->
  		<link href="res/static/one/AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
		<link href="res/static/one/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">
		<link href="res/static/one/css/personal.css" rel="stylesheet" type="text/css">
		<link href="res/static/one/css/infstyle.css" rel="stylesheet" type="text/css">
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
					<div class="user-info">
						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">个人信息</strong> / <small>Personal&nbsp;information</small></div>
						</div>
						<hr/>

					<form action="user_changeuserinfo" class="am-form am-form-horizontal" method="post" enctype="multipart/form-data">
						<!--头像 -->
						<div class="user-infoPic">
						<script>
							if('${userlogin.photo}'){
								$('#select_btn').src='/userphoto/${userlogin.photo}';
							}
						</script>
							<div class="filePic">
								<input id="btn_file" type="file" name="userphoto" class="inputPic" allowexts="gif,jpeg,jpg,png,bmp" accept="image/*">
								<img id="select_btn" class="am-circle am-img-thumbnail" style="width=100px;height: 100px" src="res/static/one/images/getAvatar.do.jpg" alt="" />
							</div>
							<p class="am-form-help">头像</p>
							<script type="text/javascript">
							//头像效果预览
								$(function(){
									$('#select_btn').click(function(){
										$('#btn_file').click();
									})
									$('#btn_file').change(function(event){
										var file=event.currentTarget.files[0];
										
										var reader=new FileReader();
										
										reader.onload=function(e){
											document.getElementById('select_btn').src=e.target.result;
										}
										reader.readAsDataURL(file);
										
									})
								})
								
							</script>
							<script type="text/javascript">
								//从数据库中获取头像并显示头像
								if('${userlogin.photo}'){
									//如果有头像就显示头像    userphoto/${userlogin.photo}
									document.getElementById('select_btn').src='/userphoto/${userlogin.photo}';
								}
								console.info('${userlogin.photo}')
							</script>
							
							<div class="info-m">
								<div><b id="view_name">用户名：路人甲</b></div>
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
								<div class="u-level">
									<span class="rank r2">
							             <a class="classes" href="javascript:void(0)">拼XiXi会员</a>
						            </span>
								</div>
								<div class="u-safety">
									<a href="usersafety.jsp">
									 账户安全管理
									<span class="u-profile"><i class="bc_ee0000" style="width: 60px;"></i></span>
									</a>
								</div>
							</div>
						</div>

						<!--个人信息 -->
						<div class="info-main">
							
								
								<div class="am-form-group">
									<label for="user-name2" class="am-form-label">昵称</label>
									<div class="am-form-content">
										<input type="text" name="nickname" id="user-nickname" placeholder="请输入昵称">
										<input type="hidden" id="hiddennickname" name="hiddennickname" value="">
									</div>
								</div>

								<div class="am-form-group">
									<label for="user-name" class="am-form-label">姓名</label>
									<div class="am-form-content">
										<input type="text" name="name" id="user-realname" placeholder="请输入姓名">
										<input type="hidden" id="hiddenname" name="hiddenname" value="">
									</div>
								</div>
								
								<div class="am-form-group">
									<label class="am-form-label">性别</label>
									<div class="am-form-content sex">
										<label class="am-radio-inline">
											<input type="radio" name="sex" id="sexnan" value="nan" data-am-ucheck> 男
										</label>
										<label class="am-radio-inline">
											<input type="radio" name="sex" id="sexnv" value="nv" data-am-ucheck> 女
										</label>
										<label class="am-radio-inline">
											<input type="radio" name="sex" id="sexno" value="no" data-am-ucheck> 保密
										</label>
									</div>
								</div>
								<script type="text/javascript">
									
									$(function(){
										//将用户信息显示到文本框中
										//获取用户昵称
										var nickname=$('#unickname').val();
										//获取用户姓名
										var name=$('#uname').val();
										//获取用户性别
										var sex=$('#usex').val();
										if(nickname){
											//如果昵称不为空则将昵称显示到页面上
											$('#user-nickname').val(nickname);
										}
										if(name){
											//如果真实姓名不为空则将真实姓名显示到页面上
											$('#user-realname').val(name);
										}
										if(sex){
											//如果性别不为空则将性别显示到页面上
											if(sex=="男"){
 												document.getElementById('sexnan').removeAttribute('data-am-ucheck');
 												document.getElementById('sexnan').setAttribute('checked','checked');
											}
											if(sex=="女"){
												document.getElementById('sexnv').removeAttribute('data-am-ucheck');
 												document.getElementById('sexnv').setAttribute('checked','checked');
											}
										}else{
											document.getElementById('sexno').removeAttribute('data-am-ucheck');
											document.getElementById('sexno').setAttribute('checked','checked');
										}
									})
								</script>
								<input type="hidden" id="hiddenuid" name="hiddenuid" value="${userlogin.uid}">
								<div class="info-btn">
									<button type="submit" class="am-btn am-btn-danger" id="tj" >保存修改</button>
								</div>
								<script >
									document.getElementById('tj').addEventListener("mousedown",function(){
										var nickname=$('#user-nickname').val();
										var name=$('#user-realname').val();
										if(nickname.length%2==0){
											//如果是偶数个字符
											$('#hiddennickname').val(nickname);
										}else{
											//如果是奇数个字符
											$('#hiddennickname').val(nickname+'~');
										}
										
										if(name.length%2==0){
											//如果是偶数个字符
											$('#hiddenname').val(name);
										}else{
											//如果是奇数个字符
											$('#hiddenname').val(name+'~');
										}
										
										var photo=$('#select_btn').src;
										console.info(photo);
										
// 										console.info("姓名"+name);
// 										console.info("昵称"+nickname);
// 										console.info('姓名长度'+name.length);
// 										console.info("昵称长度"+nickname.length);
									})
									
									if('${changeuserinfook}'){
										//修改信息成功
										layer.msg("修改成功");
										request.setAttribute("changeuserinfook","");
									}
									if('${changeuserinfoerror}'){
										//修改信息失败
										layer.msg('系统繁忙，请稍后再试');
										
									}
									
									
								</script>
							</div>
						</form>							
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
							<li class="active"> <a href="userinfo.jsp">个人信息</a></li>
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