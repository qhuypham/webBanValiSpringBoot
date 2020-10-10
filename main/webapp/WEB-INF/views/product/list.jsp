<%@ page pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:forEach var="p" items="${list}">
	<div class="col-sm-4">
		<div class="thumbnail pro-icon-wraper">
			<a href="/product/detail/${p.id}"> <img class="vali-pro" src="/static/images/products/${p.image}">
			</a>
			<div class="caption">
				<p>${p.name}</p>
				<div class="pull-right" data-id="${p.id}">
				<button class="btn btn-sm btn-danger btn-add-to-cart">
					<i class="glyphicon glyphicon-shopping-cart"></i>
				</button>
				<button class="btn btn-sm btn-warning btn-star">
					<i class="glyphicon glyphicon-star"></i>
				</button>
				<button class="btn btn-sm btn-success btn-open-dialog" data-toggle="modal" data-target="#myModal">
					<i class="glyphicon glyphicon-envelope"></i>
				</button>
				</div>
				<p>${p.unitPrice}</p>
			</div>
			<c:choose>
				<c:when test="${p.special}">
					<img class="pro-icon" src="/static/images/special-icon.png"	/>
				</c:when>
				<c:when test="${p.discount>0}">
					<img class="pro-icon" src="/static/images/sale-icon.jpg"/>
				</c:when>
				<c:when test="${p.available}">
					<img class="pro-icon" src="/static/images/new-icon.png"/>
				</c:when>
			</c:choose>
			
			 
		</div>
		
	</div>

</c:forEach>

<jsp:include page="dialog.jsp"></jsp:include>
