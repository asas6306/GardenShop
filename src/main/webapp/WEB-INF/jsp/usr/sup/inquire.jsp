<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../part/mainLayoutHeader.jspf"%>

<script>
doInquire__submited = false;
function doInquire__checkAndSubmit(form) {
	if ( doInquire__submited ) {
		alert('처리중입니다.');
		return;
	}
	
	form.group.value = form.group.value.trim();
	if ( form.group.value == 0 ) {
		alert('그룹을 선택해주세요.');
		form.group.focus();
		return false;
	}
	
	form.title.value = form.title.value.trim();
	if ( form.title.value.length == 0 ) {
		alert('제목을 입력해주세요.');
		form.title.focus();
		return false;
	}
	
	form.body.value = form.body.value.trim();
	if ( form.body.value.length == 0 ) {
		alert('내용을 입력해주세요.');
		form.body.focus();
		return false;
	}
	
	doInquire__submited = true;
	submit();
}
</script>

<section class="flex justify-center">
	<div class="max-w-5xl w-full">
		<div class="flex items-center justify-center h-20 text-4xl font-bold">1:1 문의하기</div>
		<form onsubmit="doInquire__checkAndSubmit(this); return false;" action="doInquire" method="post">
			<div class="">
				<div class="w-full">
					<select name="group" class="p-2 font-thin text-lg outline-none border border-blue-500">
						<option value="0" class="font-thin">=== 분류 선택 ===</option>
						<c:forEach var="board" items="${boards}">
							<option value="${board.group}" class="font-thin">${board.group}</option>
						</c:forEach>
						<option value="기타" class="font-thin">기타</option>
					</select>
				</div>
			</div>
			<div class="flex border-b border-blue-500">
				<div class="w-full">
					<input type="text" name="title" placeholder="제목을 입력해주세요." autofocus="autofocus" autocomplete="off" class="w-full h-12 text-2xl p-2 outline-none"/>
				</div>
			</div>
			<div class="flex border-b border-blue-500">
				<div class="w-full">
					<textarea name="body" placeholder="내용을 입력해주세요." class="w-full h-40 p-2 text-xl outline-none"></textarea>
				</div>
			</div>
			<div class="flex w-full justify-center">
				<input type="submit" value="작성" class="bg-blue-300 h-8 w-16 mt-2 mr-1 hover:bg-blue-500 rounded" />
				<input type="button" value="취소" onclick="history.back()" class="bg-red-300 h-8 w-16 mt-2 ml-1 hover:bg-red-500 rounded" />
			</div>
		</form>
	</div>
</section>

<%@ include file="../part/mainLayoutFooter.jspf"%>