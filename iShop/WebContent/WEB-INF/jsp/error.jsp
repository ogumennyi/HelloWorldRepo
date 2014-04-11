<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Welcome to iShop</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ishop.css" media="screen" />
</head>
<body>
	<div style="width: 100%; margin-top: 300px; text-align: center; font-size: 1.4em">
	<div>There were some mistakes with the request parameters :(</div>
	<br/>
	<a href="<c:url value="/ipage"/>" style="color: #0101DF"><i>Get back to <b>iShop</b> and try again</i></a>
	</div>
</body>
</html>