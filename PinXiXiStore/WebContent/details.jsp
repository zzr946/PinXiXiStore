<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>拼XiXi商城-商品详情</title>
  
  <!--导入bootstrap的css-->
<link rel="stylesheet" type="text/css" href="res/bootstrap/css/bootstrap.css"/>
<!--导入jquery-->
<script type="text/javascript" src="res/JQuery/jquery-3.3.1.js" ></script>
<!--导入bootstrap的js-->
<script type="text/javascript" src="res/bootstrap/js/bootstrap.js" ></script>
  
  
  
  		<link href="res/static/one/AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css" />
		<link href="res/static/one/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css" />
		<link href="res/static/one/basic/css/demo.css" rel="stylesheet" type="text/css" />
		<link type="text/css" href="res/static/one/css/optstyle.css" rel="stylesheet" />
		<link type="text/css" href="res/static/one/css/style.css" rel="stylesheet" />
<!-- 		<script type="text/javascript" src="res/static/one/basic/js/quick_links.js"></script> -->
		<script type="text/javascript" src="res/static/one/basic/js/jquery-1.7.min.js"></script>
		<script type="text/javascript" src="res/static/one/AmazeUI-2.4.2/assets/js/amazeui.js"></script>
		<script type="text/javascript" src="res/static/one/js/jquery.imagezoom.min.js"></script>
		<script type="text/javascript" src="res/static/one/js/jquery.flexslider.js"></script>
		<script type="text/javascript" src="res/static/one/js/list.js"></script>
  
  
  
  
  <link rel="stylesheet" type="text/css" href="res/static/css/main.css">
  <link rel="stylesheet" type="text/css" href="res/layui/css/layui.css">
<!--   <script type="text/javascript" src="res/JQuery/jquery-3.3.1.js" ></script> -->
  <script type="text/javascript" src="res/layer/layer.js"></script>
  <script type="text/javascript" src="res/layui/layui.js"></script>
  
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
  <style type="text/css">
  	.hidden{
  		display:none;
  	}
  </style>
  <script type="text/javascript">
					$(function() {});
					$(window).load(function() {
						$('.flexslider').flexslider({
							animation: "slide",
							start: function(slider) {
								$('body').removeClass('loading');
							}
						});
					});
	</script>
</head>
<body>

  <div class="site-nav-bg">
    <div class="site-nav w1200">
      <p class="sn-back-home">
        <i class="layui-icon layui-icon-home"></i>
        <a href="index.jsp">首页</a>
      </p>
      <div class="sn-quick-menu">
      <input type="hidden" id="uid" name="uid" value="${userlogin.uid}">
      <input type="hidden" id="unickname" name="unickname" value="${userlogin.nickname}">
      <input type="hidden" id="upassword" name="upassword" value="${userlogin.password}">
      <input type="hidden" id="uphone" name="uphone" value="${userlogin.phone}">
      <input type="hidden" id="uphoto" name="uphoto" value="${userlogin.photo}">
      <input type="hidden" id="uname" name="uname" value="${userlogin.name}">
      <input type="hidden" id="usex" name="usex" value="${userlogin.sex}">
      <input type="hidden" id="uuserdr" name="uuserdr" value="${userlogin.userdr}">
      <input type="hidden" id="ureserved1" name="ureserved1" value="${userlogin.reserved1}">
      <input type="hidden" id="ureserved2" name="ureserved2" value="${userlogin.reserved2}">
      <input type="hidden" id="ureserved3" name="ureserved3" value="${userlogin.reserved3}">
      <input type="hidden" id="ureserved4" name="ureserved4" value="${userlogin.reserved4}">
      <input type="hidden" id="urved1ord5" name="ureserved5" value="${userlogin.reserved5}">
      <div class="reg" id="welcome"><span class="layui-icon layui-icon-face-smile"></span><a href="javascript:void(0)" id="weltext"></a></div>
      	<div class="reg" id="info"><a href="userspace.jsp"><span class="layui-icon layui-icon-user"></span>个人中心</a></div>
      	<div class="reg" id="reg"><a href="userreg.jsp">注册</a></div>
        <div class="login" id="login"><a href="login.jsp">登录</a></div>
        <div class="login" id="logout"><a href="user?method=logout"><span class="layui-icon layui-icon-close"></span>退出</a></div>
        <div class="sp-cart" id="cart"><a href="cart?method=cart_no1&uid=${userlogin.uid}"><span class="layui-icon layui-icon-cart"></span>购物车</a></div>
      </div>
    </div>
  </div>
    <script type="text/javascript">
  //显示用户昵称的方法
	//先判断该用户是否设置昵称，如果设置了昵称就直接显示昵称，否则显示手机号码
	//从作用域中获取用户昵称和手机号
	var phone=$('#uphone').val();
	var nickname=$('#unickname').val();
	if(!nickname){
		//昵称为空
		$('#weltext').text('欢迎您,'+phone);
// 		console.info('手机号');
	}else{
		//昵称为空
		$('#weltext').text('欢迎您,'+nickname);
// 		console.info('昵称');
	}

