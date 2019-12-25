<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%@include file="taglib.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>类别列表</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://unpkg.com/animate.css@3.5.2/animate.min.css">
<script type="text/javascript" src="js/jquery-3.3.1.js" ></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js" ></script>
<script type="text/javascript" src="layer/layer.js"></script>
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

<!-- 类别修改模态框 -->
	<div class="modal animated pulse" id="sortEdit">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" data-dismiss="modal">&times;</button>
					<h4>类别修改</h4>
				</div>
				<form action="sort" class="update-form" id="update-form" method="get">
					<div class="modal-body">
<!-- 					隐藏域带值 -->
					<input type="hidden" id="mid" name="mid" value="${merchant.mid}">
						<!-- 使用隐藏域来区分业务逻辑 -->
						<input type="hidden" name="method" value="updateSort"/>
						
						<input type="hidden" name="did">
						<input type="hidden" name="sortname1">
						<div id="" class="form-group">
							<div class="input-group">
							  <span class="input-group-addon">类别名:</span>
							  <textarea rows="5" name="sortname2" placeholder="请输入类别名..." required class="form-control" style="resize: none;"></textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
							  <span class="input-group-addon">类别简介:</span>
							  <textarea rows="5" name="sortinfo" placeholder="请输入类别简介..." required class="form-control" style="resize: none;"></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-default" data-dismiss="modal">取消</button>
						<button class="btn btn-primary" type="submit" form="update-form">确认修改</button>
					</div>
				</form>
			</div>
		</div>
	</div>	
<!-- 	内容区 -->
	<div class="container-fluid">
<!-- 		路径导航 -->
		<ol class="breadcrumb">
		  <li class="active">类别列表</li>
		</ol>
		
<!-- 		数据库区 -->
		<div class="data-container">
			<table class="table table-striped table-bordered">
				<tr>
<!-- 					<th><input type="checkbox" hidden/></th> -->
					<th>类别名</th>
					<th>类别简介</th>
<!-- 					<th>完成情况</th> -->
<!-- 					<th>明日计划</th> -->
<!-- 					<th>提交时间</th> -->
<!-- 					<th>更新时间</th> -->
					<th>操作</th>
				</tr>
				<c:forEach items="${sortList}" var="sort" varStatus="s">
					<tr>
						
						<input type="hidden" value="${sort.sid }" >
						<td>${sort.sortname }</td>
						<td>${sort.sortinfo }</td>
						<td>
							<button class="btn btn-info btn-sm btnUpdate" data-toggle="modal"  data-target="#sortEdit"><span class="glyphicon glyphicon-edit"></span>修改</button>
							<button data-id="${sort.sid }" class="btn btn-danger btn-sm btnDel" onclick="removesort(this)"><span class="glyphicon glyphicon-remove"></span>删除</button>
						</td>
					</tr>
				
				</c:forEach>
					
			</table>
<!-- 			用于翻页的按钮 -->
			<div >
			<button class="btn btn-primary col-md-1 col-md-offset-4" onClick="previousPage()">&lt</button> <button class="btn btn-primary col-md-1 col-md-offset-2" onClick="nextPage()">&gt</button>
			</div>
		<script type="text/javascript">
		
		//下一页点击事件
		function nextPage(){
			var page = '${pageNow}';
			page = eval(page+'+1')
			location.href='sort?method=findByPage&mid=${merchant.mid}&page='+page;
		}
// 		上一页点击事件
		function previousPage(){
			if('${pageNow}' > 1){
				var page = '${pageNow}';
				page = eval(page-'1');
				location.href='sort?method=findByPage&mid=${merchant.mid}&page='+page;
			}else{
				layer.msg('已到达第一页');
			}
		}
			$(function(){
				
				
				console.info('${merchant.mid}')
				console.info('${pageNow}');
				if('${selectsort}' == 1 && '${pageNow}' != 1){
					layer.msg('最后一页');
				}
				if('${selectsort}' == 1 && '${pageNow}' == 1){
					layer.msg('你还没有分类');
				}
				
				
				
				//如果没有类别则弹窗提示
				if('${dailyList.code}'=='1'){
					layer.msg('你还没有写过日志');
				}
				
				if('${deletesort}'=='1'){
					layer.alert('删除成功');
				}else if('${deletesort}'=='2'){
					layer.alert('删除失败');
				}
				if('${updatesort}'=='1'){
					layer.alert('修改成功',function(){
						location.href="sort?method=findByPage&page=${pageNow}&mid=${merchant.mid}"
					});
				}else if('${updatesort}'=='2'){
					layer.alert('修改失败');
				}
				
				//修改分类
				$('.btnUpdate').click(function(){
					var sortname = $(this).parent().parent().children(':nth-child(2)').text();
					console.info(sortname);
					var sortinfo = $(this).parent().parent().children(':nth-child(3)').text();
					console.info(sortinfo);
// 					location.href='sort?method=updatesort&sid='+id;
					$('#update-form textarea[name=sortname2]').text(sortname);
					$('#update-form textarea[name=sortinfo]').text(sortinfo);
					$('#update-form input[name=sortname1]').val(sortname);
// 					$('#update-form textarea[name=nextplan]').text(nextplan);
// 					$('#update-form input[name=did]').val(daily_id);
				})
				
				
			})
			//删除
			function removesort(btn){
				//获取data-*中的值
				var id = $(btn).data('id');
				layer.confirm('是否确认删除?',{btn:['确认','取消'],icon:2},function(){
					location.href='sort?method=deletesort&sid='+id;
				});
			}
		
		</script>

</body>
</html>