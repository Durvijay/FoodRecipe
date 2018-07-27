package com.altimetrik.food.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.altimetrik.food.bean.Ingredient;

public class NutritionalRestClient {
	static final String URL = "https://trackapi.nutritionix.com/v2/natural/nutrients";

	public Object getNutritionalValueOfIngredient(Ingredient ingredient, HttpMethod requestType) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("x-app-id", "5059cb0f");
		headers.set("x-app-key", "3755f0e705f4cf64f329a24360c40b47");
		headers.set("x-remote-user-id", "0");
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Ingredient> entity = new HttpEntity<Ingredient>(ingredient,headers);
		ResponseEntity<Object> responseEntity = restTemplate.exchange(URL, requestType, entity, Object.class);
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			return responseEntity.getBody();
		} else {
			return null;
		}
	}
}