//   	$(function(){
  	//判断当前用户是否登录，若登录则显示个人小中心、购物车 ;若没有登录则显示登录注册
  	  	if('${userlogin}'){
  	  		//已经登录
  	  		$('#login').addClass('hidden');//登录 
  	  		$('#reg').addClass('hidden');//注册
  	  	}else{
  	  		//还未登录
  	  		$('#welcome').addClass('hidden');//欢迎
  	  		$('#info').addClass('hidden');//个人中心
  	  		$('#cart').addClass('hidden');//购物车
  	  		$('#logout').addClass('hidden');//退出
  	  	}
//   	})
  	
  </script>



  <div class="header" style="margin-left: 190px">
    <div class="headerLayout w1200">
      <div class="headerCon">
        <h1 class="mallLogo">
          <a href="index.jsp" title="拼XiXi商城">
            <div style="width: 180px;height: 60px">
            <img src="res/static/img/logo2.PNG" width="180px" height="60px">
            </div>
          </a>
        </h1>
        <div class="mallSearch">
          <form action="goods" class="layui-form" novalidate style="height: 44px">
          <!-- 隐藏域用于区分是什么操作 -->
          <input type="hidden" name="method" value="goodsbytitle">
            <input type="text" name="title" required  lay-verify="required" autocomplete="off" class="layui-input" placeholder="请输入需要的商品">
            <button class="layui-btn" lay-submit lay-filter="formDemo">
                <i class="layui-icon layui-icon-search"></i>
            </button>
            <input type="hidden" name="" value="">
          </form>
        </div>
      </div>
    </div>
  </div>


  <div class="content content-nav-base datails-content">
    <div class="main-nav">
      <div class="inner-cont0">
        <div class="inner-cont1 w1200">
          <div class="inner-cont2">
            <a href="goods?method=productall">所有商品</a>
            <a href="sales?method=salesall">今日特惠</a>
            <a href="information.jsp">热点资讯</a>
            <a href="about.jsp">关于我们</a>
          </div>
        </div>
      </div>
    </div>
    <div class="data-cont-wrap w1200">
      <div class="product-intro layui-clear" style="margin-top: 30px">
      <!-- 商品图片 -->
        <div class="preview-wrap">
          <a href="javascript:;"><img src="/userphoto/${goodsdetails.goodsimage }" style="width: 402px;height: 402px"></a>
        </div>
        
        
        <!-- 举报的模态窗 -->
        <div class="modal animated fadeInUp" id="dailyAdd">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" data-dismiss="modal">&times;</button>
					<h1>举报</h1>
				</div>
				<form action="report" class="form-sub" id="form-sub">
					<div class="modal-body">
						<!-- 使用隐藏域来区分业务逻辑 -->
						<input type="hidden" name="method" value="reportGoods"/>
						<input type="hidden" name="uid" value="${userlogin.uid}"><!-- 举报人id -->
						<input type="hidden" name="gid" value="${goodsdetails.gid}"><!-- 商品id -->
						<input type="hidden" name="goodsname" value="${goodsdetails.goodsname}"><!-- 商品名称 -->
						<div id="" class="form-group">
							<div class="input-group">
							  <span class="input-group-addon">举报原因:</span>
							  <textarea rows="3" name="reportcause" placeholder="请输入内容..." required class="form-control" style="resize: none;"></textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
							  <span class="input-group-addon">详情描述:</span>
							  <textarea rows="5" name="reportcontent" placeholder="请输入内容..." required class="form-control" style="resize: none;"></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-default" data-dismiss="modal">取消</button>
						<button class="btn btn-primary" type="submit" form="form-sub">提交</button>
					</div>
				</form>
			</div>
		</div>
	</div>
        
        
        <script type="text/javascript">
        	function getUrlSearch(name) {//地址栏取参数的方法
			  // 未传参，返回空
			  if (!name) return null;
			  // 查询参数：先通过search取值，如果取不到就通过hash来取
			  var after = window.location.search;
			  after = after.substr(1) || window.location.hash.split('?')[1];
			  // 地址栏URL没有查询参数，返回空
			  if (!after) return null;
			  // 如果查询参数中没有"name"，返回空
			  if (after.indexOf(name) === -1) return null;
			  var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)');
			  // 当地址栏参数存在中文时，需要解码，不然会乱码
			  var r = decodeURI(after).match(reg);
			  // 如果url中"name"没有值，返回空
			  if (!r) return null;
			  return r[2];
			}
        	
        	var plaselogin=getUrlSearch("plaselogin");
        	if(plaselogin=='0'){
        		layer.alert("请先登录",function(){
        			location.href="login.jsp";
        		});
        	}
        	
        	var represult=getUrlSearch("represult");
        	if(represult=='0'){
        		layer.msg('举报成功');
        	}
        	if(represult=='1'){
        		layer.msg('系统繁忙');
        	}
        	
        	
        </script>
        
        <div class="itemInfo-wrap">
          <div class="itemInfo">
            <div class="title" style="margin-bottom:10px;">
              <h4>${goodsdetails.goodsname} </h4><!-- 商品名 -->
              <a onclick="callect()" href="javascript:void(0)"><span style="display: block;"><i class="layui-icon layui-icon-rate"></i>收藏</span></a><!--layui-icon-star-fill(图标选中)-->
            </div>
            <div style="margin-top:5px;width: 50px;float: right;">
            	<a onclick="" href="javascript:void(0)" data-toggle="modal" data-target="#dailyAdd"><span style="display: block;"><i class="layui-icon layui-icon-chat"></i>举报</span></a>
            </div>
            
            
            <script type="text/javascript">
            	function callect(){/**************收藏商品的方法******************/
            		//获取用户id
            		var uid=$('#uid').val();
            		//alert(uid);
            		if(uid==""){
            			//alert('没登录');
            			//如果当前用户没有登录，则提示用户登录
            			layer.alert('请先前往登录',function(){
            				location.href="login.jsp";
            			})
            			return;
            		}
            		//如果是已经登录的用户
            		//获取用户的id
            		uid=$('#uid').val();
            		//获取商品id
            		var gid=$('#hiddengid').val();
            		
            		//向后台发送请求
            		location.href="collection?method=collect&uid="+uid+"&gid="+gid;
            	}
            	
            	//处理后台的数据
            	if('${collresult.code}'=='0'){
            		layer.msg("添加成功,可在个人中心查看");
            	}
            	if('${collresult.code}'=='1'){
            		layer.msg('系统繁忙,请稍后再试');
            	}
            	if('${collresult.code}'=='2'){
            		layer.msg('该商品已经收藏过了')
            	}
            	
            	
            	function addshopcart(){/***********添加到购物车的方法**************/
            		//获取用户id
            		var uid=$('#uid').val();
            		//alert(uid);
            		if(uid==""){
            			//alert('没登录');
            			//如果当前用户没有登录，则提示用户登录
            			layer.alert('请先前往登录',function(){
            				location.href="login.jsp";
            			})
            			return;
            		}
            		//如果是已经登录的用户
            		//获取用户的id
            		uid=$('#uid').val();
            		//获取商品id
            		var gid=$('#hiddengid').val();
            		//获取商品单价
            		var price=$('#goodsprice').text();
            		price=price.substring(1,price.length);
            		//console.info(price);
            		//获取数量
            		var count=$('#goodscount').val();
            		//console.info(count);
            		//获取库存
            		var repertory=$('#goodstotal').text();
            		//console.info(repertory);
            		//如果购买数量大于库存(库存不足)
            		if(parseInt(count)>parseInt(repertory)){
            			layer.alert('库存不足！大批量购买请联系商家!');
            			return;
            		}
            		
            		//所有条件都满足则向后台发请求
            		location.href="cart?method=addtocart&uid="+uid+"&gid="+gid+"&price="+price+"&count="+count;
            	}
            	
            	//添加到购物车结果处理
            	if('${addCartResult.code}'=='0'){
            		layer.msg('添加成功');
            	}
            	if('${addCartResult.code}'=='1'){
            		layer.msg('系统繁忙,请稍后再试');
            	}
            </script>
            
            <div class="summary" style="height: 220px">
              <input type="hidden" id="hiddengid" value="${goodsdetails.gid}"><!-- 商品id -->
              <p class="reference"><span>参考价</span> <del>￥${goodsdetails.goodsprice}</del></p><!--商品价格-->
              <p class="activity"><span>活动价</span><strong id="goodsprice" class="price"><i>￥</i>${goodsdetails.discountprice}</strong></p><!-- 商品促销价 -->
              <p class="reference"><span>剩余库存</span> <strong id="goodstotal">${goodsdetails.goodstotal}</strong></p><!--商品库存-->
              <p class="reference"><span>累计销量</span> <strong>${goodsdetails.goodssell}</strong></p><!--商品销量-->
            </div>
            <div class="choose-attrs">
              <div class="color layui-clear"><span class="title">规&nbsp;&nbsp;&nbsp;&nbsp;格</span> <div class="color-cont"><span class="btn active">${goodsdetails.reserved1}</span></div></div>
              <div class="number layui-clear"><span class="title">数&nbsp;&nbsp;&nbsp;&nbsp;量</span><div class="number-cont">
              <span class="cut btn" style="width: 40px;">-</span>
              <input id="goodscount" style="height: 39px;width: 50px" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" maxlength="4" type="" name="" value="1">
              <span class="add btn" style="width: 40px">+</span></div></div>
            </div>
            <div class="choose-btns">
