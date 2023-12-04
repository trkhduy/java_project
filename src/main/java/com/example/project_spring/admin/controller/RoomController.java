package com.example.project_spring.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project_spring.dao.impl.FacilityDAOImpl;
import com.example.project_spring.dao.impl.HotelDAOImpl;
import com.example.project_spring.dao.impl.ImageDAOImpl;
import com.example.project_spring.dao.impl.PolicyDAOImpl;
import com.example.project_spring.dao.impl.RoomDAOImpl;
import com.example.project_spring.dao.impl.RoomFacilityDAOImpl;
import com.example.project_spring.dao.impl.RoomPolicyDAOImpl;
import com.example.project_spring.dao.impl.RoomTypeDAOImpl;
import com.example.project_spring.dto.room.CreateDTO;
import com.example.project_spring.dto.room.UpdateDTO;
import com.example.project_spring.entity.AccountDetails;
import com.example.project_spring.entity.Image;
import com.example.project_spring.entity.Room;
import com.example.project_spring.entity.RoomFacility;
import com.example.project_spring.entity.RoomPolicy;

@Controller
@RequestMapping(value = "/admin/room")
public class RoomController {

	@Autowired
	RoomDAOImpl roomDAOImpl;

	@Autowired
	RoomTypeDAOImpl roomTypeDAOImpl;

	@Autowired
	HotelDAOImpl hotelDAOImpl;

	@Autowired
	PolicyDAOImpl policyDAOImpl;

	@Autowired
	FacilityDAOImpl facilityDAOImpl;

	@Autowired
	RoomFacilityDAOImpl roomFacilityDAOImpl;

	@Autowired
	RoomPolicyDAOImpl roomPolicyDAOImpl;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ImageDAOImpl imageDAOImpl;

