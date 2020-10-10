 <%@ page pageEncoding="utf-8"%>

<h2>Change password</h2>
<h4>${message}</h4>
<form action="/account/change" method="post" >
	<div class="form-group">
		<label>Username</label>
		<input name="id" class="form-control"/>
	</div>
	<div class="form-group">
		<label>Current password</label>
		<input name="pw" class="form-control"/>
	</div>
	<div class="form-group">
		<label>New password</label>
		<input name="pw1" class="form-control"/>
	</div>
	<div class="form-group">
		<label>Confirm new password</label>
		<input name="pw2" class="form-control"/>
	</div>
	
	<div class="form-group">
		<button class="btn btn-success">Change password</button>
	</div>
</form>