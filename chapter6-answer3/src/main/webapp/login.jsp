<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%--meta标签区域--%>
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="author" content="">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<meta http-equiv="imagetoolbar" content="no">
<meta name="robots" content="all">

<%--标题区域--%>
<title>登录页面</title>

<%--资源引入区域--%>
<link href="./css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/login.js"></script>
</head>
<body>
    <form class="focus" id="sys0101S01Form" name="sys0101S01Form" action="sys0101S01"  namespace="/sys" onsubmit="checkDoubleSubmit()">
        <div class="container">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="loginTable">
                <tr>
                    <td height="74" colspan="3" align="center" valign="middle"></td>
                </tr>
                <tr>
                <%--消息区域--%>
                    <td height="90" colspan="3" width="80%" align="center" valign="middle"></td>
                </tr>
                <tr>
                    <td width="120" height="38" align="right" valign="middle"><font color="#FFFFFF" size="+1">账号：</font></td>
                    <td width="150" align="left" valign="middle"><input  type="text" class="input_txt" id="sys0101S01Form_userId"  maxlength="64" size="64" tabindex="1" /></td>
                </tr>
                <tr>
                    <td height="38" align="right" valign="middle"><font color="#FFFFFF" size="+1">密&nbsp;&nbsp;&nbsp;&nbsp;码：&nbsp;</font></td>
                    <td align="left" valign="middle"><input  type="password" class="input_txt" id="sys0101S01Form_pwd" maxlength="32" size="32" tabindex="2" /></td>
                </tr>
                <tr>
                    <td height="38" align="right" valign="middle"><font color="#FFFFFF" size="+1">验证码：&nbsp;</font></td>
                    <td align="left" valign="middle"><input  type="text" class="input_txt" id="sys0101S01Form_validateCd" name="sys0101S01Form.validateCd" maxlength="4" size="8" tabindex="3" /></td>
                </tr>
            </table>

            <div id="login_submit_btn">
            </div>

        </div>
    </form>

    <div class="validateCode" style="cursor: pointer; display: none;" onclick="changeCode();">
        <table width="100%" border="1" cellspacing="0" cellpadding="0">
            <tr>
                <td height="60"><img src="./servlet/getValidateImageCode" id="imgValidateCode" /></td>
            </tr>
            <tr>
                <td align="center" valign="middle" height="18" style="color: blue">看不清，点图片换一张</td>
            </tr>
        </table>
    </div>
    <%--页脚区域--%>
</body>

</html>
