<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.example.demo.util.Util"%>
<%@ include file="../part/mainLayoutHeader.jspf"%>

<section class="main-setting flex justify-center">
	<div class="flex justify-center container">
		<div class="max-w-5xl">
			<div class="main-banner">
				<img src="/gen/home/banner/banner__2.jpg" />
			</div>
			<div class="h-4"></div>
			<div class="main-best-4 border-2 border-gray-300">
				<div class="flex justify-between">
					<div></div>
					<div>
						<span class="w-full text-center text-4xl p-4">FAQ</span>
					</div>
					<div class="flex items-center">
						<input type="button" value="작성하기" class="p-1 cursor-pointer bg-blue-300 hover:bg-blue-500" onclick="location.href='addFAQ'" />
					</div>
				</div>
				<div class="grid grid-cols-4 p-1">
					<c:if test="${FAQs.size() == 0}">
						음슴
					</c:if>
					<c:forEach var="FAQ" items="${FAQs}">
						${FAQ.title}
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</section>

<%@ include file="../part/mainLayoutFooter.jspf"%>