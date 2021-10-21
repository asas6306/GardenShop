<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.example.demo.util.Util"%>
<%@ include file="../part/mainLayoutHeader.jspf"%>

<section class="flex justify-center">
	<div class="max-w-5xl w-full">
		<div class="p-2 border-b-4 border-blue-500 text-3xl font-bold">
			<span>주문목록</span>
		</div>
		<div class="grid grid-cols-1 gap-3 p-4">
			<c:choose>
				<c:when test="${orders.size() == 0}">
					<span>들어온 주문이 없습니다.</span>
				</c:when>
				<c:otherwise>
					<c:forEach var="item" items="${orders}">
						<c:set var="fileNo" value="${String.valueOf(0)}"></c:set>
						<c:set var="file" value="${item.extra.file__common__all[fileNo]}"></c:set>
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
											<span>${Util.numberFormat(sum)}원</span>
										</div>
									</div>
									<div class="flex justify-end pt-1">
										<div class="gap-1">
											<input type="button" value="배송하기" class="p-1 bg-blue-300 hover:bg-blue-500 text-sm" onclick="order(${item.bid})" />
											<input type="button" value="취소하기" class="p-1 bg-red-300 hover:bg-red-500 text-sm" onclick="item__putOut(${item.bid})" />
										</div>
										<script>
										function order(bid) 
										{
												location.href='../item/order?bid=' + bid;
										}
										function item__putOut(bid) 
										{
											const result = confirm('정말로 주문목록을 취소하시겠습니까?');
											
											if(result)
												location.href='../item/putOut?bid=' + bid;
										}
										</script>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="flex">
			<div class="flex justify-center text-lg flex-grow text-gray-700">
				<a href="list?group=${param.group}&page=1" class="p-2 hover:text-black hover:text-blue-500">처음</a>
				<a href="list?group=${param.group}&page=${printPageIndexDown}" class="p-2 hover:text-black hover:text-blue-500">이전</a>
				<c:forEach items='${printPageIndexs}' var='printPageIndex'>
					<c:choose>
						<c:when test="${printPageIndex == page}">
							<a href="list?group=${param.group}&page=${printPageIndex}" class="p-2 text-black font-extrabold">${printPageIndex}</a>
						</c:when>
						<c:otherwise>
							<a href="list?group=${param.group}&page=${printPageIndex}" class="p-2 hover:text-black hover:text-blue-500">${printPageIndex}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<a href="list?group=${param.group}&page=${printPageIndexUp}" class="p-2 hover:text-black hover:text-blue-500">다음</a>
				<a href="list?group=${param.group}&page=1000000" class="p-2 hover:text-black hover:text-blue-500">끝</a>
			</div>
		</div>
	</div>
</section>

<%@ include file="../part/mainLayoutFooter.jspf"%>