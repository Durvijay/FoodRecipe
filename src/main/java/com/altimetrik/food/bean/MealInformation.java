package com.altimetrik.food.bean;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MealInformation {
	private HashMap<String, Object> recipe;
}
