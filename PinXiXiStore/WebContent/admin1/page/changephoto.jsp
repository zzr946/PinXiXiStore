<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>txt</title>


<link rel="stylesheet" type="text/css" href="../css/style.css">

<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/vector.js"></script>

<style type="text/css">
	#img{
		width:168px;
		height:168px;
		border-radius:50%;
	
	}


</style>


	<script type="text/javascript">
		$(function(){
			$('#btn_file').change(function(event){
				var file = event.currentTarget.files[0];
				var reader = new FileReader();
				reader.onload=function(e){
					document.getElementById('img').src = e.target.result;
				}
				reader.readAsDataURL(file);
			})
		})
	</script>


</head>
<body>


<div id="container">
	<div id="output">
		<div class="containerT">
			<h1>上传头像</h1>
			<form action="../upload" method="post"  enctype="multipart/form-data">
				<img src="../img/123.jpg" id="img">
				<input type="file" name="myfile" id="btn_file">
				<input type="submit" value="上传">
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