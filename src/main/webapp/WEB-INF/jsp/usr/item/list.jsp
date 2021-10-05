<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.example.demo.util.Util"%>
<%@ include file="../part/mainLayoutHeader.jspf"%>

<c:set var="fileInputMaxCount" value="3" />

<section class="flex justify-center">
	<div class="max-w-5xl w-full">
		<div class="p-2 border-b-4 border-blue-500 text-3xl font-bold">
			<span>전체품목</span>
			<c:if test="${param.group != null}">
				<span> : ${param.group.toUpperCase()}</span>
			</c:if>
		</div>
		<div class="grid grid-cols-4 gap-3 p-4">
			<c:forEach var="car" items="${cars}">
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
					</div>
				</div>
			</c:forEach>
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