<!--               <button class="layui-btn layui-btn-primary purchase-btn">立刻购买</button> -->
              <button onclick="addshopcart()" class="layui-btn  layui-btn-danger car-btn"><i class="layui-icon layui-icon-cart-simple"></i>加入购物车</button>  
            </div>
          </div>
        </div>
      </div>
      
      
      
      <div class="introduce">
					<div class="browse">
					    <div class="mc"> 
						     <ul>					    
						     	<div class="mt">            
						            <h2>特价推荐</h2>        
					            </div>
					            <script>
					            	function choose1(){
					            	location.href="details?method=goodsdetails&gid=41f714b0-3c45-4d86-9d73-48741edfd159";
					            }
					            	function choose2(){
					            		location.href="details?method=goodsdetails&gid=ca724db4-aaf3-4143-87a1-2e07191cab36";
					            	}
					            </script>
						     	
							      <li class="first">
							      	<div class="p-img">                    
							      		<a onclick="choose1()" href="javascript:void(0)"> <img class="" src="/userphoto/dc304c5a-3f0c-424d-8718-d08b6762621d.jpg"> </a>               
							      	</div>
							      	<div class="p-name"><a href="choose()">
							      		进口食品
							      	</a>
							      	</div>
							      	<div class="p-price"><strong>￥90</strong></div>
							      </li>
							      
							      <li class="first">
							      	<div class="p-img">                    
							      		<a onclick="choose2()"  href="javascript:void(0)"> <img class="" src="/userphoto/2cbb00ec-4608-4069-82cd-97b79d35ceb1.jpg"> </a>               
							      	</div>
							      	<div class="p-name"><a href="choose2()">
							      		休闲零食
							      	</a>
							      	</div>
							      	<div class="p-price"><strong>￥90</strong></div>
							      </li>
					      
						     </ul>					
					    </div>
					</div>
					<div class="introduceMain">
						<div class="am-tabs" data-am-tabs>
							<ul class="am-avg-sm-3 am-tabs-nav am-nav am-nav-tabs">
								<li class="am-active">
									<a href="#"><span class="index-needs-dt-txt">宝贝详情</span></a>
								</li>
								<li>
									<a href="#"><span class="index-needs-dt-txt">全部评价</span></a>
								</li>
							</ul>

							<div class="am-tabs-bd">
								<div class="am-tab-panel am-fade am-in am-active">
									<div class="J_Brand">
										<div class="attr-list-hd tm-clear">
											<h4>商品简介：</h4></div>
										<div class="clear"></div>
											<div style="width: 600px;margin: 0 auto;">
												<b style="font-size: 18px">${goodsdetails.goodsinfo}</b>
											</div>
										<!-- <ul id="J_AttrUL">
											<li title="">产品类型:&nbsp;烘炒类</li>
											<li title="">原料产地:&nbsp;巴基斯坦</li>
											<li title="">产地:&nbsp;湖北省武汉市</li>
											<li title="">配料表:&nbsp;进口松子、食用盐</li>
											<li title="">产品规格:&nbsp;210g</li>
											<li title="">保质期:&nbsp;180天</li>
											<li title="">产品标准号:&nbsp;GB/T 22165</li>
											<li title="">生产许可证编号：&nbsp;QS4201 1801 0226</li>
											<li title="">储存方法：&nbsp;请放置于常温、阴凉、通风、干燥处保存 </li>
											<li title="">食用方法：&nbsp;开袋去壳即食</li>
										</ul> -->
										<div class="clear"></div>
									</div>

									<div class="details">
										<div class="attr-list-hd after-market-hd">
											<h4>商品细节</h4>
										</div>
										<div class="twlistNews">
											<img src="/userphoto/${goodsdetails.goodsimage }" />
											<img src="/userphoto/${goodsdetails.goodsimage }" />
											<img src="/userphoto/${goodsdetails.goodsimage }" />
											<img src="/userphoto/${goodsdetails.goodsimage }" />
											<img src="/userphoto/${goodsdetails.goodsimage }" />
											<img src="/userphoto/${goodsdetails.goodsimage }" />
											<img src="/userphoto/${goodsdetails.goodsimage }" />
										</div>
									</div>
									<div class="clear"></div>
								</div>


								<!-- 商品评论 -->
								<div class="am-tab-panel am-fade">
                                    <div class="clear"></div>
									<div class="clear"></div>

									<ul class="am-comments-list am-comments-list-flip">
									<c:forEach var="comm" items="${commlist}" varStatus="s">
										<li class="am-comment">
											<!-- 评论容器 -->

											<div class="am-comment-main">
												<!-- 评论内容容器 -->
												<header class="am-comment-hd">
													<!--<h3 class="am-comment-title">评论标题</h3>-->
													<div class="am-comment-meta">
														<!-- 评论元数据 -->
														<a href="#link-to-user" class="am-comment-author">${comm.nickname}</a>
														<!-- 评论者 -->
														评论于
														<time datetime="">${comm.subtime}</time>
													</div>
												</header>

												<div class="am-comment-bd">
													<div class="tb-rev-item " data-id="255776406962">
														<div class="J_TbcRate_ReviewContent tb-tbcr-content ">
															${comm.content}
														</div>
														<div class="tb-r-act-bar">
														</div>
													</div>

												</div>
												<!-- 评论内容 -->
											</div>
										</li>
										</c:forEach>
										
									</ul>

									<div class="clear"></div>

									<!--分页 -->
									<ul class="am-pagination am-pagination-right">
										<li class="am-disabled"><a href="javascript:void(0)">&laquo;</a></li>
										<li class="am-disabled"><a href="javascript:void(0)">&raquo;</a></li>
