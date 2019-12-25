<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>PinXiXi商家管理</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>

		<script type="text/javascript" src="layer/layer.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/fullcalendar.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>

<script type="text/javascript">
		$(function(){
			if (window != top){
				top.location.href = location.href;
			}
				if('${result.code}' == -1){
					layer.msg('您还未创建店铺哦,请先去创建店铺哦')
				}
				if('${result.code}' == 3){
					layer.msg('您的店铺正在审核中')
				}
				if('${result.code}' == 2){
					layer.msg('您的店铺正处于封禁中，请联系相关管理人员')
				}
				
		})
</script>
</head>
<body>

<!--Header-part-->
<div id="header">
  <!--<h1><a href="dashboard.html">PinXiXi</a></h1>-->
   <img src="images/logo2.PNG" style="height: 60px;"/>
</div>
<!--close-Header-part-->
<!--top-Header-menu-->
<div id="user-nav" class="navbar navbar-inverse">
  <ul class="nav">
    <li  class="dropdown" id="profile-messages" ><a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle"><i class="icon icon-user"></i>  <span class="text" id="merchantphone">请登录</span><b class="caret"></b></a>


<!--      点击登录，跳转到登陆页面  -->
      <ul class="dropdown-menu">
        <li ><a href="merchant?method=logout"><i class="icon-key"></i><span id="dl" class="text">登录</span></a></li>
        
        <!--      如果有传递过来的商家， 则显示商家手机号,登录变为切换账号 -->
         <script type="text/javascript">
      	var phone ='${merchant.phone}';
      	if(phone){
          	$('#merchantphone').text('欢迎你，'+phone);
          	$('#dl').text('切换账号');
      	}
      </script>
      </ul>
    </li>
    <li class="dropdown" id="menu-messages"><a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle"><span class="text">店铺</span> <b class="caret"></b></a>
      <ul class="dropdown-menu">
      
<!--       点击创建店铺，跳转到创建店铺页面 -->
        <li><a class="sAdd" title="" href="createStore.jsp" target="iframe"><i class="icon-plus"></i>创建店铺</a></li>
        <li class="divider"></li>
        
<!--         点击我的店铺，先查询是否创建了店铺,有并且激活了则跳转到main2.jsp，可以对店铺进行操作 -->
        <li id="mystore"><a class="sInbox"  href="storesecond?method=findStore&msid=${merchant.sid}" target="iframe" id="myStore"><i class="icon-home"></i> 我的店铺</a></li>
         <li class="divider"></li>
         
<!--          点击修改店铺信息，跳转到修改店铺信息页面 -->
        <li><a class="sInbox" title="" href="updateStore.jsp" target="iframe"><i class="icon-legal"></i>修改店铺信息</a></li>
      </ul>
    </li>
<!--     点击退出，进入merchantservlet，清空所有session -->
    <li class=""><a title="" href="merchant?method=logout"><i class="icon icon-share-alt"></i> <span class="text">退出</span></a></li>
  </ul>
</div>



<!--       显示区 -->
<div id="content">

  <div class="container-fluid">
      

    <div class="row-fluid">
    
<!--     iframe显示各个店铺相关此操作的页面 -->
      <iframe style="width: 100%; height:1200px; border: none;" name="iframe" src="welcome.html">
      	
      </iframe>
		</div>

<!--end-main-container-part-->

<!--Footer-part-->

<div class="row-fluid">
  <div id="footer" class="span12">Copyright &copy;版权归PinXiXi所有 </div>
</div>

<!--end-Footer-part-->

<script src="js/excanvas.min.js"></script> 
<script src="js/jquery.min.js"></script> 
<script src="js/jquery.ui.custom.js"></script> 
<script src="js/bootstrap.min.js"></script> 
<script src="js/jquery.flot.min.js"></script> 
<script src="js/jquery.flot.resize.min.js"></script> 
<script src="js/jquery.peity.min.js"></script> 
<script src="js/matrix.js"></script>   
<script src="js/jquery.validate.js"></script>  
<script src="js/jquery.uniform.js"></script> 
<script src="js/select2.min.js"></script> 
<script src="js/matrix.popover.js"></script> 
<script src="js/jquery.dataTables.min.js"></script> 
</body>
</html>
