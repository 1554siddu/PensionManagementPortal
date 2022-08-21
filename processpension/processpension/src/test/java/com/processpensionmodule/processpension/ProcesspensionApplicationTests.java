 package com.processpensionmodule.processpension;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProcessPensionApplicationTests {

	@Test
	void contextLoads() {
		ProcesspensionApplication.main(new String[] {});
		assertNotNull(ProcesspensionApplication.class);
	}

}
