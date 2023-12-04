<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- SUB BANNER -->
<section class="section-sub-banner bg-9">
	<div class="awe-overlay"></div>
	<div class="sub-banner">
		<div class="container">
			<div class="text text-center">
				<h2>ROOMS &amp; RATES</h2>
				<p>Lorem Ipsum is simply dummy text</p>
			</div>
		</div>
	</div>
</section>
<!-- END / SUB BANNER -->

<!-- ROOM -->
<section class="section-room bg-white">
	<div class="container">

		<div class="room-wrap-1">
			<div class="row">
				<c:forEach items="${list }" var="item">
					<!-- ITEM -->
					<div class="col-md-6">
						<div class="room_item-1">

							<h2>
								<a href="#">${item.roomName }</a>
							</h2>

							<div class="img">
								<a href="#"><img
									src="${pageContext.servletContext.contextPath}/resources/uploads/${item.image }"
									alt="" class="card-img" style="height: 300px; object-fit:contain" ></a>
							</div>

							<div class="desc">
								<p>Located in the heart of Aspen with a unique blend of
									contemporary luxury and historic heritage, deluxe
									accommodations, superb amenities, genuine hospitality and
									dedicated service for an elevated experience in the Rocky
									Mountains.</p>
								<ul>
									<li>Max: ${item.roomType.maxOccupancy }(s)</li>
									<li>Size: ${item.roomSize } m2</li>
									<li>View: Ocen</li>
									<li>Bed: ${item.bedType }</li>
								</ul>
							</div>

							<div class="bot">
								<span class="price">Starting <span class="amout">$${item.pricePerNight }</span>
									/days
								</span> <a href="<c:url value='/room/${item.roomId }' />" class="awe-btn awe-btn-13">VIEW DETAILS</a>
							</div>

						</div>
					</div>
					<!-- END / ITEM -->
				</c:forEach>
			</div>
		</div>

	</div>
</section>
<!-- END / ROOM -->