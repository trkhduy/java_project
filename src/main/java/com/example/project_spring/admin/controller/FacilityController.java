package com.example.project_spring.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project_spring.dao.impl.FacilityDAOImpl;
import com.example.project_spring.dao.impl.RoomFacilityDAOImpl;
import com.example.project_spring.dto.facility.CreateDTO;
import com.example.project_spring.dto.facility.UpdateDTO;
import com.example.project_spring.entity.AccountDetails;
import com.example.project_spring.entity.Facility;
import com.example.project_spring.entity.RoomFacility;
import com.example.project_spring.entity.RoomType;

@Controller
@RequestMapping(value = "/admin/facility")
public class FacilityController {

	@Autowired
	FacilityDAOImpl facilityDAOImpl;

	@Autowired
	RoomFacilityDAOImpl roomFacilityDAOImpl;

	@RequestMapping(value = "")
	public String index(Model model, @RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "pageNo", required = false, defaultValue = "1") String pageNo) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int pagesize = 5;
		int count = facilityDAOImpl.count();
		int totalPage = count % pagesize == 0 ? count / pagesize : (count / pagesize) + 1;
		int pageno = pageNo != null ? Integer.parseInt(pageNo) : 1;
		List<Facility> list = facilityDAOImpl.paginate(pageno, pagesize);
		if (keyword != null) {
			System.out.println(1);
			count = facilityDAOImpl.countPage(keyword);
			totalPage = count % pagesize == 0 ? count / pagesize : (count / pagesize) + 1;
			list = facilityDAOImpl.searchPage(keyword, pageno, pagesize);
		}
		model.addAttribute("count", count);
		model.addAttribute("pagesize", pagesize);
		model.addAttribute("account", account);
		model.addAttribute("facilities", list);
		model.addAttribute("totalPage", totalPage);
		return "facility";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String create(Model model) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("account", account);
		Facility facility = new Facility();
		model.addAttribute("facility", facility);
		return "createFacility";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(RedirectAttributes redirectAttributes, @Valid @ModelAttribute("facility") CreateDTO facility,
			BindingResult result, Model model) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("account", account);
		if (result.hasErrors()) {
			model.addAttribute("facility", facility);
			return "createFacility";
		} else {
			var listCheck = facilityDAOImpl.checkName(facility.getFacilityName());
			if (!listCheck.isEmpty()) {
				model.addAttribute("error", "! Tiện ích Đã tồn tại vui lòng thử lại");
				model.addAttribute("facility", facility);
				return "createFacility";
			} else {
				facilityDAOImpl.insert(facilityDAOImpl.mapToCreate(facility));
				redirectAttributes.addFlashAttribute("success", "Thêm mới tiện ích thành công !!!");
				return "redirect:/admin/facility";
			}
		}
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(Model model, @RequestParam("id") Integer id) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("account", account);
		model.addAttribute("facility", facilityDAOImpl.find(id));
		return "updateFacility";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(RedirectAttributes redirectAttributes, @Valid @ModelAttribute("facility") UpdateDTO facility,
			BindingResult result, Model model) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("account", account);

		if (result.hasErrors()) {
			model.addAttribute("facility", facility);
			return "updateFacility";
		}
		var listCheck = facilityDAOImpl.checkName(facility.getFacilityName());
		if (!listCheck.isEmpty()) {
			for (var item : listCheck) {
				if (item.getFacilityId() == facility.getFacilityId()) {
					facilityDAOImpl.update(facilityDAOImpl.mapToUpdate(facility));
					redirectAttributes.addFlashAttribute("success", "Cập nhật tiện ích thành công !!!");
					return "redirect:/admin/facility";
				}
			}
			model.addAttribute("error", "! Tiện ích này đã tồn tại vui lòng thử lại");
			model.addAttribute("facility", facility);
			return "updateFacility";
		}
		facilityDAOImpl.update(facilityDAOImpl.mapToUpdate(facility));
		redirectAttributes.addFlashAttribute("success", "Cập nhật tiện ích thành công !!!");
		return "redirect:/admin/facility";
	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(RedirectAttributes redirectAttributes, Model model, @RequestParam("id") Integer id) {
		List<RoomFacility> facilities = roomFacilityDAOImpl.getByFacility(id);
		if (facilities != null && !facilities.isEmpty()) {
			redirectAttributes.addFlashAttribute("error", "Tiện ích đang được sử dụng không thể xóa !!!");
		}
		facilityDAOImpl.delete(id);
		redirectAttributes.addFlashAttribute("success", "Xóa tiện ích thành công !!!");
		return "redirect:/admin/facility";
	}
}
