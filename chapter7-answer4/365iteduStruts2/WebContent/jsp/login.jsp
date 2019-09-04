<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<body>
		<h1>
			Login
		</h1>
		<form action="login.action" method="post">
			name:<input type="text" name="name">
			password:<input type="password" name="password">
			<s:submit id="loginSubmit" method="login" type="submit" value="登录"></s:submit>
		</form>

	</body>
</html>
