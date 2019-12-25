<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
	var dr = document.getElementById("ta").children[0].children[i].children[7].value;
	if(dr == 0){
		document.getElementById("ta").children[0].children[i].children[8].innerText='已支付'
	}else if(dr == 1){
		document.getElementById("ta").children[0].children[i].children[8].innerText='未支付'
	}else if(dr == 2){
		document.getElementById("ta").children[0].children[i].children[8].innerText='已评价'
	}
	i++;
}

var j = 1;
function show2(){
	var dr = document.getElementById("ta").children[0].children[j].children[10].value;
	console.info(dr);
	if(dr == 0){
		document.getElementById("ta").children[0].children[j].children[11].innerText='已发货'	
	}else if(dr == 1){
		document.getElementById("ta").children[0].children[j].children[11].innerText='未发货'
	}else if(dr == 2){
		document.getElementById("ta").children[0].children[j].children[11].innerText='已收货'	
	}
	j++;
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

<!-- 	内容区 -->
	<div class="container-fluid">
<!-- 		路径导航 -->
		<ol class="breadcrumb">
		  <li class="active">订单列表</li>
		</ol>
		
<!-- 		数据库区 -->
		<div class="data-container">
			<table class="table table-striped table-bordered" id="ta">
				<tr>
					<th>订单编号</th>
					<th>商品id</th>
					<th>购买数量</th>
					<th>购买人姓名</th>
					<th>收货人姓名</th>
					<th>订单总金额</th>
					<th>支付状态</th>
					<th>订单状态</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${orderList}" var="order" varStatus="o">
					<tr>
						
						<input type="hidden" value="${order.id }" >
						<td>${order.ordernumber}</td>
						<td>${order.gidlist}</td>
						<td>${order.goodstotallist}</td>
						<td>${order.name}</td>
						<td>${order.uadname}</td>
						<td>${order.account}</td>
						
						<input type="hidden" value="${order.paydr}" id="paydr">
						<td id="dr1"></td>
						<script>
						show1();//支付状态
						</script>
						
						<input type="hidden" value="${order.orderdr}" id="orderdr">
						<td id="dr2"></td>
						<script>
						show2();//订单状态
						</script>
						
						<td>
							<button data-id="${order.id }" class="btn btn-success btn-sm " id="btnSend" onclick="fahuo(this)"><span class="glyphicon glyphicon-plane"></span>发货</button>
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
			var page = '${orderPage}';
			page = eval(page+'+1')
			location.href='order?method=findOrderByPage&mid=${merchant.mid}&page='+page;
		}
// 		上一页点击事件
		function previousPage(){
			if('${orderPage}' > 1){
				var page = '${orderPage}';
				page = eval(page-'1');
				location.href='order?method=findOrderByPage&mid=${merchant.mid}&page='+page;
			}else{
				layer.msg('已到达第一页');
			}
		}
		
		
			$(function(){
				console.info(document.getElementById("ta").children[0].children[1].children[10].value)
				if('${selectorder}' == 1 && '${orderPage}' != 1){
					layer.msg('最后一页');
				}
				if('${selectorder}' == 1 && '${orderPage}' == 1){
					layer.msg('你还没有订单');
				}
					
			})
			
			if('${updateOrder}'=='1'){
					layer.alert('发货成功',function(){
						location.href="order?method=findOrderByPage&page=${orderPage}&mid=${merchant.mid}"
					});
				}else if('${updateOrder}'=='2'){
					layer.alert('发货失败',function(){
						location.href="order?method=findOrderByPage&page=${orderPage}&mid=${merchant.mid}"
					});}
			
					function fahuo(btn){
						//获取data-*中的值
						var id = $(btn).data('id');
						layer.confirm('是否确认发货?',{btn:['确认','取消'],icon:2},function(){
							location.href='order?method=fahuo&id='+id;
						});
					}
		</script>

</body>
</html>