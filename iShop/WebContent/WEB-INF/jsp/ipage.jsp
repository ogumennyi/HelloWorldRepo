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
		<th width="150px" style="padding: 1x 2px">Product group (<a href="${pageContext.request.contextPath}/ipage?p_name_order=${p_name_order}&p_price_order=${p_price_order}">all</a>)</th>
	</tr>
	<tr>
		<td align="center">
			<div style="padding: 5px 2px">
				<c:forEach items="${groupsList}" var="group" varStatus="vs">
					<c:if test="${!vs.first}"><hr/></c:if>
					<div>
						<a href="${pageContext.request.contextPath}/ipage?group_id=${group.group_id}&p_name_order=${p_name_order}&p_price_order=${p_price_order}">
						<c:choose>
							<c:when test="${group.group_id == group_id}">
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
			<a href="${pageContext.request.contextPath}/ipage?group_id=${group_id}&page=${page}&p_name_order=${p_name_order_n}&p_price_order=${p_price_order}">Product name</a>
		</th>
		<th width="120px">
			<c:if test="${'asc' eq p_price_order}"><img src="${pageContext.request.contextPath}/images/arrow_asc.png"/>&nbsp;</c:if>
			<c:if test="${'desc' eq p_price_order}"><img src="${pageContext.request.contextPath}/images/arrow_desc.png"/>&nbsp;</c:if>
			<a href="${pageContext.request.contextPath}/ipage?group_id=${group_id}&page=${page}&curr_order_by=p_name_order&p_name_order=${p_name_order}&p_price_order=${p_price_order_n}">Product price</a>
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
	<tr>
		<td colspan=2 align="center" style="font-size: 0.8em">
			<div style="display: table-cell; vertical-align: bottom">
				<c:choose>
					<c:when test="${page==1}">
						<img src="${pageContext.request.contextPath}/images/dbl_arr_l_d.png" align="middle" title="First page"/>
						<img src="${pageContext.request.contextPath}/images/arr_l_d.png" align="middle" title="Previous page"/>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/ipage?group_id=${group_id}&page=${1}&p_name_order=${p_name_order}&p_price_order=${p_price_order}">
							<img src="${pageContext.request.contextPath}/images/dbl_arr_l.png" align="middle" title="First page"/></a>
						<a href="${pageContext.request.contextPath}/ipage?group_id=${group_id}&page=${page-1}&p_name_order=${p_name_order}&p_price_order=${p_price_order}">
							<img src="${pageContext.request.contextPath}/images/arr_l.png" align="middle" title="Previous page"/></a>
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
								<c:when test="${pagenum eq page or pagenum eq '0'}">
									<span style="padding: 0px 5px"><b>${pagenum}</b></span>
								</c:when>
								<c:otherwise>
									<span style="padding: 0px 5px; cursor: pointer; text-decoration: underline">
										<a href="${pageContext.request.contextPath}/ipage?group_id=${group_id}&page=${pagenum}&p_name_order=${p_name_order}&p_price_order=${p_price_order}">${pagenum}</a>
									</span>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
			<div style="display: table-cell; vertical-align: bottom">
				<c:choose>
					<c:when test="${page==maxPageNum or maxPageNum==0}">
						<img src="${pageContext.request.contextPath}/images/arr_r_d.png" align="middle" title="Previous page"/>
						<img src="${pageContext.request.contextPath}/images/dbl_arr_r_d.png" align="middle" title="First page"/>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/ipage?group_id=${group_id}&page=${page+1}&p_name_order=${p_name_order}&p_price_order=${p_price_order}">
							<img src="${pageContext.request.contextPath}/images/arr_r.png" align="middle" title="Previous page"/></a>
						<a href="${pageContext.request.contextPath}/ipage?group_id=${group_id}&page=${maxPageNum}&p_name_order=${p_name_order}&p_price_order=${p_price_order}">
							<img src="${pageContext.request.contextPath}/images/dbl_arr_r.png" align="middle" title="First page"/></a>
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