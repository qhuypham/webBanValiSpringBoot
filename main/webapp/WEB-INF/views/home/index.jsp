<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>


<!-- CSS -->
    <link href="../multislider/css/custom.css" rel="stylesheet">

    <!-- Font Awesome -->
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	<h2>TRANG CHU</h2>
	<!-- Slide Show -->
    <div id="exampleSlider">
       <div class="MS-content">
       <c:forEach var="c" items="${rands}">
       	<c:forEach	var="p" items="${c.products}">
       		<div class="item">
					<div class="thumbnail">
						<a href="/product/detail/${p.id}"> <img class="vali-pro"
							src="/static/images/products/${p.image}">
						</a>
					</div>
				</div>
       	</c:forEach>
       </c:forEach>
       </div>
       <div class="MS-controls">
           <button class="MS-left"><i class="fa fa-chevron-left" aria-hidden="true"></i></button>
           <button class="MS-right"><i class="fa fa-chevron-right" aria-hidden="true"></i></button>
       </div>
   </div>
   
   <!-- Special -->
   <jsp:include page="../product/list.jsp"></jsp:include>
   
   

    <!-- Include Multislider -->
    <script src="../multislider/js/multislider.min.js"></script>

    <!-- Initialize element with Multislider -->
    <script>
    $('#exampleSlider').multislider({
        interval: 4000,
        slideAll: true,
        duration: 1500
    });
    </script>