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
		})
</script>
</head>
<body>

<!--Header-part-->
<div id="header">
   <img src="images/logo2.PNG" style="height: 60px;"/>
</div>
<!--close-Header-part-->
<!--top-Header-menu-->
<div id="user-nav" class="navbar navbar-inverse">
  <ul class="nav">
    <li  class="dropdown" id="profile-messages" ><a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle"><i class="icon icon-user"></i>  <span class="text" id="merchantphone">请登录</span><b class="caret"></b></a>
     
     
     
<!--         隐藏域带值 -->
<!-- <!--      商家用户信息 --> 
<%--       <input type="hidden" id="mphone" name="mphone" value="${merchant.phone}"> --%>
<%--       <input type="hidden" id="mid" name="mid" value="${merchant.mid}"> --%>
<%--        <input type="hidden" id="msid" name="msid" value="${merchant.sid}"> --%>
<%--        <input type="hidden" id="mphone" name="mphone" value="${merchant.mpassword}"> --%>
<%--       <input type="hidden" id="mid" name="mid" value="${merchant.merchantdr}"> --%>
<!--          店铺信息    -->
<%--    		<input type="hidden" id="storesid" name="storesid" value="${store.sid}"> --%>
<%--        <input type="hidden" id="storename" name="storename" value="${store.storename}"> --%>
<%--       <input type="hidden" id="storelogo" name="storelogo" value="${store.storelogo}"> --%>
<%--       <input type="hidden" id="storeinfo" name="storeinfo" value="${store.storeinfo}"> --%>
<%--        <input type="hidden" id="storeaddress" name="storeaddress" value="${store.storeaddress}"> --%>
<%--       <input type="hidden" id="storedr" name="storedr" value="${store.storedr}"> --%>
<%--       <input type="hidden" id="aptitude" name="aptitude" value="${store.aptitude}"> --%>
<%--        <input type="hidden" id="subtime" name="subtime" value="${store.subtime}"> --%>
<!-- <!--        分类信息 --> 
<%--       <input type="hidden" id="sortsid" name="sortsid" value="${sort.sid}"> --%>
<%--        <input type="hidden" id="sortname" name="sortname" value="${store.sortname}"> --%>
<%--       <input type="hidden" id="sortinfo" name="sortinfo" value="${store.sortinfo}"> --%>
<%--       <input type="hidden" id="storemerchantid" name="storemerchantid" value="${store.merchantid}"> --%>
      
      
      <!--      如果有传递过来的商家， 则显示商家手机号 -->
         <script type="text/javascript">
      	var phone ='${merchant.phone}';
      	if(phone){
          	$('#merchantphone').text('欢迎你，'+phone);
      	}
      </script>
      
      
      
      <ul class="dropdown-menu">
      
<!--       //点击切换账号，相当于点击退出按钮 -->
        <li><a href="merchant?method=logout"><i class="icon-key"></i> 切换账号</a></li>
      </ul>
    </li>
    <li class="dropdown" id="menu-messages"><a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle"><span class="text">店铺</span> <b class="caret"></b></a>
      <ul class="dropdown-menu">
        <li><a class="sAdd" title="" href="createStore.jsp" target="iframe"><i class="icon-plus"></i>创建店铺</a></li>
        <li class="divider"></li>
        <li><a class="sInbox" title="" href="main2.jsp" target="iframe" id="myStore"><i class="icon-home"></i> 我的店铺</a></li>
         <li class="divider"></li>
        <li><a class="sInbox" title="" href="updateStore.jsp" target="iframe"><i class="icon-legal"></i>修改店铺信息</a></li>
      </ul>
    </li>
    <li class=""><a title="" href="merchant?method=logout"><i class="icon icon-share-alt"></i> <span class="text">退出</span></a></li>
  </ul>
  
  
</div>
<!--close-top-Header-menu-->
<!--sidebar-menu-->
<div id="sidebar"><!--<a href="#" class="visible-phone"><i class="icon icon-home"></i> 控制台</a>-->
  <ul>
    <li class="active"><a href="welcome.html" target="iframe"><i class="icon icon-home"></i> <span>首页</span></a> </li>
    <li class="submenu" id="storeManage" > <a href="#"><i class="icon icon-list-alt"></i> <span>商品管理</span> </a>
      <ul>
        <li><a href="goodssecond?method=findByPage&page=1&mid=${merchant.mid }" target="iframe">商品列表</a></li>
        <li><a href="goodssecond?method=findSort&mid=${merchant.mid }" target="iframe">添加商品</a></li>
        <li><a href="sales?method=findBySpecialPage&specialPage=1&mid=${merchant.mid }" target="iframe">特价促销</a></li>
      </ul>
    </li>
    <li class="submenu"> <a href="#"><i class="icon icon-tasks"></i> <span>类别管理</span> </a>
      <ul>
      	<li><a href="createSort.jsp" target="iframe">添加类别</a></li>
        <li><a href="sort?method=findByPage&page=1&mid=${merchant.mid}" target="iframe">类别列表</a></li>
      </ul>
    </li>
     <li class="submenu"> <a href="#"><i class="icon icon-tasks"></i> <span>订单管理</span> </a>
      <ul>
        <li><a href="order?method=findOrderByPage&page=1&mid=${merchant.mid}" target="iframe">订单列表</a></li>
        <li><a href="returns?method=findByRPage&page=1&mid=${merchant.mid}" target="iframe">退货审批</a></li>
      </ul>
    </li>
     <li class="submenu"> <a href="#"><i class="icon icon-envelope-alt"></i> <span>评论管理</span> </a>
      <ul>
        <li><a href="comment?method=findComment&page=1" target="iframe">评论列表</a></li>
      </ul>
    </li>
  </ul>
</div>
<!--sidebar-menu-->

<!--main-container-part-->
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
