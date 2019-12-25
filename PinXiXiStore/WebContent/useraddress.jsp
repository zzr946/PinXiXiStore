<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>拼XiXi商城-个人中心-收货地址</title>
  
  <!-- 个人中心样式 -->
		<link href="res/static/one/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">
		<link href="res/static/one/css/personal.css" rel="stylesheet" type="text/css">
		<link href="res/static/one/css/addstyle.css" rel="stylesheet" type="text/css">
		<script src="res/static/one/AmazeUI-2.4.2/assets/js/jquery.min.js" type="text/javascript"></script>
		<script src="res/static/one/AmazeUI-2.4.2/assets/js/amazeui.js" type="text/javascript"></script>
<!-- 		<script type="text/javascript" src="res/jQuery/jquery-3.3.1.js" ></script> -->
  
  
  
  
  <link rel="stylesheet" type="text/css" href="res/static/css/main.css">
  <link rel="stylesheet" type="text/css" href="res/layui/css/layui.css">
  <script type="text/javascript" src="res/JQuery/jquery-3.3.1.js" ></script>
  <script type="text/javascript" src="res/layer/layer.js"></script>
  <script type="text/javascript" src="res/layui/layui.js"></script>
  
  <!-- 三级联动js文件 -->
  <script type="text/javascript" src="res/static/js/jquery-3.3.1.js" ></script>
	<script type="text/javascript" src="res/static/js/jsAddress.js" ></script>
  
  
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
					<div class="user-address">
						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">地址管理</strong> / <small>Address&nbsp;list</small></div>
						</div>
						<hr/>
						<script>
							//点击设为默认地址后执行
							function sub(t){
								//获取当前用户的id
								var uid=$('#uid').val();
								//获取选择的地址的id
								var uadid=$(t).prev().val();
								//回调
								$.get('uaddress',{method:"defaultaddress",uid:uid,uadid:uadid},function(result){
									//将后台传来的json字符串转为json对象
// 									var res=JSON.parse(result);
// 									if(res.status==0){
// 										//成功
// 										window.sessionStorage.setItem("user", res.data);
// 										location.href="main.html";
// 									}else{
// 										//失败
// 										alert("失败");
// 									}
								})
								
								//加载界面时显示默认地址
// 								if($('.hiddenaddress').val()==0){
// 									console.info($('.hiddenaddress').prev().val());
// 								}
								
								
								
							}
						</script>
						<script type="text/javascript">
						$(function(){
							//显示默认地址的方法
							$('.hiddenaddress[value=0]').parent().addClass('defaultAddr');
							//console.info($('.hiddenaddress').val());
						})
						</script>
						
						<ul class="am-avg-sm-1 am-avg-md-3 am-thumbnails" id="address_ul">
							<!-- 循环标签 begin:开始条件  end:结束条件  step:步径  var:循环变量-->
							<c:forEach var="address" items="${useraddress}" varStatus="s">
								<li class="user-addresslist "><!-- class里加上defaultAddr表示被选中 -->
								<input type="hidden" class="hiddenaddress" value="${address.addressdr}"><!-- 地址的状态值 -->
									<input type="hidden" value="${address.uadid}"><!-- 地址的id -->
									<span class="new-option-r" onclick="sub(this)"><i class="am-icon-check-circle"></i>默认地址</span>
									<p class="new-tit new-p-re">
										<span class="new-txt">${address.uadname}</span>
										<span class="new-txt-rd2">${address.mobile}</span>
									</p>
									<div class="new-mu_l2a new-p-re">
										<p class="new-mu_l2cw">
											<span class="title">地址：</span>
											<span class="province">${address.province}</span>省
											<span class="city">${address.city}</span>市
											<span class="dist">${address.area}</span>区
											<span class="street">${address.datailaddress}</span></p>
									</div>
									<div class="new-addr-btn">
<!-- 										<a href="#"><i class="am-icon-edit"></i>编辑</a> -->
<!-- 										<span class="new-addr-bar">|</span> -->
										<a href="javascript:void(0);" onclick="delClick(this);"><i class="am-icon-trash"></i>删除</a>
									</div>
								</li>
							</c:forEach>
							<script>
								function delClick(t){
									//获取当前登录的用户id
									var uid=$("#uid").val();
									//获取删除的地址id
									var addressid=$(t).parent().prev().prev().prev().prev().val();
									//向后台发请求
									location.href="uaddress?method=deladdress&uid="+uid+"&addressid="+addressid;
									
									
								}
							</script>
							
