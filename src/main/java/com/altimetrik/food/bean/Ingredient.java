package com.altimetrik.food.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(Include.NON_NULL)
public class Ingredient {
	
	private String query;
	private String timezone = "America/Chicago";
	private boolean line_delimited = false;
	private boolean use_raw_foods = false;
	private boolean use_branded_foods = false;

	
	/*public Ingredient(String ingredient) {
		this.query = ingredient;
		this.timezone = "America/Chicago";
		this.line_delimited = false;
		this.use_raw_foods = false;
		this.use_branded_foods = false;
	}*/

}
