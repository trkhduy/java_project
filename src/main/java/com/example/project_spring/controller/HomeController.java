package com.example.project_spring.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project_spring.dao.HotelDAO;
import com.example.project_spring.dao.ImageDAO;
import com.example.project_spring.dao.RoomDAO;
import com.example.project_spring.dao.RoomFacilityDAO;
import com.example.project_spring.dao.RoomTypeDAO;
import com.example.project_spring.entity.AccountDetails;
import com.example.project_spring.entity.Image;
import com.example.project_spring.entity.Room;
import com.example.project_spring.entity.RoomFacility;
import com.example.project_spring.entity.RoomType;

@Controller
public class HomeController {

	@Autowired
	HotelDAO hotelDAO;

	@Autowired
	RoomDAO roomDAO;

	@Autowired
	RoomTypeDAO roomTypeDAO;

	@Autowired
	ImageDAO imageDAO;

	@Autowired
	RoomFacilityDAO roomFacilityDAO;

	@RequestMapping(value = "/")
	public String index(Model model) {
		model.addAttribute("hotel", hotelDAO.find(1));
		// return room
		List<Room> rooms = roomDAO.getAll();
		var totalSlide = roomDAO.count() % 6 == 0 ? roomDAO.count() / 6 : (roomDAO.count() / 6) + 1;
		model.addAttribute("rooms", rooms);
		model.addAttribute("totalSlide", totalSlide);
		// return roomType
		List<RoomType> roomTypes = roomTypeDAO.getAll();
		model.addAttribute("roomTypes", roomTypes);

		return "home";
	}

	@RequestMapping(value = "/about")
	public String about(Model model) {
		List<RoomType> roomTypes = roomTypeDAO.getAll();
		model.addAttribute("roomTypes", roomTypes);
		return "about";
	}

	@RequestMapping(value = "/contact")
	public String contact(Model model) {
		List<RoomType> roomTypes = roomTypeDAO.getAll();
		model.addAttribute("roomTypes", roomTypes);
		return "contact";
	}

	@RequestMapping(value = "/register")
	public String register(Model model) {
		List<RoomType> roomTypes = roomTypeDAO.getAll();
		model.addAttribute("roomTypes", roomTypes);
		return "register";
	}

	@RequestMapping(value = { "/roomType", "/roomType/{id}" })
	public String room(@PathVariable(value = "id", required = false) Integer id, Model model) {
		// return roomType
		List<RoomType> roomTypes = roomTypeDAO.getAll();
		model.addAttribute("roomTypes", roomTypes);
		List<Room> list = roomDAO.getAll();
		if (id != null) {
			list = roomDAO.getRoomByType(id);
		}
		model.addAttribute("list", list);
		return "homeRoom";
	}

	@RequestMapping(value = "/room/{id}")
	public String detail(@PathVariable(value = "id") String id, Model model) {
		List<RoomType> roomTypes = roomTypeDAO.getAll();
		model.addAttribute("roomTypes", roomTypes);
		Room room = roomDAO.find(id);
		model.addAttribute("room", room);
		List<Image> imgByRoom = imageDAO.getImageByRoom(id);
		model.addAttribute("images", imgByRoom);
		List<RoomFacility> facilities = roomFacilityDAO.getByRoom(id);
		model.addAttribute("facilities", facilities);
		List<Room> relatedRoom = roomDAO.getRoomByType(room.getRoomType().getRoomTypeId());
		model.addAttribute("relatedRoom", relatedRoom);
		return "roomDetail";
	}

	@RequestMapping(value = "/addBooking")
	public String addBookng(@RequestParam("checkOut") String checkout, @RequestParam("checkIn") String checkin,
			@RequestParam("quantity") Integer quantity, @RequestParam("roomId") String roomId, HttpSession session) {

		session.setAttribute("checkin", checkin);
		session.setAttribute("checkout", checkout);
		session.setAttribute("roomId", roomId);
		session.setAttribute("quantity", quantity);

		return "redirect:/checkout";
	}

	@RequestMapping(value = "/checkout")
	public String checkout(Model model, HttpSession session) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("account", account);
		List<RoomType> roomTypes = roomTypeDAO.getAll();
		model.addAttribute("roomTypes", roomTypes);
		model.addAttribute("room", null);
		if (session.getAttribute("roomId") != null) {
			model.addAttribute("room", roomDAO.find((String) session.getAttribute("roomId")));
		}
		var quantity = (Integer) session.getAttribute("quantity");
		var checkin = (String) session.getAttribute("checkin");
		var checkout = (String) session.getAttribute("checkout");

		Date date1 = null;
		try {
			date1 = sdf.parse(checkin);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date date2 = null;
		try {
			date2 = sdf.parse(checkout);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Chuyển đổi thành milliseconds từ 1/1/1970
		long time1 = date1.getTime();
		long time2 = date2.getTime();

		// Tính số ngày chênh lệch
		long diff = time2 - time1;
		long days = diff / (1000 * 60 * 60 * 24);

		model.addAttribute("quantity", quantity);
		model.addAttribute("checkin", checkin);
		model.addAttribute("checkout", checkout);
		model.addAttribute("days", days);
		return "checkout";
	}
}
