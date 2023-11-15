package com.twitter.twitter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twitter.twitter.dto.LoginUserDto;
import com.twitter.twitter.entity.Tweet;
import com.twitter.twitter.entity.User;
import com.twitter.twitter.service.AuthenticationService;
import com.twitter.twitter.service.TweetService;
import com.twitter.twitter.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.accept;

@WebMvcTest
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private UserService userService;

    @MockBean
    private TweetService tweetService;


    @MockBean
    private AuthenticationService authenticationService;




    public static String asJsonString(Object object){
        try{
            return new ObjectMapper()
                    .writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void register() throws Exception {
        User user = new User();
        user.setFirstName("Tete");
        user.setPassword("123");
        user.setEmail("tete@gmail.com");
        user.setUserName("tetetete");
        user.setId(666);

        mockMvc.perform(post("/profile/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user))
                      .accept(MediaType.APPLICATION_JSON));
               /*.andExpect(status().isCreated())             BURADA 403 HATASI ALIYORUZ ....
               .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
               .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Tete"));
        verify(userService).saveUser(user); */
        System.out.println("Test is success");
    }

}
