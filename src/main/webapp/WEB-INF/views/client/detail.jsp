<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- SUB BANNER -->
<section class="section-sub-banner bg-16">
	<div class="awe-overlay"></div>
	<div class="sub-banner">
		<div class="container">
			<div class="text text-center">
				<h2>${room.roomName }</h2>
				<p>Lorem Ipsum is simply dummy text</p>
			</div>
		</div>

	</div>

</section>
<!-- END / SUB BANNER -->

<!-- ROOM DETAIL -->
<section class="section-room-detail bg-white">
	<div class="container">

		<!-- DETAIL -->
		<div class="room-detail">
			<div class="row">
				<div class="col-lg-9">

					<!-- LAGER IMGAE -->
					<div class="room-detail_img">
						<c:forEach items="${images }" var="i">
							<div class="room_img-item">
								<img
									src="${pageContext.servletContext.contextPath}/resources/uploads/${i.imageName }"
									alt="">
								<h6>Lorem Ipsum is simply dummy text of the printing and
									typesetting industry</h6>
							</div>
						</c:forEach>
					</div>
					<!-- END / LAGER IMGAE -->

					<!-- THUMBNAIL IMAGE -->
					<div class="room-detail_thumbs">
						<c:forEach items="${images }" var="i">
							<a href="#"><img
								src="${pageContext.servletContext.contextPath}/resources/uploads/${i.imageName }"
								alt=""></a>
						</c:forEach>

					</div>
					<!-- END / THUMBNAIL IMAGE -->

				</div>

				<div class="col-lg-3">

					<!-- FORM BOOK -->
					<div class="room-detail_book">

						<div class="room-detail_total">
							<img src="images/icon-logo.png" alt="" class="icon-logo">

							<h6>STARTING ROOM FROM</h6>

							<p class="price">
								<span class="amout">$${room.pricePerNight }</span> /days
							</p>
						</div>
						<form action="<c:url value='/addBooking' />" method="post">
							<input type="hidden" name="roomId" value="${room.roomId }">
							<div class="room-detail_form">
								<label>Arrive</label> <input type="text"
									class="awe-calendar from" name="checkIn"
									placeholder="Arrive Date"> <label>Depature</label> <input
									type="text" name="checkOut" class="awe-calendar to"
									placeholder="Departure Date"> <label>Quantity</label> <select
									class="awe-select" name="quantity">
									<option selected>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
								</select>
								<button type="submit" class="awe-btn awe-btn-13">Book
									Now</button>
							</div>
						</form>
					</div>
					<!-- END / FORM BOOK -->

				</div>
			</div>
		</div>
		<!-- END / DETAIL -->

		<!-- TAB -->
		<div class="room-detail_tab">

			<div class="row">
				<div class="col-md-3">
					<ul class="room-detail_tab-header">
						<li><a href="#overview" data-toggle="tab">OVERVIEW</a></li>
						<li class="active"><a href="#amenities" data-toggle="tab">amenities</a></li>
						<li><a href="#package" data-toggle="tab">PACKAGE</a></li>
						<li><a href="#rates" data-toggle="tab">RATES</a></li>
						<li><a href="#calendar" data-toggle="tab">Calendar</a></li>
					</ul>
				</div>

				<div class="col-md-9">
					<div class="room-detail_tab-content tab-content">

						<!-- OVERVIEW -->
						<div class="tab-pane fade" id="overview">

							<div class="room-detail_overview">
								<h5
									class='text-uppercase
                                        '>de
									Finibus Bonorum et Malorum", written by Cicero in 45 BC</h5>
								<p>Located in the heart of Aspen with a unique blend of
									contemporary luxury and historic heritage, deluxe
									accommodations, superb amenities, genuine hospitality and
									dedicated service for an elevated experience in the Rocky
									Mountains.</p>

								<div class="row">
									<div class="col-xs-6 col-md-4">
										<h6>SPECIAL ROOM</h6>
										<ul>
											<li>Max: ${room.roomType.maxOccupancy } Person(s)</li>
											<li>Size: ${room.roomSize } m2 / 376 ft2</li>
											<li>View: Ocen</li>
											<li>Bed: ${room.bedType }</li>
										</ul>
									</div>
									<div class="col-xs-6 col-md-4">
										<h6>SERVICE ROOM</h6>
										<ul>
											<li>Oversized work desk</li>
											<li>Hairdryer</li>
											<li>Iron/ironing board upon request</li>
										</ul>
									</div>
								</div>

							</div>

						</div>
						<!-- END / OVERVIEW -->

						<!-- AMENITIES -->
						<div class="tab-pane fade active in" id="amenities">

							<div class="room-detail_amenities">
								<p>Located in the heart of Aspen with a unique blend of
									contemporary luxury and historic heritage, deluxe
									accommodations, superb amenities, genuine hospitality and
									dedicated service for an elevated experience in the Rocky
									Mountains.</p>

								<div class="row">
									<div class="col-xs-6 col-lg-4">
										<ul>
											<c:forEach items="${facilities }" var="f">
												<li>${f.facility.facilityName }</li>
											</c:forEach>
										</ul>
									</div>
								</div>

							</div>

						</div>
						<!-- END / AMENITIES -->

						<!-- PACKAGE -->
						<div class="tab-pane fade" id="package">

							<div class="room-detail_package">

								<!-- ITEM package -->
								<div class="room-package_item">

									<div class="text">
										<h4>
											<a href="#">package standar</a>
										</h4>
										<p>Lorem Ipsum is simply dummy text of the printing and
											typesetting industry. Lorem Ipsum has been the industry's
											standard dummy text ever since the 1500s, when an unknown
											printer took a galley of type and scrambled</p>

										<div class="room-package_price">
											<p class="price">
												<span class="amout">$260</span> / Package
											</p>
											<a href="#" class="awe-btn awe-btn-default">Book package</a>
										</div>
									</div>
								</div>
								<!-- END / ITEM package -->

								<!-- ITEM package -->
								<div class="room-package_item">

									<div class="text">
										<h4>
											<a href="#">package standar</a>
										</h4>
										<p>Lorem Ipsum is simply dummy text of the printing and
											typesetting industry. Lorem Ipsum has been the industry's
											standard dummy text ever since the 1500s, when an unknown
											printer took a galley of type and scrambled</p>

										<div class="room-package_price">
											<p class="price">
												<span class="amout">$260</span> / Package
											</p>
											<a href="#" class="awe-btn awe-btn-default">Book package</a>
										</div>
									</div>
								</div>
								<!-- END / ITEM package -->

								<!-- ITEM package -->
								<div class="room-package_item">

									<div class="text">
										<h4>
											<a href="#">package standar</a>
										</h4>
										<p>Lorem Ipsum is simply dummy text of the printing and
											typesetting industry. Lorem Ipsum has been the industry's
											standard dummy text ever since the 1500s, when an unknown
											printer took a galley of type and scrambled</p>

										<div class="room-package_price">
											<p class="price">
												<span class="amout">$260</span> / Package
											</p>
											<a href="#" class="awe-btn awe-btn-default">Book package</a>
										</div>
									</div>
								</div>
								<!-- END / ITEM package -->
							</div>

						</div>
						<!-- END / PACKAGE -->

						<!-- RATES -->
						<div class="tab-pane fade" id="rates">

							<div class="room-detail_rates">
								<table>
									<thead>
										<tr>
											<th>Rate Period</th>
											<th>Nightly</th>
											<th>Weekend Night</th>
											<th>Weekly</th>
											<th>Monthly</th>
											<th>Event</th>
										</tr>
									</thead>
									<tr>
										<td>
											<h6>Spring/Summer Season</h6>
											<ul>
												<li>Jun 1 - Aug 31</li>
												<li>3 night minimum stay</li>
											</ul>
										</td>
										<td>
											<p class="price">
												<span class="amout">$320</span>
											</p>
										</td>
										<td>
											<p class="price">
												<span class="amout">$23</span>
											</p>
										</td>
										<td>
											<p class="price">
												<span class="amout">$120</span>
											</p>
										</td>
										<td>
											<p class="price">
												<span class="amout">$100</span>
											</p>
										</td>
										<td>
											<p class="price">
												<span class="amout">$89</span>
											</p>
										</td>
									</tr>
									<tr>
										<td>
											<h6>Summer/Fall Season</h6>
											<ul>
												<li>Jun 1 - Aug 31</li>
												<li>3 night minimum stay</li>
											</ul>
										</td>
										<td>
											<p class="price">
												<span class="amout">$320</span>
											</p>
										</td>
										<td>
											<p class="price">
												<span class="amout">$23</span>
											</p>
										</td>
										<td>
											<p class="price">
												<span class="amout">$120</span>
											</p>
										</td>
										<td>
											<p class="price">
												<span class="amout">$100</span>
											</p>
										</td>
										<td>
											<p class="price">
												<span class="amout">$89</span>
											</p>
										</td>
									</tr>
									<tr>
										<td>
											<h6>Christmas Season</h6>
											<ul>
												<li>Jun 1 - Aug 31</li>
												<li>3 night minimum stay</li>
											</ul>
										</td>
										<td>
											<p class="price">
												<span class="amout">$320</span>
											</p>
										</td>
										<td>
											<p class="price">
												<span class="amout">$23</span>
											</p>
										</td>
										<td>
											<p class="price">
												<span class="amout">$120</span>
											</p>
										</td>
										<td>
											<p class="price">
												<span class="amout">$100</span>
											</p>
										</td>
										<td>
											<p class="price">
												<span class="amout">$89</span>
											</p>
										</td>
									</tr>
								</table>
							</div>

						</div>
						<!-- END / RATES -->

						<!-- CALENDAR -->
						<div class="tab-pane fade" id="calendar">

							<div class="room-detail_calendar-wrap row">

								<div class="col-sm-6">
									<!-- CALENDAR ITEM -->
									<div class="calendar_custom">

										<div class="calendar_title">
											<span class="calendar_month">JUNE</span> <span
												class="calendar_year">2015</span> <a href="#"
												class="calendar_prev calendar_corner"><i
												class="lotus-icon-left-arrow"></i></a>
										</div>

										<table class="calendar_tabel">

											<thead>
												<tr>
													<th>Su</th>
													<th>Mo</th>
													<th>Tu</th>
													<th>We</th>
													<th>Th</th>
													<th>Fr</th>
													<th>Sa</th>
												</tr>
											</thead>

											<tr>
												<td></td>
												<td class="apb-calendar_current-date"><a href="#"><small>1</small></a>
												</td>
												<td><a href="#"><small>2</small></a></td>
												<td><a href="#"><small>3</small></a></td>
												<td><a href="#"><small>4</small></a></td>
												<td><a href="#"><small>5</small></a></td>
												<td><a href="#"><small>6</small></a></td>
											</tr>

											<tr>
												<td><a href="#"><small>7</small></a></td>
												<td><a href="#"><small>8</small></a></td>
												<td><a href="#"><small>9</small></a></td>
												<td><a href="#"><small>10</small></a></td>
												<td class="apb-calendar_current-select"><a href="#"><small>11</small></a></td>
												<td class="apb-calendar_current-select"><a href="#"><small>12</small></a></td>
												<td><a href="#"><small>13</small></a></td>
											</tr>

											<tr>
												<td><a href="#"><small>14</small></a></td>
												<td><a href="#"><small>15</small></a></td>
												<td class="not-available"><a href="#"><small>16</small></a></td>
												<td class="not-available"><a href="#"><small>17</small></a></td>
												<td><a href="#"><small>18</small></a></td>
												<td><a href="#"><small>19</small></a></td>
												<td><a href="#"><small>20</small></a></td>
											</tr>

											<tr>
												<td><a href="#"><small>21</small></a></td>
												<td><a href="#"><small>22</small></a></td>
												<td><a href="#"><small>23</small></a></td>
												<td><a href="#"><small>24</small></a></td>
												<td><a href="#"><small>25</small></a></td>
												<td><a href="#"><small>26</small></a></td>
												<td><a href="#"><small>27</small></a></td>
											</tr>

											<tr>
												<td><a href="#"><small>28</small></a></td>
												<td><a href="#"><small>29</small></a></td>
												<td><a href="#"><small>30</small></a></td>
												<td><a href="#"><small>31</small></a></td>
												<td></td>
												<td></td>
												<td></td>
											</tr>

										</table>

									</div>
									<!-- END CALENDAR ITEM -->
								</div>

								<div class="col-sm-6">

									<!-- CALENDAR ITEM -->
									<div class="calendar_custom">

										<div class="calendar_title">
											<span class="calendar_month">JUNE</span> <span
												class="calendar_year">2015</span> <a href="#"
												class="calendar_next calendar_corner"><i
												class="lotus-icon-right-arrow"></i></a>
										</div>

										<table class="calendar_tabel">

											<thead>
												<tr>
													<th>Su</th>
													<th>Mo</th>
													<th>Tu</th>
													<th>We</th>
													<th>Th</th>
													<th>Fr</th>
													<th>Sa</th>
												</tr>
											</thead>

											<tr>
												<td></td>
												<td class="apb-calendar_current-date"><a href="#"><small>1</small></a>
												</td>
												<td><a href="#"><small>2</small></a></td>
												<td><a href="#"><small>3</small></a></td>
												<td><a href="#"><small>4</small></a></td>
												<td><a href="#"><small>5</small></a></td>
												<td><a href="#"><small>6</small></a></td>
											</tr>

											<tr>
												<td><a href="#"><small>7</small></a></td>
												<td><a href="#"><small>8</small></a></td>
												<td><a href="#"><small>9</small></a></td>
												<td><a href="#"><small>10</small></a></td>
												<td class="apb-calendar_current-select"><a href="#"><small>11</small></a></td>
												<td class="apb-calendar_current-select"><a href="#"><small>12</small></a></td>
												<td><a href="#"><small>13</small></a></td>
											</tr>

											<tr>
												<td><a href="#"><small>14</small></a></td>
												<td><a href="#"><small>15</small></a></td>
												<td class="not-available"><a href="#"><small>16</small></a></td>
												<td class="not-available"><a href="#"><small>17</small></a></td>
												<td><a href="#"><small>18</small></a></td>
												<td><a href="#"><small>19</small></a></td>
												<td><a href="#"><small>20</small></a></td>
											</tr>

											<tr>
												<td><a href="#"><small>21</small></a></td>
												<td><a href="#"><small>22</small></a></td>
												<td><a href="#"><small>23</small></a></td>
												<td><a href="#"><small>24</small></a></td>
												<td><a href="#"><small>25</small></a></td>
												<td><a href="#"><small>26</small></a></td>
												<td><a href="#"><small>27</small></a></td>
											</tr>

											<tr>
												<td><a href="#"><small>28</small></a></td>
												<td><a href="#"><small>29</small></a></td>
												<td><a href="#"><small>30</small></a></td>
												<td><a href="#"><small>31</small></a></td>
												<td></td>
												<td></td>
												<td></td>
											</tr>

										</table>

									</div>
									<!-- END CALENDAR ITEM -->
								</div>

								<div class="calendar_status text-center col-sm-12">
									<span>Available</span> <span class="not-available">Not
										Available</span>
								</div>
							</div>

						</div>
						<!-- END / CALENDAR -->

					</div>
				</div>

			</div>

		</div>
		<!-- END / TAB -->

		<!-- COMPARE ACCOMMODATION -->
		<div class="room-detail_compare">
			<h2 class="room-compare_title">COMPARE ACCOMMODATION</h2>

			<div class="room-compare_content">

				<div class="row">
					<c:forEach items="${relatedRoom }" var="r">
						<!-- ITEM -->
						<c:if test="${r.roomId != room.roomId }">
							<div class="col-sm-6 col-md-4 col-lg-3">
								<div class="room-compare_item">
									<div class="img">
										<a href="#"><img
											src="${pageContext.servletContext.contextPath}/resources/uploads/${r.image }"
											alt=""></a>
									</div>

									<div class="text">
										<h2>
											<a href="#">${r.roomName }</a>
										</h2>

										<ul>
											<li><i class="lotus-icon-person"></i> Max:
												${r.roomType.maxOccupancy } Person(s)</li>
											<li><i class="lotus-icon-bed"></i> Bed: ${r.bedType }
												beds</li>
											<li><i class="lotus-icon-view"></i> View: Ocen</li>
										</ul>

										<a href="<c:url value='/room/${r.roomId }' />"
											class="awe-btn awe-btn-default">VIEW DETAIL</a>

									</div>

								</div>
							</div>
						</c:if>
						<!-- END / ITEM -->
					</c:forEach>

				</div>

			</div>
		</div>
		<!-- END / COMPARE ACCOMMODATION -->

	</div>
</section>
<!-- END / SHOP DETAIL -->