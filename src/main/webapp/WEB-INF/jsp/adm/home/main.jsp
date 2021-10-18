<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.example.demo.util.Util"%>
<%@ include file="../part/mainLayoutHeader.jspf"%>

<section class="main-setting flex justify-center">
	<div class="flex justify-center container">
		<div class="max-w-5xl">
			<div>
				<div class="flex justify-center">
					<span class="text-2xl p-2">판매량</span>
				</div>
				<div class="mx-2 py-2 border-2 border-blue-300 text-center">
					<c:forEach var="car" items="${cars}" >
						<div class="grid grid-cols-3 gap-4">
							<span>${Util.numberFormat(car.extra.num)}</span>
							<span>${car.name}</span>
							<span>${car.sales}</span>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</section>

<%@ include file="../part/mainLayoutFooter.jspf"%>