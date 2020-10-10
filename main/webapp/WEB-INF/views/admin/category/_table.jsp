<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<table class="table table-hover">
<thead>
	<tr>
		<th>Id</th>
		<th>Name</th>
		<th>NameVN</th>
		<th></th>
	</tr>
</thead>
<tbody id="tbody">
<c:forEach var="e" items="${list}">
	<tr>
		<td>${e.id}</td>
		<td>${e.name}</td>
		<td>${e.nameVN}</td>
		<td class="text-center">
			<a class="btn btn-sm btn-info" href="${base}/edit/${e.id}">Edit</a>
			<a class="btn btn-sm btn-danger" href="${base}/delete/${e.id}">Delete</a>
		</td>
	</tr>
</c:forEach>
</tbody>
</table>
<ul class="pager">
  <li><a href="#"><i class="glyphicon glyphicon-hand-up"></i></a></li>
  <li><a href="#"><i class="glyphicon glyphicon-hand-left"></i></a></li>
  <li><a href="#"><span id="info">5/15</span></a></li>
  <li><a href="#"><i class="glyphicon glyphicon-hand-right"></i></a></li>
  <li><a href="#"><i class="glyphicon glyphicon-hand-down"></i></a></li>
</ul>


<script>
$(function(){
	var pageNo=0;
	var pageCount=0;
	var pageSize =10;
	$.ajax({
		url:'/pager/category/page-count',
		success:function(response){
			pageCount = response;
			$(".pager a:eq(0)").click();
		}
	})
	
	$(".pager a:eq(0)").click(function(){
		pageNo = 0;
		$.ajax({
			url:'/pager/category/page/'+pageNo,
			success:fnSuccess
		});
		return false;
	});
	
	$(".pager a:eq(1)").click(function(){
		if(pageNo>0)
		{
			pageNo--;
			$.ajax({
				url:'/pager/category/page/'+pageNo,
				success:fnSuccess
			});
		}
		return false;
	});
	$(".pager a:eq(3)").click(function(){
		if(pageNo<pageCount-1)
		{
			pageNo++;
			$.ajax({
				url:'/pager/category/page/'+pageNo,
				success:fnSuccess
			});
		}
		return false;
	});
	$(".pager a:eq(4)").click(function(){
		pageNo = pageCount-1;
		$.ajax({
			url:'/pager/category/page/'+pageNo,
			success:fnSuccess
		});
		return false;
	});
	function fnSuccess(response){
		$("#tbody").html("");
		$(response).each(function(index,cate){
			var tr = $("<tr/>");
			$("<td/>").html(cate.id).appendTo(tr);
			$("<td/>").html(cate.name).appendTo(tr);
			$("<td/>").html(cate.nameVN).appendTo(tr);
			s=`<td class="text-center">
				<a class="btn btn-sm btn-info" href="/admin/category/edit/`+cate.id+`">Edit</a>
				<a class="btn btn-sm btn-danger" href="/admin/category/delete/`+cate.id+`">Delete</a>
			</td>`;
			$(s).appendTo(tr);
			tr.appendTo("#tbody")
			
			$("#info").html((pageNo+1)+'/'+(pageCount))
			//console.log(tr.html());
		})
	}

})

</script>