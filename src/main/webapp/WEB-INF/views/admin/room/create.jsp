<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box" style="padding: 15px">
	<h3>Thêm mới thông tin:</h3>
	<span style="color: red">${error}</span>

	<f:form action="save" method="post" modelAttribute="room"
		enctype="multipart/form-data">
		<div class="row">
			<div class="col-lg-6">
				<div class="mb-4" style="margin-bottom: 15px">
					<label for="" class="form-label" style="margin-bottom: 10px">Room
						Id</label>
					<f:input path="roomId" class="form-control"></f:input>
					<f:errors cssClass="text-danger" path="roomId"></f:errors>
				</div>
				<div class="mb-4" style="margin-bottom: 15px">
					<label for="" class="form-label" style="margin-bottom: 10px">Room
						Name</label>
					<f:input path="roomName" class="form-control"></f:input>
					<f:errors cssClass="text-danger" path="roomName"></f:errors>
				</div>
				<div class="mb-4" style="margin-bottom: 15px">
					<label for="" class="form-label" style="margin-bottom: 10px">Hotel</label>
					<f:select path="hotel.hotelId" class="form-control">
						<f:options items="${hotels }" itemLabel="name" itemValue="hotelId"></f:options>
					</f:select>
					<f:errors cssClass="text-danger" path="hotel"></f:errors>
				</div>
				<div class="mb-4" style="margin-bottom: 15px">
					<label for="" class="form-label" style="margin-bottom: 10px">Room
						Type</label>
					<f:select path="roomType.roomTypeId" class="form-control">
						<f:options items="${roomTypes }" itemLabel="typeName"
							itemValue="roomTypeId"></f:options>
					</f:select>
					<f:errors cssClass="text-danger" path="roomType"></f:errors>
				</div>
				<div class="mb-4" style="margin-bottom: 15px">
					<label for="" class="form-label" style="margin-bottom: 10px">Bed
						Type</label>
					<f:input path="bedType" class="form-control"></f:input>
					<f:errors cssClass="text-danger" path="bedType"></f:errors>
				</div>
				<div class="mb-4" style="margin-bottom: 15px">
					<label for="" class="form-label" style="margin-bottom: 10px">Size</label>
					<f:input path="roomSize" class="form-control"></f:input>
					<f:errors cssClass="text-danger" path="roomSize"></f:errors>
				</div>
			</div>
			<div class="col-lg-6">
				<div class="mb-4" style="margin-bottom: 15px">
					<label for="" class="form-label" style="margin-bottom: 10px">Price
						/ Night</label>
					<f:input path="pricePerNight" class="form-control"></f:input>
					<f:errors cssClass="text-danger" path="pricePerNight"></f:errors>
				</div>
				<div class="mb-4" style="margin-bottom: 15px">
					<label for="" class="form-label" style="margin-bottom: 10px">Sale
						Price</label>
					<f:input path="salePrice" class="form-control"></f:input>
					<f:errors cssClass="text-danger" path="salePrice"></f:errors>
				</div>
				<div class="mb-4" style="margin-bottom: 15px">
					<label for="" class="form-label" style="margin-bottom: 10px">Facility</label>
					<select name="facility" multiple class="form-control" >
						<option value="">-- Choose --</option>
						<c:forEach items="${facilities }" var="f">
							<option value="${f.facilityId }">${f.facilityName }</option>
						</c:forEach>
					</select>
				</div>
				<div class="mb-4" style="margin-bottom: 15px">
					<label for="" class="form-label" style="margin-bottom: 10px">Policy</label>
					<select name="policy" multiple class="form-control">
						<option value="">-- Choose --</option>
						<c:forEach items="${policies }" var="p">
							<option value="${p.policyId }">${p.policyName }</option>
						</c:forEach>
					</select>
				</div>
				<div class="mb-4" style="margin-bottom: 15px">
					<label for="" class="form-label" style="margin-bottom: 10px">Image</label>
					<input type="file" name="file" onchange="upload_file(this)">
					<img src="" id="upload" width="200px"
						style="object-fit: cover; margin-top: 10px;" alt="">
				</div>
				<div class="mb-4" style="margin-bottom: 15px">
					<label for="" class="form-label" style="margin-bottom: 10px">Availability</label>
					<f:radiobutton path="availability" value="available" class=""
						checked="true"></f:radiobutton>
					<span>Available</span>
					<f:radiobutton path="availability" value="unavailable" class=""></f:radiobutton>
					<span>Unavailable</span>
				</div>
			</div>
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
	</f:form>
</div>
<script>
var upload_file = (input) => {
    var file = input.files[0];
    if (file) {
        var reader = new FileReader();
        
        reader.onload = function(e) {
            var upload = document.getElementById('upload');
            if (upload) {
                upload.src = e.target.result;
            } else {
                console.error("Không tìm thấy phần tử có id 'upload'.");
            }
        };

        reader.readAsDataURL(file);
    }
}
</script>
