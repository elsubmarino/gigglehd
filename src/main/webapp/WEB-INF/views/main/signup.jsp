<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="row">
	<form id="form" method="POST" action="/signup">
		<input type="text" id="username" name="username" value=""> <input type="password" value="" id="passwords"
			name="passwords">
		<a href="#" id="submit">등록</a>
	</form>
</div>
<script>
	$(function() {
		$("#submit").click(function(evt){
			evt.preventDefault();
			if(!$("#username").val()){
				alert("아이디를 입력하세요!");
			}else if(!$("#passwords").val()){
				alert("패스워드를 입력하세요!");
			}else{
				$.ajax({
					url:"/idCheck",
					data:JSON.stringify({"username":$('#username').val()}),
					contentType:"application/json;charset=UTF-8",
					type:"POST",
					dataType:"json",
					success:function(res){
						if(res.status=="success"){
							$("#form").submit();
						}else if(res.status="fail"){
							alert("중복된 아이디입니다!");
							$("#username").focus();
						}
					},
					error:function(xhr,status,err){
					}
				});
				
			}
			
		});

	})
</script>