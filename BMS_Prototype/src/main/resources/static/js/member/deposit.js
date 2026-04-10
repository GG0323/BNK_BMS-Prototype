function deposit(){
	const fm = document.querySelector('#depo');
	fetch('/api/2/log', {method:'post', body: new FormData(fm)})
	.then(data => data.json())
	.then(data => {
		if(data) {
			alert('입금되었습니다.');
			location.href="/api/member/mypage";
		}
		else alert('잠시 후 다시 시도해주세요.');
	})
	.catch(e => alert(e));
}