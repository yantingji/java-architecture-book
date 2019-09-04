package com.itedu.aspect;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itedu.aspect.controller.UserController;
import com.itedu.aspect.log.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class IteduTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test1(){
    	UserController userController = applicationContext.getBean(UserController.class);
    	User user = new User();
		user.setUserName("365ITEDU");
		user.setUserId("365");
    	userController.addUser(user);
        
    }
}
