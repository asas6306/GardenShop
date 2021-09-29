<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.example.demo.util.Util"%>
<%@ include file="../part/mainLayoutHeader.jspf"%>

<c:set var="fileInputMaxCount" value="3" />

<section class="flex justify-center">
	<div class="max-w-5xl w-full">
		<div>
			<c:forEach var="car" items="${cars}">
				<c:set var="fileValue" value="car.extra.file__common__${car.name}[0]"></c:set>
				${fileValue}
				<!-- 
				<c:set var="file" value="${fileValue}"></c:set>
				${file}
				<c:if test="${file != null && file.fileExtTypeCode == 'img'}">
					<div>
						<a href="${file.forPrintUri}" target="_blank" title="자세히 보기">
							<img class="h-40 w-40 sm:h-48 sm:w-48 lg:h-60 lg:w-60 rounded-lg object-cover" src="${file.forPrintUri}" />
						</a>
					</div>
				</c:if>
				 -->
			</c:forEach>
		</div>
	</div>
</section>

<%@ include file="../part/mainLayoutFooter.jspf"%>