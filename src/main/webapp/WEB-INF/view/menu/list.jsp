<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>메뉴목록<%=application.getAttribute("a") %> / ${test}</h1>
	<ul>
	<c:forEach var="m" items="${list}">		
		<li><a href="detail?id=${m.id}">${m.korName }</a>(${m.engName})</li>
	</c:forEach>
	<hr>
	
	<%-- <% 
		for(Menu m : list){
			pageContext.setAttribute("m", m);
	%>
		<li><a href="detail.jsp?id=${m.id}">${m.korName }</a>(<%=m.getEngName() %>)</li>
	<%} %> --%>
	</ul>
	<%-- <hr>
	<h1>메뉴목록<%=application.getAttribute("a") %></h1>
	<ul>
	<% for(Menu m : list){ %>
		<li><a href="detail.jsp?id=<%=m.getId()%>"><%=m.getKorName() %></a>(<%=m.getEngName() %>)</li>
	<%} %>
	</ul> --%>
</body>
</html>