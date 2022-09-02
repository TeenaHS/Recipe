package com.assessment.Recipe.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "recipes")
public class Recipes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RID")
    private Integer rid;
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
        super();
    }
    public Recipes(int rid, String dish, String foodType, int numberOfServings, String ingredients, String instruction) {
    }

}