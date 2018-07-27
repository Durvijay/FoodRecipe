package com.altimetrik.food.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.food.bean.Calories;
import com.altimetrik.food.bean.Ingredient;
import com.altimetrik.food.bean.IngredientInfo;
import com.altimetrik.food.bean.Recipe;
import com.altimetrik.food.client.FoodSearchRestClient;
import com.altimetrik.food.client.NutritionalRestClient;
import com.altimetrik.food.exception.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/food")
public class FoodSearchController {
	
	FoodSearchRestClient foodSearchRestClient=new FoodSearchRestClient();
	NutritionalRestClient nutritionalRestClient=new NutritionalRestClient();
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public List<IngredientInfo> foodsearch(@RequestParam String mealName) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		Recipe mealResponse = foodSearchRestClient.getRestRequestForReciepe(mealName, "&q="+mealName, HttpMethod.GET);
		if (mealResponse!=null && !mealResponse.getHits().isEmpty()) {
			Object obj = mealResponse.getHits().get(0);
			HashMap<String, Object> test = (HashMap<String, Object>) obj;
			ObjectMapper objectMapper = new ObjectMapper();
			HashMap<String, Object> test1 = (HashMap<String, Object>) test.get("recipe");
			List<IngredientInfo> recipe = objectMapper.readValue(objectMapper.writeValueAsString(test1.get("ingredients")),new TypeReference<List<IngredientInfo>>(){});
			return recipe;
		} else {
			throw new ResourceNotFoundException("Meal Not Found");
		}
	}
	@RequestMapping(value="/nutrient", method = RequestMethod.GET)
	public Calories getCompleteNutrientValue(@RequestParam String mealName) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		List<IngredientInfo> ingredient = foodsearch(mealName);
		Calories totalCal=new Calories();
		for (IngredientInfo ingredient2 : ingredient) {
			Ingredient ind =new Ingredient();
			ind.setQuery(ingredient2.getText());
			Calories cal = getIngredientNutrient(ind);
			addCalories(totalCal,cal);
		}
		return totalCal;
	}
	
	private void addCalories(Calories totalCal, Calories cal) {
		totalCal.setNf_calories(totalCal.getNf_calories()+cal.getNf_calories());
		totalCal.setNf_total_fat(totalCal.getNf_total_fat()+cal.getNf_total_fat());
		totalCal.setNf_saturated_fat(totalCal.getNf_saturated_fat()+cal.getNf_saturated_fat());
		totalCal.setNf_cholesterol(totalCal.getNf_cholesterol()+cal.getNf_cholesterol());
		totalCal.setNf_sodium(totalCal.getNf_sodium()+cal.getNf_sodium());
		totalCal.setNf_total_carbohydrate(totalCal.getNf_total_carbohydrate()+cal.getNf_total_carbohydrate());
		totalCal.setNf_dietary_fiber(totalCal.getNf_dietary_fiber()+cal.getNf_dietary_fiber());
		totalCal.setNf_sugars(totalCal.getNf_sugars()+cal.getNf_sugars());
		totalCal.setNf_protein(totalCal.getNf_protein()+cal.getNf_protein());
		totalCal.setNf_potassium(totalCal.getNf_potassium()+cal.getNf_potassium());
		totalCal.setNf_p(totalCal.getNf_p()+cal.getNf_p());
		totalCal.setServing_weight_grams(totalCal.getServing_weight_grams()+cal.getServing_weight_grams());
	}
	
	public Calories getIngredientNutrient(Ingredient ingredient) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		HashMap<String, Object> ingredientResponse = (HashMap<String, Object>) nutritionalRestClient.getNutritionalValueOfIngredient(ingredient, HttpMethod.POST);
		if (ingredientResponse!=null && ingredientResponse.get("foods") != null) {
			List<Object> obj = (List<Object>) ingredientResponse.get("foods");
			ObjectMapper objectMapper = new ObjectMapper();
			Calories calories = objectMapper.readValue(objectMapper.writeValueAsString(obj.get(0)), Calories.class);
			return calories;	
		} else {
			throw new ResourceNotFoundException("Ingredients Not Found");
		}
	}

}
