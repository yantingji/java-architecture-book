package com.itedu.example.dao;

import java.util.List;

import com.itedu.example.domain.Permission;

public interface PermissionDao {
	public List<Permission> findAll();

	public List<Permission> findByAdminUserId(String userId);
}
