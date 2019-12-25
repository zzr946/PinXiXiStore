<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>拼XiXi商城-用户登录</title>
  <link rel="stylesheet" type="text/css" href="res/static/css/main.css">
  <link rel="stylesheet" type="text/css" href="res/layui/css/layui.css">
  <script type="text/javascript" src="res/JQuery/jquery-3.3.1.js" ></script>
  <script type="text/javascript" src="res/layer/layer.js"></script>
  <script type="text/javascript" src="res/layui/layui.js"></script>
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
          <form action="goods" class="layui-form" novalidate>
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
    <div class="main-nav">
      <div class="inner-cont0">
        <div class="inner-cont1 w1200">
          <div class="inner-cont2">
            <a href="goods?method=productall">所有商品</a>
            <a href="sales?method=salesall">今日特惠</a>
            <a href="information.jsp">热点资讯</a>
            <a href="about.jsp">关于我们</a>
          </div>
        </div>
      </div>
    </div>
    <div class="login-bg">
      <div class="login-cont w1200">
        <div class="form-box">
          <form class="layui-form" action="user" method="get">
          <!-- 隐藏域用于区分是什么操作 -->
          <input type="hidden" name="method" value="user_loginBypwd">
            <legend>用户名密码登录</legend>
            <div class="layui-form-item">
              <div class="layui-inline iphone">
                <div class="layui-input-inline">
                  <i class="layui-icon layui-icon-cellphone iphone-icon"></i>
                  <input type="tel" name="phone" id="phone" lay-verify="required|phone" placeholder="请输入手机号" autocomplete="off" class="layui-input">
                </div>
              </div>
              
              
              <div class="layui-inline iphone">
                <div class="layui-input-inline">
                  <i class="layui-icon layui-icon-password iphone-icon"></i>
                  <input type="password" name="password" id="password" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
                </div>
              </div>
              
            </div>
            <div class="layui-form-item login-btn">
              <div class="layui-input-block">
                <button type="submit" id="log_btn" class="layui-btn" lay-submit="" lay-filter="demo1">登录</button>
              </div>
            </div>
            
            <div style="float: left">
            	<a href="loginbyphone.jsp">短信登录</a>
            </div>
            <div style="float:right">
            	<a href="changepwd.jsp">忘记密码</a>
            </div>
            
            <script type="text/javascript">
            $(function(){
            	if('${loginBypwderror}'){
            		//如果登录不成功则弹出提示框
                    layer.alert('${loginBypwderror}');
                   }
            	
                
            })
            
            
            </script>
            
            
          </form>
        </div>
      </div>
    </div>
  </div>

  <div class="footer">
    <div class="ng-promise-box">
      <div class="ng-promise w1200">
        <p class="text">
          <a class="icon1" href="javascript:;">7天无理由退换货</a>
          <a class="icon2" href="javascript:;">满99元全场免邮</a>
          <a class="icon3" style="margin-right: 0" href="javascript:;">100%品质保证</a>
        </p>
      </div>
    </div>
    <div class="mod_help w1200">                                     
      <p>
        <a href="javascript:;">关于我们</a>
        <span>|</span>
        <a href="javascript:;">帮助中心</a>
        <span>|</span>
        <a href="javascript:;">售后服务</a>
        <span>|</span>
        <a href="javascript:;">热点资讯</a>
        <span>|</span>
        <a href="javascript:;">关于货源</a>
      </p>
      <p class="coty">拼XiXi商城版权所有 &copy; 2019-9999</p>
    </div>
  </div>
  <script type="text/javascript">
   layui.config({
      base: 'res/static/js/util' //你存放新模块的目录，注意，不是layui的模块目录
    }).use(['jquery','form'],function(){
          var $ = layui.$,form = layui.form;


        $("#find").click(function() {
            if(!/^1[3,5,6,7,8,9]{1}\d{9}$/.test($("#phone").val())){
              layer.msg("请输入正确的手机号");
              return false;
            }
            var obj=this;
            $.ajax({
                type:"get",
                url:"../json/login.json",
                dataType:"json",//返回的
                success:function(data) {
                    
                    if(data.result){
                        $("#find").addClass(" layui-btn-disabled");
                        $('#find').attr('disabled',"true");
                        settime(obj);
                        $("#msg").text(data.msg);
                    }else{
                        layer.msg(data.msg);
                    }
                },
                error:function(msg) {
                    console.log(msg);
                }
            }); 
        })
        var countdown=60; 
        function settime(obj) { 
        if (countdown == 0) { 
            obj.removeAttribute("disabled"); 
            obj.classList.remove("layui-btn-disabled")
            obj.value="获取验证码"; 
            countdown = 60; 
            return;
        } else { 
            
            obj.value="重新发送(" + countdown + ")"; 
            countdown--; 
        } 
        setTimeout(function() { 
            settime(obj) }
            ,1000) 
        }
    })
  </script>

</body>
</html>