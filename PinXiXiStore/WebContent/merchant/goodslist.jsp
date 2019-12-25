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
	var dr = document.getElementById("ta").children[0].children[i].children[9].value;
	if(dr == 0){
		document.getElementById("ta").children[0].children[i].children[10].innerText='上架'
	}else if(dr == 1){
		document.getElementById("ta").children[0].children[i].children[10].innerText='下架'
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
	<div class="modal animated pulse" id="sortEdit">
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
						
						<input type="hidden" name="did">
						<input type="hidden" name="goodsnumber">
						<div id="" class="form-group">
							<div class="input-group">
							  <span class="input-group-addon">商品名:</span>
							  <textarea rows="5" name="goodsname" placeholder="请输入商品名..." required class="form-control" style="resize: none;"></textarea>
							</div>
						</div>
						
						<div id="" class="form-group">
							<div class="input-group">
							  <span class="input-group-addon">商品价格:</span>
							  <textarea rows="5" name="goodsprice" placeholder="请输入商品价格..." required class="form-control" style="resize: none;"></textarea>
							</div>
						</div>
						
						<div id="" class="form-group">
							<div class="input-group">
							  <span class="input-group-addon">商品库存:</span>
							  <textarea rows="5" name="goodstotal" placeholder="请输入商品库存..." required class="form-control" style="resize: none;"></textarea>
							</div>
						</div>
						
						<div id="" class="form-group">
							<div class="input-group">
							  <span class="input-group-addon">计量单位:</span>
							  <textarea rows="5" name="reserved1" placeholder="请输入计量单位..." required class="form-control" style="resize: none;"></textarea>
							</div>
						</div>
						
						<div class="form-group">
							<div class="input-group">
							  <span class="input-group-addon">商品简介:</span>
							  <textarea rows="5" name="goodsinfo" placeholder="请输入商品简介..." required class="form-control" style="resize: none;"></textarea>
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
		  <li class="active">商品列表</li>
		</ol>
		
<!-- 		数据库区 -->
		<div class="data-container">
			<table class="table table-striped table-bordered" id="ta">
				<tr>
					<th>商品编号</th>
					<th>商品名</th>
					<th>商品价格</th>
					<th>促销价</th>
					<th>商品库存</th>
					<th>销量</th>
					<th>计量单位</th>
					<th>商品简介</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${goodsList}" var="goods" varStatus="g">
					<tr>
						
						<input type="hidden" value="${goods.gid }" >
						<td>${goods.goodsnumber }</td>
						<td>${goods.goodsname }</td>
						<td>${goods.goodsprice }</td>
						<td>${goods.discountprice }</td>
						<td>${goods.goodstotal }</td>
						<td>${goods.goodssell }</td>
						<td>${goods.reserved1 }</td>
						<td>${goods.goodsinfo }</td>
						<input type="hidden" value="${goods.goodsdr }" id="goodsdr">
						<td id="dr"></td>
						<script>
						show1();
						</script>
						<td>
							<button class="btn btn-info btn-sm btnUpdate" data-toggle="modal"  data-target="#sortEdit"><span class="glyphicon glyphicon-edit"></span>修改</button>
							<button data-id="${goods.gid }" class="btn btn-success btn-sm btnDel" onclick="shangjia(this)"><span class="glyphicon glyphicon-plus"></span>上架</button>
							<button data-id="${goods.gid }" class="btn btn-danger btn-sm btnDel" onclick="xiajia(this)"><span class="glyphicon glyphicon-remove"></span>下架</button>
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
			var page = '${pageNowGoods}';
			page = eval(page+'+1')
			location.href='goodssecond?method=findByPage&mid=${merchant.mid}&page='+page;
		}
// 		上一页点击事件
		function previousPage(){
			if('${pageNowGoods}' > 1){
				var page = '${pageNowGoods}';
				page = eval(page-'1');
				location.href='goodssecond?method=findByPage&mid=${merchant.mid}&page='+page;
			}else{
				layer.msg('已到达第一页');
			}
		}
		
		
			$(function(){
				
				if('${selectgoods}' == 1 && '${pageNowGoods}' != 1){
					layer.msg('最后一页');
				}
				if('${selectgoods}' == 1 && '${pageNowGoods}' == 1){
					layer.msg('你还没有商品');
				}
				
				
				
// 				//如果没有类别则弹窗提示
// 				if('${dailyList.code}'=='1'){
// 					layer.msg('你还没有写过日志');
// 				}
				
// 				if('${deletesort}'=='1'){
// 					layer.alert('删除成功');
// 				}else if('${deletesort}'=='2'){
// 					layer.alert('删除失败');
// 				}
				if('${updateGoods}'=='1'){
					layer.alert('修改成功',function(){
						location.href="goodssecond?method=findByPage&page=${pageNowGoods}&mid=${merchant.mid}"
					});
				}else if('${updateGoods}'=='2'){
					layer.alert('修改失败');
				}else if('${updateGoods}'=='3'){
					layer.alert('下架成功',function(){
						location.href="goodssecond?method=findByPage&page=${pageNowGoods}&mid=${merchant.mid}"
					});
				}else if('${updateGoods}'=='4'){
					layer.alert('下架失败',function(){
						location.href="goodssecond?method=findByPage&page=${pageNowGoods}&mid=${merchant.mid}"
					});
				}else if('${updateGoods}'=='5'){
					layer.alert('上架成功',function(){
						location.href="goodssecond?method=findByPage&page=${pageNowGoods}&mid=${merchant.mid}"
					});
				}else if('${updateGoods}'=='6'){
					layer.alert('上架失败',function(){
						location.href="goodssecond?method=findByPage&page=${pageNowGoods}&mid=${merchant.mid}"
					});
				}
				
				//修改分类
				$('.btnUpdate').click(function(){
					var goodsname = $(this).parent().parent().children(':nth-child(3)').text();
					var goodsprice = $(this).parent().parent().children(':nth-child(4)').text();
					var goodstotal = $(this).parent().parent().children(':nth-child(6)').text();
					var reserved1 = $(this).parent().parent().children(':nth-child(8)').text();
					var goodsinfo = $(this).parent().parent().children(':nth-child(9)').text();
					var goodsnumber = $(this).parent().parent().children(':nth-child(2)').text();
					$('#update-form textarea[name=goodsname]').text(goodsname);
					$('#update-form textarea[name=goodsprice]').text(goodsprice);
					$('#update-form textarea[name=goodstotal]').val(goodstotal);
					$('#update-form textarea[name=reserved1]').val(reserved1);
					$('#update-form textarea[name=goodsinfo]').text(goodsinfo);
					$('#update-form input[name=goodsnumber]').val(goodsnumber);
				})
				
				
			})
// 			//删除
// 			function removesort(btn){
// 				//获取data-*中的值
// 				var id = $(btn).data('id');
// 				layer.confirm('是否确认删除?',{btn:['确认','取消'],icon:2},function(){
// 					location.href='sort?method=deletesort&sid='+id;
// 				});
// 			}
			
			function xiajia(btn){
				//获取data-*中的值
				var id = $(btn).data('id');
				layer.confirm('是否确认下架?',{btn:['确认','取消'],icon:2},function(){
					location.href='goodssecond?method=xiajia&gid='+id;
				});
			}
			function shangjia(btn){
				//获取data-*中的值
				var id = $(btn).data('id');
				layer.confirm('是否确认上架?',{btn:['确认','取消'],icon:2},function(){
					location.href='goodssecond?method=shangjia&gid='+id;
				});
			}
		
		</script>

</body>
</html>