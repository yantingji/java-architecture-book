package com.itedu365.springframework.test.service;

import com.itedu365.springframework.test.dao.StudentDao;

public class StudentServiceImpl implements StudentService{

	private StudentDao studentDao;
	
	public void save() {
		this.studentDao.add();
	}

	public StudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
}
