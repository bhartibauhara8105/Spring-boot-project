package com.example.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RunWith(SpringRunner.class)
public class UserControllerTest {
	private MockMvc mockMvc;

	@Mock
	UserRepository userRepository;

	@Mock
	UserServiceImpl userService;
	
	@InjectMocks
	UserController userController; 

	User user = null;
	/*
	 * @Before(value = "") public void init(){ MockitoAnnotations.initMocks(this);
	 * mockMvc = MockMvcBuilders .standaloneSetup(UserController.class)
	 * .addFilters(new CORSFilter()) .build();
	 */
	
	@Before(value = "demo")
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc =
                MockMvcBuilders
                        .standaloneSetup(userController)
                        .build();
        user = users();
    }

	@Test
	public void createUserTest() throws Exception {

		User u = new User();
		u.setId(19L);
		u.setName("Megh");
		u.setAddress("Delhi");
		
		  when(userRepository.save(u)).thenReturn(users()); //String
		String USER_JSON = convertUserDomainToJson(u);
		String END_POINT_URL="localhost:8080/user/createUser";
	
		
		/*
		 * RequestBuilder requestBuilder =
		 * MockMvcRequestBuilders.post("localhost:8080/user/createUser")
		 * .content(USER_JSON); MvcResult result =
		 * mockMvc.perform(requestBuilder).andReturn(); MockHttpServletResponse response
		 * = result.getResponse(); //assertEquals(201, response.getStatus());
		 */
		
		mockMvc.perform((RequestBuilder) ((ResultActions) post(END_POINT_URL)
                .content(USER_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated()));
	}

	protected String convertUserDomainToJson(User user) {
		ObjectWriter objectWrite = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			return objectWrite.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}

	private User users() {
		User u = new User();
		u.setId(19L);
		u.setName("Megh");
		u.setAddress("Delhi");

		return u;

	}

}
