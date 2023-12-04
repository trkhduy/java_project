package com.example.project_spring.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.project_spring.dao.AccountDAO;
import com.example.project_spring.entity.Acc;
import com.example.project_spring.entity.AccountDetails;
import com.example.project_spring.entity.AccountRole;

@Service
public class AccountDetailsService implements UserDetailsService {

	@Autowired
	private AccountDAO accountDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return getAccount(username);
	}

	private AccountDetails getAccount(String username) {
		
		Acc acc = accountDAO.get(username);

		if (acc == null) {

			return null;

		}

		// xử lý lấy roles của người dùng đưa vào Authority

		Collection<GrantedAuthority> grantedAuthoritySet = new HashSet<>();

		Set<AccountRole> roles = acc.getAccRoles();

		for (AccountRole accountRole : roles) {

			String rolename = accountRole.getRole().getRoleName();

			grantedAuthoritySet.add(new SimpleGrantedAuthority(rolename));

		}

		return new AccountDetails(grantedAuthoritySet, acc.getUsername(), acc.getEmail(),
				acc.getAvatar(), acc.getPhoneNumber(), acc.getPassword() ,acc.getEnabled(), true, true, true);

	}
}
