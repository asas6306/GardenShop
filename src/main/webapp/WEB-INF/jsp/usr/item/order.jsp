<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.example.demo.util.Util"%>
<%@ include file="../part/mainLayoutHeader.jspf"%>

<script>
const doOrder__checkAndSubmitDone = false;

doOrder__submited = false;
function doOrder__checkAndSubmit(form) {
	if ( doOrder__submited ) {
		alert('처리중입니다.');
		return;
	}
	
	form.submit();
	doOrder__submited = true;
}
</script>

<section class="flex justify-center">
	<div class="max-w-5xl w-full">
		<div class="p-2 border-b-4 border-blue-500 text-3xl font-bold">
			<span>주문/결제</span>
		</div>
		<div class="p-4">
			<c:set var="fileNo" value="${String.valueOf(0)}"></c:set>
			<c:set var="file" value="${item.extra.file__common__all}"></c:set>
			<div class="flex border">
				<a href="${file.forPrintUri}" target="_blank" title="자세히 보기" >
					<img alt="사진 준비중 입니다." src="${file.forPrintUri}" class="w-36" />
				</a>
				<div class="flex items-center w-full p-2">
					<div class="w-full">
						<div>
							<span class="text-2xl font-bold">${item.name}</span>
						</div>
						<div class="flex justify-between text-xl">
							<div>
								<span>${Util.numberFormat(item.price)}원</span>
							</div>
							<div></div> <!-- 공백 -->
							<div>
								<span>${item.count}개</span>
							</div>
							<div>
								<c:set var="sum" value="${item.price * item.count}"></c:set>
								<span>총 ${Util.numberFormat(sum)}원</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<form enctype="multipart/form-data" onsubmit="doOrder__checkAndSubmit(this); return false;" action="doOrder" method="post" class="px-4" >
			<div class="flex justify-center border">
				<div>
					<div>이름</div>
					<div>연락처</div>
					<div>주소</div>
					<div>포인트</div>
					<div>결재수단</div>
				</div>
			</div>
			<div class="flex justify-end gap-2 p-2">
				<input type="submit" value="주문완료" class="px-2 py-1 bg-blue-300 hover:bg-blue-500" />
				<input type="button" value="취소" class="px-2 py-1 bg-red-300 hover:bg-red-500" />
			</div>
		</form>
	</div>
</section>

<%@ include file="../part/mainLayoutFooter.jspf"%>