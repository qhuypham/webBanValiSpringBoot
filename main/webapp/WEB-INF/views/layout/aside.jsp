<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>


<c:set var="cart" value="${sessionScope['scopedTarget.cartService']}"></c:set>
<c:set var="lang" value="${pageContext.response.locale.language}"></c:set>


<div class="panel panel-default">
  <div class="panel-heading">
  	<s:message code="lyt.cart.title"/>
  </div>
  <div class="panel-body">
  	<img id="cart-img" src="/static/images/cart.jpg" class="col-sm-5">
  	<ul class="col-sm-7">
  		<li><b id="cart-count">${cart.count}</b></li>
  		<li><b id="cart-amount"><f:formatNumber value="${cart.amount}" pattern="#,###.00"></f:formatNumber></b> VND</li>
  		<li>
  			<a href="/cart/view"><s:message code="lyt.cartView.title" /></a>
  		</li>
  	</ul>
  </div>
</div>

<div class="panel panel-default">
  <div class="panel-heading">TIM KIEM</div>
	<div class="panel-body">
		<form action="/product/list-by-keyword" method="post">
			<input name="keywords" class="form-control" placeholder="keywords">
		</form>
	</div>
</div>
<div class="panel panel-default">
  <div class="panel-heading">
  	<s:message code="lyt.cate.title" />
  </div>
  <div class="list-group">
  <c:forEach var="c" items="${cates}">
  	<a href="/product/list-by-category/${c.id}" class="list-group-item">${(lang=='vi')?c.nameVN:c.name}</a>
  </c:forEach>
	  
</div>
</div>

<div class="panel panel-default">
  <div class="panel-heading">DAC BIET</div>
	  <div class="list-group">
		  <a href="/product/list-by-special/0" class="list-group-item list-group-item-action">Hang Moi</a>
		  <a href="/product/list-by-special/1" class="list-group-item list-group-item-action">Ban Chay</a>
		  <a href="/product/list-by-special/2" class="list-group-item list-group-item-action">Yeu Thich</a>
		  <a href="/product/list-by-special/3" class="list-group-item list-group-item-action">Giam Gia</a>
		  <a href="/product/list-by-special/4" class="list-group-item list-group-item-action">Dac Biet</a>
		</div>
</div>

<style id="cart-css">
	
</style>