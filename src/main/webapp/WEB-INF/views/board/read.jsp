<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<form method="POST" id="delForm">
	<input type="hidden" value="${dto.num }" />
</form>

<fmt:parseDate value="${dto.datetime }" var="dateTime"
	pattern="yyyy-MM-dd'T'HH:mm:ss" type="both" />
<div class="row">${dto.title }
	<fmt:formatDate value="${dateTime}" pattern="yyyy-MM-dd HH:mm" />
</div>
<div class="row">${dto.writer } ${dto.hit }</div>
<div class="row">${dto.content }</div>
<div class="row center-block">
	<a href="#" id="updateDelete"><span id="updateDeleteCount">${updateDeleteCount }</span>삭제
		요청</a> <a href="#" id="updateRecommendation"><span
		id="updateRecommendationCount">${recommendation }</span>추천</a>
</div>
<div class="row">
	<a href="#" onclick="goback()">목록</a> <a href="#">스크랩</a>
	<c:if test="${param.maincategory eq 'community' }">
		<a
			href="/board/write${pageMaker.makeQuery(pageMaker.cri.page) }&sequence=${dto.sequence }&lvl=${dto.lvl }&group_num=${dto.group_num}&mode=reply">답글</a>
	</c:if>
	<c:if test="${sessionScope.user.username eq dto.writer }">
		<a href="#" id="modify">수정</a>
		<a href="#" id="del">지우기</a>
	</c:if>

</div>
<div class="row">
	<form id="commentForm" method="POST" action="/board/writeComments">
		<input type="hidden" name="sequence" value="1" /> <input
			type="hidden" name="lvl" value="0" /> <input type="hidden"
			name="root_num" value="${dto.num }" />
		<textarea name="content"></textarea>
		<button id="comment">등록</button>
	</form>
</div>
<div class="row">
	<c:forEach items="${list }" var="item">
		<form method="POST" action="/board/writeComments">
			<input type="hidden" name="sequence" value="${item.sequence + 1 }">
			<input type="hidden" name="lvl" value="${item.lvl + 1 }"> <input
				type="hidden" name="root_num" value="${item.root_num }" /> <input
				type="hidden" name="group_num" value="${item.group_num }">
			<div style="margin-left:${item.lvl * 10}px" id="${item.num }">
				<div class="row">${item.writer }${item.date }</div>
				<div class="row">${item.content }<a href="#" class="replyHead">댓글</a>
				</div>
				<div class="row" class="replyBody" style="display: none">
					<textarea name="content"></textarea>
					<input type="submit" value="등록">
				</div>
			</div>
		</form>
	</c:forEach>
</div>
<script>
	function goback() {
		var search = location.search;
		var match = new RegExp("[?]([^]*)").exec(search);
		location.href = "list?" + match[1];

	}
	$(function() {
		var data = {
			"num" : "${dto.num}"
		};
		var updateDeleteCount = $("#updateDeleteCount");
		$("#updateDelete").click(
				function() {
					$.ajax({
						url : "/board/updateDelete",
						dataType : "json",
						type : "POST",
						data : JSON.stringify(data),
						contentType : "application/json;charset=utf-8",
						success : function(res) {
							if (res.status == "success") {
								updateDeleteCount.text(Number(updateDeleteCount
										.text()) + 1);
							}
						},
						error : function(xhr, stat, err) {
						}
					});
				});

		$("#updateRecommendation")
				.click(
						function() {
							var data = {
								"num" : "${dto.num}"
							};
							var updateRecommendationCount = $("#updateRecommendationCount");
							$
									.ajax({
										type : "POST",
										dataType : "json",
										url : "/board/updateRecommendation",
										data : JSON.stringify(data),
										contentType : "application/json;charset=utf-8",
										success : function(res) {
											if (res.status == "success")
												updateRecommendationCount
														.text(Number(updateRecommendationCount
																.text()) + 1);
										},
										error : function(xhr, stat, err) {

										}
									})
						});

		$(".replyHead").click(function() {
			$(this).parent().next().toggle();
			$(".replyBody").hide();

		})

		$("#comment").click(function() {
			$("#commentForm").submit();
		});
		var search = location.search;
		$("#del").click(function() {
			$("#delForm").attr("action", "/board/delete" + search);
			$("#delForm").submit();
		});
		$("#modify").click(function() {
			location.href = "/board/modify" + search;
		});
	});
</script>