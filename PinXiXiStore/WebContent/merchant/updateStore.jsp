<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css" />
		<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
		<script type="text/javascript"></script>
		<script type="text/javascript" src="layer/layer.js"></script>
		<script type="text/javascript">
		$(function(){
				if('${sendUpdate}' == 1){
					layer.msg('提交成功，等待管理员审核')
				}
				if('${result.code}' == 2){
					layer.msg('提交失败')
				}
				
		})
</script>
	</head>
	<body >
		<div class="col-md-offset-3">
		  <h1>我的小店</small></h1>
		</div>
			<div>
				<form class="form-horizontal" style="padding:20px" action="storesecond" method="get" enctype="multipart/form-data">
				<!--    店铺信息    -->
				<input type="hidden" name="method" value="update"/>
   				<input type="hidden" id="storesid" name="storesid" value="${store.sid}">
   				<!-- 				隐藏域，放置商家id -->
				<input type="hidden" id="mid" name="mid" value="${merchant.mid}">
				  <div class="form-group">
				    <label  class="col-md-2 control-label">店铺名</label>
				    <div class="col-md-6">
				      <input type="text" class="form-control" id="newname" placeholder="请输入新店铺名" name="newname">
				    </div>
				  </div>
				   <div class="form-group">
				    <label  class="col-md-2 control-label">店铺地址</label>
				    <div class="col-md-6">
				      <input type="text" class="form-control" id="newaddress" placeholder="请输入新店铺地址" name="newaddress">
				    </div>
				  </div>
				  
				   <div class="form-group">
				    <label  class="col-md-2 control-label">店铺简介</label>
				    <div class="col-md-6">
				      <textarea class="form-control" rows="3" id="newstoreinfo" placeholder="请输入新店铺简介" name="newstoreinfo"></textarea>
				    </div>
				  </div>
				   <div class="form-group">
				    <div class="col-md-3 col-md-offset-5">
				  <button class="btn btn-primary btn-block" >确定修改</button>
				    </div>
				  </div>
				  </form>
			</div>
			</div>
	</body>
</html>
