<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="panel panel-default">
	<div class="panel-body">
		<form:form action="${base}/index" modelAttribute="entity" enctype="multipart/form-data">
			<div class="row">
				<div class="form-group col-sm-6">
					<label>Id</label>
					<form:input path="id" class="form-control" readonly="true" placeholder = "Auto number" />
				</div>
				<div class="form-group col-sm-6">
					<label>Name</label>
					<form:input path="name" class="form-control"/>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-6">
					<label>Unit price</label>
					<form:input path="unitPrice" class="form-control"/>
				</div>
				<div class="form-group col-sm-6">
					<label>Quantity</label>
					<form:input path="quantity" class="form-control"/>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-6">
					<label>Discount</label>
					<form:input path="discount" class="form-control"/>
				</div>
				<div class="form-group col-sm-6">
					<label>Product Date</label>
					<form:input path="productDate" class="form-control"/>
				</div>
			</div>
			<div class="row">
				<div class="form-group">
					<label>Special</label>
					<div class="form-control col-sm-6">
						<form:radiobutton path="special" value="true" label="Yes"/>
						<form:radiobutton path="special" value="false" label="No"/>	
					</div>
				</div>
			
				<div class="form-group">
					<label>Available</label>
					<div class="form-control col-sm-6">
						<form:radiobutton path="available" value="true" label="Yes"/>
						<form:radiobutton path="available" value="false" label="No"/>	
					</div>
				</div>
				<div class="row">
					<div class="form-group col-sm-6">
						<label>View Count</label>
						<form:input path="viewCount" class="form-control" readonly="true"/>
					</div>
					<div class="form-group col-sm-6">
						<label>Catagory</label>
						<form:select path="category.id" class="form-control">
							<form:options items="${cates}" itemLabel="nameVN" itemValue="id" />
						</form:select>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-sm-12">
					<label>Image</label>
					<input type="file" name="image_file" class="form-control">
					<form:hidden path="image"/>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-12">
					<label>Description</label>
					<form:textarea rows="3" path="description"/>
				</div>
			</div>
			<div class="row">	
				<div class="form-group col-sm-12">
					<button class="btn btn-default" formaction="${base}/create">Create</button>
					<button class="btn btn-default" formaction="${base}/update">Update</button>
					<button class="btn btn-default" formaction="${base}/delete">Delete</button>
					<a class="btn btn-default" href="${base}/index">Reset</a>
				</div>
			</div>
		</form:form>
				
	</div>
</div>
<script type="text/javascript">
//<![CDATA[
        bkLib.onDomLoaded(function() { nicEditors.allTextAreas() });
  //]]>
 </script>

