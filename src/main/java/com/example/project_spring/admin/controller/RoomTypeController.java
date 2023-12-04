package com.example.project_spring.admin.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project_spring.dao.impl.RoomDAOImpl;
import com.example.project_spring.dao.impl.RoomTypeDAOImpl;
import com.example.project_spring.dto.roomType.CreateDTO;
import com.example.project_spring.dto.roomType.UpdateDTO;
import com.example.project_spring.entity.AccountDetails;
import com.example.project_spring.entity.Room;
import com.example.project_spring.entity.RoomType;

@Controller
@RequestMapping(value = "/admin/roomType")
public class RoomTypeController {

	@Autowired
	RoomTypeDAOImpl roomTypeDAOImpl;

	@Autowired
	RoomDAOImpl roomDAOImpl;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model, @RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "pageNo", required = false, defaultValue = "1") String pageNo) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int pagesize = 5;
		int count = roomTypeDAOImpl.count();
		int totalPage = count % pagesize == 0 ? count / pagesize : (count / pagesize) + 1;
		int pageno = pageNo != null ? Integer.parseInt(pageNo) : 1;
		List<RoomType> list = roomTypeDAOImpl.paginate(pageno, pagesize);
		if (keyword != null) {
			count = roomTypeDAOImpl.countPage(keyword);
			totalPage = count % pagesize == 0 ? count / pagesize : (count / pagesize) + 1;
			list = roomTypeDAOImpl.searchPage(keyword, pageno, pagesize);
		}
		model.addAttribute("count", count);
		model.addAttribute("pagesize", pagesize);
		model.addAttribute("account", account);
		model.addAttribute("roomTypes", list);
		model.addAttribute("totalPage", totalPage);
		return "roomType";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String create(Model model) {
		RoomType rt = new RoomType();
		model.addAttribute("roomType", rt);
		return "createRoomType";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(RedirectAttributes redirectAttributes, @Valid @ModelAttribute("roomType") CreateDTO roomType,
			BindingResult result, Model model) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("account", account);
		if (result.hasErrors()) {
			model.addAttribute("roomType", roomType);
			return "createRoomType";
		} else {
			var listCheck = roomTypeDAOImpl.search(roomType.getTypeName());
			if (!listCheck.isEmpty()) {
				model.addAttribute("error", "! TypeName Đã tồn tại vui lòng thử lại");
				model.addAttribute("roomType", roomType);
				return "createRoomType";
			} else {
				roomTypeDAOImpl.insert(roomTypeDAOImpl.mapToCreate(roomType));
				redirectAttributes.addFlashAttribute("success", "Thêm mới loại phòng thành công !!!");
				return "redirect:/admin/roomType";
			}
		}
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(Model model, @RequestParam("id") Integer id) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("account", account);
		model.addAttribute("roomType", roomTypeDAOImpl.find(id));
		return "updateRoomType";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(RedirectAttributes redirectAttributes, @Valid @ModelAttribute("roomType") UpdateDTO roomType,
			BindingResult result, Model model) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("account", account);

		if (result.hasErrors()) {
			model.addAttribute("roomType", roomType);
			return "updateRoomType";
		}
		var listCheck = roomTypeDAOImpl.checkTypeName(roomType.getTypeName());
		if (!listCheck.isEmpty()) {
			for (var item : listCheck) {
				if (item.getRoomTypeId().equals(roomType.getRoomTypeId())) {
					roomTypeDAOImpl.update(roomTypeDAOImpl.mapToUpdate(roomType));
					redirectAttributes.addFlashAttribute("success", "Cập nhật loại phòng thành công !!!");
					return "redirect:/admin/roomType";
				}
			}
			model.addAttribute("error", "! TypeName Đã tồn tại vui lòng thử lại");
			model.addAttribute("roomType", roomType);
			return "updateRoomType";
		}
		roomTypeDAOImpl.update(roomTypeDAOImpl.mapToUpdate(roomType));
		redirectAttributes.addFlashAttribute("success", "Cập nhật loại phòng thành công !!!");
		return "redirect:/admin/roomType";
	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(RedirectAttributes redirectAttributes, Model model, @RequestParam("id") Integer id) {
		List<Room> rooms = roomDAOImpl.getRoomByType(id);
		if (rooms != null && !rooms.isEmpty()) {
			redirectAttributes.addFlashAttribute("error", "Không thể xóa loại phòng vì đang có phòng thuộc loại này.");
		} else {
			// Trường hợp không có phòng thuộc loại phòng này
			roomTypeDAOImpl.delete(id);
			redirectAttributes.addFlashAttribute("success", "Xóa loại phòng thành công !!!");
		}

		return "redirect:/admin/roomType";
	}


}
