<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*, com.mycompany.webapp.dto.*"%>
						
<%@ include file="/WEB-INF/views/common/header.jsp" %>

							<table class="table">
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>내용</th>
									<th>글쓴이</th>
								</tr>

								<c:forEach var="board" items="${list}">
									<tr>
										<td>${board.bno}</td>
										<td>${board.btitle}</td>
										<td>${board.bcontent}</td>
										<td>${board.bwriter}</td>
									</tr>
								</c:forEach>
							</table>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>