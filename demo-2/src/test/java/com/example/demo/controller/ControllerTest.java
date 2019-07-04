package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RunWith(SpringRunner.class)
public class ControllerTest {

    private MockMvc mockMvc;
    
    @InjectMocks
    UserController userController;
    @Mock
    UserRepository userRepository;

    @Before
    public void setUp(){
    	mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
    }
    
    @Test
    public void addItem() throws Exception {
    	User u = new User();
		u.setId(19L);
		u.setName("Megh");
		u.setAddress("Delhi");
		
		String USER_JSON = convertUserDomainToJson(u);
		String END_POINT_URL="/user/createUser";
        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(USER_JSON));
//                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
    
    @Test
    public void getAllItems() throws Exception {
    	User u = new User();
		u.setId(19L);
		u.setName("Megha");
		u.setAddress("Delhi");
        List<User> user = Arrays.asList(u);
        Mockito.when(userRepository.findAll()).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/users"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    public void getItem() throws Exception{
    	User u = new User();
		u.setId(8L);
		u.setName("Raj");
		u.setAddress("Banglore");
	    Mockito.when(userRepository.findOne(8L)).thenReturn(u);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/getById/8"))
        //.accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
       // Mockito.verify(userRepository).findById(8L).get();
    }
    @Test
    public void deleteItem() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/deleteUser/3")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void updateItem() throws Exception {
    	User u = new User();
		u.setId(103L);
		u.setName("Raj");
		u.setAddress("Banglore");
    	String USER_JSON = convertUserDomainToJson(u);
     
        mockMvc.perform(MockMvcRequestBuilders.put("/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(USER_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
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
}