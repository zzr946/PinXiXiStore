<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css" />
		<script type="text/javascript" src="js/jquery.js" ></script>
		<script type="text/javascript" src="layer/layer.js"></script>
		<link rel="stylesheet" href="layui/css/layui.css" />
		<script type="text/javascript" src="layui/layui.js" ></script>
	</head>
	<body >
		<div class="col-md-offset-3">
		  <h1>开始创建店铺吧 <small>你的小店从现在开始</small></h1>
		</div>
			<div>
				<form class="form-horizontal" style="padding:20px" action="store" method="post" enctype="multipart/form-data">
<!-- 				隐藏域，放置商家id -->
				<input type="hidden" id="mid" name="mid" value="${merchant.mid}">
				  <div class="form-group">
				    <label  class="col-md-2 control-label">店铺名</label>
				    <div class="col-md-6">
				      <input type="text" class="form-control" id="storename" placeholder="请输入店铺名" name="storename">
				    </div>
				  </div>
				   <div class="form-group">
				    <label  class="col-md-2 control-label">店铺地址</label>
				    <div class="col-md-6">
				      <input type="text" class="form-control" id="storeaddress" placeholder="请输入店铺地址" name="storeaddress">
				    </div>
				  </div>
<!-- 				  营业执照上传 -->
				   <div class="form-group">
				    <label  class="col-md-2 control-label">营业执照</label>
				    <div class="col-md-6">
<!-- 				     <input type="text" class="form-control" id="yyzz"> -->
				     <input type="file" name="aptitude" multiple />
				    </div>
				  </div>
				  
<!-- 				  店铺logo上传 -->
				   <div class="form-group">
				    <label  class="col-md-2 control-label">店铺logo</label>
				    <div class="col-md-6">
<!-- 				     <input type="text" class="form-control" id="storelogo"> -->
				     <input type="file" name="storelogo" multiple />
				    </div>
				  </div>
				  
				   <div class="form-group">
				    <label  class="col-md-2 control-label">店铺简介</label>
				    <div class="col-md-6">
				      <!--<input type="password" class="form-control" id="inputPassword3" placeholder="请输入店铺简介" name="storeinfo>-->
				      <textarea class="form-control" rows="3" placeholder="请输入店铺简介" name="storeinfo"></textarea>
				    </div>
				  </div>
				   <div class="form-group">
				    <div class="col-md-3 col-md-offset-5">
				  <button class="btn btn-primary btn-block">确定创建</button>
				    </div>
				  </div>
				  </form>
			</div>
			</div>
			
			
			<script type="text/javascript">
				 $(function(){
					if('${createstore}' == 1){
						layer.msg('创建成功,等待管理员审核')
					}else if('${createstore}' == 2){
						layer.msg('创建失败,店铺名已存在')
					}
				})
					layui.use(['form'], function(){
					  var form = layui.form
					});
		</script>
	</body>
</html>
