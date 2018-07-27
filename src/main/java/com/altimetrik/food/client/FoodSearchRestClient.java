package com.altimetrik.food.client;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.altimetrik.food.bean.Recipe;

public class FoodSearchRestClient {
	static String URL = "https://api.edamam.com/search?app_id=21125b03&app_key=f28f37b59bb5d3f40e35bc60b8c68aec";

	public Recipe getRestRequestForReciepe(String mealName, String urlappend, HttpMethod requestType) {
		RestTemplate restTemplate = new RestTemplate();
		System.out.println(URL + urlappend);
		ResponseEntity<Recipe> responseEntity = restTemplate.exchange(URL + urlappend, requestType, null, Recipe.class);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			return responseEntity.getBody();
		} else {
			return null;
		}
	}
}
