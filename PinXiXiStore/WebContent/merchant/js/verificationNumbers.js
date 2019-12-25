var boo = false;

//显示验证码的方法
//function showCheck(a){
//	var c = document.getElementById("myCanvas");
//  var ctx = c.getContext("2d");
//	ctx.clearRect(0,0,1000,1000);
//	ctx.font = "80px 'Microsoft Yahei'";
//	ctx.fillText(a,0,100);
//	ctx.fillStyle = "white";
//}
var code ;  

//产生随机验证码的方法
//function createCode(){       
//    code = "";      
//    var codeLength = 4;
//    var selectChar = new Array(1,2,3,4,5,6,7,8,9,'a','b','c','d','e','f','g','h','j','k','l','m','n','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z');      
//    for(var i=0;i<codeLength;i++) {
//       var charIndex = Math.floor(Math.random()*60);      
//      code +=selectChar[charIndex];
//    }      
//    if(code.length != codeLength){      
//      createCode();      
//    }
//    showCheck(code);
//}

//验证验证码是否正确的方法
//function validate () {
//    var inputCode = document.getElementById("J_codetext").value.toUpperCase();
//    var codeToUp=code.toUpperCase();
//    if(inputCode.length <=0) {
//      document.getElementById("J_codetext").setAttribute("placeholder","输入验证码");
//      createCode();
//      return false;
//    }
//    else if(inputCode != codeToUp ){
//      document.getElementById("J_codetext").value="";
//      document.getElementById("J_codetext").setAttribute("placeholder","验证码错误");
//      createCode();
//      return false;
//    }
//    else {
////    window.open(document.getElementById("J_down").getAttribute("data-link"));
//      document.getElementById("J_codetext").value="";
//      createCode();
//      return true;
//    }
//
//}
//发送验证码（测试)
function sendyzm(){
	var phone = document.getElementById('phone');
	var boo1 = checkphone(phone);
	if(boo1){
		alert('发送成功');
	}
	
	 
}
//注册按钮点击事件
function reg_btn(){
	console.info(boo);
	if(boo){
		$("#submit_btn_reg").click(function(){
	  location.href="merchant_login.html";
	  });
	}
	
}
//登陆按钮点击事件（测试）
//function login_btn(){
//	var boo2 = validate ();
//	var phone = document.getElementById('login_phone');
//	var boo3 = checkphone(phone);
//	if(boo3){
//		if(boo2){
//		alert('登录成功');
//	 location.href="merchant_manage.html";
	
//	}
//	}
	
//}

 //验证电话号码
 function checkphone(phone){
// 	var phone = document.getElementById('phone');
		var reg = /^1[356789]\d{9}$/;
			console.log(phone.value);
			boo = reg.test(phone.value);
			if(!boo){
				alert('手机格式不正确，请重新输入');
				return boo;
			}
			return boo;
 }
