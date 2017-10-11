package com.lt;

import com.lt.dao.UserDao;
import com.lt.entity.BUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BountyApplicationTests {

	@Autowired
	UserDao userDao;

	@Test
	public void contextLoads() {
		BUser test = userDao.queryBySysUserName("test");
	}

}
