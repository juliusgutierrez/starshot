package com.starshot.demo.controller.rest;

import org.assertj.core.api.BDDAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Created by juliusgutierrez on 20/05/2019.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmployeeControllerTest {

  @Autowired
  private WebApplicationContext context;

  private MockMvc mockMvc;

  @Before
  public void setUpControllerTest() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  public void search_success() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/employee/search")
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.draw", is(1)));
  }

  @Test
  public void search_name_pedro() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/employee/search?name=pedro")
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data[0].fullName", is("pedro penduko")));
  }

  @Test
  public void search_active() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/employee/search?active=true")
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data[0].active", is(true)));
  }

  @Test
  public void delete() throws Exception {
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/employee/delete/1")
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andReturn();
    Assert.assertEquals("Success", mvcResult.getResponse().getContentAsString());
  }

  @Test
  public void save() throws Exception {
    String params = "id=&fullName=ju&timeIn=05%2F20%2F2019+6%3A45+PM&timeOut=05%2F20%2F2019+6%3A45+PM&active=true";
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(String.format("/employee/save?"))
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("fullName", "Julius")
        .param("timeIn", "05/20/2019 6:45 AM")
        .param("timeOut", "05/20/2019 6:45 PM")
        .param("active", "true"))
        .andReturn();
    Assert.assertEquals("Success", mvcResult.getResponse().getContentAsString());
  }


}
