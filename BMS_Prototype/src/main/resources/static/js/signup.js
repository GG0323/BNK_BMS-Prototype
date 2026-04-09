function signup(){
	const fm = document.querySelector('form');
	fetch("/member", {method:'post', body: new FormData(fm)})
	.then(data => data.json())
	.then(data => {
		if(data){
			alert('정상적으로 가입되었습니다.\n로그인 페이지로 이동합니다.');
			location.href="/loginForm";
		}else alert('가입 실패..');
	})
	.catch(e=> alert(e));
}