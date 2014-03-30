<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List of iShop products</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ishop.css" media="screen" />
</head>
<body>

<div style="width: 100%;">
<div style="text-align: center; margin-top: 100px; color: #0101DF; font-size: 1.5em">List of <b>iShop</b> products:</div>
<br/>
<table class="table" border="1">
	<tr>
		<td>
			<div style="padding: 5px 2px 5px 2px">
			<c:forEach items="${groupsList}" var="group" varStatus="vs">
				<c:if test="${!vs.first}"><hr/></c:if>
				<div>${group.group_name}</div>
			</c:forEach>
			</div>		
		</td>
		<td>
		</td>
	</tr>
</table>
</div>

</body>
</html>