<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<div class="box" style="padding: 15px">
	<h3>Cập nhật thông tin:</h3>
	<span style="color: red">${error}</span>

	<f:form action="update" method="post" modelAttribute="hotel" enctype="multipart/form-data">
		<f:hidden path="hotelId" />
		<div class="mb-4" style="margin-bottom: 15px">
			<label for="" class="form-label" style="margin-bottom: 10px">Name</label>
			<f:input path="name" class="form-control"></f:input>
			<f:errors cssClass="text-danger" path="name"></f:errors>
		</div>
		<div class="mb-4" style="margin-bottom: 15px">
			<label for="" class="form-label" style="margin-bottom: 10px">Address</label>
			<f:input path="address" class="form-control"></f:input>
			<f:errors cssClass="text-danger" path="address"></f:errors>
		</div>
		<div class="mb-4" style="margin-bottom: 15px">
			<label for="" class="form-label" style="margin-bottom: 10px">Logo</label>
			<input type="file" name="file">
		</div>
		<div class="mb-4" style="margin-bottom: 15px">
			<label for="" class="form-label">Rating</label>
			<f:hidden path="rating" />${hotel.rating }
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
	</f:form>
</div>
