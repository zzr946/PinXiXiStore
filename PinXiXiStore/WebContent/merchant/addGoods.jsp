<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="taglib.jsp" %>
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
		  <h1>添加商品 <small>为你的小店添砖加瓦</small></h1>
		</div>
			<div>
				<form class="form-horizontal" style="padding:20px" action="goods" method="post" enctype="multipart/form-data">
<!-- <!-- 				隐藏域，放置商家id --> 
				<input type="hidden" id="mid" name="mid" value="${merchant.mid}">
				 <div class="form-group">
				  <label  class="col-md-2 control-label">类别</label>
				    <div class="col-md-2 ">
				      <select class="form-control" name="select">
							 <c:forEach items="${sortList}" var="sort" varStatus="s">
<%-- 							 <input type="hidden" name="sid" value="${sort.sid}"/> --%>
								<option>${sort.sortname }</option>
							</c:forEach>
					</select>
				    </div>  
				  </div>
				  <div class="form-group">
				    <label  class="col-md-2 control-label">商品名</label>
				    <div class="col-md-6">
				      <input type="text" class="form-control" id="goodsname" placeholder="请输入商品名" name="goodsname">
				    </div>
				  </div>
				   <div class="form-group">
				    <label  class="col-md-2 control-label">商品价格</label>
				    <div class="col-md-6">
				      <input type="text" class="form-control" id="goodsprice" placeholder="请输入商品价格" name="goodsprice">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <label  class="col-md-2 control-label">商品库存</label>
				    <div class="col-md-6">
				      <input type="text" class="form-control" id="goodstotal" placeholder="请输入商品现有库存" name="goodstotal">
				    </div>
				  </div>
				  
				   <div class="form-group">
				    <label  class="col-md-2 control-label">计量单位</label>
				    <div class="col-md-6">
				      <input type="text" class="form-control" id="reserved1" placeholder="请输入计量单位" name="reserved1">
				    </div>
				  </div>
<!-- 				  商品图片上传 -->
				   <div class="form-group">
				    <label  class="col-md-2 control-label">商品图片</label>
				    <div class="col-md-6">
				     <input type="file" name="goodimage" multiple />
				    </div>
				  </div>
				  
				   <div class="form-group">
				    <label  class="col-md-2 control-label">商品简介</label>
				    <div class="col-md-6">
				      <textarea class="form-control" rows="3" placeholder="请输入商品简介" name="goodsinfo"></textarea>
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
					if('${addGoods}' == 1){
						layer.alert('添加成功',function(){
							location.href="goodssecond?method=findSort&mid=${merchant.mid}"
						})
					}else if('${addGoods}' == 2){
						layer.msg('添加失败,可能你的商品名已存在哟')
					}
				})
					layui.use(['form'], function(){
					  var form = layui.form
					});
		</script>
	</body>
</html>