<!-- 							<li class="user-addresslist"> -->
<!-- 								<span class="new-option-r"><i class="am-icon-check-circle"></i>设为默认</span> -->
<!-- 								<p class="new-tit new-p-re"> -->
<!-- 									<span class="new-txt">小叮当</span> -->
<!-- 									<span class="new-txt-rd2">159****1622</span> -->
<!-- 								</p> -->
<!-- 								<div class="new-mu_l2a new-p-re"> -->
<!-- 									<p class="new-mu_l2cw"> -->
<!-- 										<span class="title">地址：</span> -->
<!-- 										<span class="province">湖北</span>省 -->
<!-- 										<span class="city">武汉</span>市 -->
<!-- 										<span class="dist">洪山</span>区 -->
<!-- 										<span class="street">雄楚大道666号(中南财经政法大学)</span></p> -->
<!-- 								</div> -->
<!-- 								<div class="new-addr-btn"> -->
<!-- 									<a href="#"><i class="am-icon-edit"></i>编辑</a> -->
<!-- 									<span class="new-addr-bar">|</span> -->
<!-- 									<a href="javascript:void(0);" onclick="delClick(this);"><i class="am-icon-trash"></i>删除</a> -->
<!-- 								</div> -->
<!-- 							</li> -->
						</ul>
						
						<div class="clear"></div>
						<a class="new-abtn-type" data-am-modal="{target: '#doc-modal-1', closeViaDimmer: 0}">添加新地址</a>
						<!--例子-->
						<div class="am-modal am-modal-no-btn" id="doc-modal-1">

							<div class="add-dress">

								<!--标题 -->
								<div class="am-cf am-padding">
									<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">新增地址</strong> / <small>Add&nbsp;address</small></div>
								</div>
								<hr/>

								<div class="am-u-md-12 am-u-lg-8" style="margin-top: 20px;">
									<form class="am-form am-form-horizontal">
									<!-- 隐藏域用来区分是什么操作 -->
									<input type="hidden" id="method" name="method" value="addaddress">
										<div class="am-form-group">
											<label for="user-name" class="am-form-label">收货人</label>
											<div class="am-form-content">
												<input type="text" id="user-name" name="user-name" placeholder="收货人">
											</div>
										</div>

										<div class="am-form-group">
											<label for="user-phone" class="am-form-label">手机号码</label>
											<div class="am-form-content">
												<input id="user-phone" name="user-phone" placeholder="手机号必填" type="email">
											</div>
										</div>
										<div class="am-form-group">
											<label for="user-address" class="am-form-label">所在地</label>
											<div class="am-form-content address">
												<select  class="form-control" id="cmbProvince" name="cmbProvince">
<!-- 													<option value="a">省</option> -->
												</select>
												<select  class="form-control" id="cmbCity" name="cmbCity">
<!-- 													<option value="a">市</option> -->
												</select>
												<select  class="form-control" id="cmbArea" name="cmbArea">
<!-- 													<option value="a">区</option> -->
												</select>
												<!-- 三级联动方法 -->
												<script type="text/javascript">
													$(document).ready(function(){
														addressInit('cmbProvince', 'cmbCity', 'cmbArea');  
													});
											  	</script>
											</div>
										</div>

										<div class="am-form-group">
											<label for="user-intro" class="am-form-label">详细地址</label>
											<div class="am-form-content">
												<textarea class="" rows="3" id="user-intro" id="user-intro" name="user-intro" placeholder="输入详细地址" style="resize:none;"></textarea>
												<small>100字以内写出你的详细地址...</small>
											</div>
										</div>

										<div class="am-form-group">
											<div class="am-u-sm-9 am-u-sm-push-3">
												<input type="button" class="am-btn am-btn-danger" value="保存" id="sub" style="margin-left: 50px;width: 200px">
											</div>
											<script type="text/javascript">
											if('${useraddresserror}'){
												layer.msg('系统异常');
											}
											if('${noaddaddress}'){
												layer.msg('不能再添加地址了，已达到上限');
											}
// 											if('${useraddress}'){
// 												layer.msg('添加成功');
// 											}
											
												
												
												$(function(){
													//点击保存按钮时触发事件
													$('#sub').click(function(){
														//获取当前登录的用户id
														var uid=$('#uid').val();
														//获取收货人姓名
														var uadname=$('#user-name').val();
														//获取收货人手机号
														var mobile=$('#user-phone').val();
														//获取省
														var province=$('#cmbProvince').val();
														//获取市
														var city=$('#cmbCity').val();
														//获取区
														var area=$('#cmbArea').val();
														//获取详细地址
														var detailaddress=$('#user-intro').val();
// 														console.info("当前登录的用户"+uid);
// 														console.info("收货人姓名"+uadname);
// 														console.info("收货人手机号"+mobile);
// 														console.info("省"+province);
// 														console.info("市"+city);
// 														console.info("区"+area);
// 														console.info("详细地址"+detailaddress);
														//校验数据有效性
														var ok=true;
														if(uadname==""||mobile==""||province==""||city==""||area==""||detailaddress==""||province=="请选择"||city=="请选择"||area=="请选择"){
															ok=false;
															layer.msg('请将信息补充完整');
														}
														if(!(/^1[3456789]\d{9}$/.test(mobile))){
															//电话输入有误
															ok=false;
															layer.msg('电话号码输入有误');
														}
														//校验通过后，通过异步请求到后台
														if(ok){
															//获取隐藏域中的内容
															var method=$('#method').val();
															location.href="uaddress?method="+method+"&uid="+uid+"&uadname="+uadname+"&mobile="+mobile+"&province="+province+"&city="+city+"&area="+area+"&detailaddress="+detailaddress;
															
															/* $.get('uaddress',{method:method,uid:uid,uadname:uadname,mobile:mobile,province:province,city:city,area:area,detailaddress:detailaddress},function(result){
																//将后台传来的json字符串转为json对象
																var res=JSON.parse(result);
																if(res.code==0){
																	//添加成功
																	layer.msg('添加成功')
																	console.info(res.data);
																}else if(res.code==1){
																	//添加失败
																	layer.msg("系统异常，请稍后再试",{btn:['确认'],icon:5},function(){})
																}else if(res.code==2){
																	//达到上限，不能再添加
																	layer.msg("地址存储达到上限了,装不下啦~",{btn:['确认'],icon:5},function(){})
																}
															}) */
														}
														
													})
												})
												
											</script>
											
										</div>
									</form>
								</div>

							</div>

						</div>

					</div>
					<script type="text/javascript">
						$(document).ready(function() {							
							$(".new-option-r").click(function() {
								$(this).parent('.user-addresslist').addClass("defaultAddr").siblings().removeClass("defaultAddr");
							});
							
							var $ww = $(window).width();
							if($ww>640) {
								$("#doc-modal-1").removeClass("am-modal am-modal-no-btn")
							}
							
						})
					</script>
					
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
							<li class="active"> <a href="useraddress.jsp">收货地址</a></li>
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