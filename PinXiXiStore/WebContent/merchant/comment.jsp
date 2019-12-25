<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%@include file="taglib.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>商品列表</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://unpkg.com/animate.css@3.5.2/animate.min.css">
<script type="text/javascript" src="js/jquery-3.3.1.js" ></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js" ></script>
<script type="text/javascript" src="layer/layer.js"></script>
<script type="text/javascript">
var i = 1;
function show1(){
	var dr = document.getElementById("ta").children[0].children[i].children[5].value;
	if(dr == 0){
		document.getElementById("ta").children[0].children[i].children[6].innerText='未回复'
	}else if(dr == 1){
		document.getElementById("ta").children[0].children[i].children[6].innerText='已回复'
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


<!-- 商品信息修改模态框 -->
	<div class="modal animated pulse" id="lookContent">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" data-dismiss="modal">&times;</button>
					<h4>商品信息修改</h4>
				</div>
				<form action="goodssecond" class="update-form" id="update-form" method="get">
					<div class="modal-body">
<!-- 					隐藏域带值 -->
					<input type="hidden" id="mid" name="mid" value="${merchant.mid}">
						<!-- 使用隐藏域来区分业务逻辑 -->
						<input type="hidden" name="method" value="updateGoods"/>
						<div class="form-group">
							<div class="input-group">
							  <span class="input-group-addon">评论内容:</span>
							  <textarea rows="5" name="content" readonly required class="form-control" style="resize: none;"></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-default" data-dismiss="modal">关闭</button>
<!-- 						<button class="btn btn-primary" type="submit" form="update-form">确认修改</button> -->
					</div>
				</form>
			</div>
		</div>
	</div>	


<!-- 	内容区 -->
	<div class="container-fluid">
<!-- 		路径导航 -->
		<ol class="breadcrumb">
		  <li class="active">评论列表</li>
		</ol>
		
<!-- 		数据库区 -->
		<div class="data-container">
			<table class="table table-striped table-bordered" id="ta">
				<tr>
					<th>商品id</th>
					<th>用户昵称</th>
					<th>评论内容</th>
					<th>评论时间</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${commentList}" var="comment" varStatus="c">
					<tr>
<!-- 						隐藏于存放评论id -->
						<input type="hidden" value="${comment.cid }" >
						<td>${comment.gid}</td>
						<td>${comment.nickname }</td>
						<td>${comment.content }</td>
						<td>${comment.subtime }</td>
						<input type="hidden" value="${comment.commentdr }" id="commentdr">
						<td id="dr"></td>
						<script>
						show1();
						</script>
						<td>
							<button class="btn btn-info btn-sm btnLook" data-toggle="modal"  data-target="#lookContent"><span class="glyphicon glyphicon-search"></span>查看内容</button>
							<button data-id="${comment.cid}" class="btn btn-danger btn-sm btnDel" onclick="removeComment(this)"><span class="glyphicon glyphicon-remove"></span>删除</button>
						</td>
					</tr>
				
				</c:forEach>
					
			</table>
<!-- 			用于翻页的按钮 -->
			<div >
			<button class="btn btn-primary col-md-1 col-md-offset-4" onClick="previousPage()">&lt</button> <button class="btn btn-primary col-md-1 col-md-offset-2" onClick="nextPage()">&gt</button>
			</div>
		<script type="text/javascript">
		
// 		//下一页点击事件
		function nextPage(){
			var page = '${commentPage}';
			page = eval(page+'+1');
			console.info(page);
			location.href='comment?method=findComment&page='+page;
		}
// 		上一页点击事件
		function previousPage(){
			if('${commentPage}' > 1){
				var page = '${commentPage}';
				page = eval(page-'1');
				location.href='comment?method=findComment&page='+page;
			}else{
				layer.msg('已到达第一页');
			}
		}
		
		
			$(function(){
				
				if('${selectcomment}' == 1 && '${commentPage}' != 1){
					layer.msg('最后一页');
				}
				if('${selectcomment}' == 1 && '${commentPage}' == 1){
					layer.msg('你还没有评论');
				}
				
				//查看内容
				$('.btnLook').click(function(){
					var content = $(this).parent().parent().children(':nth-child(4)').text();
					$('#update-form textarea[name=content]').val(content);
				})
				
				if('${deleteComment}'=='1'){
					layer.alert('删除成功',function(){
						location.href="comment?method=findComment&page=${commentPage}";
					});
				}else if('${deleteComment}'=='2'){
					layer.alert('删除失败');
				}
				
				
			})
			//删除
			function removeComment(btn){
				//获取data-*中的值
				var id = $(btn).data('id');
				layer.confirm('是否确认删除?',{btn:['确认','取消'],icon:2},function(){
					location.href='comment?method=deleteComment&cid='+id;
				});
			}
		</script>

</body>
</html>