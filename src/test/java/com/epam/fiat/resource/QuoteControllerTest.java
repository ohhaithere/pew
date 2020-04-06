package com.epam.fiat.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.epam.fiat.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={ Application.class })
public class QuoteControllerTest {

  private static final String URL = "/quote/";
  @Autowired
  private WebApplicationContext webApplicationContext;
  private MockMvc mockMvc;

  @Before
  public void setUp() {
    mockMvc = webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void testValidateIncorrectIsin() throws Exception {
    //given:
    ResultActions resultActions = mockMvc.perform(post(URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"isin\": \"test\", \"bid\": 1.0, \"ask\": 11.0}")
        ).andExpect(status().is4xxClientError());
  }

  @Test
  public void testValidateBidLessThenAsk() throws Exception {
    //given:
    ResultActions resultActions = mockMvc.perform(post(URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"isin\": \"123456789123\", \"bid\": 11.0, \"ask\": 1.0}")
    ).andExpect(status().is4xxClientError());
  }

}
