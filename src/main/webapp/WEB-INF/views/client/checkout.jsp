<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- SUB BANNER -->
<section class="section-sub-banner bg-9">
	<div class="awe-overlay"></div>
	<div class="sub-banner">
		<div class="container">
			<div class="text text-center">
				<h2>CheckOut</h2>
				<p>Lorem Ipsum is simply dummy text</p>
			</div>
		</div>
	</div>
</section>
<!-- END / SUB BANNER -->

<!-- BLOG -->
<section class="section-checkout">
	<div class="container">
		<div class="checkout">

			<p class="checkout_login">
				Returning customer? <a href="#">Click here to login</a>
			</p>
			<form action="" method="post">
				<div class="row">
					<div class="col-md-6">
						<div class="checkout_head">
							<h3>BILLING DETAILS</h3>
							<span>Lorem Ipsum is simply dummy text</span>
						</div>

						<div class="checkout_form">

							<div class="row">
								<div class="col-xs-12 col-sm-12">
									<label>FullName*</label> <input type="text" name="fullName"
										class="field-text" placeholder="Full Name">
								</div>

								<div class="col-xs-12 col-sm-12">
									<label>Address*</label> <input type="text" name="address"
										class="field-text" placeholder="Street Address">
								</div>

								<div class="col-xs-6 col-sm-6">
									<label>Email Address*</label> <input type="text"
										class="field-text" value="${account.email }">
								</div>

								<div class="col-xs-6 col-sm-6">
									<label>Phone*</label> <input type="text"
										value="${account.phoneNumber }" class="field-text">
								</div>

								<div class="col-xs-12 col-sm-12">
									<label>Order Notes</label>
									<textarea class="field-textarea" name="orderNote"
										placeholder="Notes about your order, eg. special notes for delivery"></textarea>
								</div>
								<div class="col-xs-12 col-sm-12">
									<label>&nbsp;</label>
									<p class="code-enter">
										You have a coupon? <a href="#">Click here to enter your
											code</a>
									</p>
								</div>

							</div>

						</div>
					</div>

					<div class="col-md-6">

						<div class="checkout_head checkout_margin">
							<h3>Your payment details</h3>
						</div>

						<div class="checkout_form checkout_margin">

							<div class="checkout_cart">

								<!-- ITEM -->
								<c:choose>
									<c:when test="${ room != null}">
										<div class="cart-item">
											<div class="img">
												<a href="#"><img src="${pageContext.servletContext.contextPath}/resources/uploads/${room.image}" alt=""></a>
											</div>
											<div class="text">
												<a href="#">${room.roomName }</a>
												<p>
													<span>${days } days - ${quantity } rooms</span> <b>$${(days + quantity) * room.pricePerNight }</b>
												</p>
											</div>
											<a href="#" class="remove"><i class="fa fa-close"></i></a>
										</div>
									</c:when>
									<c:otherwise>
										<p>Hiện đang không có sản phẩm</p>
									</c:otherwise>
								</c:choose>
								<!-- END / ITEM -->

							</div>

							<div class="checkout_cartinfo">
								<p>
									<span>Order Total:</span> <span class="color-red">$${(days + quantity) * room.pricePerNight }</span>
								</p>
							</div>

							<div class="checkout_option">
								<ul>
									<li><input type="radio" class="radio payment-methor"
										name="payment">
										<h6>Direct Bank Transfer</h6>
										<p>Make your payment directly into our bank account.
											Please use your Order ID as the payment reference. Your order
											won’t be shipped until the funds have cleared in our account.</p></li>
									<li><input type="radio" class="radio payment-methor"
										name="payment">
										<h6>Cheque Payment</h6></li>
									<li><input type="radio" class="radio payment-methor"
										name="payment">
										<h6>Credit Card</h6> <img src="images/icon-card.jpg" alt="">
									</li>
								</ul>
							</div>

							<div class="checkout_btn">
								<button class="awe-btn awe-btn-13 btn-order">PLACE
									ORDER</button>
							</div>
						</div>

					</div>

				</div>
			</form>
		</div>
	</div>
</section>
<!-- END / BLOG -->