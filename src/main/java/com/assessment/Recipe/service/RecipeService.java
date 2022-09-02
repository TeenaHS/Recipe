package com.assessment.Recipe.service;

import com.assessment.Recipe.dto.RecipeDTO;
import com.assessment.Recipe.dto.UserDTO;
import com.assessment.Recipe.entity.Recipes;
import com.assessment.Recipe.exception.RecipeException;
import java.util.List;

public interface RecipeService {
    public List<RecipeDTO> getAllRecipes() throws RecipeException;
    public RecipeDTO getRecipe(Integer RID) throws RecipeException;
    public Recipes addRecipe(RecipeDTO recipe) throws RecipeException;
    public Recipes updateRecipe(Integer RID, Integer numberofservings) throws RecipeException;
    public void deleteRecipe(Integer RID) throws RecipeException;
    public List<Recipes> getSearch(UserDTO recipe) throws RecipeException;

}
