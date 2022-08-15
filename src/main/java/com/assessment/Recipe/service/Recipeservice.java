package com.assessment.Recipe.service;

import com.assessment.Recipe.dto.RecipeDTO;
import com.assessment.Recipe.dto.UserDTO;
import com.assessment.Recipe.entity.Recipes;
import com.assessment.Recipe.exception.RecipeException;
import com.assessment.Recipe.repository.Reciperepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class Recipeservice {
    @Autowired
    private Reciperepository reciperepo;

    //Get a list of recipes
    public List<RecipeDTO> getAllRecipes() {
        List<Recipes> recipes = reciperepo.findAll();
        List<RecipeDTO> RecipeDTOs = new ArrayList<RecipeDTO>();
        for (Recipes recipe : recipes) {
            RecipeDTO recipedto1 = RecipeDTO.valueOf(recipe);
            RecipeDTOs.add(recipedto1);
        }
        log.info("Recipe Details : {}", RecipeDTOs);
        return RecipeDTOs;
    }

    //get particular recipe by ID
    public RecipeDTO getRecipe(Integer RID) throws RecipeException {
        Optional<Recipes> optional = reciperepo.findById(RID);
        Recipes recipe = optional.orElseThrow(() -> new RecipeException("Service.RECIPE_NOT_FOUND"));
        RecipeDTO recipe2 = new RecipeDTO();
        recipe2 = RecipeDTO.valueOf(recipe);
        return recipe2;
    }

    //Add a recipe
    public Integer addRecipe(RecipeDTO recipe) throws RecipeException {
        Recipes recipeEntity = recipe.createEntity();
        Recipes recipeEntity2 = reciperepo.save(recipeEntity);
        return recipeEntity2.getRid();
    }

    //Update a particular recipe by ID
    public Recipes updateRecipe(Integer RID, Integer numberofservings) throws Exception {
        Optional<Recipes> searchRecipe = reciperepo.findById(RID);
        Recipes updateRecipe = searchRecipe.orElseThrow(() -> new Exception());
        updateRecipe.setNumberOfServings(numberofservings);
        return updateRecipe;
    }

    //Delete a particular recipe by ID
    public void deleteRecipe(Integer RID) throws RecipeException {
        Optional<Recipes> recipe = reciperepo.findById(RID);
        recipe.orElseThrow(() -> new RecipeException("Service.RECIPE_NOT_FOUND"));
        reciperepo.deleteById(RID);
    }

    //Filter recipes
    public List<Recipes> getSearch(UserDTO recipe) throws RecipeException {
        List<Recipes> returnList=new ArrayList<>();
        List<Recipes>list= reciperepo.findAll();
        list.forEach(r ->{
            if (r.getFoodType().equals(recipe.getFoodType())) {

                if (r.getNumberOfServings().equals(recipe.getNumberOfServings())) {

                    if (r.getIngredients().contains(recipe.getIngredients())) {

                        if (r.getInstruction().contains(recipe.getInstruction())) {
                            returnList.add(r);

                        }
                    }
                }
            }
        });
        if(returnList.isEmpty())
            throw new RecipeException("Service.RECIPE_NOT_FOUND");
        return returnList;
        }

    }
