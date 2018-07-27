package com.altimetrik.food.unit;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FoodSearchControllerTest {

    private final String SAMPLE_MEAL = "chicken";
    private final String INVALID_MEAL = "invalid";

    @Autowired
    private MockMvc mvc;

    @Test
    public void getFoodSearchSuccessfully() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/food/search?mealName=" + SAMPLE_MEAL).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("text")))
                .andExpect(content().string(containsString("weight")));
    }

    @Test
    public void getFoodSearchUnsuccessfully() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/food/search?mealName=" + INVALID_MEAL).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
