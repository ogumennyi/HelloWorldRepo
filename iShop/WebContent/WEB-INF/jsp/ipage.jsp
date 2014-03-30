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
<div style="text-align: center; margin-top: 100px; color: #0101DF; font-size: 1.5em"><b>List of <i>iShop</i> products:</b></div>
<br/>
<table class="outer_table"><tr><td>
<table class="inner_table" border="1">
	<tr>
		<th style="padding: 1x 2px">Product group</th>
	</tr>
	<tr>
		<td>
			<div style="padding: 5px 2px">
				<c:forEach items="${groupsList}" var="group" varStatus="vs">
					<c:if test="${!vs.first}"><hr/></c:if>
					<div>${group.group_name}</div>
				</c:forEach>
			</div>		
		</td>
	</tr>
</table>
</td><td>
<table class="inner_table" border="1">
	<tr>
		<th>Product name</th>
		<th>Product price</th>
	</tr>
	<tr>
		<td>
			<div style="padding: 5px 2px">
				<c:forEach items="${productsList}" var="product" varStatus="vs">
					<c:if test="${!vs.first}"><hr/></c:if>
					<div>${product.product_name}</div>
				</c:forEach>
			</div>
		</td>
		<td>
			<div style="padding: 5px 2px">
				<c:forEach items="${productsList}" var="product" varStatus="vs">
					<c:if test="${!vs.first}"><hr/></c:if>
					<div>${product.product_price}</div>
				</c:forEach>
			</div>
		</td>
	</tr>
</table>
</td></tr></table>
</div>

<!-- <table class="table" border="1">
	<tr>
		<th>Group</th>
		<th>Product name</th>
		<th>Product price</th>
	</tr>
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
			<div style="padding: 5px 2px 5px 2px">
				<c:forEach items="${productsList}" var="product" varStatus="vs">
					<c:if test="${!vs.first}"><hr/></c:if>
					<div>${product.product_name}</div>
				</c:forEach>
			</div>
		</td>
		<td>
			<div style="padding: 5px 2px 5px 2px">
				<c:forEach items="${productsList}" var="product" varStatus="vs">
					<c:if test="${!vs.first}"><hr/></c:if>
					<div>${product.product_price}</div>
				</c:forEach>
			</div>
		</td>
	</tr>
</table> -->
</div>

</body>
</html>