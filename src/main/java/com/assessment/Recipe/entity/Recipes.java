package com.assessment.Recipe.entity;

import javax.persistence.*;

@Entity
@Table(name = "recipes")
public class Recipes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RID")
    private Integer rid ;
    @Column(name = "Dish")
    private String dish;
    @Column(name = "Foodtype")
    private String foodType;
    @Column(name = "Numberofservings")
    private Integer numberOfServings;
    @Column(name = "Ingredients")
    private String ingredients;
    @Column(name = "Instruction")
    private String instruction;

    public Recipes() {

    }

    public Recipes(int rid, String dish, String foodType, Integer numberOfServings, String ingredients, String instruction) {
    }


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
}
