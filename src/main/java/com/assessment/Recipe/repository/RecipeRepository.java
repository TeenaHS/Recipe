package com.assessment.Recipe.repository;

import com.assessment.Recipe.entity.Recipes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipes, Integer> {

}