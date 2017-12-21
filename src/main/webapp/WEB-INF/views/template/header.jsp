<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
/* .icon-bar{
	background-color:black !important;
}
.navbar-toggle{
border-color:black;
} */
</style>
<div class="row">
	<div class="pull-right">
		<ul class="nav nav-pills">
			<li><c:if test="${sessionScope.user == null }">
					<a href="#modal" data-toggle="modal">로그인</a>
				</c:if> <c:if test="${sessionScope.user != null }">
					<a href="/logout">로그아웃</a>
				</c:if></li>
			<c:if test="${sessionScope.user == null }">
				<li><a href="/signup">가입하기</a></li>
			</c:if>
		</ul>
	</div>
</div>
<div id="modal" class="modal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<form method="POST" action="/login" id="loginForm">
					<input type="text" name="username" value="" /> <input
						type="password" name="passwords" value="" /> <input
						type="checkbox" name="maintain"> 로그인 유지
					<button id="login">로그인</button>
				</form>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<h1>
		<a href="/">Gigglehd.com</a>
	</h1>
</div>
<div class="row">
	<nav class="navbar">
		<ul class="nav navbar-nav">
			<c:set var="superbase" value="" />
			<c:forEach items="${menu }" var="item">
				<c:choose>
					<c:when test="${superbase ne '' and item.superbase ne superbase}">
						</li>
		</ul>
		<li class="dropdown"><a style="cursor: pointer">${item.superbase }</a>
			<ul class="dropdown-menu">
				</c:when>
				<c:when test="${superbase eq '' }">
					<li class="dropdown"><a style="cursor: pointer">${item.superbase }</a>
						<ul class="dropdown-menu">
				</c:when>
				</c:choose>
				<li><a href="${item.link }">${item.content }</a></li>
				<c:set var="superbase" value="${item.superbase}" />
				</c:forEach>
			</ul> <!-- 	<li class="dropdown"><a href="#" data-toggle="dropdown">기글하드웨어</a>
				<ul class="dropdown-menu">
					<li><a href="#">인기글</a></li>
					<li><a href="/board/list?mainCategory=news">주간 / 국내뉴스</a></li>
					<li><a href="/board/list?mainCategory=reviews">리뷰 / 사용기</a></li>
					<li><a href="/board/list?mainCategory=ads">홍보 / 필테</a></li>
					<li><a href="/points">포인트 랭킹</a></li>
				</ul></li>
			<li class="dropdown"><a href="#" data-toggle="dropdown">포럼</a>
				<ul class="dropdown-menu">
					<li><a href="#">컴퓨터 / 하드웨어</a></li>
				</ul></li>
			<li class="dropdown"><a href="#" data-toggle="dropdown">커뮤니티</a>
				<ul class="dropdown-menu">
					<li><a href="#">커뮤니티 게시판</a></li>
				</ul></li> -->
			</ul>
			<div class="navbar-form navbar-right">
				<form id="ultimateSearch" action="/board/search">
					<div class="input-group">
						<input type="text" class="form-control" name="keywords">
						<div class="input-group-btn">
							<button class="btn btn-primary">검색</button>
						</div>
					</div>
				</form>
			</div>
	</nav>
</div>
<div class="col-md-2 pull-right"
	style="height: 300px; overflow-y: scroll">
	<div>최근 코멘트 30 개</div>
	<ul class="list-unstyled">
		<c:forEach items="${replyList }" var="item">
			<li>${item.writer }<a
				href="/board/read?num=${item.root_num }#${item.num}">${item.content }</a></li>
		</c:forEach>
	</ul>
</div>
<div class="col-md-10">
	<script>
		$(function() {
			$(".dropdown").hover(function() {
				$(".dropdown-menu", this).slideDown("fast");
			}, function() {
				$(".dropdown-menu", this).css("display", "none");
			});
			$("#login").on("click", function(evt) {
				evt.preventDefault();
				if (!$("[name=username]").val()) {
					alert("사용자 이름을 입력하세요!");
					return;
				} else if (!$("[name=passwords]").val()) {
					alert("패스워드를 입력하세요!");
					return;
				}
				$.ajax({
					data : $("#loginForm").serialize(),
					url : '/login',
					type : 'post',
					success : function(res) {
						if (res.status == "fail") {
							alert("로그인이 실패하였습니디!");
							return;
						}
						location.reload();
					},
					error : function(xhr, status, err) {
						alert(xhr + " " + status + " " + err);
					}
				})
			});
		})
	</script>