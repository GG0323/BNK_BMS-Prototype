function login(){
	const fm = document.querySelector('form');
	fetch('/login', {method:'post', body: new FormData(fm)})
	.then(data => data.json())
	.then(data => {
		if(data.result){
			alert('로그인 되었습니다.');
			location.href= "/api/" + (data.role == "ADMIN" ? "admin" : "member") + "/mypage";
		}
	})
	.catch(e => alert(e));
}