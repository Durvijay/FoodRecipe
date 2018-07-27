package com.altimetrik.food.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@ToString
public class Recipe {
	
	private String q;
	private List<Object> hits;
	
}
