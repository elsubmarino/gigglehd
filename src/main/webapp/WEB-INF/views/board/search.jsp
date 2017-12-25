<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:forEach items="${list }" var="item">
<div class="row"><a href="/board/read?num=${item.num}&maincategory=${item.maincategory}&subcategory=${item.subcategory}">${item.title }</a></div>
<div class="row"></div>
<div class="row">${item.content }</div>
</c:forEach>
<ul class="pagination">
<c:if test="${pageMaker.prev }">
<li><a href="/board/search${pageMaker.makeQuery(pageMaker.startPage-1)}">&laquo;</a></li>
</c:if>
<c:forEach begin="${pageMaker.startPage }" step="1" end="${pageMaker.endPage }" varStatus="i">
<li ${i.index eq pageMaker.cri.page?"class='ac	tive'":'' }><a href="/board/search${pageMaker.makeQuery(i.index) }">${i.index }</a></li>
</c:forEach>
<c:if test="${pageMaker.next }">
<li><a href="/board/search${pageMaker.makeQuery(pageMaker.endPage+1)}">&raquo;</a></li>
</c:if>
</ul>