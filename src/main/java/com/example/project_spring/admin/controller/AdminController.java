package com.example.project_spring.admin.controller;

import javax.annotation.Nullable;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project_spring.entity.AccountDetails;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@GetMapping(value = "")
	public String index (Model model) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("account",account);
		return "adminHome";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@Nullable @RequestParam(value = "error") String error, Model model) {
		if (error != null) {

			model.addAttribute("msg", "Đăng nhập sai!");

		}
		return "adminlogin";

	}

	@RequestMapping(value = "/checkrole")

	public String checkRole() {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		// duyệt role để kiểm tra và điều hướng

		for (var g : account.getAuthorities()) {
			if (g.getAuthority().equals("ROLE_ADMIN")) {
				return "redirect:/admin/";
			}
		}

		return "403";

	}

	@RequestMapping("/logoutSuccess")

	public String logout(Model model) {
		model.addAttribute("msg", "Logout thành công!!!");
		return "adminlogin";

	}

	@RequestMapping("/403")

	public String accessDenied(Model model) {

		model.addAttribute("msg", "BẠN KHÔNG CÓ QUYỀN TRUY CẬP VÀO TRANG NÀY");

		return "adminlogin";

	}

}
