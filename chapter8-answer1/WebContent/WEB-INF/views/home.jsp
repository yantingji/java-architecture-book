<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<style type="text/css">
.error-messages {
    color: #b94a48;
    display: block;
    padding-left: 5px;
    overflow-x: auto;
}

</style>
<body>
	<h1>Hello world!</h1>

	<P>${hello}</p>

	<form:form action="user"  modelAttribute="userForm"  method="post">
		UserName:<form:input  path="userName" /><br> 
		Phone:<form:input  path="phone" /><br> 
		 <form:errors path="phone"
            class="error-messages" />
		<input type="submit" value="Login">
	</form:form>
</body>
</html>