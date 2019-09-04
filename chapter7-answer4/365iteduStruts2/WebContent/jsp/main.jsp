<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="i"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <body>
        <h1>
            Hello, <i:property value="name"></i:property>
            <br>
            <a href="redirectLogin.action">Go to Login page</a>
        </h1>
    </body>
</html>
