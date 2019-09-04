package com.itedu.example.service;

import com.itedu.example.dao.PermissionDao;
import com.itedu.example.dao.UserDao;
import com.itedu.example.domain.Permission;
import com.itedu.example.domain.SysRole;
import com.itedu.example.domain.SysUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserService implements UserDetailsService { // 自定义UserDetailsService 接口

	@Autowired
	UserDao userDao;
	@Autowired
	PermissionDao permissionDao;

	public UserDetails loadUserByUsername(String username) {
		SysUser user = userDao.findByUserName(username);
		if (user != null) {
			List<Permission> permissions = permissionDao.findByAdminUserId(user.getUsername());
			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			for (Permission permission : permissions) {
				if (permission != null && permission.getName() != null) {

					GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
					grantedAuthorities.add(grantedAuthority);
				}
			}
			return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
		} else {
			throw new UsernameNotFoundException("admin: " + username + " do not exist!");
		}
	}

}
