<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>

<div class="row">
	<div class="col-sm-5 text-center">
		<img class="detail-img" src="/static/images/products/${prod.image}">
	</div>
	<div class="col-sm-7">
		<ul class="detail-info">
			<li>Name: ${prod.name}</li>
			<li>unitPrice: <f:formatNumber value="${prod.unitPrice}" pattern="#,###.00"/> </li>
			<li>productDate: <f:formatDate value="${prod.productDate}" pattern="dd-MM-yyyy"/> </li>
			<li>nameVN: ${prod.category.nameVN}</li>
			<li>quantity: ${prod.quantity}</li>
			<li>discount: <f:formatNumber value="${prod.discount}" type="percent" /> </li>
			<li>viewCount: ${prod.viewCount}</li>
			<li>available: ${prod.available?'Yes':'No'}</li>
			<li>special: ${prod.special?'Yes':'No'}</li>
		</ul>
	</div>
</div>

<div class="text-justify">${prod.description}</div>


<ul class="nav nav-tabs">
  <li class="active"><a data-toggle="tab" href="#tab1">Cung loai</a></li>
  <li><a data-toggle="tab" href="#tab2">Yeu thich</a></li>
  <li><a data-toggle="tab" href="#tab3">Da xem</a></li>
</ul>

<div class="tab-content">
  <div id="tab1" class="tab-pane fade in active">
    <div>
			<c:forEach var="p" items="${list}">
				<a href="/product/detail/${p.id}"> <img class="thumb-img"
					src="/static/images/products/${p.image}">
				</a>

			</c:forEach>
	</div>
  </div>
  <div id="tab2" class="tab-pane fade">
    <div>
			<c:forEach var="p" items="${favo}">
				<a href="/product/detail/${p.id}"> <img class="thumb-img"
					src="/static/images/products/${p.image}">
				</a>

			</c:forEach>
	</div>
  </div>
  <div id="tab3" class="tab-pane fade">
    <div>
			<c:forEach var="p" items="${viewed}">
				<a href="/product/detail/${p.id}"> <img class="thumb-img"
					src="/static/images/products/${p.image}">
				</a>

			</c:forEach>
	</div>
  </div>
</div>

