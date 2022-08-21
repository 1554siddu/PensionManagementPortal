package com.authorizationservice.jwt;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.authorizationservice.jwt.service.UserServiceImpl;



@SpringBootTest
class JwtApplicationTests {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Test
	void contextLoads() {
		JwtApplication.main(new String[] {});
		assertThat(userServiceImpl).isNotNull();
	}

}
