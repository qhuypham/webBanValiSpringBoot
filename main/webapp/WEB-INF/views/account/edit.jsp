<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<h2>Update Profile</h2>
<h4>${message}</h4>
<form:form action="/account/edit" modelAttribute="form" enctype="multipart/form-data">
	<div class="form-group">
		<label>Username</label>
		<form:input path="id" class="form-control"/>
	</div>
	<div class="form-group">
		<label>Fullname</label>
		<form:input path="fullname" class="form-control"/>
	</div>
	<div class="form-group">
		<label>Email Address</label>
		<form:input path="email" class="form-control"/>
	</div>
	<div class="form-group">
		<label>Photo</label>
		<img src="/static/images/customers/${form.photo}" style="width: 80px; height: 90px">
		<input type="file" name="photo_file">
		<form:hidden path="photo" class="form-control"/>
	</div>
	<div class="form-group">
		<form:hidden path="password" class="form-control"/>
		<form:hidden path="activated" class="form-control"/>
		<form:hidden path="admin" class="form-control"/>
		<button class="btn btn-success">Register</button>
	</div>
</form:form>