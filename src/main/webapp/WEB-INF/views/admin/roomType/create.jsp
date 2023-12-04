<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<div class="box" style="padding: 15px">
	<h3>Thêm mới danh mục phòng:</h3>
	<span class="text-danger">${error}</span>
	<f:form action="save" method="post" modelAttribute="roomType">
		<div class="mb-4" style="margin-bottom: 15px">
			<label for="" class="form-label" style="margin-bottom: 10px">Type Name</label>
			<f:input path="typeName" class="form-control"></f:input>
			<f:errors cssClass="text-danger" path="typeName"></f:errors>
		</div>
		<div class="mb-4" style="margin-bottom: 15px">
			<label for="" class="form-label" style="margin-bottom: 10px">Description</label>
			<f:input path="description" class="form-control"></f:input>
			<f:errors cssClass="text-danger" path="description"></f:errors>
		</div>
		<div class="mb-4" style="margin-bottom: 15px">
			<label for="" class="form-label" style="margin-bottom: 10px">Max Occupancy</label>
			<f:input path="maxOccupancy" class="form-control"></f:input>
			<f:errors cssClass="text-danger" path="maxOccupancy"></f:errors>
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
	</f:form>
</div>
