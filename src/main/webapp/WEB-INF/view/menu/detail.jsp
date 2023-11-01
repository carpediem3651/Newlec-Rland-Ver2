
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!-- --------------------------------------- -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<dl>
		<dt>메뉴이름:</dt>
		<dd>${m.korName}(${m.engName})</dd>
		<dt>가격:</dt>
		<dd>${m.price}</dd>
		<dt>이미지:</dt>
		<dd>coffee.png</dd>
	</dl>
	<hr>
	<%-- <dl>
		<dt>메뉴이름:</dt>
		<dd><%=m.getKorName() %>(<%=m.getEngName() %>)</dd>
		<dt>가격:</dt>
		<dd><%=m.getPrice() %></dd>
		<dt>이미지:</dt>
		<dd>coffee.png</dd>
	</dl> --%>
	<a href="list">목록</a>
</body>
</html>