	@RequestMapping(value = "")
	public String index(Model model, @RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "pageNo", required = false, defaultValue = "1") String pageNo) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int pagesize = 4;
		int count = roomDAOImpl.count();
		int totalPage = count % pagesize == 0 ? count / pagesize : (count / pagesize) + 1;
		int pageno = pageNo != null ? Integer.parseInt(pageNo) : 1;
		List<Room> list = roomDAOImpl.paginate(pageno, pagesize);
		if (keyword != null) {
			count = roomDAOImpl.countPage(keyword);
			totalPage = count % pagesize == 0 ? count / pagesize : (count / pagesize) + 1;
			list = roomDAOImpl.searchPage(keyword, pageno, pagesize);
		}
		model.addAttribute("count", count);
		model.addAttribute("pagesize", pagesize);
		model.addAttribute("account", account);
		model.addAttribute("rooms", list);
		model.addAttribute("totalPage", totalPage);
		return "room";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String create(Model model) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("account", account);
		Room room = new Room();
		model.addAttribute("roomTypes", roomTypeDAOImpl.getAll());
		model.addAttribute("hotels", hotelDAOImpl.getAll());
		model.addAttribute("policies", policyDAOImpl.getAll());
		model.addAttribute("facilities", facilityDAOImpl.getAll());
		model.addAttribute("room", room);
		return "createRoom";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(RedirectAttributes redirectAttributes, @Valid @ModelAttribute("room") CreateDTO room,
			BindingResult result, Model model, @RequestParam(name = "file", required = false) MultipartFile file,
			HttpServletRequest req, @RequestParam(name="policy",required = false) Integer[] policies,
			@RequestParam(name="facility",required = false) Integer[] facilities) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("account", account);
		System.out.println(result.hasErrors());
		if (result.hasErrors()) {
			model.addAttribute("roomTypes", roomTypeDAOImpl.getAll());
			model.addAttribute("hotels", hotelDAOImpl.getAll());
			model.addAttribute("policies", policyDAOImpl.getAll());
			model.addAttribute("facilities", facilityDAOImpl.getAll());
			model.addAttribute("room", room);
			return "createRoom";
		} else {
			var listCheck = roomDAOImpl.find(room.getRoomId());
			if (listCheck != null) {
				model.addAttribute("error", "! Id phòng đã tồn tại vui lòng thử lại");
				model.addAttribute("roomTypes", roomTypeDAOImpl.getAll());
				model.addAttribute("hotels", hotelDAOImpl.getAll());
				model.addAttribute("policies", policyDAOImpl.getAll());
				model.addAttribute("facilities", facilityDAOImpl.getAll());
				model.addAttribute("room", room);
				return "createRoom";
			} else {
				if (!file.getOriginalFilename().isEmpty()) {
					String uploadRootPath = req.getServletContext().getRealPath("resources/uploads");
					File destination = new File(uploadRootPath + "/" + file.getOriginalFilename());

					try {

						file.transferTo(destination);

					} catch (IllegalStateException | IOException e) {

						e.printStackTrace();

					}

					room.setImage(file.getOriginalFilename());

					Room roomCreate = modelMapper.map(room, Room.class);
					roomDAOImpl.insert(roomCreate);
					if (facilities.length > 0) {
						List<RoomFacility> roomFacilities = new ArrayList<>();
						for (Integer f : facilities) {
							RoomFacility rf = new RoomFacility();
							rf.setFacility(facilityDAOImpl.find(f));
							rf.setRoom(roomDAOImpl.find(room.getRoomId()));
							roomFacilities.add(rf);
						}
						roomFacilityDAOImpl.bulkInsert(roomFacilities);
					}
					if (policies.length > 0) {
						List<RoomPolicy> roomPolicies = new ArrayList<>();
						for (Integer f : policies) {
							RoomPolicy rp = new RoomPolicy();
							rp.setPolicy(policyDAOImpl.find(f));
							rp.setRoom(roomDAOImpl.find(room.getRoomId()));
							roomPolicies.add(rp);
						}
						roomPolicyDAOImpl.bulkInsert(roomPolicies);
					}
					redirectAttributes.addFlashAttribute("success", "Thêm mới thành công !!!");
					return "redirect:/admin/room";
				} else {
					model.addAttribute("error", "! Vui lòng chọn ảnh");
					model.addAttribute("roomTypes", roomTypeDAOImpl.getAll());
					model.addAttribute("hotels", hotelDAOImpl.getAll());
					model.addAttribute("policies", policyDAOImpl.getAll());
					model.addAttribute("facilities", facilityDAOImpl.getAll());
					model.addAttribute("room", room);
					return "createRoom";
				}

			}
		}
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(Model model, @RequestParam("id") String id) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("account", account);
		model.addAttribute("roomTypes", roomTypeDAOImpl.getAll());
		model.addAttribute("hotels", hotelDAOImpl.getAll());
		model.addAttribute("room", roomDAOImpl.find(id));
		return "updateRoom";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(RedirectAttributes redirectAttributes, @Valid @ModelAttribute("room") UpdateDTO room,
			BindingResult result, Model model, @RequestParam("file") MultipartFile file, HttpServletRequest req) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("account", account);
		System.out.println(room.getRoomId());
		if (result.hasErrors()) {
			model.addAttribute("account", account);
			model.addAttribute("roomTypes", roomTypeDAOImpl.getAll());
			model.addAttribute("hotels", hotelDAOImpl.getAll());
			model.addAttribute("room", room);
			return "updateRoom";
		} else {
			if (!file.getOriginalFilename().isEmpty()) {
				String uploadRootPath = req.getServletContext().getRealPath("resources/uploads");
				File destination = new File(uploadRootPath + "/" + file.getOriginalFilename());
				try {
					file.transferTo(destination);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				room.setImage(file.getOriginalFilename());
			} else {
				var $curRoom = roomDAOImpl.find(room.getRoomId());
				room.setImage($curRoom.getImage());
			}

			Room roomUpdate = modelMapper.map(room, Room.class);
			roomDAOImpl.update(roomUpdate);
			redirectAttributes.addFlashAttribute("success", "Cập nhật thành công !!!");
			return "redirect:/admin/room";
		}
	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(RedirectAttributes redirectAttributes, Model model, @RequestParam("id") String id) {
		List<RoomPolicy> policies = roomPolicyDAOImpl.getByRoom(id);
		List<RoomFacility> facilities = roomFacilityDAOImpl.getByRoom(id);
		List<Image> images = imageDAOImpl.getImageByRoom(id);
		if (policies != null ) {
			System.out.println("po");
			roomPolicyDAOImpl.deleteByRoom(id);
		}
		if (facilities != null ) {
			System.out.println("fa");
			roomFacilityDAOImpl.deleteByRoom(id);
		}
		if (images != null ) {
			System.out.println("img");
			imageDAOImpl.deleteByRoom(id);
		}
		System.out.println("ok");
		roomDAOImpl.delete(id);
		redirectAttributes.addFlashAttribute("success", "Xóa thành công !!!");
		return "redirect:/admin/room";
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") String id, Model model) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("account", account);
		model.addAttribute("images", imageDAOImpl.getImageByRoom(id));
		model.addAttribute("room", roomDAOImpl.find(id));
		return "detailRoom";
	}

	@RequestMapping(value = "saveImg", method = RequestMethod.POST)
	public String saveImg(RedirectAttributes redirectAttributes,
			@RequestParam(name = "uploads", required = true) MultipartFile[] files,
			@RequestParam(name = "roomId", required = true) String id, HttpServletRequest req) {
		System.out.println("saveimg");
		for (MultipartFile file : files) {
			if (!file.getOriginalFilename().isEmpty()) {
				String uploadRootPath = req.getServletContext().getRealPath("resources/uploads");
				File destination = new File(uploadRootPath + "/" + file.getOriginalFilename());
				try {
					file.transferTo(destination);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				Image img = new Image();
				img.setImageName(file.getOriginalFilename());
				img.setRoom(roomDAOImpl.find(id));
				imageDAOImpl.insert(img);
			}
		}

		redirectAttributes.addFlashAttribute("success", "Thêm mới ảnh thành công !!!");
		var directory = "redirect:/admin/room/" + id;
		return directory;
	}

	@RequestMapping(value = "updateImg", method = RequestMethod.POST)
	public String updateImg(RedirectAttributes redirectAttributes,
			@RequestParam(name = "upload", required = true) MultipartFile file,
			@RequestParam(name = "roomId", required = true) String id,
			@RequestParam(name = "imgId", required = true) Integer imgId, HttpServletRequest req) {
		Image img = new Image();
		img.setId(imgId);
		if (!file.getOriginalFilename().isEmpty()) {
			String uploadRootPath = req.getServletContext().getRealPath("resources/uploads");
			File destination = new File(uploadRootPath + "/" + file.getOriginalFilename());
			try {
				file.transferTo(destination);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			img.setImageName(file.getOriginalFilename());
		} else {
			Image curImg = imageDAOImpl.find(imgId);
			img.setImageName(curImg.getImageName());
		}
		img.setRoom(roomDAOImpl.find(id));
		imageDAOImpl.update(img);
		redirectAttributes.addFlashAttribute("success", "Cập nhât ảnh thành công !!!");
		var directory = "redirect:/admin/room/" + id;
		return directory;
	}

	@RequestMapping(value = "deleteImg")
	public String deleteImg(RedirectAttributes redirectAttributes,
			@RequestParam(name = "id", required = true) Integer imgId,
			@RequestParam(name = "roomId", required = true) String id) {
		imageDAOImpl.delete(imgId);
		redirectAttributes.addFlashAttribute("success", "Xóa ảnh thành công !!!");
		var directory = "redirect:/admin/room/" + id;
		return directory;
	}
}
