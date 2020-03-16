package org.vadtel.support.controller;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.vadtel.support.config.ApplicationContextConfig;

import javax.servlet.ServletContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(value = SpringExtension.class)
@ContextConfiguration(
        classes = {ApplicationContextConfig.class})
@WebAppConfiguration
class TopicControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesGreetController() {
        ServletContext servletContext = wac.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(wac.getBean("topicController"));
    }

    @Test
    void getAllTopics() throws Exception {
        this.mockMvc.perform(get("/topics/").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Connection"))
                .andExpect(jsonPath("$[1].name").value("Payment"))
                .andExpect(jsonPath("$[2].name").value("Additional services"))
                .andExpect(jsonPath("$[3].name").value("Internet"))
                .andExpect(jsonPath("$[4].name").value("SIM"))
                .andExpect(jsonPath("$[5].name").value("Roaming"))
                .andExpect(jsonPath("$[6].name").value("Other"));
    }
}