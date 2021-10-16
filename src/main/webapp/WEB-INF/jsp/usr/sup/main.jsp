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
					<div class="flex items-center px-2 gap-2">
						<input type="button" value="1:1 문의" class="py-1 px-2 cursor-pointer bg-blue-300 hover:bg-blue-500" onclick="location.href='inquire'" />
						<input type="button" value="작성하기" class="p-1 cursor-pointer bg-blue-300 hover:bg-blue-500" onclick="location.href='addFAQ'" />
					</div>
				</div>
				<div class="flex px-8 gap-3 border-t-2 border-b-2">
					<c:choose>
						<c:when test="${param.group == null}">
							<a href="main?group=${group.group}" class="text-blue-500">[전체보기]</a>
						</c:when>
						<c:otherwise>
							<a href="main">[전체보기]</a>
						</c:otherwise>
					</c:choose>
					<c:forEach var="group" items="${groups}">
						<c:choose>
							<c:when test="${param.group == group.group}">
								<a href="main?group=${group.group}" class="text-blue-500">[${group.group}]</a>
							</c:when>
							<c:otherwise>
								<a href="main?group=${group.group}">[${group.group}]</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
				<div class="flex p-1">
					<div class="FAQ-board w-full">
						<div class="flex justify-center">
							<c:if test="${FAQs.size() == 0}">
								<span>등록된 게시물이 없습니다.</span>
							</c:if>
						</div>
						<c:forEach var="FAQ" items="${FAQs}">
							<div class="FAQ px-4 border-b-2 border-blue-300">
								<div class="flex py-2">
									<span class="w-20 text-center">[${FAQ.group}]</span>
									<span class="px-2" class="" onclick="show_body(this);">${FAQ.title}</span>
								</div>
								<div class="FAQBody flex w-full hidden">
									<span class="bg-gray-100 w-full px-4 py-2">${FAQ.body}</span>
								</div>
							</div>
							<script>
								function show_body(btn) {
									const $clicked = $(btn);
									const target1 = $clicked.closest('.FAQ-board').find('.FAQBody');
									const target2 = $clicked.closest('.FAQ').find('.FAQBody');
									target1.css('display', 'none');
									target2.css('display', 'flex');
								}
							</script>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="h-4"></div>
			<div class="border-2 border-gray-300">
				<div class="w-full text-center text-4xl p-4">찾아오시는 길</div>
				<div class="border">
					<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=yfaodwt4aq"></script>
					<div id="map" style="width:100%;height:400px;"></div>

					<script>
						var HOME_PATH = window.HOME_PATH || '.';
	
						var company = new naver.maps.LatLng(36.28844821350354, 127.24170138246308),
						    map = new naver.maps.Map('map', {
						        center: company.destinationPoint(0, 15),
						        zoom: 19
						    }),
						    marker = new naver.maps.Marker({
						        map: map,
						        position: company
						    });
	
						var contentString = [
						        '<div class="p-6">',
						        '   <h3 class="text-2xl font-bold">엄사사거리</h3>',
						        '   <p>충남 계룡시 엄사면 엄사리 | 충남 계룡시 엄사면 엄사중앙로<br />',
						        '       02-120 | 개인기업 &gt; 사무실<br />',
						        '       <a href="http://localhost:8023/" target="_blank">http://localhost:8023/</a>',
						        '   </p>',
						        '</div>'
						    ].join('');
	
						var infowindow = new naver.maps.InfoWindow({
						    content: contentString
						});
	
						naver.maps.Event.addListener(marker, "click", function(e) {
						    if (infowindow.getMap()) {
						        infowindow.close();
						    } else {
						        infowindow.open(map, marker);
						    }
						});
	
						infowindow.open(map, marker);
					</script>
				</div>
			</div>
		</div>
	</div>
</section>

<%@ include file="../part/mainLayoutFooter.jspf"%>