<!-- 									</ul> -->
									<div class="clear"></div>

									<div class="tb-reviewsft">
										<div class="tb-rate-alert type-attention">购买前请查看该商品的 <a href="#" target="_blank">购物保障</a>，明确您的售后保障权益。</div>
									</div>

								</div>

								

							</div>

						</div>

						<div class="clear"></div>

						
					</div>

				</div>
      
      
      
      
      
      
      
      
      
    </div>
  </div>
<script type="text/javascript">
  layui.config({
    base: 'res/static/js/util/' //你存放新模块的目录，注意，不是layui的模块目录
  }).use(['mm','jquery'],function(){
      var mm = layui.mm,$ = layui.$;
      var cur = $('.number-cont input').val();
      $('.number-cont .btn').on('click',function(){
        if($(this).hasClass('add')){
          cur++;
         
        }else{
          if(cur > 1){
            cur--;
          }  
        }
        $('.number-cont input').val(cur)
      })
      
  });
</script>

  <script>
  	//这里我用的是svg，所以代码老长了，可以把svg部分换成自己的图片什么的
$(function(){
    var obj = $('<div style="width:3rem;position:fixed;top:90%;right:1.6%;height:1.3rem;text-align:center;display: none;"class="Bottomsetting"><svg class="icon" style="height:3.5rem;vertical-align: middle;fill: #732676;overflow: hidden;" viewBox="0 0 1026 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="22374"><path d="M517.64662 0.191659a511.920142 511.920142 0 1 0 0 1023.776398 511.920142 511.920142 0 0 0 0-1023.776398z m0 986.275197a474.418941 474.418941 0 1 1 474.355055-474.418941c0 261.933431-212.48551 474.418941-474.355055 474.418941z m0 0M667.204221 382.997785L530.10443 244.939701a15.332689 15.332689 0 0 0-10.988427-4.472034c-0.51109-0.127772-0.958293-0.319431-1.469383-0.319431a15.716006 15.716006 0 0 0-6.260848 1.34161 13.927192 13.927192 0 0 0-5.302555 3.385969L382.335643 368.431731a15.204916 15.204916 0 1 0 21.593536 21.52965l98.448639-98.193093v268.833141a15.204916 15.204916 0 1 0 30.473718 0v-269.599776l112.631376 113.39801a15.268802 15.268802 0 0 0 21.593536 0.063886 14.949371 14.949371 0 0 0 0.127773-21.465764z m18.271454-173.323268H349.689793a15.332689 15.332689 0 0 1-15.332688-15.268802c0-8.432979 6.89971-15.204916 15.332688-15.204916h335.785882a15.204916 15.204916 0 1 1 0 30.473718z m0 0M269.512609 618.418442c7.985775 0.51109 15.907664 0.958293 23.765667 0.958293h28.748791c7.985775 0 16.163209-0.447203 24.787847-1.022179v16.738185c-10.60511-0.51109-19.165861-1.022179-25.74614-1.022179v149.685373c0 13.160558-4.088717 21.146333-12.330037 23.829553a366.706804 366.706804 0 0 1-29.259881 7.730231 70.913685 70.913685 0 0 0-7.985775-23.637895c9.966248 0 17.50482-0.319431 22.743488-1.022179 5.366441-0.638862 7.985775-5.238669 7.985776-13.863306V634.134448c-13.224444 0-24.085098 0.51109-32.709736 1.022179V618.418442z m66.377764 191.019746c18.590885-3.833172 33.220825-9.263499 44.145366-15.716006a47.020245 47.020245 0 0 0 21.274106-24.340643c3.258196-9.58293 5.366441-21.082447 6.38862-34.562436 1.022179-13.671647 1.213838-27.726612 0.51109-42.164893 7.985775 1.277724 15.524347 2.236017 22.807374 3.002651-1.980472 7.219141-3.322083 17.249275-3.960944 30.154288-0.702748 12.777241-2.363789 26.385002-4.983124 40.631625s-10.285679 26.512774-22.743488 37.18177a158.182238 158.182238 0 0 1-49.511807 26.640546 58.136444 58.136444 0 0 0-13.927193-20.826902z m67.399944-150.452007c1.277724-11.243972 2.299903-22.168512 2.938766-32.837508h-18.782544c-7.921889 0-18.782544 0.447203-32.64585 1.022179v-16.738185c13.160558 0.574976 24.021212 0.958293 32.64585 0.958293h72.255295c11.243972 0 22.424057-0.383317 33.731915-1.022179v16.802071a745.743644 745.743644 0 0 0-33.731915-0.958293h-32.645849a475.057803 475.057803 0 0 0-4.919238 32.773622h58.391989c-0.638862 9.774589-0.958293 28.684905-0.958293 56.475403 0 27.726612 0.319431 47.148018 0.958293 58.328103h-19.804723V673.80778H380.546829v102.026266H361.636513c0.766634-11.180085 1.022179-30.601491 1.022179-58.519762 0-28.301588-0.255545-47.722993-1.022179-58.264217h41.653804v-0.063886zM467.68761 790.719531c10.60511 7.985775 20.826902 15.907664 30.729263 23.701781a144.702249 144.702249 0 0 0-16.929844 17.824251 286.593505 286.593505 0 0 0-28.684905-27.215523 332.27214 332.27214 0 0 0-25.746139-18.271454 114.356303 114.356303 0 0 0 15.843778-14.885485c6.005303 4.663693 14.182737 10.924541 24.787847 18.84643z m0 0M614.689762 624.232087c-4.024831 1.980472-7.666344 4.024831-10.924541 6.005303 27.854384 0 45.231432-0.319431 52.514459-1.02218v18.910316c-4.663693 0-13.543875-0.319431-26.704433-1.022179l16.802072 7.921889a373.478741 373.478741 0 0 0-19.804723 38.587267c15.779892 0 27.982157-0.319431 36.67068-1.02218v18.84643a321.858689 321.858689 0 0 0-25.873912-1.022179h-74.235768c-11.882834 0-21.785195 0.319431-29.643198 1.022179v-18.782543c4.599807 0.702748 17.185389 1.022179 37.628974 1.022179a259.697414 259.697414 0 0 0-17.824251-34.690208l16.865958-9.007955c6.516393 9.263499 13.416103 20.826902 20.699129 34.754095-6.516393 3.385969-11.499516 6.260848-14.693826 8.944068h31.62367c8.496865-20.443585 13.927192-36.031818 15.843778-46.573042h-23.829553c-30.409832 0-50.086783 0.255545-59.414169 1.022179v-18.910316c7.921889 0.702748 25.74614 1.022179 53.408866 1.02218a111.289765 111.289765 0 0 0-15.716006-24.723961c5.941417-3.258196 11.243972-6.644165 15.716006-9.966247 10.668996 13.224444 17.632592 22.871261 20.890788 28.684905z m29.707084 155.562903c0 15.14103 0.255545 31.048695 1.02218 47.531335h-19.804723v-19.740837h-53.472752v19.740837h-19.868609c0.638862-13.927192 1.022179-28.365474 1.022179-43.57039 0-15.907664-0.383317-32.454191-1.022179-49.575694h93.082197a989.59728 989.59728 0 0 0-0.958293 45.614749z m-18.782543 11.94672v-41.653804h-53.472752v41.589918h53.472752v0.063886z m128.155722-58.903079c5.749758 10.221792 8.113548 20.507471 7.602458 31.048695a29.643198 29.643198 0 0 1-13.543875 24.276757c-8.24132 5.685872-20.507471 9.519044-37.053997 11.563402a84.138129 84.138129 0 0 0-8.944069-23.893439c15.907664 0 26.257229-1.788814 31.240354-5.430328s7.219141-8.496865 6.963596-14.310509c-0.51109-6.005303-3.897058-14.182737-10.541224-24.851733a163.548679 163.548679 0 0 0-22.743488-28.621019c5.302555-12.010606 12.202265-34.690208 20.890788-68.486009h-35.840159v195.236235h-19.676951c0.51109-13.991078 1.022179-29.643198 1.022179-47.531334V661.030539c0-14.62994-0.766634-29.132108-1.980472-43.634276h83.179836a2112.077861 2112.077861 0 0 1-26.832205 82.221543c11.94672 11.882834 20.763016 22.999033 26.257229 33.220825z m0 0" p-id="22375"></path></svg></div>');
    $("body").append(obj);  
    $(document).scroll(function() {
        var dh=$(window).height();//网页可视区高度
        var hidtop=$(document).scrollTop();//网页被卷去的高度
        if(dh>hidtop){
            $(".Bottomsetting").fadeOut()
        }else if(dh<hidtop){
            $(".Bottomsetting").fadeIn()
        }
      });
      $(".Bottomsetting").on("click",function(){
        $('html,body').animate({scrollTop:0},500);
   })
})
  </script>

</body>
</html>