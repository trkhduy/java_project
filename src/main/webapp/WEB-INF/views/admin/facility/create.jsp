<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<div class="box" style="padding: 15px">
	<h3>Thêm mới tiện ích:</h3>
	<span class="text-danger">${error}</span>
	<f:form action="save" method="post" modelAttribute="facility">
		<div class="mb-4" style="margin-bottom: 15px">
			<label for="" class="form-label" style="margin-bottom: 10px">Facility Name</label>
			<f:input path="facilityName" class="form-control"></f:input>
			<f:errors cssClass="text-danger" path="facilityName"></f:errors>
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
	</f:form>
</div>
