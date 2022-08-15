package com.assessment.Recipe.dto;

public class UserDTO {

    String foodType;
    Integer numberOfServings;
    String ingredients;
    String instruction;

    public UserDTO(String foodType, Integer numberOfServings, String ingredients, String instruction) {
        this.foodType = foodType;
        this.numberOfServings = numberOfServings;
        this.ingredients = ingredients;
        this.instruction = instruction;
    }

    public UserDTO() {

    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public Integer getNumberOfServings() {
        return numberOfServings;
    }

    public void setNumberOfServings(Integer numberOfServings) {
        this.numberOfServings = numberOfServings;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
