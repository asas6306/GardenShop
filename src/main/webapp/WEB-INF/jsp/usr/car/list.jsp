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
				${car.name}
			</c:forEach>
		</div>
	</div>
</section>

<%@ include file="../part/mainLayoutFooter.jspf"%>