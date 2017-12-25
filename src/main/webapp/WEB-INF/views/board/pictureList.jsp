<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<ul class="nav nav-tabs">
	<li ${param.subcategory eq null ? 'class=active':'' }><a
			href="/board/list?maincategory=${pageMaker.cri.maincategory }">전체</a></li>
		<c:forEach items="${subcategory }" var="item">
			<li ${item.content eq param.subcategory?"class='active'":"" }><a href="/board/list?maincategory=${pageMaker.cri.maincategory }&subcategory=${item.content}">${item.name }</a>
		</c:forEach>

	</ul>
</div>
<c:forEach items="${list }" var="item" varStatus="i">
	<c:if test="${i.index % 3 ==0 }">
		<div class="row">
	</c:if>
	<div class="pull-left">
	<img src="${item.url }" style="width:100px !important;height:100px !important;">
	<div><a href="/board/read${pageMaker.makeQuery(pageMaker.cri.page)}&num=${item.num}">${item.title }</a></div>
	<div>${item.date }${item.writer }${item.hit }</div>
	</div>
	<c:if test="${i.index % 3 ==2 or list.size()-1 eq i.index}">
		</div>
	</c:if>
</c:forEach>
<ul class="pagination">
<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage+1 }" step="1" varStatus="i">
<li><a href="${pageMaker.makeQuery(i.index) }">${i.index }</a>
</c:forEach>
</ul>
<div class="row">
	<a href="#">목록</a>
	<form method="get" action="/board/list" id="searchForm">
		<input type="hidden" name="subcategory"
			value="${pageMaker.cri.subcategory }" /> <input type="text"
			name="keywords"><select name="searchType"><option
				value="all">제목+내용</option>
			<option>제목</option></select>
	</form>
	<a href="#" id="search">검색</a> <a
		href="/board/write${pageMaker.makeQuery(pageMaker.cri.page) }">쓰기</a>
</div>