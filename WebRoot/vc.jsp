<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>验证码</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<form action="<%= path%>/search">
		验证码：<img alt="" src="http://gsxt.saic.gov.cn/zjgs/captcha?preset=&ra=0.030452584000915772">
		<input type="text" name="captcha"><br>
		<input type="text" name="keyword"><br>
		<input type="submit" value="搜索">
	</form>
	输入企业名称或者注册编号
</body>
</html>
