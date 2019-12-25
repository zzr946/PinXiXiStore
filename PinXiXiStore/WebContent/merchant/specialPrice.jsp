<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="taglib.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>设置促销</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://unpkg.com/animate.css@3.5.2/animate.min.css">
<script type="text/javascript" src="js/jquery-3.3.1.js" ></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js" ></script>
<script type="text/javascript" src="layer/layer.js"></script>
<script type="text/javascript">
var i = 1;
function show1(){
	var dr = document.getElementById("ta").children[0].children[i].children[4].value;
	if(dr == 0){
		document.getElementById("ta").children[0].children[i].children[5].innerText='上架'
	}else if(dr == 1){
		document.getElementById("ta").children[0].children[i].children[5].innerText='下架'
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
	<div class="modal animated pulse" id="specialAdd">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" data-dismiss="modal">&times;</button>
					<h4>加入促销</h4>
				</div>
				<form action="sales" class="update-form" id="update-form" method="get">
					<div class="modal-body">
<!-- 					隐藏域带值 -->
					<input type="hidden" id="mid" name="mid" value="${merchant.mid}">
						<!-- 使用隐藏域来区分业务逻辑 -->
						<input type="hidden" name="method" value="addSpecial"/>
						
						<input type="hidden" name="gid">
						<input type="hidden" name="goodsname">
						<input type="hidden" name="goodsprice">
						<input type="hidden" name="goodsdr">
						
						<div id="" class="form-group">
							<div class="input-group">
							  <span class="input-group-addon">促销价格:</span>
							  <textarea rows="5" name="discountprice" placeholder="请输入商品促销价格..." required class="form-control" style="resize: none;"></textarea>
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
		  <li class="active">促销列表</li>
		</ol>
		
<!-- 		数据库区 -->
		<div class="data-container">
			<table class="table table-striped table-bordered" id="ta">
				<tr>
					<th>商品名</th>
					<th>商品价格</th>
					<th>促销价</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${specialGoodsList}" var="sgoods" varStatus="g">
					<tr>
						
						<input type="hidden" value="${sgoods.gid }" >
						<td>${sgoods.goodsname }</td>
						<td>${sgoods.goodsprice }</td>
						<td>${sgoods.discountprice }</td>
						<input type="hidden" value="${sgoods.goodsdr }" id="goodsdr">
						<td id="dr"></td>
						<script>
						show1();
						</script>
						<td>
							<button class="btn btn-success btn-sm btnUpdate" data-toggle="modal"  data-target="#specialAdd"><span class="glyphicon glyphicon-plus"></span>加入促销</button>
							<button data-id="${sgoods.gid }" class="btn btn-danger btn-sm btnDel" onclick="quxiaocuxiao(this)"><span class="glyphicon glyphicon-remove"></span>取消促销</button>
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
			var page = '${specialPage}';
			page = eval(page+'+1')
			location.href='sales?method=findBySpecialPage&mid=${merchant.mid}&specialPage='+page;
		}
// 		上一页点击事件
		function previousPage(){
			if('${specialPage}' > 1){
				var page = '${specialPage}';
				page = eval(page-'1');
				location.href='sales?method=findBySpecialPage&mid=${merchant.mid}&specialPage='+page;
			}else{
				layer.msg('已到达第一页');
			}
		}
		
		
			$(function(){
				
				if('${selectSpecialGoods}' == 1 && '${specialPage}' != 1){
					layer.msg('最后一页');
				}
				if('${selectSpecialGoods}' == 1 && '${specialPage}' == 1){
					layer.msg('你还没有分类');
				}
				
				if('${addSales}' == 1){
					layer.alert('加入成功',function(){
						location.href="sales?method=findBySpecialPage&specialPage=${specialPage}&mid=${merchant.mid}"
					})
				}
				if('${addSales}' == 2){
					layer.alert('加入失败,请检查是否已在促销表中',function(){
						location.href="sales?method=findBySpecialPage&specialPage=${specialPage}&mid=${merchant.mid}"
					})
				}
				if('${addSales}' == 3){
					layer.alert('取消成功',function(){
						location.href="sales?method=findBySpecialPage&specialPage=${specialPage}&mid=${merchant.mid}"
					})
				}
				if('${addSales}' == 4){
					layer.alert('取消失败',function(){
						location.href="sales?method=findBySpecialPage&specialPage=${specialPage}&mid=${merchant.mid}"
					})
				}
				
				
				//修改分类
				$('.btnUpdate').click(function(){
					var gid = $(this).parent().parent().children(':nth-child(1)').val();
					var goodsname = $(this).parent().parent().children(':nth-child(2)').text();
					var goodsprice = $(this).parent().parent().children(':nth-child(3)').text();
					var goodsdr = $(this).parent().parent().children(':nth-child(5)').val();
					$('#update-form input[name=goodsprice]').val(goodsprice);
					$('#update-form input[name=gid]').val(gid);
					$('#update-form input[name=goodsname]').val(goodsname);
					$('#update-form input[name=goodsdr]').val(goodsdr);
				})
				
				
			})
			
			function quxiaocuxiao(btn){
				//获取data-*中的值
				var id = $(btn).data('id');
				var goodsprice = $(btn).parent().prev().prev().prev().prev().prev().text();
				console.info(goodsprice);
				layer.confirm('是否确认取消促销?',{btn:['确认','取消'],icon:2},function(){
					location.href='sales?method=quxiaocuxiao&gid='+id+'&goodsprice='+goodsprice;
				});
			}
		
		</script>

</body>
</html>