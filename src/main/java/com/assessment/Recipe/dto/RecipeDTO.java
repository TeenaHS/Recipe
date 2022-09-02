package com.assessment.Recipe.dto;

import com.assessment.Recipe.entity.Recipes;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RecipeDTO {
    private Integer rid;
    private String dish;
    private String foodType;
    private Integer numberOfServings;
    private String ingredients;
    private String instruction;

    public RecipeDTO() {
        super();
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