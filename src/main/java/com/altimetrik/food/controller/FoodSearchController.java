package com.altimetrik.food.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.food.bean.Recipe;
import com.altimetrik.food.client.FoodSearchRestClient;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/food")
public class FoodSearchController {
	
	FoodSearchRestClient restClient=new FoodSearchRestClient();
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public Object foodsearch(@RequestParam String mealName) {
		Recipe mealrespnse = restClient.createRestRequestForReciepe(mealName, "&q="+mealName, HttpMethod.GET);
		Object obj = mealrespnse.getHits().get(0);
		System.out.println(obj);
		HashMap<String, Object> test = (HashMap<String, Object>) obj;
		HashMap<String, Object> recipe = (HashMap<String, Object>) test.get("recipe");
		System.out.println(recipe.get("ingredients"));
		return recipe.get("ingredients");
		
	}
	
	

}
