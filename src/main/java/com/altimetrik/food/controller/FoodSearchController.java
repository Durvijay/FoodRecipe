package com.altimetrik.food.controller;

import java.util.HashMap;

import com.altimetrik.food.exception.ResourceNotFoundException;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import com.altimetrik.food.bean.Recipe;
import com.altimetrik.food.client.FoodSearchRestClient;

@RestController
@RequestMapping("/food")
public class FoodSearchController {
	
	FoodSearchRestClient restClient=new FoodSearchRestClient();

	@RequestMapping(value="/search", method = RequestMethod.GET)
	public Object foodsearch(@RequestParam String mealName) {
			Recipe mealresponse = restClient.createRestRequestForReciepe(mealName, "&q=" + mealName, HttpMethod.GET);
			if (!mealresponse.getHits().isEmpty()) {
				Object obj = mealresponse.getHits().get(0);
				System.out.println(obj);
				HashMap<String, Object> test = (HashMap<String, Object>) obj;
				HashMap<String, Object> recipe = (HashMap<String, Object>) test.get("recipe");
				System.out.println(recipe.get("ingredients"));
				return recipe.get("ingredients");
			} else {
				throw new ResourceNotFoundException("Meal Not Found");
			}
	}
	
	

}
