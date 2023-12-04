<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ACCOUNT -->
<section class="section-account parallax bg-11">
	<div class="awe-overlay"></div>
	<div class="container">
		<div class="login-register">
			<div class="text text-center">
				<h2>LOGIN ACCOUNT</h2>
				<span class="text-danger">${msg}</span>
				<p>Lorem Ipsum is simply dummy text of the printing</p>
				<form action="<c:url value='/loginProcess' />" method="post" class="account_form">
					<div class="field-form">
						<input type="text" class="field-text" name="username" placeholder="User name">
					</div>
					<div class="field-form">
						<input type="password" class="field-text" name="password" placeholder="Password">
						<span class="view-pass"><i class="lotus-icon-view"></i></span>
					</div>
					<div class="field-form field-submit">
						<button class="awe-btn awe-btn-13">Login</button>
					</div>
					<span class="account-desc">I don’t have an account - <a
						href="#">Forgot Password</a></span>
				</form>
			</div>
		</div>
	</div>
</section>
<!-- END / ACCOUNT -->
