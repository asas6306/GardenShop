<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../part/mainLayoutHeader.jspf"%>

<section class="main-setting flex justify-center">
	<div class="flex justify-center container">
		<div class="max-w-5xl">
			<div class="main-banner">
				<img src="/gen/home/banner/banner__2.jpg" />
			</div>
			<div class="main-best-4 border">
				<div class="flex">
					<span class="w-full text-center text-4xl p-4"><i class="far fa-thumbs-up"></i> BEST</span>
				</div>
				<div class="grid grid-cols-4 p-1">
					<c:forEach var="car" items="${bestCar}">
						${car.name}
					</c:forEach>
				</div>
			</div>
			<div class="h-4"></div>
			<div class="main-recommend border">
				<div class="flex">
					<span class="w-full text-center text-4xl p-4"><i class="far fa-star"></i> RECOMMEND</span>
				</div>
				<div class="main-ad grid grid-cols-2 gap-4 p-1">
					<div class="border">
						<img src="/gen/home/ad/ad__1.jpg" class="" />
						<div class="p-4">
							<div class="text-2xl font-bold">2021 GRANDURE</div>
							<span class="text-xl font-bold">33,030,000</span>
							<span>원 부터</span>
						</div>
					</div>
					<div class="border">
						<img src="/gen/home/ad/ad__2.jpg" class="" />
						<div class="p-4">
							<div class="text-2xl font-bold">2021 SANTAFE</div>
							<span class="text-xl font-bold">29,750,000</span>
							<span>원 부터</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<%@ include file="../part/mainLayoutFooter.jspf"%>