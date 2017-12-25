<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">${maincategory.content}: ${maincategory.details }
</div>
<div class="row">
	<ul class="nav nav-tabs">
		<li ${param.subcategory eq null ? 'class=active':'' }><a
			href="/board/list?maincategory=${pageMaker.cri.maincategory }">전체</a></li>
		<c:forEach items="${subcategory }" var="item">
			<li ${item.content eq param.subcategory?"class='active'":"" }><a
				href="/board/list?maincategory=${pageMaker.cri.maincategory }&subcategory=${item.content}">${item.name }</a>
		</c:forEach>
	
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
			<td>${item.rownum }</td>
			<td>${item.menuName }</td>
			<td style="padding-left:${item.lvl*5}px"><c:if test="${item.date ge dateNow }"><span class="badge">New</span></c:if><a href="/board/read${pageMaker.makeQuery(pageMaker.cri.page) }&group_num=${item.group_num}&num=${item.num}">${item.title}</a></td>
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
		<input type="hidden" name="subcategory"
			value="${pageMaker.cri.subcategory }" /> <input type="text"
			name="keywords"><select name="searchType"><option
				value="all">제목+내용</option>
			<option>제목</option></select>
	</form>
	<a href="#" id="search">검색</a> 
	<c:if test="${sessionScope.user ne null }">
	<a
		href="/board/write${pageMaker.makeQuery(pageMaker.cri.page) }">쓰기</a>
	</c:if>
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