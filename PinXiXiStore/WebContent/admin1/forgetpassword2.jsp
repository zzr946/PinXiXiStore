<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>txt</title>


<link rel="stylesheet" type="text/css" href="css/style.css">


<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/vector.js"></script>


</head>
<body>


<div id="container">
	<div id="output">
		<div class="containerT">
			<form name="form_forgetPwd" method="get" action="admin" onsubmit="return checkForm(this)">
    		<!-- 隐藏域 -->
       		<input type="hidden" name="method" value="Admin_checkByadminname2">
       		您的账号是<input type="text" readonly="readonly" value="${admincheck1.adminaccount}"/><br>
    		您的密码是<input type="text" readonly="readonly" value="${admincheck1.adminpassword}"/><br>
			<a href="adminlogin.jsp">返回到登录页面</a>
   	 </form>
		</div>
	</div>
</div>


<script type="text/javascript">
    $(function(){
        Victor("container", "output");   //登录背景函数
        $("#entry_name").focus();
        $(document).keydown(function(event){
            if(event.keyCode==13){
                $("#entry_btn").click();
            }
        });
    });
</script>
<div style="text-align:center;">


</div>
</body>
</html>