// (1) 회원정보 수정
function update(userId, event) {
	event.preventDefault(); // 폼태그 액션을 막기
	// 여기서 EL을 바로 쓸 수 있는 이유는 header.jsp 에 추가해놨다 . 
	// #profileUpdate 는 update.jsp의 폼태그 id 이다. 폼태그를 찾아서 폼태그가 들고있는 모든 info값들을 serialize 하면 다 담김
	let data = $("#profileUpdate").serialize();
	
	console.log(data);
	
	$.ajax({
		type:"put",
		url:`/api/user/${userId}`,    
		data:data,
		contentType:"application/x-www-form-urlencoded; charset=urf-8",
		dataType:"json"
	}).done(res=>{ // HttpStatus 상태코드 200번대
		console.log("성공", res);
		location.href=`/user/${userId}`;
	}).fail(error=>{ // HttpStatus 상태코드 200번대가 아닐때	
		if(error.data==null) {
			alert(error.responseJSON.message);
		} else {
			alert(JSON.stringify(error.responseJSON.data)); //stringify 를 쓰면 자바스크립트 오브젝트를 JSON 문자열로 변경해줌 
		}
	});
}