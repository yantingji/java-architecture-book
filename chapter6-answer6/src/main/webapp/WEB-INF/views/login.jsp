<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form action="login" method="post" modelAttribute="userForm">
		<label>账号：</label>
		<input type="text" id="username" />
		<br />
		<label>密码：</label>
		<input type="password" id="password" />
		<br />
		<table class="table_base" cellpadding="0" cellspacing="0">
			<tr>
				<td class="td1">系统状態</td>
				<td class="td2_2"><form:select path="systemStatus">
						<form:option value="" label="--Select--" />
						<form:options items="${CL_USER_SYSTEM_STATUS}" />
					</form:select></td>
			</tr>
		</table>
		<input type="submit" value="提交" />
		<input type="reset" value="重置" />
	</form:form>
</body>
</html>