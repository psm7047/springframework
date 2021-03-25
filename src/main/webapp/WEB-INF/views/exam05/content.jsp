<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*, com.mycompany.webapp.dto.*"%>
						
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<script>
	$(function() {
		getList(1);
	});
	
	const getList = (pageNo) => {
		const args = {url: "list",method: "get"};
		if(pageNo) {
			args.data = {pageNo};
		}
		$.ajax(args).then(data => {
			$("#board").html(data);
		});
	};
	
	const read = (bno) => {
		$.ajax({
			url: "read",
			data: {bno},
			method: "get"
		}).then(data => {
				$("#board").html(data);
		});
	};
	
	const updateForm = (bno) => {
		$.ajax({
			url: "updateForm",
			data: {bno},
			method: "get"
		}).then(data => {
				$("#board").html(data);
		});
	};
	
	const update = (bno) => {
		event.preventDefault();		//폼태그의 기본 섭밋 기능을 제거
		const btitle = $("#btitle").val();
		const bcontent = $("#bcontent").val();
		$.ajax({
			url: "update",
			data: {bno, btitle, bcontent},
			method: "post"
		}).then(data => {
			if(data.result == "success") {
				getList(1);
				}
		});
	};
	
	const deleteBoard = (bno) => {
		$.ajax({
			url: "delete",
			data: {bno},
			method: "get"
		}).then(data => {
			if(data.result == "success") {
				getList(1);
				}
		});
	};
	
	const createForm = () => {
		$.ajax({
			url: "createForm",
			method: "get"
		}).then(data => {
				$("#board").html(data);
		});
	};
	
	const create = () => {
		event.preventDefault();
		
		const btitle = $("#btitle").val();
		const bcontent = $("#bcontent").val();
		const battach = $("#battach")[0].files[0];
		
		const formData = new FormData();	//multipart/form-data로 데이터 형식을 생성
		formData.append("btitle",btitle);
		formData.append("bcontent",bcontent);
		
		if(battach) {
			formData.append("battach", battach);
		}
		
		$.ajax({
			url: "create",
			data: formData,
			method: "post",
			cache: false,		//캐싱하지 않음
			processData: false,		//데이터 그대로 보냄
			contentType: false	
		}).then(data => {
			if(data.result == "success") {
				getList(1);
				}
		});
	};
</script>

	<div>
		<div class="alert alert-primary">
			AJAX를 이용한 게시판
		</div>
		
		<div id="board">
		</div>
	</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>