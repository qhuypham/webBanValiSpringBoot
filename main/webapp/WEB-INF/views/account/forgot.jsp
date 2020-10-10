<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<h2>Forgot password</h2>
<h4>${message}</h4>
<form action="/account/forgot" method="post">
	<div class="form-group">
		<label>Username</label>
		<input name="id" class="form-control"/>
	</div>
	<div class="form-group">
		<label>Email Address</label>
		<input name="email" class="form-control"/>
	</div>
	
	<div class="form-group">
		<button class="btn btn-success">Receive password</button>
	</div>
</form>