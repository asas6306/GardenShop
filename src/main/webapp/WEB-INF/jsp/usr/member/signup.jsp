<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../part/mainLayoutHeader.jspf"%>

<!-- debounce 사용을 위한 스크립트 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.21/lodash.min.js"></script>
<!-- sha256 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>

<script>
const SignupForm__checkAndSubmitDone = false;

//로그인 아이디 중복체크 함수
let SignupForm__validID = '';
function SignupForm__checkIDDup() {
	const form = $('.formLogin').get(0);

	form.ID.value = form.ID.value.trim();

	if (form.ID.value.length == 0) {
		return;
	}

	$.get('getLoginIdDup', {
		ID : form.ID.value
	}, function(data) {
		let colorClass = 'text-green-500';

		if (data.fail) {
			colorClass = 'text-red-500';
		}

		$('.IDInputMsg').html(
				"<span class='" + colorClass + "'>" + data.msg);

		if (data.fail) {
			form.ID.focus();
		} else {
			SignupForm__validID = data.body.ID;
		}
	}, 'json');
}

$(function() {
	$('.inputLoginId').keyup(_.debounce(SignupForm__checkIDDup, 500));
});

let SignupForm__validNickname = '';
function SignupForm__checkNicknameDup() {
	const form = $('.formLogin').get(0);

	form.nickname.value = form.nickname.value.trim();

	if (form.nickname.value.length == 0) {
		return;
	}

	$.get('getNicknameDup', {
		nickname : form.nickname.value
	}, function(data) {
		let colorClass = 'text-green-500';

		if (data.fail) {
			colorClass = 'text-red-500';
		}

		$('.nicknameInputMsg').html(
				"<span class='" + colorClass + "'>" + data.msg);

		if (data.fail) {
			form.nickname.focus();
		} else {
			SignupForm__validNickname = data.body.nickname;
		}
	}, 'json');
}

$(function() {
	$('.inputNickname').keyup(_.debounce(SignupForm__checkNicknameDup, 500));
});

let SignupForm__validEmail = '';
function SignupForm__checkEmailDup() {
	const form = $('.formLogin').get(0);

	form.email.value = form.email.value.trim();

	if (form.email.value.length == 0) {
		return;
	}
	
	$.get('getEmailDup', {
		email : form.email.value
	}, function(data) {
		let colorClass = 'text-green-500';

		if (data.fail) {
			colorClass = 'text-red-500';
		}

		$('.emailInputMsg').html(
				"<span class='" + colorClass + "'>" + data.msg);

		if (data.fail) {
			form.email.focus();
		} else {
			SignupForm__validEmail = data.body.email;
		}
	}, 'json');
}

$(function() {
	$('.inputEmail').keyup(_.debounce(SignupForm__checkEmailDup, 500));
});

let SignupForm__validPhoneNo = '';
function SignupForm__checkPhoneNoDup() {
	const form = $('.formLogin').get(0);
	
	form.phoneNo.value = form.phoneNo.value.trim();

	if (form.phoneNo.value.length == 0) {
		return;
	}
	
	$.get('getPhoneNoDup', {
		phoneNo : form.phoneNo.value
	}, function(data) {
		let colorClass = 'text-green-500';

		if (data.fail) {
			colorClass = 'text-red-500';
		}
		
		$('.phoneNoInputMsg').html(
				"<span class='" + colorClass + "'>" + data.msg);

		if (data.fail) {
			form.phoneNo.focus();
		} else {
			SignupForm__validPhoneNo = data.body.phoneNo;
		}
	}, 'json');
}

$(function() {
	$('.inputPhoneNo').keyup(_.debounce(SignupForm__checkPhoneNoDup, 500));
});
</script>

<script>
let SignupForm__validPW = '';
function SignupForm__checkPWDup() {
	const form = $('.formLogin').get(0);

	form.PWInput.value = form.PWInput.value.trim();

	if (form.PWInput.value.length == 0) {
		return;
	}

	$.get('getPWDup', {
		PW : form.PWInput.value
	}, function(data) {
		let colorClass = 'text-green-500';

		if (data.fail) {
			colorClass = 'text-red-500';
		}

		$('.PWInputMsg').html(
				"<span class='" + colorClass + "'>" + data.msg);

		if (data.fail) {
			form.PWInput.focus();
		} else {
			SignupForm__validPW = data.body.PWInput;
		}
	}, 'json');
}

$(function() {
	$('.inputPW').keyup(_.debounce(SignupForm__checkPWDup, 500));
});
</script>

<script>
function SignupForm__checkPWCheckDup() {
	const form = $('.formLogin').get(0);

	form.PWInput.value = form.PWInput.value.trim();
	form.PWCheck.value = form.PWCheck.value.trim();

	if (form.PWInput.value.length == 0) {
		return;
	}
	if (form.PWCheck.value.length == 0) {
		return;
	}
	
	let colorClass = 'text-red-500';
	if ( form.PWInput.value != form.PWCheck.value ) {
		$('.PWCheckInputMsg').html(
				"<span class='" + colorClass + "'>" + "비밀번호가 일치하지 않습니다");
		form.PWCheck.focus();
	} else {
		$('.PWCheckInputMsg').html('');
	}
}

