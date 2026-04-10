let depositors = true;

function remit(){
	if(depositors){
		alert("예금자 확인을 해주세요");
		return;
	}
	const fm = document.querySelector('#remit');
	fetch('/api/2/log', {method:'post', body: new FormData(fm)})
		.then(data => data.json())
		.then(data => {
			if(data){
				alert('입금되었습니다.');
				location.href="/api/member/mypage";
			}
			else alert('잔액이 부족합니다.');
		})
		.catch(e => alert(e));
}

function check(){
	const ck = document.querySelector('#ck').value;
	fetch("/api/1/account?acn="+ck)
	.then(data=> data.json())
	.then(data=>{
		if(data){
			depositors = false;
			alert('확인되었습니다.')
		}else alert('계좌번호를 다시 확인해주세요.');
	})
}