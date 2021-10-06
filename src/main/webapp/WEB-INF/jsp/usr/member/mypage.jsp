<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.example.demo.util.Util"%>
<%@ include file="../part/mainLayoutHeader.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.example.demo.util.Util"%>

<script>
function Delete__Articles__Confirm() {
	const result = confirm('정말로 삭제하시겠습니까?');
	if(result){
		onSuccess();
		return;
	}
}
</script>

<script>
function del__checkAll(btn) {
	if(btn.checked) {
		for(let i = 1; i <= 20; i++) {
			if(document.getElementsByName("delete__" + i)[0].getAttribute('type') == 'checkbox'){
	            document.getElementsByName("delete__" + i)[0].checked = true;
	        }
		}
	} else {
		for(let i = 1; i <= 20; i++) { 
			if(document.getElementsByName("delete__" + i)[0].getAttribute('type') == 'checkbox'){
	            document.getElementsByName("delete__" + i)[0].checked = false;
	        }
			//더 나은 방법은 ?
			//$(btn).closest('.mypage-width').find(' > input[name=delete__0]').removeAttr("checked");
			//$find(':input[name=delete__0]').removeAttr("checked");
			//.attr("checked", "checked")
		}
	}
}
</script>

<section class="base-higth flex justify-center">
	<div class="" style="width: 500px;">
		<div>
			<div class="flex justify-between">
				<span class="flex items-center justify-center h-20 text-4xl font-bold px-1">마이페이지</span>
				<div class="flex justify-center items-center">
					<a href="../member/authentication?afterUri=${Util.getUriEncoded('../member/update')}" class="p-1 h-7 rounded-full text-sm bg-blue-300 hover:bg-blue-500 flex-shrink-0">
						<i class="fas fa-user-edit"></i>
						<span>정보수정</span>
					</a>
				</div>
			</div>
			<div class="flex border-t-4 border-blue-500 rounded p-2">
				<div class="p-2">
					<img src="${rq.loginedMember.profileImgUri}" onerror="${rq.loginedMember.profileFallbackImgOnErrorHtmlAttr}" class="border w-32 h-32 sm:w-40 sm:h-40 rounded-full bg-gray-300">
				</div>
				<div class="p-2 text-xl">
					<div class="sm:flex text-4xl">
						<div>
							<span>${rq.loginedMember.ID}</span>
						</div>
						<div>
							<span>(${rq.loginedMember.nickname})</span>
						</div>
					</div>
					<div>
						<span>${rq.loginedMember.authName}</span>
					</div>
					<div>
						<span>${rq.loginedMember.email}</span>
					</div>
					<div>
						<span>${rq.loginedMember.phoneNo}</span>
					</div>
				</div>
			</div>
			<div class="p-4">
				<div class="flex justify-center text-xl">
					<div class="flex justify-between w-4/5 sm:w-2/3 md:w-3/5">
						<div class="text-center border-b border-blue-300 w-16 sm:w-32">장바구니</div>
						<div class="text-center border-b border-blue-300 w-16 sm:w-32">주문수량</div>
						<div class="text-center border-b border-blue-300 w-16 sm:w-32">구매수량</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<%@ include file="../part/mainLayoutFooter.jspf"%>