<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">포럼 일주일 인기글 20</div>
<div class="row">
	<table>
		<c:forEach items="${forumsWeekly }" var="item">
			<tr>
				<td>${item.maincategory }</td>

				<td>${item.title }</td>
				<td>${item.writer }</td>
				<td>${item.date }</td>
				<td>${item.hit }</td>

			</tr>
		</c:forEach>
	</table>
</div>
<div class="row">커뮤니티 일주일 인기글 10</div>
<div class="row">
	<table>
		<c:forEach items="${communityWeekly }" var="item">
			<tr>
				<td>${item.maincategory }</td>
				<td>${item.title }</td>
				<td>${item.writer }</td>
				<td>${item.date }</td>

				<td>${item.hit }</td>
		</c:forEach>
	</table>
</div>
<div class="row">포럼 한달 인기글 20</div>
<div class="row">
	<table>
		<c:forEach items="${forumsMonthly }" var="item">
			<tr>
				<td>${item.maincategory }</td>

				<td>${item.title }</td>
				<td>${item.writer }</td>
				<td>${item.date }</td>

				<td>${item.hit }</td>
		</c:forEach>
	</table>
</div>
<div class="row">커뮤니티 한달 인기글 10</div>
<div class="row">
	<table>
		<c:forEach items="${communityWeekly }" var="item">
			<tr>
				<td>${item.maincategory }</td>

				<td>${item.title }</td>
				<td>${item.writer }</td>
				<td>${item.date }</td>

				<td>${item.hit }</td>
		</c:forEach>
	</table>
</div>