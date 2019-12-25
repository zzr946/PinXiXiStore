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
	</head>
	<body >
		
			<div>
				<form class="form-horizontal" style="padding:20px" action="sort" method="post" >
				<input type="hidden" name="method" value="add">
				
				
				
         <!--    隐藏域带值 -->
<!--      商家用户信息 -->
      <input type="hidden" id="mphone" name="mphone" value="${merchant.phone}">
      <input type="hidden" id="mid" name="mid" value="${merchant.mid}">
       <input type="hidden" id="msid" name="msid" value="${merchant.sid}">
       <input type="hidden" id="mphone" name="mphone" value="${merchant.mpassword}">
      <input type="hidden" id="mid" name="mid" value="${merchant.merchantdr}">
<!--    店铺信息    -->
   		<input type="hidden" id="storesid" name="storesid" value="${store.sid}">
       <input type="hidden" id="storename" name="storename" value="${store.storename}">
      <input type="hidden" id="storelogo" name="storelogo" value="${store.storelogo}">
      <input type="hidden" id="storeinfo" name="storeinfo" value="${store.storeinfo}">
       <input type="hidden" id="storeaddress" name="storeaddress" value="${store.storeaddress}">
      <input type="hidden" id="storedr" name="storedr" value="${store.storedr}">
      <input type="hidden" id="aptitude" name="aptitude" value="${store.aptitude}">
       <input type="hidden" id="subtime" name="subtime" value="${store.subtime}">
       
       
       
       
				  <div class="form-group">
				    <label  class="col-md-2 control-label">类别名</label>
				    <div class="col-md-6">
				      <input type="text" class="form-control" id="storename" placeholder="请输入类别名" name="sortname">
				    </div>
				  </div>
				  

				   <div class="form-group">
				    <label  class="col-md-2 control-label">类别简介</label>
				    <div class="col-md-6">
				      <textarea class="form-control" rows="3" placeholder="请输入类别简介" name="sortinfo"></textarea>
				    </div>
				  </div>
				   <div class="form-group">
				    <div class="col-md-3 col-md-offset-5">
				  <button class="btn btn-primary btn-block">确定添加</button>
				    </div>
				  </div>
				  </form>
			</div>
			</div>
			<script type="text/javascript">
				 $(function(){
					if('${addsort}' == 1){
						layer.msg('创建成功')
					}else if('${addsort}' == 2){
						layer.msg('创建失败')
					}else if('${addsort}' == 3){
						layer.msg('商品类别已存在')
					}
				})
// 					layui.use(['form'], function(){
// 					  var form = layui.form
// 					});
		</script>
	</body>
</html>
