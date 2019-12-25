<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>拼XiXi商城-首页</title>
  <link rel="stylesheet" type="text/css" href="res/static/css/main.css">
  <link rel="stylesheet" type="text/css" href="res/layui/css/layui.css">
  <script type="text/javascript" src="res/JQuery/jquery-3.3.1.js" ></script>
  <script type="text/javascript" src="res/layer/layer.js"></script>
  <script type="text/javascript" src="res/layui/layui.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
  <style type="text/css">
  	.hidden{
  		display:none;
  	}
  </style>
  
</head>
<body id="list-cont">
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

  <div class="header">
    <div class="headerLayout w1200">
      <div class="headerCon">
        <h1 class="mallLogo">
          <a href="index.jsp" title="拼XiXi商城">
            <img src="res/static/img/logo2.PNG" width="180px" height="60px">
          </a>
        </h1>
        <div class="mallSearch">
          <form action="goods" class="layui-form" novalidate>
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


  <div class="content">
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
    <div class="category-con">
      <div class="category-inner-con w1200">
        <div class="category-type">
          <h3>全部分类</h3>
        </div>
        <div class="category-tab-content">
          <div class="nav-con">
            <ul class="normal-nav layui-clear">
              <li class="nav-item">
                <div class="title">服饰</div>
                <p><a href="goods?method=goodsbytitle&title=男士上装">精品男装</a><a href="goods?method=goodsbytitle&title=女士上装">潮流女装</a></p>
                <i class="layui-icon layui-icon-right"></i>
              </li>
              <li class="nav-item">
                <div class="title">家居</div>
                <p><a href="goods?method=goodsbytitle&title=床上用品">家纺家居</a><a href="goods?method=goodsbytitle&title=生活日用">百货用品</a></p>
                <i class="layui-icon layui-icon-right"></i>
              </li>
              <li class="nav-item">
                <div class="title">食品</div>
                <p><a href="goods?method=goodsbytitle&title=进口食品">进口食品</a><a href="休闲食品">休闲食品</a></p>
                <i class="layui-icon layui-icon-right"></i>
              </li>
              <li class="nav-item">
                <div class="title">母婴</div>
                <p><a href="goods?method=goodsbytitle&title=营养辅食">奶粉辅食</a><a href="goods?method=goodsbytitle&title=洗护用品">儿童用品</a></p>
                <i class="layui-icon layui-icon-right"></i>
              </li>
              
              <li class="nav-item">
                <div class="title">美妆</div>
                <p><a href="goods?method=goodsbytitle&title=面部护理">个护清洁</a><a href="goods?method=goodsbytitle&title=彩妆">护肤彩妆</a></p>
                <i class="layui-icon layui-icon-right"></i>
              </li>
              
              
              <li class="nav-item">
                <div class="title">箱包</div>
                <p><a href="goods?method=goodsbytitle&title=精品包包">奢侈箱包</a><a href="goods?method=goodsbytitle&title=功能箱包">功能箱包</a></p>
                <i class="layui-icon layui-icon-right"></i>
              </li>
              <li class="nav-item">
                <div class="title">户外</div>
                <p><a href="goods?method=goodsbytitle&title=户外鞋服">户外鞋服</a><a href="goods?method=goodsbytitle&title=户外装备">户外装备</a></p>
                <i class="layui-icon layui-icon-right"></i>
              </li>
              <li class="nav-item nobor">
                <div class="title">文娱</div>
                <p><a href="goods?method=goodsbytitle&title=图书">正版图书</a><a href="goods?method=goodsbytitle&title=文娱音像">文娱音像</a></p>
                <i class="layui-icon layui-icon-right"></i>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <div class="category-banner">
        <div class="w1200">
          <img src="res/static/img/banner1.jpg">
        </div>
      </div>
    </div>
    <div class="floors">
      <div class="sk">
        <div class="sk_inner w1200">
          <div class="sk_hd">
            <a href="javascript:;">
              <img src="res/static/img/s_img1.jpg">
            </a>
          </div>
          <div class="sk_bd">
            <div class="layui-carousel" id="test1">
              <div carousel-item>
                <div class="item-box">
                <!--轮播商品推荐 -->
                  <div class="item">
                    <a href="details?method=goodsdetails&gid=9b47cd67-efa7-484a-89a8-bb8aa115b489"><img src="/userphoto/fa27d76a-6cd3-446c-b4cc-1a1ef685935a.jpg"></a>
                    <div class="title">花花公子精品男士T恤</div>
                    <div class="price">
                      <span>￥69.00</span>
                      <del>￥129.00</del>
                    </div>
                  </div>
                  <div class="item">
                    <a href="details?method=goodsdetails&gid=9dcef34e-40c1-4185-a86d-d0870be2ac21"><img src="/userphoto/3d5f633a-98f3-44fb-9ccd-2da2a9636984.jpg"></a>
                    <div class="title">女士小碎花连衣裙</div>
                    <div class="price">
                      <span>￥99.00</span>
                      <del>￥199.00</del>
                    </div>
                  </div>
                  <div class="item">
                    <a href="details?method=goodsdetails&gid=e2be00c2-de7b-4002-b97c-0cd07b37270f"><img src="/userphoto/14d39278-b758-417e-89bb-88a5d061dc12.jpg"></a>
                    <div class="title">泰国金枕榴莲</div>
                    <div class="price">
                      <span>￥99.00</span>
                      <del>￥158.00</del>
                    </div>
                  </div>
                  <div class="item">
                    <a href="details?method=goodsdetails&gid=e4f091fe-5189-423f-a163-3ad50eca0966"><img src="/userphoto/0bc7d007-5774-4bbf-ba73-7f23b1066da7.jpg"></a>
                    <div class="title">精选少刺带鱼</div>
                    <div class="price">
                      <span>￥9.90</span>
                      <del>￥99.00</del>
                    </div>
                  </div>
                </div>
                <div class="item-box">
                  <div class="item">
                    <a href="details?method=goodsdetails&gid=cf7f5fd9-89e8-418a-b810-3b563f20d115"><img src="/userphoto/1c6d11c8-9abf-41ca-b005-e4dc98f594df.png"></a>
                    <div class="title">新鲜无刺三文鱼</div>
                    <div class="price">
                      <span>￥49.00</span>
                      <del>￥99.00</del>
                    </div>
                  </div>
                  <div class="item">
                    <a href="details?method=goodsdetails&gid=7e101e30-3baa-4987-af1f-27149b269715"><img src="/userphoto/8f830e93-5dee-4327-ac00-ec5c97d3c0a9.jpg"></a>
                    <div class="title">女士宽脚牛仔休闲裤</div>
                    <div class="price">
                      <span>￥109.00</span>
                      <del>￥169.00</del>
                    </div>
                  </div>
                  <div class="item">
                    <a href="details?method=goodsdetails&gid=87cb2b99-0396-4d65-bbdb-a64a010d0516"><img src="/userphoto/e0618769-4252-4593-92c2-bcfdf8f37fbd.jpg"></a>
                    <div class="title">女士休闲套装</div>
                    <div class="price">
                      <span>￥99.00</span>
                      <del>￥199.00</del>
                    </div>
                  </div>
                  <div class="item">
                    <a href="details?method=goodsdetails&gid=b1729fbf-38cf-4fb7-aaf1-3ac3b67db474"><img src="/userphoto/bb8cf011-ce75-4759-a293-50313aa62b2b.jpg"></a>
                    <div class="title">多功能大容量旅行箱</div>
                    <div class="price">
                      <span>￥199.00</span>
                      <del>￥399.00</del>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>    
      </div>
    </div>






    <div class="hot-recommend-con">
       <div class="hot-con1 w1200 layui-clear">
          <div class="item">
            <h4>热销推荐</h4>
            <div class="big-img">
              <a href="goods?method=goodsbytitle&title=婴童寝居"><img src="res/static/img/hot1.png"></a>
            </div>
            <div class="small-img">
              <a href="goods?method=goodsbytitle&title=婴童寝居"><img src="res/static/img/hot2.png" alt=""></a>
            </div>
          </div>
          <div class="item">
            <div class="top-img">
              <a href="goods?method=goodsbytitle&title=床上用品"><img src="res/static/img/hot5.jpg" width=""></a>
            </div>
            <div class="bottom-img">
              <a href="goods?method=goodsbytitle&title=休闲食品"><img src="res/static/img/hot6.png"></a>
              <a class="baby-cream" href="goods?method=goodsbytitle&title=洗护用品"><img src="res/static/img/hot7.jpg"></a>
            </div>
          </div>
          <div class="item item1 margin0 padding0">
            <a href="goods?method=goodsbytitle&title=面部护理"><img src="res/static/img/hot8.jpg"></a>
            <a href="goods?method=goodsbytitle&title=进口食品"><img class="btm-img" src="res/static/img/hot9.jpg"></a>
          </div>
      </div>
    </div>
    


    <div class="product-cont w1200" id="product-cont">
      <div class="product-item product-item1 layui-clear">
        <div class="left-title">
          <h4><i>1F</i></h4>
          <img src="res/static/img/icon_gou.png">
          <h5>家纺家居</h5>
        </div>
        <div class="right-cont">
          <a href="javascript:;" class="top-img"><img src="res/static/img/img13.jpg" alt=""></a>
          <div class="img-box">
            <a href="goods?method=goodsbytitle&title=床上用品"><img src="res/static/img/s_img7.jpg"></a>
            <a href="goods?method=goodsbytitle&title=床上用品"><img src="res/static/img/s_img8.jpg"></a>
            <a href="goods?method=goodsbytitle&title=床上用品"><img src="res/static/img/s_img9.jpg"></a>
            <a href="goods?method=goodsbytitle&title=床上用品"><img src="res/static/img/s_img10.jpg"></a>
            <a href="goods?method=goodsbytitle&title=床上用品"><img src="res/static/img/s_img11.jpg"></a>
          </div>
        </div>
      </div>
      <div class="product-item product-item2 layui-clear">
        <div class="left-title">
          <h4><i>2F</i></h4>
          <img src="res/static/img/icon_gou.png">
          <h5>特价食品</h5>
        </div>
        <div class="right-cont">
          <a href="javascript:;" class="top-img"><img src="res/static/img/img12.jpg" alt=""></a>
          <div class="img-box">
            <a href="goods?method=goodsbytitle&title=休闲食品"><img src="res/static/img/s_img7.1.jpg"></a>
            <a href="goods?method=goodsbytitle&title=休闲食品"><img src="res/static/img/s_img8.1.jpg"></a>
            <a href="goods?method=goodsbytitle&title=休闲食品"><img src="res/static/img/s_img9.1.png"></a>
            <a href="goods?method=goodsbytitle&title=休闲食品"><img src="res/static/img/s_img10.1.jpg"></a>
            <a href="goods?method=goodsbytitle&title=休闲食品"><img src="res/static/img/s_img11.1.jpg"></a>
          </div>
        </div>
      </div>
      <div class="product-item product-item3 layui-clear">
        <div class="left-title">
          <h4><i>3F</i></h4>
          <img src="res/static/img/icon_gou.png">
          <h5>母婴专区</h5>
        </div>
        <div class="right-cont">
          <a href="javascript:;" class="top-img"><img src="res/static/img/img14.jpg" alt=""></a>
          <div class="img-box">
            <a href="goods?method=goodsbytitle&title=营养辅食"><img src="res/static/img/s_img7.2.jpg"></a>
            <a href="goods?method=goodsbytitle&title=营养辅食"><img src="res/static/img/s_img8.2.jpg"></a>
            <a href="goods?method=goodsbytitle&title=洗护用品"><img src="res/static/img/s_img9.2.jpg"></a>
            <a href="goods?method=goodsbytitle&title=喂养用品"><img src="res/static/img/s_img10.2.jpg"></a>
            <a href="goods?method=goodsbytitle&title=洗护用品"><img src="res/static/img/s_img11.2.jpg"></a>
          </div>
        </div>
      </div>
    </div>

    <div class="product-list-box" id="product-list-box">
      <div class="product-list-cont w1200">
        <h4>更多推荐</h4>
        <div class="product-item-box layui-clear">
          <div class="list-item">
            <a href="details?method=goodsdetails&gid=943315da-bc04-42a8-8621-68faf583bbf5"><img src="/userphoto/5f01aed4-25a7-4eee-b8ec-dae631c16e1f.jpg" width="180px" height="180px"></a>
            <p>时尚男士Polo衫</p>
            <span>￥89.00</span>
          </div>
          <div class="list-item">
            <a href="details?method=goodsdetails&gid=fd90a3e7-1b5b-4f7b-9724-e604227f01d3"><img src="/userphoto/5c4ae4b5-50ec-48e4-92e9-a9a78b03144f.jpg" width="180px" height="180px"></a>
            <p>女士碎花连衣裙</p>
            <span>￥130.00</span>
          </div>
          <div class="list-item">
            <a href="details?method=goodsdetails&gid=0e779887-83b5-4358-a61c-7f11d5aa9970"><img src="/userphoto/708ecc52-5933-46ee-8439-bc3ccff0dabb.jpg" width="180px" height="180px"></a>
            <p>女士牛皮尖角小皮鞋</p>
            <span>￥299.00</span>
          </div>
          <div class="list-item">
            <a href="details?method=goodsdetails&gid=b1729fbf-38cf-4fb7-aaf1-3ac3b67db474"><img src="/userphoto/bb8cf011-ce75-4759-a293-50313aa62b2b.jpg" width="180px" height="180px"></a>
            <p>多功能行李箱</p>
            <span>￥199.00</span>
          </div>
          <div class="list-item">
            <a href="details?method=goodsdetails&gid=fdd9f014-0895-42c1-9e74-d5993bd34ed1"><img src="/userphoto/72b4ff7f-eee8-42b9-a5dc-7af8e68dcee4.jpg" width="180px" height="180px"></a>
            <p>女士上衣</p>
            <span>￥90.00</span>
          </div>
          <div class="list-item">
            <a href="details?method=goodsdetails&gid=72d5c541-759c-43b5-b573-816aadc21ed6"><img src="/userphoto/d42964a5-b34a-40ff-bd1f-83040ef01f05.jpg.jpg" width="180px" height="180px"></a>
            <p>女士时尚连衣裙</p>
            <span>￥160.00</span>
          </div>
          <div class="list-item">
            <a href="details?method=goodsdetails&gid=0b5941e7-e3a2-4678-9278-effc15f8198e"><img src="/userphoto/1247bb97-c28d-42bd-8071-028113d374ba.jpg" width="180px" height="180px"></a>
            <p>冷库直发新鲜牛排</p>
            <span>￥60.00</span>
          </div>
          <div class="list-item">
            <a href="details?method=goodsdetails&gid=783cf92a-32fc-4f56-8f7e-0365f2261570"><img src="/userphoto/1247bb97-c28d-42bd-8071-028113d374ba.jpg.jpg" width="180px" height="180px"></a>
            <p>法货进口银鳕鱼</p>
            <span>￥129.00</span>
          </div>
          <div class="list-item">
            <a href="details?method=goodsdetails&gid=f40b8730-9193-4262-8413-27db2435f656"><img src="/userphoto/4e3ff38c-69d8-4313-a125-6958e919501e.jpg" width="180px" height="180px"></a>
            <p>时尚运动跑鞋</p>
            <span>￥199.00</span>
          </div>
          <div class="list-item">
            <a href="details?method=goodsdetails&gid=4e9c7c89-b0e3-453f-90c8-b74054f7a249"><img src="/userphoto/d65c29a1-f06b-4f47-b62d-f86268d58f3d.jpg" width="180px" height="180px"></a>
            <p>果园直发新鲜脐橙</p>
            <span>￥60.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="res/static/img/more3.1.jpg" width="180px" height="180px"></a>
            <p>宝宝纯棉耐连体衣</p>
            <span>￥169.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="res/static/img/more3.2.jpg" width="180px" height="180px"></a>
            <p>宝宝时尚棉衣</p>
            <span>￥169.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="res/static/img/more3.3.jpg" width="180px" height="180px"></a>
            <p>宝宝保暖帽子</p>
            <span>￥99.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="res/static/img/more3.4.jpg" width="180px" height="180px"></a>
            <p>时尚炫酷口红</p>
            <span>￥100.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="res/static/img/more3.5.jpg" width="180px" height="180px"></a>
            <p>VANS运动休闲板鞋</p>
            <span>￥399.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="res/static/img/more4.1.jpg" width="180px" height="180px"></a>
            <p>女士便携式小包包</p>
            <span>￥199.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="res/static/img/more4.2.jpg" width="180px" height="180px"></a>
            <p>女士大容量包包</p>
            <span>￥199.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="res/static/img/more4.3.jpg" width="180px" height="180px"></a>
            <p>男士皮夹</p>
            <span>￥888.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="res/static/img/more4.4.jpg" width="180px" height="180px"></a>
            <p>Dior女士口红</p>
            <span>￥315.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="res/static/img/more4.5.jpg" width="180px" height="180px"></a>
            <p>自然堂神仙水</p>
            <span>￥229.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="res/static/img/more5.1.jpg" width="180px" height="180px"></a>
            <p>圣罗兰粉底液</p>
            <span>￥510.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="res/static/img/more5.2.jpg" width="180px" height="180px"></a>
            <p>进口曲奇饼干</p>
            <span>￥59.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="res/static/img/more5.3.jpg" width="180px" height="180px"></a>
            <p>日本原装进口白色恋人饼干</p>
            <span>￥109.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="res/static/img/more5.4.jpg" width="180px" height="180px"></a>
            <p>台湾牛轧糖</p>
            <span>￥59.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="res/static/img/more5.5.jpg" width="180px" height="180px"></a>
            <p>纳宝帝威化饼干</p>
            <span>￥19.00</span>
          </div>
          
          
        </div>
      </div>
    </div>
  </div>

  <div class="footer">
    <div class="ng-promise-box">
      <div class="ng-promise w1200">
        <p class="text">
          <a class="icon1" href="javascript:;">7天无理由退换货</a>
          <a class="icon2" href="javascript:;">满99元全场免邮</a>
          <a class="icon3" style="margin-right: 0" href="javascript:;">100%品质保证</a>
        </p>
      </div>
    </div>
    <div class="mod_help w1200">                                     
      <p>
        <a href="javascript:;">关于我们</a>
        <span>|</span>
        <a href="javascript:;">帮助中心</a>
        <span>|</span>
        <a href="javascript:;">售后服务</a>
        <span>|</span>
        <a href="javascript:;">热点资讯</a>
        <span>|</span>
        <a href="javascript:;">关于货源</a>
      </p>
      <p class="coty">拼XiXi商城版权所有 &copy; 2019-9999 </p>
    </div>
  </div>
  <script type="text/javascript">

layui.config({
    base: 'res/static/js/util/' //你存放新模块的目录，注意，不是layui的模块目录
  }).use(['mm','carousel'],function(){
      var carousel = layui.carousel,
     mm = layui.mm;
     var option = {
        elem: '#test1'
        ,width: '100%' //设置容器宽度
        ,arrow: 'always'
        ,height:'298' 
        ,indicator:'none'
      }
      carousel.render(option);
      // 模版引擎导入
     // var ins = carousel.render(option);
     // var html = demo.innerHTML;
     // var listCont = document.getElementById('list-cont');
     // // console.log(layui.router("#/about.jsp"))
     //  mm.request({
     //    url: '../json/index.json',
     //    success : function(res){
     //      console.log(res)
     //      listCont.innerHTML = mm.renderHtml(html,res)
     //      ins.reload(option);
     //    },
     //    error: function(res){
     //      console.log(res);
     //    }
     //  })
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