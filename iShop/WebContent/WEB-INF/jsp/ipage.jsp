<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List of iShop products</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/ishop.css"/>" media="screen" />
</head>
<body>

<div style="width: 100%;">
<div style="text-align: center; margin-top: 100px; color: #0101DF; font-size: 1.5em">
	<b>List of <i>iShop</i> products:</b>
</div>

<br/>

<table class="outer_table">
<tr><td valign="top">
<table class="inner_table" border="1">
	<tr>
		<c:url value="/ipage" var="allProductsURL">
			<c:param name="nameOrder" value="${params.nameOrder}"/>
			<c:param name="priceOrder" value="${params.priceOrder}"/>
		</c:url>
		<th width="150px" style="padding: 1x 2px">Product group (<a href="${allProductsURL}">all</a>)</th>
	</tr>
	<tr>
		<td align="center">
			<div style="padding: 5px 2px">
				<c:forEach items="${groupsList}" var="group" varStatus="vs">
					<c:if test="${!vs.first}"><hr/></c:if>
					<div>
						<c:url value="/ipage" var="groupURL">
							<c:param name="groupId" value="${group.groupId}"/>
							<c:param name="nameOrder" value="${params.nameOrder}"/>
							<c:param name="priceOrder" value="${params.priceOrder}"/>
						</c:url>
						<a href="${groupURL}">
						<c:choose>
							<c:when test="${group.groupId == params.groupId}">
								<b>${group.groupName}</b>
							</c:when>
							<c:otherwise>
								${group.groupName}
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
			<c:set var="nameOrderNext" value="${(empty params.nameOrder)?'asc':(params.nameOrder eq 'asc')?'desc':''}"/>
			<c:if test="${'asc' eq params.nameOrder}"><img src="<c:url value="/images/arrow_asc.png"/>"/>&nbsp;</c:if>
			<c:if test="${'desc' eq params.nameOrder}"><img src="<c:url value="/images/arrow_desc.png"/>"/>&nbsp;</c:if>
			<c:url value="/ipage" var="productNameOrderURL">
				<c:param name="groupId" value="${params.groupId}"/>
				<c:param name="page" value="${params.page}"/>
				<c:param name="nameOrder" value="${nameOrderNext}"/>
				<c:param name="priceOrder" value="${params.priceOrder}"/>
			</c:url>
			<a href="${productNameOrderURL}">Product name</a>
		</th>
		<th width="120px">
			<c:set var="priceOrderNext" value="${(empty params.priceOrder)?'asc':(params.priceOrder eq 'asc')?'desc':''}"/>
			<c:if test="${'asc' eq params.priceOrder}"><img src="<c:url value="/images/arrow_asc.png"/>"/>&nbsp;</c:if>
			<c:if test="${'desc' eq params.priceOrder}"><img src="<c:url value="/images/arrow_desc.png"/>"/>&nbsp;</c:if>
			<c:url value="/ipage" var="productPriceOrderURL">
				<c:param name="groupId" value="${params.groupId}"/>
				<c:param name="page" value="${params.page}"/>
				<c:param name="nameOrder" value="${params.nameOrder}"/>
				<c:param name="priceOrder" value="${priceOrderNext}"/>
			</c:url>
			<a href="${productPriceOrderURL}">Product price</a>
		</th>
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
							<div>${product.productName}</div>
						</c:forEach>
					</div>
				</td>
				<td>
					<div style="padding: 5px 2px">
						<c:forEach items="${productsList}" var="product" varStatus="vs">
							<c:if test="${vs.count == 0}"><div>No products found</div></c:if>
							<c:if test="${!vs.first}"><hr/></c:if>
							<div>${product.productPrice}</div>
						</c:forEach>
					</div>
				</td>
			</c:otherwise>
		</c:choose>
	</tr>
	<tr>
		<td colspan=2 align="center" style="font-size: 0.8em">
			<div style="display: table-cell; vertical-align: bottom">
				<c:choose>
					<c:when test="${params.page==1}">
						<img src="<c:url value="/images/dbl_arr_l_d.png"/>" align="middle" title="First page"/>
						<img src="<c:url value="/images/arr_l_d.png"/>" align="middle" title="Previous page"/>
					</c:when>
					<c:otherwise>
						<c:url value="/ipage" var="firstURL">
							<c:param name="groupId" value="${params.groupId}"/>
							<c:param name="page" value="1"/>
							<c:param name="nameOrder" value="${params.nameOrder}"/>
							<c:param name="priceOrder" value="${params.priceOrder}"/>
						</c:url>
						<a href="${firstURL}"><img src="<c:url value="/images/dbl_arr_l.png"/>" align="middle" title="First page"/></a>
						<c:url value="/ipage" var="leftPageURL">
							<c:param name="groupId" value="${params.groupId}"/>
							<c:param name="page" value="${params.page-1}"/>
							<c:param name="nameOrder" value="${params.nameOrder}"/>
							<c:param name="priceOrder" value="${params.priceOrder}"/>
						</c:url>
						<a href="${leftPageURL}"><img src="<c:url value="/images/arr_l.png"/>" align="middle" title="Previous page"/></a>
					</c:otherwise>
				</c:choose>
			</div>
			<div style="display: table-cell; vertical-align: bottom">
				<c:forEach items="${pagesList}" var="pagenum" varStatus="vs">
					<c:choose>
						<c:when test="${pagenum eq '...'}">
							<span style="padding: 0px 5px">...</span>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${pagenum eq params.page or pagenum eq '0'}">
									<span style="padding: 0px 5px"><b>${pagenum}</b></span>
								</c:when>
								<c:otherwise>
									<c:url value="/ipage" var="pageURL">
										<c:param name="groupId" value="${params.groupId}"/>
										<c:param name="page" value="${pagenum}"/>
										<c:param name="nameOrder" value="${params.nameOrder}"/>
										<c:param name="priceOrder" value="${params.priceOrder}"/>
									</c:url>
									<span style="padding: 0px 5px; cursor: pointer; text-decoration: underline">
										<a href="${pageURL}">${pagenum}</a>
									</span>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
			<div style="display: table-cell; vertical-align: bottom">
				<c:choose>
					<c:when test="${params.page==maxPageNum or maxPageNum==0}">
						<img src="<c:url value="/images/arr_r_d.png"/>" align="middle" title="Next page"/>
						<img src="<c:url value="/images/dbl_arr_r_d.png"/>" align="middle" title="Last page"/>
					</c:when>
					<c:otherwise>
						<c:url value="/ipage" var="nextURL">
							<c:param name="groupId" value="${params.groupId}"/>
							<c:param name="page" value="${params.page+1}"/>
							<c:param name="nameOrder" value="${params.nameOrder}"/>
							<c:param name="priceOrder" value="${params.priceOrder}"/>
						</c:url>
						<a href="${nextURL}"><img src="<c:url value="/images/arr_r.png"/>" align="middle" title="Next page"/></a>
						<c:url value="/ipage" var="lastURL">
							<c:param name="groupId" value="${params.groupId}"/>
							<c:param name="page" value="${maxPageNum}"/>
							<c:param name="nameOrder" value="${params.nameOrder}"/>
							<c:param name="priceOrder" value="${params.priceOrder}"/>
						</c:url>						
						<a href="${lastURL}"><img src="<c:url value="/images/dbl_arr_r.png"/>" align="middle" title="Last page"/></a>
					</c:otherwise>
				</c:choose>
			</div>
		</td>
	</tr>
</table>
</td></tr>
</table>
</div>

</body>
</html>