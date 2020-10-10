$(document).ready(function(){
	$("tr[data-id] input").on("input",function(){
		var id = $(this).closest("tr").attr("data-id");
		var price = $(this).closest("tr").attr("data-price");
		var discount = $(this).closest("tr").attr("data-discount");
		var qty = $(this).val();
		$.ajax({
			url:`/cart/update/${id}/${qty}`,
			success: function(response){
				$("#cart-count").html(response[0]);
				$("#cart-amount").html(response[1]);
			}
		});
		var amount = qty*price*(1-discount);
		$(this).closest("tr").find("td.amount").html(amount);
	});
	$(".btn-cart-remove").click(function(){
		var id = $(this).closest("tr").attr("data-id");
		$.ajax({
			url:"/cart/remove/"+id,
			success: function(response){
				$("#cart-count").html(response[0]);
				$("#cart-amount").html(response[1]);
			}
		});
		$(this).closest("tr").remove();
	});
	
	$(".btn-cart-clear").click(function(){
		$.ajax({
			url:"/cart/clear",
			success: function(response){
				$("#cart-count").html(0);
				$("#cart-amount").html(0);
				$("table>tbody").html("");
			}
		});
	});
	
	$(".btn-add-to-cart").click(function(){
		var id =$(this).closest("div").attr("data-id");
		$.ajax({
			url:"/cart/add/"+id,
			success: function(response){
				$("#cart-count").html(response[0]);
				$("#cart-amount").html(response[1]);
			}
		});
		
		var img =$(this).closest(".thumbnail").find("a>img");
		var options={to:'#cart-img',className:'cart-fly'}
		var cart_css = '.cart-fly{background-image:url("'+img.attr("src")+'");background-size: 100% 100%;}';
		$("style#cart-css").html(cart_css);
		img.effect('transfer',options,500);
	});
	$(".btn-open-dialog").click(function(){
		var id =$(this).closest("div").attr("data-id");
		$("#myModal #id").val(id);
	});
	$(".btn-send").click(function(){
		var form ={
		 id: $("#myModal #id").val(),
		 to: $("#myModal #email").val(),
		}
		$.ajax({
			url:"/product/send-email",
			data: form,
			success: function(response){
				if(response){
					$("[data-dismiss]").click();
					alert("Da gui thanh cong")
				}
				else{
					alert("loi gui mail")
				}
			}
		})
		
	});
	$(".btn-star").click(function(){
		var id =$(this).closest("div").attr("data-id");
		$.ajax({
			url:"/product/add-to-favo/"+id,
			success: function(response){
				if(response){
					alert("Da them vao thanh cong")
				}
				else{
					alert("Da co san")
				}
			}
		})
	})
})