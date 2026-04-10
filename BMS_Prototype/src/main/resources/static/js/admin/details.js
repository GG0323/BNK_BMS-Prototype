function toggleAc(id, state){
	fetch("/api/3/member/" + id + "/" + state, {method:"put"})
	.then(data => data.json())
	.then(data =>{
		if(data){
			if(state) alert('동결되었습니다.');
			else alert('해제되었습니다.');
			location.reload();
		}
	})
	.catch(e => alert(e));
}