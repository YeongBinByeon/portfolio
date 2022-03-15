package com.example.mvc;

import com.example.mvc.dto.UserReq;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MvcApplicationTests {

	@Test
	void contextLoads() throws JsonProcessingException {
		System.out.println("-----------");

		// Text JSON -> Object
		// Object -> Text JSON

		//controller req json(text) -> object
		// response object -> json(text)

		var objectMapper = new ObjectMapper();

		// object -> text
		// object mapper get method를 활용한다.
		var user = new UserReq("aaa","aaa#asda.com", 10);
		var text = objectMapper.writeValueAsString(user);
		System.out.println(text);

		// text -> object
		// object mapper는 default 생성자를 필요로 한다.
		var objectUser = objectMapper.readValue(text, UserReq.class);
		System.out.println(objectUser);
	}
}
