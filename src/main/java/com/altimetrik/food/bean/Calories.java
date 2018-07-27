package com.altimetrik.food.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Calories {
	
	private float nf_calories;
	private float nf_total_fat;
	private float nf_saturated_fat;
	private float nf_cholesterol;
	private float nf_sodium;
	private float nf_total_carbohydrate;
	private float nf_dietary_fiber;
	private float nf_sugars;
	private float nf_protein;
	private float nf_potassium;
	private float nf_p;
	private float serving_weight_grams;
	
}
