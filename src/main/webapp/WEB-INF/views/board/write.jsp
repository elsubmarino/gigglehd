<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//cdn.ckeditor.com/4.7.3/standard/ckeditor.js"></script>
<form method="POST" id="form">
<input type="hidden" name="writer" value="haha"/>
<input type="hidden" name="mainCategory" value="${pageMaker.cri.mainCategory }"/>
<input type="hidden" name="subCategory" value=""/>
<c:if test="${param.mode eq 'reply' }">
<input type="hidden" name="groupNum" value="${param.groupNum }"/>
<input type="hidden" name="sequence" value="${param.sequence }"/>
<input type="hidden" name="lvl" value="${param.lvl+1 }"/>
</c:if>
<div class="well">
	<div class="row">
		<select id="subcategory"><option>분류</option>
		<c:forEach items="${list }" var="item">
		<option value="${item.content }">${item.name }</option>
		</c:forEach>
		</select><input type="text" name="title" value="${mode eq 'modify' ? dto.title:'' }">
	</div>
	<div class="row">
	참고 / 링크 <input type="text">
	</div>
	<div class="row">
	<textarea name="content" id="editor">${mode eq 'modify' ? dto.content:'' }</textarea>
	</div>
	<div class="row">
	<a href="#">임시 저장</a>
	<a href="#">미리보기</a>
	<button id="submit">등록</button>
	</div>
</div>
</form>
<script>
$(function(){
	$("#submit").click(function(){
		if("${mode}"==""||"${mode}"==null){
		$("#form").attr("action","/board/write");
		}else if("${mode}"=="modify"){
			$("#form").attr("action","/board/modify"+search);
		}
		var subcategory=$("#subcategory").val();
		$("[name=subCategory]").val(subcategory);
		$("#form").submit();
	});
});
CKEDITOR.replace("editor");
CKEDITOR.config.filebrowserUploadUrl = "/board/upload";

</script>
