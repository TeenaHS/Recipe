package com.assessment.Recipe.dto;

import com.assessment.Recipe.entity.Recipes;

public class RecipeDTO {
    Integer rid;
    String dish;
    String foodType;
    Integer numberOfServings;
    String ingredients;
    String instruction;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
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

    public Recipes createEntity() {
        Recipes recipe1 = new Recipes();
        recipe1.setRid(this.getRid());
        recipe1.setDish(this.getDish());
        recipe1.setFoodType(this.getFoodType());
        recipe1.setNumberOfServings(this.getNumberOfServings());
        recipe1.setIngredients(this.getIngredients());
        recipe1.setInstruction(this.getInstruction());
        return recipe1;
    }


    //convert entity to dto
    public static RecipeDTO valueOf(Recipes res) {
        RecipeDTO recipedto1 = new RecipeDTO();
        recipedto1.setRid(res.getRid());
        recipedto1.setDish(res.getDish());
        recipedto1.setFoodType(res.getFoodType());
        recipedto1.setNumberOfServings(res.getNumberOfServings());
        recipedto1.setInstruction(res.getInstruction());
        recipedto1.setIngredients(res.getIngredients());
        return recipedto1;
    }
}