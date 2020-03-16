package org.vadtel.support.controller;

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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(value = SpringExtension.class)
@ContextConfiguration(
        classes = {ApplicationContextConfig.class})
@WebAppConfiguration
class InquiryControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesTopicController() {
        ServletContext servletContext = wac.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(wac.getBean("topicController"));
    }

    @Test
    void getAllInquiriesByCustomerName() throws Exception {
        this.mockMvc.perform(get("/customers/{customerName}/inquiries", "Andrey")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].description").value("I want to know my puk code for my sim"));

    }

    @Test
    void getInquiryByCustomerNameAndInquiryId() throws Exception {
        this.mockMvc.perform(get("/customers/{customerName}/inquiries/{inquiryId}", "Andrey", 3L)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.description").value("I want to know my puk code for my sim"));
    }

    @Test
    void createInquiry() throws Exception {
        this.mockMvc.perform(post("/customers/{customerName}/inquiries", "Andrey")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "        \"topicName\": \"Internet\",\n" +
                        "        \"description\": \"TESTProblem with internet download speed\",\n" +
                        "        \"attributeEntityList\": [\n" +
                        "        \t{\n" +
                        "        \t\t\"name\": \"Address\",\n" +
                        "            \t\"value\": \"Brest\"\n" +
                        "        \t},\n" +
                        "            {\n" +
                        "            \t\"name\": \"Telephone\",\n" +
                        "            \t\"value\": \"+375331445674\"\n" +
                        "            }\n" +
                        "        ]\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.description").value("TESTProblem with internet download speed"));
    }

    @Test
    void undateInquiry() {
    }

    @Test
    void deleteInquiryByCustomerNameAndInquiryId() {
    }
}