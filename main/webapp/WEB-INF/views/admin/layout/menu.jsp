<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/home/index">Home</a>
    </div>
    <ul class="nav navbar-nav">
	    <li class="dropdown">
	        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Quan Ly<span class="caret"></span></a>
	        		<ul class="dropdown-menu">
	          			<li><a href="/admin/category/index">Loai</a></li>
	          			<li><a href="/admin/product/index">San Pham</a></li>
	          			<li><a href="/admin/customer/index">Khach Hang</a></li>
	          			<li><a href="/admin/order/index">Don Hang</a></li>
	        		</ul>
	         
	      </li>
	      <li class="dropdown">
	        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Thong Ke<span class="caret"></span></a>
	        		<ul class="dropdown-menu">
	          			<li><a href="/admin/inventory/index">Ton Kho Theo Loai</a></li>
	          			<li><a href="/admin/revenue/category">Doanh So Theo Loai</a></li>
	          			<li><a href="/admin/revenue/customer">Doanh So Theo Khach Hang</a></li>
	          			<li><a href="/admin/revenue/year">Danh so theo nam</a></li>
	          			<li><a href="/admin/revenue/month">Danh so theo thang</a></li>
	          			<li><a href="/admin/revenue/quarter">Danh so theo quy</a></li>
	        		</ul>
	      </li>
      <li class="dropdown">
	        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Hi, ${sessionScope.user.id}<span class="caret"></span></a>
        		<ul class="dropdown-menu">
		          <li><a href="/account/logout"><s:message code="lyt.logout.title" /></a></li>
		          <li><a href="/account/change"><s:message code="lyt.change.title" /></a></li>
		          <li><a href="/account/edit"><s:message code="lyt.edit.title" /></a></li>
		          <li><a href="/order/list"><s:message code="lyt.orders.title" /></a></li>
		          <li><a href="/order/items"><s:message code="lyt.items.title" /></a></li>
		        </ul>
	         
	      </li>
    </ul>
  </div>
</nav>