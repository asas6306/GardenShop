<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.example.demo.util.Util"%>
<%@ include file="../part/mainLayoutHeader.jspf"%>

<c:set var="fileInputMaxCount" value="3" />

<section class="flex justify-center">
	<div class="max-w-5xl w-full">
		<div class="p-2 border-b-4 border-blue-500 text-3xl font-bold">
			<span>차량목록</span>
			<c:if test="${param.group != null}">
				<span> : ${param.group.toUpperCase()}</span>
			</c:if>
		</div>
		<div class="grid grid-cols-3 gap-5 p-4">
			<c:forEach var="car" items="${cars}">
				<c:set var="carName" value="${car.name}"></c:set>
				<c:set var="fileNo" value="${String.valueOf(0)}"></c:set>
				<c:set var="file" value="${car.extra.file__common__ALL[fileNo]}"></c:set>
					<div class="border">
						<a href="${file.forPrintUri}" target="_blank" title="자세히 보기" >
							<img alt="사진 준비중 입니다." src="${file.forPrintUri}" />
						</a>
						<div class="p-2">
							<div>
								<span class="text-2xl font-bold">${car.name}</span>
								<span class="text-gray-500">&nbsp${car.year}</span>
							</div>
							<div class="flex justify-between">
								<div>
									<span class="text-2xl">${Util.numberFormat(car.price)}</span>
									<span>원 부터</span>
								</div>
								<div class="flex justify-center pt-1">
									<input type="button" value="견적내기" class="px-2 bg-blue-300 hover:bg-blue-500 text-sm" onclick="send__contact('${car.name}', ${car.year}, ${rq.loginedMember.uid})" />
									<script>
									function send__contact(car, year, uid) {
										alert(car + year + uid);
									}
									</script>
								</div>
							</div>
						</div>
					</div>
			</c:forEach>
		</div>
	</div>
</section>

<%@ include file="../part/mainLayoutFooter.jspf"%>