$(function() {
	$('.inputPWCheck').change(function() {
		SignupForm__checkPWCheckDup();
	});
});
</script>

<script>
MemberSignup__submited = false;
function MemberSignup__checkAndSubmit(form) {
	if ( MemberSignup__submited ) {
		alert('처리중입니다.');
		return;
	}
	if ( form.ID.value.length == 0 ) {
		alert('아이디를 입력해주세요.');
		form.ID.focus();
		return false;
	}
	if( form.PWInput.value.length == 0 ) {
		alert('비밀번호를 입력해주세요');
		form.PWInput.focus();
		return false;
	}
	if( form.PWCheck.value.length == 0 ) {
		alert('비밀번호 확인을 입력해주세요');
		form.PWCheck.focus();
		return false;
	}
	if( form.PWInput.value != form.PWCheck.value ){
		alert('비밀번호가 일치하지 않습니다.');
		form.PWCheck.focus();
		return false;
	}
	if( form.nickname.value.length == 0 ) {
		alert('닉네임을 입력해주세요');
		form.nickname.focus();
		return false;
	}
	if( form.name.value.length == 0 ) {
		alert('이름을 입력해주세요');
		form.name.focus();
		return false;
	}
	if( form.email.value.length == 0 ) {
		alert('이메일을 입력해주세요');
		form.email.focus();
		return false;
	}
	if( form.phoneNo.value.length == 0 ) {
		alert('연락처를 입력해주세요');
		form.phoneNo.focus();
		return false;
	}
	
	form.PW.value = sha256(form.PWInput.value);
	form.PWInput.value = '';
	form.PWCheck.value = '';
	
	form.submit();
	MemberSignup__submited = true;
}
</script>

<section class="flex justify-center">
	<div class="container p-4" style="width: 500px;">
		<div class="border-2 border-blue-300 rounded-xl p-5">
			<form enctype="multipart/form-data" onsubmit="MemberSignup__checkAndSubmit(this); return false;" action="doSignup" method="post" class="formLogin" >
				<input type="hidden" name="afterUri" value="${param.afterUri}" >
				<input type="hidden" name="PW">
				<div class="text-gray-900 text-xl">
					<div class="flex justify-center">
						<span class="text-4xl">회원가입</span>
					</div>
					<div class="mt-2">
						<input type="text" name="ID" placeholder="아이디" autocomplete="off" class="inputLoginId border-2 rounded w-full p-2 hover:border-blue-300" />
					</div>
					<div class="IDInputMsg text-sm text-center"></div>
					<div class="my-2">
						<input type="password" name="PWInput" placeholder="비밀번호" class="inputPW border-2 rounded w-full p-2 hover:border-blue-300" />
					</div>
					<div class="PWInputMsg text-sm text-center"></div>
					<div class="my-2">
						<input type="password" name="PWCheck" placeholder="비밀번호 확인" class="inputPWCheck border-2 rounded w-full p-2 hover:border-blue-300" />
					</div>
					<div class="PWCheckInputMsg text-sm text-center"></div>
					<div class="form-control text-sm">
		                <label class="label">
		                    프로필 이미지
		                </label>
		                <input type="file" accept="image/gif, image/jpeg, image/png" name="file__member__0__common__profile__0" placeholder="프로필 이미지를 선택해주세요." />
		            </div>
					<div class="my-2">
						<input type="text" name="nickname" placeholder="닉네임" autocomplete="off" class="inputNickname border-2 rounded w-full p-2 hover:border-blue-300" />
					</div>
					<div class="nicknameInputMsg text-sm text-center"></div>
					<div class="my-2">
						<input type="text" name="name" placeholder="이름" autocomplete="off" class="border-2 rounded w-full p-2 hover:border-blue-300" />
					</div>
					<div class="my-2">
						<input type="email" name="email" placeholder="이메일" autocomplete="off" class="inputEmail border-2 rounded w-full p-2 hover:border-blue-300" />
					</div>
					<div class="emailInputMsg text-sm text-center"></div>
					<div class="my-2">
						<input type="tel" name="phoneNo" placeholder="연락처" autocomplete="off" class="inputPhoneNo border-2 rounded w-full p-2 hover:border-blue-300" />
					</div>
					<div class="phoneNoInputMsg text-sm text-center mb-2"></div>
					<div class="flex">
						<input type="submit" value="가입" class="h-12 w-full hover:bg-blue-300 border mr-1 rounded"/>
						<input type="button" value="취소" onclick="history.back()" class="h-12 w-full hover:bg-red-300 ml-1 rounded"/>
					</div>
				</div>
			</form>
		</div>
	</div>
</section>


<%@ include file="../part/mainLayoutFooter.jspf"%>