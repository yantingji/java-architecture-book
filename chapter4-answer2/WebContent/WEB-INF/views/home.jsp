<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>

	<P>${hello}</p>

	<form action="user" method="post">
		UserName:<input type="text" name="userName"><br> 
		Phone:<input type="text" name="phone"><br> 
		<input type="submit" value="Login">
	</form>
</body>
</html>