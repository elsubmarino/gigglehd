<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach items="${list }" var="item" varStatus="i">
	<c:if test="${i.index % 3 ==0 }">
		<div class="row">
	</c:if>
	<div class="pull-left">
	<img src="/resources/${item.url }" width="100px" height="100px">
	<div>${item.title }</div>
	<div>${item.content }</div>
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
