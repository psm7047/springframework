<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*, com.mycompany.webapp.dto.*"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div>
	<div class="alert alert-success">게시물 수정</div>
	<form method="post" action="update">
	<input type="hidden" name="bno" value="${board.bno}"/>
	  <div class="form-group">
	    <label for="uid">제목</label>
	    <input type="text" class="form-control" id="btitle" name="btitle" value="${board.btitle}">
	  </div>
	   <div class="form-group">
	    <label for="uname">내용</label>
	    <input type="text" class="form-control" id="bcontent" name="bcontent" value="${board.bcontent}">
	  </div>
	  <button type="submit" class="btn btn-primary">수정</button>
	  <a class="btn btn-primary" href="read?bno=${board.bno}">취소</a>
	</form>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>