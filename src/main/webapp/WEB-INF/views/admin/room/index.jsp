<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box" style="padding: 15px">
	<a href="<c:url value='/admin/room/add' />" class="btn btn-primary">Thêm
		mới +</a>
	<div class="row" style="display: flex; align-items: center;">
		<div class="col-lg-8">
			<h3>Thông tin</h3>
		</div>
		<div class="col-lg-4 d-flex align-items-center">
			<form action="" method="get">
				<input type="text" style="padding: 4.5px 7px; margin-right: 10px"
					name="keyword" placeholder="Tìm kiếm ...">
				<button type="submit" class="btn btn-primary">
					<i class="fa-solid fa-magnifying-glass"></i>
				</button>
			</form>
		</div>
	</div>
	<span class="text-danger">${error}</span> <span class="text-success">${success}</span>
	<table class="table">
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
			<c:forEach items="${rooms }" var="r">
				<tr>
					<th scope="row" style="vertical-align: middle;">${r.roomId }</th>
					<td style="vertical-align: middle;">${r.roomName }</td>
					<td style="vertical-align: middle;">${r.bedType }</td>
					<td style="vertical-align: middle;">${r.roomType.typeName }</td>
					<td style="vertical-align: middle;">${r.pricePerNight }</td>
					<td style="vertical-align: middle;"><img alt=""
						src="${pageContext.servletContext.contextPath}/resources/uploads/${r.image }"
						style="object-fit: cover; width: 150px"></td>
					<td style="vertical-align: middle;">${r.roomSize }</td>
					<td style="vertical-align: middle;">${r.availability }</td>
					<td style="vertical-align: middle;"><a
						href="room/edit?id=${r.roomId }" class="btn btn-info"> <i
							class="fa-regular fa-pen-to-square"></i>
					</a> <a onclick="return confirm('Bạn có chắc muốn xóa ?')"
						href="room/delete?id=${r.roomId}" class="btn btn-danger"> <i
							class="fa-regular fa-trash-can"></i>
					</a> <a href="room/${r.roomId}" class="btn btn-warning"><i
							class="fa-solid fa-circle-info"></i> </a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:if test="${count > pagesize}">
		<nav aria-label="Page navigation example">
			<ul class="pagination">
				<c:choose>
					<c:when test="${empty param.keyword}">
						<li class="page-item "
							style="${param.pageNo > 1 ? '':'display: none' }"><a
							class="page-link" href="?pageNo=${param.pageNo - 1 }"
							aria-label="Previous"> <span aria-hidden="true">${'&laquo;'}</span>
						</a></li>
						<c:forEach begin="1" end="${totalPage}" var="t">
							<li
								class="page-item ${param.pageNo == t ? 'active': '' } ${param.pageNo == null && t == 1 ? 'active':''}"><a
								class="page-link" href="?pageNo=${t}">${t}</a></li>
						</c:forEach>
						<li class="page-item"
							style="${param.pageNo == totalPage ? 'display: none':'' }"><a
							class="page-link"
							href="?pageNo=${param.pageNo == null ? 1 : param.pageNo + 1 }"
							aria-label="Next"> <span aria-hidden="true">${'&raquo;' }</span>
						</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item "
							style="${param.pageNo > 1 ? '':'display: none' }"><a
							class="page-link"
							href="?pageNo=${param.pageNo - 1 }&keyword=${param.keyword}"
							aria-label="Previous"> <span aria-hidden="true">${'&laquo;'}</span>
						</a></li>
						<c:forEach begin="1" end="${totalPage}" var="t">
							<li
								class="page-item  ${param.pageNo == t ? 'active': '' } ${param.pageNo == null && t == 1 ? 'active':''}"><a
								class="page-link" href="?keyword=${param.keyword}&pageNo=${t}">${t}</a></li>
						</c:forEach>
						<li class="page-item"
							style="${param.pageNo == totalPage ? 'display: none':'' }"><a
							class="page-link"
							href="?pageNo=${param.pageNo == null ? 1 : param.pageNo + 1 }&keyword=${param.keyword}"
							aria-label="Next"> <span aria-hidden="true">${'&raquo;' }</span>
						</a></li>
					</c:otherwise>
				</c:choose>

			</ul>
		</nav>
	</c:if>
</div>
