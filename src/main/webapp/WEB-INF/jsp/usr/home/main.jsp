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
				<div class="flex">
					<span class="w-full text-center text-4xl p-4"><i class="far fa-thumbs-up"></i> BEST</span>
				</div>
				<div class="grid grid-cols-4 p-1">
					<c:forEach var="car" items="${bestCar}">
						<c:set var="fileNo" value="${String.valueOf(0)}"></c:set>
						<c:set var="carName" value="${car.name}"></c:set>
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
			</div>
			<div class="h-4"></div>
			<div class="main-recommend border-2 border-gray-300">
				<div class="flex">
					<span class="w-full text-center text-4xl p-4"><i class="far fa-star"></i> RECOMMEND</span>
				</div>
				<div class="main-ad grid grid-cols-2 gap-4 p-1">
					<c:forEach var="car" items="${recommendCar}">
						<div class="border">
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
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</section>

<%@ include file="../part/mainLayoutFooter.jspf"%>