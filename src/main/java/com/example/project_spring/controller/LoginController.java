package com.example.project_spring.controller;

import java.util.List;

import javax.annotation.Nullable;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project_spring.dao.RoomTypeDAO;
import com.example.project_spring.entity.AccountDetails;
import com.example.project_spring.entity.RoomType;

@Controller
public class LoginController {
	
	@Autowired 
	RoomTypeDAO roomTypeDAO;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@Nullable @RequestParam(value = "error") String error, Model model) {
		List<RoomType> roomTypes = roomTypeDAO.getAll();
		model.addAttribute("roomTypes", roomTypes);
		if (error != null) {

			model.addAttribute("msg", "Đăng nhập sai!");

		}
		return "login";

	}

	@RequestMapping(value = "/checkrole")

	public String checkRole(HttpSession session) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		session.setAttribute("account", account);
		// duyệt role để kiểm tra và điều hướng

		for (var g : account.getAuthorities()) {
			if (g.getAuthority().equals("ROLE_ADMIN") || g.getAuthority().equals("ROLE_USER")) {
				return "redirect:/";
			}
		}

		return "403";

	}

	@RequestMapping("/logoutSuccess")

	public String logout(Model model) {
		model.addAttribute("msg", "Logout thành công!!!");
		return "login";

	}

	@RequestMapping("/403")

	public String accessDenied(Model model) {

		model.addAttribute("msg", "BẠN KHÔNG CÓ QUYỀN TRUY CẬP VÀO TRANG NÀY");

		return "login";

	}
}
