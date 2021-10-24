<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.example.demo.util.Util"%>
<%@ include file="../part/mainLayoutHeader.jspf"%>

<c:set var="fileInputMaxCount" value="3" />

<section class="flex justify-center">
	<div class="max-w-5xl w-full">
		<div class="p-2 border-b-4 border-blue-500 text-3xl font-bold">
			<span>장바구니</span>
		</div>
		<div class="grid grid-cols-1 gap-3 p-4">
			<c:set var="totalPrice" value="0"></c:set>
			<c:forEach var="item" items="${items}">
				<c:set var="totalPrice" value="${totalPrice + item.price}"></c:set>
				<c:set var="fileNo" value="${String.valueOf(0)}"></c:set>
				<c:set var="file" value="${item.extra.file__common__all[fileNo]}"></c:set>
				<div class="flex border">
					<div class="flex items-center p-1">
						<input type="checkbox" name="chk" value="${item.iid}"  />
					</div>
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
									<span>${Util.numberFormat(sum)}원</span>
								</div>
							</div>
							<div class="flex justify-end pt-1">
								<div class="gap-1">
									<input type="button" value="주문하기" class="p-1 bg-blue-300 hover:bg-blue-500 text-sm" onclick="order(${item.bid})" />
									<input type="button" value="삭제하기" class="p-1 bg-red-300 hover:bg-red-500 text-sm" onclick="item__putOut(${item.bid})" />
								</div>
								<script>
								function order(bid) 
								{
										location.href='../item/order?bid=' + bid;
								}
								function item__putOut(bid) 
								{
									const result = confirm('정말로 장바구니에서 삭제하시겠습니까?');
									
									if(result)
										location.href='../item/putOut?bid=' + bid;
								}
								</script>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="border">
			<div class="flex justify-center items-center text-2xl h-24">
				<span>총 주문금액&nbsp</span>
				<span class="font-bold text-blue-500">${totalPrice}원</span>
			</div>
			<div class="flex justify-center gap-2 pb-3">
				<input type="button" value="주문하기" class="p-1 bg-blue-300 hover:bg-blue-500 text-xl" onclick="location.href='chkbox?chk=${chk}'" />
				<input type="button" value="삭제하기" class="p-1 bg-red-300 hover:bg-red-500 text-xl" onclick="item__putOut(${item.bid})" />
			</div>
		</div>
	</div>
</section>

<%@ include file="../part/mainLayoutFooter.jspf"%>