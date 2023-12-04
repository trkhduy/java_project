package com.example.project_spring.admin.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project_spring.dao.impl.HotelDAOImpl;
import com.example.project_spring.entity.AccountDetails;
import com.example.spring_project.dto.hotel.updateDTO;

@Controller
@RequestMapping(value = "/admin/hotel")
public class HotelController {
	@Autowired
	HotelDAOImpl hotelDAOImpl;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("account", account);
		model.addAttribute("hotels", hotelDAOImpl.getAll());
		return "hotel";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(Model model, @RequestParam("id") Integer id) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("account", account);
		model.addAttribute("hotel", hotelDAOImpl.find(id));
		return "updateHotel";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(RedirectAttributes redirectAttributes,@Valid @ModelAttribute("hotel") updateDTO hotel, BindingResult result,
			@RequestParam("file") MultipartFile file, Model model, HttpServletRequest req) {
		if (result.hasErrors()) {
			model.addAttribute("hotel", hotel);
			return "updateHotel";
		} else {
			if (!file.getOriginalFilename().isEmpty()) {
				String uploadRootPath = req.getServletContext().getRealPath("resources/uploads");
				File destination = new File(uploadRootPath + "/" + file.getOriginalFilename());

				try {

					file.transferTo(destination);

				} catch (IllegalStateException | IOException e) {

					e.printStackTrace();

				}

				hotel.setImage(file.getOriginalFilename());

			} else {
				var $curHotel = hotelDAOImpl.find(hotel.getHotelId());
				hotel.setImage($curHotel.getImage());
			}
			hotelDAOImpl.update(hotelDAOImpl.mapToEntity(hotel));
			redirectAttributes.addFlashAttribute("success","Cập nhật thông tin hotel thành công !!!");
			return "redirect:/admin/hotel";
		}
	}

}
