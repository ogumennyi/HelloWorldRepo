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
<table class="outer_table">
<tr><td valign="top">
<table class="inner_table" border="1">
	<tr>
		<th width="150px" style="padding: 1x 2px">Product group (<a href="${pageContext.request.contextPath}/ipage?p_name_order=${p_name_order}">all</a>)</th>
	</tr>
	<tr>
		<td align="center">
			<div style="padding: 5px 2px">
				<c:forEach items="${groupsList}" var="group" varStatus="vs">
					<c:if test="${!vs.first}"><hr/></c:if>
					<div>
						<a href="${pageContext.request.contextPath}/ipage/${group.group_id}?p_name_order=${p_name_order}">
						<c:choose>
							<c:when test="${group.group_id == current_group_id}">
								<b>${group.group_name}</b>
							</c:when>
							<c:otherwise>
								${group.group_name}
							</c:otherwise>
						</c:choose>
						</a>
					</div>
				</c:forEach>
			</div>		
		</td>
	</tr>
</table>
</td>
<td valign="top">
<table class="inner_table" border="1" style="margin-left: 30px;">
	<tr>
		<th width="500px">
			<c:if test="${'asc' eq p_name_order}"><img src="${pageContext.request.contextPath}/images/arrow_asc.png"/>&nbsp;</c:if>
			<c:if test="${'desc' eq p_name_order}"><img src="${pageContext.request.contextPath}/images/arrow_desc.png"/>&nbsp;</c:if>
			<a href="${pageContext.request.contextPath}/ipage/${current_group_id}?p_name_order=${p_name_order_n}">Product name</a>
		</th>
		<th width="100px">Product price</th>
	</tr>
	<tr>
		<c:choose>
			<c:when test="${empty productsList}">
				<td colspan=2 align="center"><div style="padding: 5px 2px">No products in this group</div></td>
			</c:when>
			<c:otherwise>
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
							<c:if test="${vs.count == 0}"><div>No products found</div></c:if>
							<c:if test="${!vs.first}"><hr/></c:if>
							<div>${product.product_price}</div>
						</c:forEach>
					</div>
				</td>
			</c:otherwise>
		</c:choose>
	</tr>
</table>
</td></tr>
</table>
</div>

</body>
</html>