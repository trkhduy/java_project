<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="box"  style="padding: 15px">
	<h3>Thông tin</h3>
	<span class="text-success">${success}</span>
	<table class="table" >
		<thead>
			<tr>
				<th scope="col">Id</th>
				<th >Name</th>
				<th >Address</th>
				<th >Logo</th>
				<th >Rating</th>
				<th ></th>
			</tr>
		</thead>
		<tbody >
			<c:forEach items="${hotels }" var="h">
				<tr >
					<th scope="row" style="vertical-align: middle;">${h.hotelId }</th>
					<td style="vertical-align: middle;">${h.name }</td>
					<td style="vertical-align: middle;">${h.address }</td>
					<td style="vertical-align: middle;"> <img alt="" src="${pageContext.servletContext.contextPath}/resources/uploads/${h.image }"> </td>
					<td style="vertical-align: middle;">${h.rating }</td>
					<td style="vertical-align: middle;">
						<a href="hotel/edit?id=${h.hotelId }" class="btn btn-info">
							<i class="fa-regular fa-pen-to-square"></i>
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
