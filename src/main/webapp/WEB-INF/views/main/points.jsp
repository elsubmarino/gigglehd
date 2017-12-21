<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-md-6">
		<div class="row">
			<div class="row">기글 하드웨어 게시판별 점수</div>
			<div class="row">
				<table>
					<tr>
						<td>게시판별 점수</td>
						<td>글</td>
						<td>댓글</td>
					</tr>
					<c:forEach items="${mainCategory }" var="item">
						<tr>
							<td>${item.content }</td>
							<td>10</td>
							<td>5</td>
						</tr>
					</c:forEach>


				</table>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="row">기글 하드웨어 종합 포인트 순위 Top 135</div>
		<c:forEach items="${ranking }" var="item" varStatus="i">
			${i.index+1 }.<svg width="14px" height="14px"><rect width="12px" height="12px" x="1"  y="1" fill="white" stroke="green" stroke-width="1"/><text font-size="12px" fill="#000000" x="3" y="11">${item.lvl}</text></svg>${item.username } <sup>${item.points }</sup>
		</c:forEach>
	</div>
	<div class="col-md-6">
		<div class="row">기글 하드웨어 레벨별 포인트</div>
		<table>
			<tr>
				<td>레벨</td>
				<td>레벨 아이콘</td>
				<td>필요 포인트</td>
			</tr>
			<tr>
				<td>1</td>
				<td></td>
				<td>3</td>
			</tr>
		</table>
	</div>
</div>