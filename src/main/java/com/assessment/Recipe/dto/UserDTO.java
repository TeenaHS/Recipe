package com.assessment.Recipe.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserDTO {

    private String foodType;
    private Integer numberOfServings;
    private String ingredients;
    private String instruction;

    public UserDTO(String foodType, Integer numberOfServings, String ingredients,String instruction) {
        super();
        this.foodType = foodType;
        this.numberOfServings = numberOfServings;
        this.ingredients = ingredients;
        this.instruction = instruction;
    }

}