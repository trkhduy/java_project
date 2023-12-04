<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section class="content-header">
	<h1>
		Room Details (ID : ${room.roomId } ) <small>advanced tables</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
		<li><a href="#">Tables</a></li>
		<li class="active">Data tables</li>
	</ol>
	<a href="/admin/product/create" class="btn btn-success"
		style="margin-top: 10px;">Thêm mới +</a>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">${room.roomName }</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<table id="example2" class="table table-bordered table-hover">
						<thead>
							<tr>
								<th scope="col">Id</th>
								<th>Room Name</th>
								<th>Bed Type</th>
								<th>Room Type</th>
								<th>Price / Night</th>
								<th>Main Image</th>
								<th>Size</th>
								<th>Availability</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td style="vertical-align: middle;">${ room.roomId}</td>
								<td style="vertical-align: middle;">${ room.roomName}</td>
								<td style="vertical-align: middle;">${ room.bedType}</td>
								<td style="vertical-align: middle;">${ room.roomType.typeName}</td>
								<td style="vertical-align: middle;">${ room.pricePerNight}</td>
								<td><img
									src="${pageContext.servletContext.contextPath}/resources/uploads/${ room.image}"
									width="100px" style="object-fit: cover;" alt=""></td>
								<td style="vertical-align: middle;">${ room.roomSize}</td>
								<td style="vertical-align: middle;">${ room.availability}</td>
								<td style="vertical-align: middle;"><a
									href="edit?id=${room.roomId }" class="btn btn-info"><i
										class="fa fa-edit"></i></a></td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>

		<div class="col-xs-12">
			<div class="box">
				<div class="box-header" style="display: flex; align-items: center;">
					<h3 class="box-title">Images Table</h3>
					<div class="addImage">
						<a data-toggle="modal" href='#modal-id' class="btn btn-warning"
							style="margin-left: 20px;">Thêm ảnh +</a>
					</div>
					<span class="text-danger" style="margin-left: 20px">${error}</span> <span class="text-success">${success}</span>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<div class="container">
						<div class="row">
							<c:choose>
								<c:when test="${!empty images }">
									<c:forEach items="${images }" var="i">
										<div class="col-lg-2" style="margin: 20px 0;">
											<div class="detail-img"
												style="text-align: center; position: relative;">
												<img
													src="${pageContext.servletContext.contextPath}/resources/uploads/${ i.imageName}"
													width="150px"
													style="object-fit: cover; border: 1.5px solid #7b7878;"
													height="170px" alt="">
												<div class="delete"
													style="position: absolute;  top: -10px; right: -18px; cursor: pointer;">
													<a href="deleteImg?id=${i.id }&roomId=${room.roomId}" style="color: red;" onclick="return confirm('Bạn có chắc muốn xóa ảnh này')"
														style="background-color: transparent; padding: 0; border: none;">
														<svg xmlns="http://www.w3.org/2000/svg"
															class="icon icon-tabler icon-tabler-square-letter-x"
															width="24" height="24" viewBox="0 0 24 24"
															stroke-width="2" stroke="currentColor" fill="none"
															stroke-linecap="round" stroke-linejoin="round">
                                                    <path stroke="none"
																d="M0 0h24v24H0z" fill="none"></path>
                                                    <path
																d="M3 3m0 2a2 2 0 0 1 2 -2h14a2 2 0 0 1 2 2v14a2 2 0 0 1 -2 2h-14a2 2 0 0 1 -2 -2z">
                                                    </path>
                                                    <path d="M10 8l4 8"></path>
                                                    <path
																d="M10 16l4 -8"></path>
                                                </svg>
													</a>
												</div>
												<div class="update"
													style="position: absolute; color: #4d6a57; bottom: 0; right: -18px; cursor: pointer;">
													<a data-toggle="modal" href='#modal-id${ i.id}'
														id="update-img"
														style="background-color: transparent; padding: 0; border: none;">
														<svg xmlns="http://www.w3.org/2000/svg"
															class="icon icon-tabler icon-tabler-photo-edit"
															width="24" height="24" viewBox="0 0 24 24"
															stroke-width="2" stroke="currentColor" fill="none"
															stroke-linecap="round" stroke-linejoin="round">
                                                    <path stroke="none"
																d="M0 0h24v24H0z" fill="none"></path>
                                                    <path d="M15 8h.01"></path>
                                                    <path
																d="M11 20h-4a3 3 0 0 1 -3 -3v-10a3 3 0 0 1 3 -3h10a3 3 0 0 1 3 3v4">
                                                    </path>
                                                    <path
																d="M4 15l4 -4c.928 -.893 2.072 -.893 3 0l3 3"></path>
                                                    <path
																d="M14 14l1 -1c.31 -.298 .644 -.497 .987 -.596"></path>
                                                    <path
																d="M18.42 15.61a2.1 2.1 0 0 1 2.97 2.97l-3.39 3.42h-3v-3l3.42 -3.39z">
                                                    </path>
                                                </svg>
													</a>
												</div>
											</div>
										</div>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<h3>Không có ảnh</h3>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<!-- /.box-body -->
				</div>
				<!-- /.box -->
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->
	</div>
</section>
<!-- /.content -->

<!-- /.content-wrapper -->

<!-- Thêm mới -->
<div class="modal fade" id="modal-id">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">Thêm ảnh mô tả sản phẩm</h4>
			</div>
			<form action="saveImg" method="POST" role="form"
				enctype="multipart/form-data">
				<input type="hidden" name="roomId" value="${room.roomId }">
				<div class="modal-body">
					<lable class="form-label">Chọn ảnh</lable>
					<input type="file" name="uploads" multiple
						onchange="previewImages(this)">
					<div id="image-container" style="margin: 20px 0;"></div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- update -->
<c:forEach items="${images }" var="i">
	<div class="modal fade" id="modal-id${i.id }">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Update ảnh mô tả sản phẩm</h4>
				</div>
				<form action="updateImg" method="POST" role="form"
					enctype="multipart/form-data">
					<input type="hidden" name="imgId" value="${i.id }">
					<input type="hidden" name="roomId" value="${room.roomId }">
					<div class="modal-body">
						<legend>Chọn ảnh</legend>
						<input type="file" name="upload" id="${i.id }"
							onchange="changeImg(this)">
						<div>
							<img
								src="${pageContext.servletContext.contextPath}/resources/uploads/${ i.imageName}"
								style="object-fit: cover; width: 100px; height: 100px; margin: 10px;"
								id="upload${ i.id}" alt="">
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">Cập nhật</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</c:forEach>

<script>
    function changeImg(input) {
        var img = input.files[0];
        var reader = new FileReader;
        var id = input.id

        reader.onload = (e) => {
            document.getElementById(`upload` + id).src = e.target.result;
        }
        reader.readAsDataURL(img)
    }

    function previewImages(input) {
        if (input.files) {
            var imageContainer = $('#image-container');
            imageContainer.empty();
            for (var i = 0; i < input.files.length; i++) {
                var reader = new FileReader();
                reader.onload = function(e) {
                    var img = $('<img>').attr('src', e.target.result);
                    img.attr('style', 'object-fit: cover;width: 100px;height:100px; margin:10px;')
                    imageContainer.append(img);
                }
                reader.readAsDataURL(input.files[i]);
            }
        }
    }
    $(document).ready(function() {
        $('.js-example-basic-multiple').select2();
    });
</script>