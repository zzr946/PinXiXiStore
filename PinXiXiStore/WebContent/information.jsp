<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>拼XiXi商城-资讯</title>
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


  <div class="content content-nav-base information-content">
    <div class="main-nav">
      <div class="inner-cont0">
        <div class="inner-cont1 w1200">
          <div class="inner-cont2">
            <a href="goods?method=productall">所有商品</a>
            <a href="sales?method=salesall">今日特惠</a>
            <a href="information.jsp"  class="active">热点资讯</a>
            <a href="about.jsp">关于我们</a>
          </div>
        </div>
      </div>
    </div>
    <div class="info-list-box">
      <div class="info-list w1200">
        <div class="item-box layui-clear" id="list-cont">
          <div class="item">
            <div class="img">
              <img src="res/static/img/new1.jpg" alt="">
            </div>
            <div class="text">
              <h4>华为P40来袭：P30无奈让路，价格感人！</h4>
              <p class="data">2019-9-6 17:30:19</p>
              <p class="info-cont">华为P40的曝光，导致华为P30价格持续走低，据网易科技报道，华为P30在电商平台最新一期的竞拍活动中以低至81元的价格成交，不仅创下了该机上市以来的价格新低，也是智能机价格史上的里程碑。</p>
            </div>
          </div>
          <div class="item">
            <div class="img">
              <img src="res/static/img/new2.jpg" alt="">
            </div>
            <div class="text">
              <h4>苹果11确认，取消64GB，128GB起步价给力，价格感人</h4>
              <p class="data">2019-9-6 17:32:26</p>
              <p class="info-cont">根据最新的消息，新的iphone将会从128G起售，直接取消了64GB。 iphone 11 128G的价格预计为6887元，256GB的价格为7577元，512GB的价格为8266元。 iphone 11将会采用IOS 13系统，黑暗模式更好的适配OLED屏幕，并且在界面上进行了更多的优化，具体的硬件配置如下。采用A13处理器，7nm台积电代工，晶体管密度提升20%，无疑是最强的手机处理器了。</p>
            </div>
          </div>
          <div class="item">
            <div class="img">
              <img src="res/static/img/new3.jpg" alt="">
            </div>
            <div class="text">
              <h4>美式装饰画，提升你的家居颜值</h4>
              <p class="data">2019-9-6 17:40:36</p>
              <p class="info-cont">装饰画是一种挂在室内墙上的挂画，不仅可以单幅点缀，而且还可以多幅组合装饰，如果想要营造出清新典雅的居室氛围的话，美式装饰画是很棒的选择，可以轻松装饰出一个清新典雅，舒适自在的家居氛围。</p>
            </div>
          </div>
          <div class="item">
            <div class="img">
              <img src="res/static/img/new4.jpg" alt="">
            </div>
            <div class="text">
              <h4>北欧风实木床，原木主义让生活更有温度</h4>
              <p class="data">2019-9-6 17:43:44</p>
              <p class="info-cont">有时候待在自己的小房间里，总会想捣鼓一番，让卧室看上去更有质感和格调。如果你有同样的想法，那选择一款精致而质朴的实木床就是很有必要的咯。虽然说一些板材制成的床架看上去挺划算的，但是质感以及耐久度和实木材质的床还是没法比的，单从外观上看，也没有实木床那么有气质。</p>
            </div>
          </div>
          <div class="item">
            <div class="img">
              <img src="res/static/img/new5.jpg" alt="">
            </div>
            <div class="text">
              <h4>明月思乡情，内外兼修颜系月饼寄相思</h4>
              <p class="data">2016-9-6 17:55:06</p>
              <p class="info-cont">在中国中秋节在传统节日中占着比较重要的位置，所以很多人都会在这节日给长辈、亲戚、好友送些礼物表示敬意。大多数人都会选择比较传统的礼物“月饼”馈赠给别人。还记得小时候常吃的那种白色大圆糕状的单一面粉与糖味道的月饼，想想那时候吃起来也是很有满足感。虽然里面没有什么蛋黄呀、五仁啊之类，但还是吃的很开心，而且外面那红色纸包装起来也很有仪式感，送礼也是很有面子的。</p>
            </div>
          </div>
          <div class="item">
            <div class="img">
              <img src="res/static/img/new6.jpg" alt="">
            </div>
            <div class="text">
              <h4>小时候用炉子做的美味，现在在家也能做</h4>
              <p class="data">2019-9-6 17:58:26</p>
              <p class="info-cont">想想还是小时候的单纯更美好，今天就来制作一道小时候的零食——烤地瓜。我生活在北方，以前家里没有暖气，一到冬天就得生炉子，虽然生炉子屋子里不会像现在这样干净和暖和，但炉子的好处就是可以用来烤东西，烤地瓜，烤苹果，烤土豆……这就是那个时候小朋友的零食。</p>
            </div>
          </div>
          <div class="item">
            <div class="img">
              <img src="res/static/img/new7.jpg" alt="">
            </div>
            <div class="text">
              <h4>开春少不了的扮酷单品，时尚风衣已上线！</h4>
              <p class="data">2019-9-6 17:39:46</p>
              <p class="info-cont">风衣应该是春秋衣橱里的常见款了，具备实用功能性，又能完美凹造型。既然这个冬天已过去了大半，那春天还远吗？凹造型当然早早准备了，风衣设计大气简约，基本在各种场合都适用，随时都能走路带风，轻松塑造优雅傲视姿态。</p>
            </div>
          </div>
          <div class="item">
            <div class="img">
              <img src="res/static/img/new8.jpg" alt="">
            </div>
            <div class="text">
              <h4>护肤三大误区，还不看看你中招了没！</h4>
              <p class="data">2019-9-6 18:01:16</p>
              <p class="info-cont">新的一年又开始了~小仙女们当然也是要继续以美丽的姿态来迎接新年~要变的精致美丽，除了化妆之外，首先就是要护肤啦！现在又正处于寒冷的冬季，脸蛋总是遭受寒风和低温的刺激，变得更加脆弱，护肤工作也就更加不可轻视！</p>
            </div>
          </div>
          
        </div>
        <div id="demo0" style="text-align: center;"></div>
      </div>
    </div>
  </div>
  <!-- 模版引擎导入 -->
  <!-- <script type="text/html" id="demo">
    {{# layui.each(d.listCont,function(index,item){}}
    <div class="item">
      <div class="img">
        <img src="res/img/new1.jpg" alt="">
      </div>
      <div class="text">
        <h4>周岁内的宝宝消化不良拉肚子怎么办?</h4>
        <p class="data">2016-12-24 16:33:26</p>
        <p class="info-cont">宝宝在周岁之前体质相对较弱，特别是薄弱肠道，一不注意就会拉肚子;那么宝宝消化不良拉肚子</p>
      </div>
    </div>
    {{# })}}
  </script> -->
<script>
  layui.config({
    base: 'res/static/js/util/' //你存放新模块的目录，注意，不是layui的模块目录
  }).use(['mm','laypage'],function(){
      var
      mm = layui.mm,laypage = layui.laypage;
      laypage.render({
        elem: 'demo0'
        ,count: 100 //数据总数
      });
    // 模版引擎导入
     // var html = demo.innerHTML;
     // var listCont = document.getElementById('list-cont');
     //  mm.request({
     //    url: '../json/information.json',
     //    success : function(res){
     //      console.log(res)
     //      listCont.innerHTML = mm.renderHtml(html,res)
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