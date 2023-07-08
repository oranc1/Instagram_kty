// (1) 회원정보 수정
function update(userId) {
	
	// 여기서 EL을 바로 쓸 수 있는 이유는 header.jsp 에 추가해놨다 . 
	// #profileUpdate 는 update.jsp의 폼태그 id 이다. 폼태그를 찾아서 폼태그가 들고있는 모든 info값들을 serialize 하면 다 담김
	let data = $("#profileUpdate").serialize();
	
	console.log(data);
	
	$.ajax({
		type:"put",
		url:'/api/user/${userId}',
		data:data,
		contentType:"application/x-www-form-urlencoded;charset=utf-8",
		dataType:"json"
	}).done(res=>{
		console.log("update 성공");
	}).fail(error=>{
		console.log("update 실패");
	});
}