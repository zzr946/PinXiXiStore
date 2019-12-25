<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>




		<link rel="stylesheet" href="../bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="../bootstrapvalidator/css/bootstrapValidator.css" />
		<script type="text/javascript" src="../js/jquery-3.3.1.js" ></script>
		<script type="text/javascript" src="../bootstrap/js/bootstrap.js" ></script>
		<script type="text/javascript" src="../bootstrapvalidator/js/bootstrapValidator.js" ></script>
		<script type="text/javascript" src="../bootstrapvalidator/js/language/zh_CN.js" ></script>
		<script type="text/javascript" src="../layer/layer.js"></script>
		
		
<style>
	.navbar{
		border-radius:0;
	}
	.table td{
		/*vertical-align: middle;*/
		white-space:nowrap;/*不自动换行*/
		overflow: hidden;/*超出的部分隐藏*/
		text-overflow: ellipsis;/*超出的文本用省略号*/
		max-width: 200px;
	}
	.table td:last-child{
		text-align:center;
	}
	.btn-group{
		margin-bottom:10px;
	}
</style>
</head>
<body>




<!-- 商家详情模态框 -->
	<div class="modal animated bounceIn" id="dailyInfo">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" data-dismiss="modal">&times;</button>
					<h4>举报详情</h4>
				</div>
				<div class="modal-body">
					<dl>
						<dt>商品名称:</dt>
						<dd id="d1"></dd>
						<dt>举报原因:</dt>
						<dd id="d2"></dd>
						<dt>举报内容：</dt>
						<dd id="d3"></dd>
					</dl>
				</div>
				<div class="modal-footer">
					<button class="btn btn-danger" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>




<!-- 	导航区 -->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a href="" class="navbar-brand">商品下架界面</a>
			</div>
			<div class="navbar-collapse collapse" id="userInfo">
<!-- 			 <ul class="nav navbar-nav navbar-right"> -->
<!-- 		        <li><a href="#"><span class="glyphicon glyphicon-user"></span></a></li> -->
<!-- 		        <li class="dropdown"> -->
<!-- 		          <a href="#" class="dropdown-toggle" data-toggle="dropdown">欢迎你，用户<span class="caret"></span></a> -->
<!-- 		          <ul class="dropdown-menu"> -->
<!-- 		            <li><a href="#">修改密码</a></li> -->
<!-- 		            <li><a href="#">安全退出</a></li> -->
<!-- 		          </ul> -->
<!-- 		        </li> -->
<!-- 		      </ul> -->
			</div>
		</div>
	</nav>	
<!-- 	内容区 -->
	<div class="container-fluid">
 		<!--路径导航--> 
		<!--<ol class="breadcrumb">
		  <li><a href="#">日报管理</a></li>
		  <li class="active">日报列表</li>
		</ol>-->
		
<!-- 		按钮组 -->
<!-- 		<div class="btn-group"> -->
<!-- 			<button class="btn btn-primary"  data-toggle="modal" data-target="#dailyAdd"><span class="glyphicon glyphicon-cloud-upload"></span>审核通过</button> -->
<!-- 			<button class="btn btn-danger btnDelAll"><span class="glyphicon glyphicon-remove"></span>审核不通过</button> -->
<!-- 		</div> -->
		
<!-- 		数据库区 -->
		<div class="data-container">
			<table class="table table-striped table-bordered" id="ta">
				<tr>
					<th>商品id</th>
					<th>商品名</th>
					<th>举报原因</th>
					<th>举报内容</th>
					<th>举报时间</th>
					
					<th>操作</th>
				</tr>
				<c:forEach var="repo1" items="${report1}" varStatus="d">
					<tr>
						<td>${repo1.gid }</td>
						<td>${repo1.goodsname}</td>
						<td>${repo1.cause}</td>
						<td>${repo1.content}</td>
						<td>${repo1.reporttime}</td>
						
						<td>
								
		<button class="btn btn-primary btn-sm btnInfo" data-toggle="modal" data-target="#dailyInfo"><span class="glyphicon glyphicon-info-sign"></span>详情</button>
<!-- 							<button class="btn btn-info btn-sm btnUpdate" data-toggle="modal"  data-target="#dailyEdit"><span class="glyphicon glyphicon-edit"></span>修改</button> -->
							<button  class="btn btn-danger btn-sm btnDel" onclick="nimabi(this)"><span class="glyphicon glyphicon-remove"></span>下架</button>
						</td>
					</tr>
				</c:forEach>
			</table>
			</div>
		
			


			
			
			
			
			
			
			
			


	<script type="text/javascript">
	function nimabi(btn){
		var id1= $(btn).parent().parent().children().eq(0).text();
		layer.confirm('是否确认下架?',{btn:['确认','取消'],icon:5},function(){
		location.href="../../admin1/admin?method=Admin_downTheGoods&gid="+id1;
		});
	}
	
	$(function(){
		$('.btnInfo').click(function(){
			var todatask = $(this).parent().parent().children(':nth-child(2)').text();
// 			console.info(todatask);
		$('#d1').text(todatask);
		
		var completeinfo = $(this).parent().parent().children(':nth-child(3)').text();
//			console.info(todatask);
	$('#d2').text(completeinfo);
	var nextplan = $(this).parent().parent().children(':nth-child(4)').text();
//		console.info(todatask);
$('#d3').text(nextplan);
		
		})
	})
	</script>








</body>
</html>