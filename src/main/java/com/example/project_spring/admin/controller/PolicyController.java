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

import com.example.project_spring.dao.impl.PolicyDAOImpl;
import com.example.project_spring.dao.impl.RoomPolicyDAOImpl;
import com.example.project_spring.dto.policy.UpdateDTO;
import com.example.project_spring.dto.policy.CreateDTO;
import com.example.project_spring.entity.AccountDetails;
import com.example.project_spring.entity.Policy;
import com.example.project_spring.entity.RoomPolicy;

@Controller
@RequestMapping(value = "/admin/policy")
public class PolicyController {
	@Autowired
	PolicyDAOImpl policyDAOImpl;
	
	@Autowired
	RoomPolicyDAOImpl roomPolicyDAOImpl;
	
	@RequestMapping(value = "")
	public String index(Model model,@RequestParam(name = "keyword", required = false) String keyword,@RequestParam(name = "pageNo", required = false, defaultValue = "1") String pageNo) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int pagesize = 5;
		int count = policyDAOImpl.count();
		int totalPage = count % pagesize == 0 ? count / pagesize : (count / pagesize) + 1;
		int pageno = pageNo != null ? Integer.parseInt(pageNo) : 1;	
		List<Policy> list = policyDAOImpl.paginate(pageno, pagesize);
		if(keyword != null) {
			count = policyDAOImpl.countPage(keyword);
			totalPage = count % pagesize == 0 ? count / pagesize : (count / pagesize) + 1;
			list = policyDAOImpl.searchPage(keyword, pageno, pagesize);
		}
		model.addAttribute("count",count);
		model.addAttribute("pagesize",pagesize);
		model.addAttribute("account", account);
		model.addAttribute("policies", list);
		model.addAttribute("totalPage", totalPage);
		return "policy";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String create(Model model) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("account", account);
		Policy policy = new Policy();
		model.addAttribute("policy", policy);
		return "createPolicy";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(RedirectAttributes redirectAttributes,@Valid @ModelAttribute("policy") CreateDTO policy, BindingResult result, Model model) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("account", account);
		if (result.hasErrors()) {
			model.addAttribute("policy", policy);
			return "createPolicy";
		} else {
			var listCheck = policyDAOImpl.checkName(policy.getpolicyName());
			if (!listCheck.isEmpty()) {
				model.addAttribute("error", "! Chính sách Đã tồn tại vui lòng thử lại");
				model.addAttribute("policy", policy);
				return "createPolicy";
			} else {
				policyDAOImpl.insert(policyDAOImpl.mapToCreate(policy));
				redirectAttributes.addFlashAttribute("success", "Thêm mới thành công !!!");
				return "redirect:/admin/policy";
			}
		}
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(Model model, @RequestParam("id") Integer id) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("account", account);
		model.addAttribute("policy", policyDAOImpl.find(id));
		return "updatePolicy";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(RedirectAttributes redirectAttributes,@Valid @ModelAttribute("policy") UpdateDTO policy, BindingResult result, Model model) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("account", account);

		if (result.hasErrors()) {
			model.addAttribute("policy", policy);
			return "updatePolicy";
		}
		var listCheck = policyDAOImpl.checkName(policy.getpolicyName());
		if (!listCheck.isEmpty()) {
			for (var item : listCheck) {
				if (item.getPolicyId() == policy.getpolicyId()) {
					policyDAOImpl.update(policyDAOImpl.mapToUpdate(policy));
					redirectAttributes.addFlashAttribute("success", "Cập nhật thành công !!!");
					return "redirect:/admin/policy";
				}
			}
			model.addAttribute("error", "! Chính sách này đã tồn tại vui lòng thử lại");
			model.addAttribute("policy",policy);
			return "updatePolicy";
		}
		policyDAOImpl.update(policyDAOImpl.mapToUpdate(policy));
		redirectAttributes.addFlashAttribute("success", "Cập nhật thành công !!!");
		return "redirect:/admin/policy";
	}
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(RedirectAttributes redirectAttributes,Model model, @RequestParam("id") Integer id) {
		List<RoomPolicy> policies = roomPolicyDAOImpl.getByPolicy(id);
		if(policies != null && !policies.isEmpty()) {
			redirectAttributes.addFlashAttribute("error", "Chính sách đang được sử dụng không thể xóa !!!");
		}
		policyDAOImpl.delete(id);
		redirectAttributes.addFlashAttribute("success", "Xóa thành công !!!");
		return "redirect:/admin/policy";
	}
}
