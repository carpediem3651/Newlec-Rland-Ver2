<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	int size = 4;
	String size_ = request.getParameter("s");
	if(size_ != null)
		size = Integer.parseInt(size_);
	
	
	
	int type = 1;
	String type_ = request.getParameter("t");
	if(type_ != null)
		type = Integer.parseInt(type_);
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<hr>
<%for(int i=0; i<size; i++){ %>
	안녕~<br>
<%} %>
	
	Welcome 4 한글 <%= size  %>
	<%
	if(type==1)
	{ %>
		<hr>
		<h2>인삿말을 몇 번 듣고 싶으세요?</h2>
		<a href="/index.jsp?s=1">1번</a><br>
		<a href="/index.jsp?s=5">5번</a><br>
		<a href="/index.jsp?s=10">10번</a><br> <!--QueryString-->
		<a href="/index.jsp?s=100">100번</a>
   <%
	}
	else if(type==2)
	{
 %>
	<hr>
	<h2>인삿말을 몇 번 듣고 싶으세요?</h2>
	<form action="index.jsp">
		<label>갯수입력</label><input type="text" name="s">
		<input type="submit" value="요청">
	</form>
	<%} %>
	<hr>
	<a href="?t=1">객관식형</a>
	<a href="?t=2">주관식형</a>
</body>
</html>