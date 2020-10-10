<%@ page pageEncoding="utf-8" autoFlush="" buffer=""%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>


<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/home/index">
      		<s:message code="lyt.menu.home"/>
		</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="/home/about"><s:message code="lyt.about.title" /></a></li>
      <li><a href="/home/contact"><s:message code="lyt.contact.title" /></a></li>
      <li><a href="/home/feedback"><s:message code="lyt.feedback.title" /></a></li>
      <li><a href="/home/faq"><s:message code="lyt.FAQs.title" /></a></li>
      <li class="dropdown">
        
        <c:choose>
        	<c:when test="${empty sessionScope.user}">
        	<a class="dropdown-toggle" data-toggle="dropdown" href="#"><s:message code="lyt.account.title" /><span class="caret"></span></a>
        		<ul class="dropdown-menu">
          			<li><a href="/account/login"><s:message code="lyt.login.title" /></a></li>
          			<li><a href="/account/register"><s:message code="lyt.register.title" /></a></li>
          			<li><a href="/account/forgot"><s:message code="lyt.forgot.title" /></a></li>
        		</ul>
        	</c:when>	
        	<c:otherwise>
        	<a class="dropdown-toggle" data-toggle="dropdown" href="#">Hi, ${sessionScope.user.id}<span class="caret"></span></a>
        		<ul class="dropdown-menu">
		          <li><a href="/account/logout"><s:message code="lyt.logout.title" /></a></li>
		          <li><a href="/account/change"><s:message code="lyt.change.title" /></a></li>
		          <li><a href="/account/edit"><s:message code="lyt.edit.title" /></a></li>
		          <li><a href="/order/list"><s:message code="lyt.orders.title" /></a></li>
		          <li><a href="/order/items"><s:message code="lyt.items.title" /></a></li>
		        </ul>
        	</c:otherwise>
        </c:choose>
      </li>
      <c:if test = "${sessionScope.user.admin}">
         <li><a href="/admin/home/index"><s:message code="lyt.admin.title" /></a></li>
      </c:if>
      
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#" data-lang = "vi">Tieng Viet </a></li>
      <li><a href="#" data-lang = "en">Tieng Anh </a></li>
    </ul>
  </div>
</nav>

<script>
$(function(){
	$('a[data-lang]').click(function(){
		var lang = $(this).attr("data-lang");
		$.ajax({
			url:'/home/language?lang='+lang,
					success: function(){
						location.reload();
					}
		});
		return false;
	});
	
})
</script>