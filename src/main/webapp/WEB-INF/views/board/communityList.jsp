<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">${mainCategory.content}: ${mainCategory.details }
</div>
<div class="row">
	<ul class="nav nav-tabs">
		<li ${param.subCategory eq null ? 'class=active':'' }><a
			href="/board/list?mainCategory=${pageMaker.cri.mainCategory }">전체</a></li>
		<c:forEach items="${subCategory }" var="item">
			<li ${item.content eq param.subCategory?"class='active'":"" }><a
				href="/board/list?mainCategory=${pageMaker.cri.mainCategory }&subCategory=${item.content}">${item.name }</a>
		</c:forEach>
		<%-- 	<li ${param.subCategory eq null ? 'class=active':'' }><a
			href="/board/list">전체</a></li>
		<li ${param.subCategory eq 'nation'?'class=active':'' }><a
			href="/board/list?mainCategory=news&subCategory=nation">국내 뉴스</a></li>
		<li ${param.subCategory eq 'event'?'class=active':'' }><a
			href="/board/list?mainCategory=news&subCategory=event">이벤트</a></li>
		<li ${param.subCategory eq 'weekly'?'class=active':'' }><a
			href="/board/list?mainCategory=news&subCategory=weekly">주간 뉴스</a></li> --%>
	</ul>
</div>
<div class="row">
	<table>
		<tr>
			<td>번호</td>
			<td>분류</td>
			<td>제목</td>
			<td>글쓴이</td>
			<td>날짜</td>
			<td>조회수</td>
		</tr>
		<c:forEach items="${list }" var="item">
			<tr>
			<td>${item.num }</td>
			<td>${item.menuName }</td>
			<td style="padding-left:${item.lvl*5}px"><a href="/board/read${pageMaker.makeQuery(pageMaker.cri.page) }&groupNum=${item.groupNum}&num=${item.num}">${item.title}</a></td>
			<td>${item.writer }</td>
			<td>${item.date }</td>
			<td>${item.hit }</td>
			</tr>
		</c:forEach>
	</table>
</div>
<div class="row">
	<a href="#">목록</a>
	<form method="get" action="/board/list" id="searchForm">
		<input type="hidden" name="subCategory"
			value="${pageMaker.cri.subCategory }" /> <input type="text"
			name="keywords"><select name="searchType"><option
				value="all">제목+내용</option>
			<option>제목</option></select>
	</form>
	<a href="#" id="search">검색</a> <a
		href="/board/write${pageMaker.makeQuery(pageMaker.cri.page) }">쓰기</a>
</div>
<div class="row">

	<ul class="pagination">
		<c:if test="${pageMaker.prev }">
			<li><a href="${pageMaker.makeQuery(pageMaker.startPage) }">Prev</a></li>
		</c:if>
		<c:forEach begin="${pageMaker.startPage }"
			end="${pageMaker.endPage }" step="1" var="i">
			<li><a href="/board/list${pageMaker.makeQuery(i) }"> <c:if
						test="${pageMaker.cri.page eq i }">
						<b>
					</c:if> ${i }
			</a> <c:if test="${pageMaker.cri.page eq i }">
					</b>
				</c:if></li>
		</c:forEach>
		<c:if test="${pageMaker.next }">
			<li><a href="${pageMaker.makeQuery(pageMaker.endPage+1) }">Next</a></li>
		</c:if>
	</ul>

</div>
</div>

<script>
	$("#search").click(function() {

		$("#searchForm").submit();
	});
</script>