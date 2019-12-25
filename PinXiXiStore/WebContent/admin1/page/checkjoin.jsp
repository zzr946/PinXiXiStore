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
		<script type="text/javascript">
		var i = 1;
		function show(){
			var dr = document.getElementById("ta").children[0].children[i].children[4].value;
			if(dr == 0){
				document.getElementById("ta").children[0].children[i].children[5].innerText='审核中'
			}else if(dr == 1){
				document.getElementById("ta").children[0].children[i].children[5].innerText='通过'
			}else if(dr == 2){
				document.getElementById("ta").children[0].children[i].children[5].innerText='已封禁'
			}
			i++;
		}
</script>
		
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
					<h4>商家资料详情</h4>
				</div>
				<div class="modal-body">
					<dl>
						<dt>新商家名称:</dt>
						<dd id="d1"></dd>
						<dt>新商家简介:</dt>
						<dd id="d2"></dd>
						<dt>新商家地址：</dt>
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
				<a href="" class="navbar-brand">商家入驻申请审核界面</a>
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
					<th>商家序号</th>
					<th>新店铺名</th>
					<th>店铺简介</th>
					<th>新店铺地址</th>
					<th>审核状态</th>
					<th>操作</th>
				</tr>
				<c:forEach var="store" items="${checkAllstore}" varStatus="d">
					<tr>
						<td>${store.sid}</td>
						<td>${store.storename}</td>
						<td>${store.storeinfo}</td>
						<td>${store.storeaddress}</td>
						<input type="hidden" value="${store.storedr}" id="storedr">
						<td></td>
						<script>
						show();
						</script>
						<td>
							<button  class="btn btn-info btn-sm btnUpdate" data-toggle="modal"  data-target="#dailyEdit"onclick="XiuGai(this)"><span class="glyphicon glyphicon-edit"></span>通过</button>
							<button  class="btn btn-info btn-sm btnUpdate" data-toggle="modal"  data-target="#dailyEdit"onclick="XiuGai1(this)"><span class="glyphicon glyphicon-edit"></span>封禁</button>
						</td>
					</tr>
				</c:forEach>
			</table>
			</div>
			
		<!--翻页 -->
			<div >
			<button class="btn btn-primary col-md-1 col-md-offset-4" onClick="previousPage()">&lt;</button> 
			<button class="btn btn-primary col-md-1 col-md-offset-2" onClick="nextPage()">&gt;</button>
			</div>
			
		<script type="text/javascript">
		//下一页点击事件
    	function nextPage(){
    		var page = '${thispage}';
    		page = eval(page+'+1')
    		location.href="../../admin1/admin?method=Admin_checkAllalterstore1&page="+page;
    	}
// 		上一页点击事件
		function previousPage(){
			if('${thispage}' > 1){
				var page = '${thispage}';
				page = eval(page-'1');
				location.href="../../admin1/admin?method=Admin_checkAllalterstore1&page="+page;
			}else{
				layer.msg('第一页，不能再往上翻啦！！！');
			}
		}
		$(function(){
			if('${thispage1}' == 1 && '${thispage}' != 1){
				layer.msg('最后一页啦，不要再点了');
			}
			if('${thispage1}' == 1 && '${thispage}' == 1){
				layer.msg('你还没有任何商家入驻');
			}
		})
		
		
			</script>
			
			
			
			
			
			
			
			

	<script type="text/javascript">
	function XiuGai(btn){
		var id1= $(btn).parent().parent().children().eq(0).text();
		layer.confirm('是否确认通过?',{btn:['确认','取消'],icon:6},function(){
		location.href="../../admin1/admin?method=Admin_changedrbysid&sid="+id1;
		});
	}
	
	function XiuGai1(btn){
		var id1= $(btn).parent().parent().children().eq(0).text();
		layer.confirm('是否确认封禁?',{btn:['确认','取消'],icon:5},function(){
		location.href="../../admin1/admin?method=Admin_banstoredr&sid="+id1;
		});
	}
	</script>




</body>
</html>