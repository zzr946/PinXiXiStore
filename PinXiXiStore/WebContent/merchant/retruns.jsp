<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="taglib.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>退货列表</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://unpkg.com/animate.css@3.5.2/animate.min.css">
<script type="text/javascript" src="js/jquery-3.3.1.js" ></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js" ></script>
<script type="text/javascript" src="layer/layer.js"></script>
<script type="text/javascript">
var i = 1;
function show1(){
	var dr = document.getElementById("ta").children[0].children[i].children[7].value;
	if(dr == 0){
		document.getElementById("ta").children[0].children[i].children[8].innerText='已退货'
	}else if(dr == 1){
		document.getElementById("ta").children[0].children[i].children[8].innerText='退货中'
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
	<div class="modal animated pulse" id="returnLook">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" data-dismiss="modal">&times;</button>
					<h4>退货信息</h4>
				</div>
				<form action="goodssecond" class="update-form" id="update-form" method="get">
					<div class="modal-body">
<!-- 					隐藏域带值 -->
					<input type="hidden" id="mid" name="mid" value="${merchant.mid}">
						<!-- 使用隐藏域来区分业务逻辑 -->
						<input type="hidden" name="method" value="updateGoods"/>
						
						<input type="hidden" name="did">
						<input type="hidden" name="goodsnumber">
						
						
		
						<div class="form-group">
							<div class="input-group">
							  <span class="input-group-addon">退款说明:</span>
							  <textarea rows="5" name="returndetails" required class="form-control" style="resize: none;"></textarea>
							</div>
						</div>
						
						<div class="form-group">
							<div class="input-group">
							  <span class="input-group-addon">退款原因:</span>
							  <textarea rows="5" name="returncause" required class="form-control" style="resize: none;"></textarea>
							</div>
						</div>
						
						
					</div>
					<div class="modal-footer">
						<button class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</form>
			</div>
		</div>
	</div>	
<!-- 	内容区 -->
	<div class="container-fluid">
<!-- 		路径导航 -->
		<ol class="breadcrumb">
		  <li class="active">退货列表</li>
		</ol>
		
<!-- 		数据库区 -->
		<div class="data-container">
			<table class="table table-striped table-bordered" id="ta">
				<tr>
					<th>商品编号</th>
					<th>退款类型</th>
					<th>退款金额</th>
					<th>用户id</th>
					<th>退款说明</th>
					<th>退款原因</th>
					<th>退货状态</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${returnsList}" var="returns" varStatus="r">
					<tr>
						
						<input type="hidden" value="${returns.id}" >
						<td>${returns.ordernumber }</td>
						<td>${returns.returntype}</td>
						<td>${returns.reserved1}</td>
						<td>${returns.reserved2}</td>
						<td>${returns.returndetails}</td>
						<td>${returns.returncause}</td>
<!-- 						退货状态 -->
						<input type="hidden" value="${returns.returndr}" id="retrundr">
						<td id="dr"></td>
						<script>
						show1();
						</script>
						<td>
							<button class="btn btn-info btn-sm btnLook" data-toggle="modal"  data-target="#returnLook"><span class="glyphicon glyphicon-search"></span>查看</button>
							<button data-id="${returns.id}" class="btn btn-success btn-sm btnDel" onclick="tuihuo(this)"><span class="glyphicon glyphicon-ok"></span>确认退货</button>
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
			var page = '${retrunsPage}';
			page = eval(page+'+1')
			location.href='returns?method=findByRPage&mid=${merchant.mid}&page='+page;
		}
// 		上一页点击事件
		function previousPage(){
			if('${retrunsPage}' > 1){
				var page = '${retrunsPage}';
				page = eval(page-'1');
				location.href='returns?method=findByRPage&mid=${merchant.mid}&page='+page;
			}else{
				layer.msg('已到达第一页');
			}
		}
		
		
			$(function(){
				
				if('${selectreturns}' == 1 && '${retrunsPage}' != 1){
					layer.msg('最后一页');
				}
				if('${selectreturns}' == 1 && '${retrunsPage}' == 1){
					layer.msg('你还没有退货列表');
				}
				
				
				
				if('${updateReturn}'=='3'){
					layer.alert('退货成功',function(){
						location.href="returns?method=findByRPage&page=${retrunsPage}&mid=${merchant.mid}"
					});
				}else if('${updateReturn}'=='4'){
					layer.alert('退货失败',function(){
						location.href="returns?method=findByRPage&page=${retrunsPage}&mid=${merchant.mid}"
					});
				}
				
				//查看退货信息
				$('.btnLook').click(function(){
					var returndetails = $(this).parent().parent().children(':nth-child(6)').text();
					$('#update-form textarea[name=returndetails]').val(returndetails);
					var returncause = $(this).parent().parent().children(':nth-child(7)').text();
					$('#update-form textarea[name=returncause]').val(returncause);
				})
				
				
			})
			
			function tuihuo(btn){
				//获取data-*中的值
				var id = $(btn).data('id');
				layer.confirm('是否确认退货?',{btn:['确认','取消'],icon:2},function(){
					location.href='returns?method=tuihuo&id='+id;
				});
			}
	</script>

</body>
</html>