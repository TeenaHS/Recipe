package com.assessment.Recipe.service;

import com.assessment.Recipe.dto.RecipeDTO;
import com.assessment.Recipe.dto.UserDTO;
import com.assessment.Recipe.entity.Recipes;
import com.assessment.Recipe.exception.RecipeException;
import com.assessment.Recipe.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeRepository reciperepository;

    //Get a list of recipes
    public List<RecipeDTO> getAllRecipes() throws RecipeException {
        List<Recipes> recipes = reciperepository.findAll();
        List<RecipeDTO> RecipeDTOs = new ArrayList<RecipeDTO>();
        for (Recipes recipe : recipes) {
            RecipeDTO recipedto1 = RecipeDTO.valueOf(recipe);
            RecipeDTOs.add(recipedto1);
        }
        log.info("Recipe Details : {}", RecipeDTOs);
        return RecipeDTOs;
    }

    //Get particular recipe by ID
    public RecipeDTO getRecipe(Integer RID) throws RecipeException {
        Optional<Recipes> optional = reciperepository.findById(RID);
        Recipes recipe = optional.orElseThrow(() -> new RecipeException("Service.RECIPE_NOT_FOUND"));
        RecipeDTO recipe2 = new RecipeDTO();
        recipe2 = RecipeDTO.valueOf(recipe);
        return recipe2;
    }

    //Create a recipe
    public Recipes addRecipe(RecipeDTO recipe) throws RecipeException {
        Recipes recipe1 = new Recipes();
        recipe1.setRid(recipe.getRid());
        recipe1.setDish(recipe.getDish());
        recipe1.setFoodType(recipe.getFoodType());
        recipe1.setNumberOfServings(recipe.getNumberOfServings());
        recipe1.setIngredients(recipe.getIngredients());
        recipe1.setInstruction(recipe.getInstruction());
        Recipes newRecipe = reciperepository.save(recipe1);
        return newRecipe;
    }

    //Update a particular recipe by ID
    public Recipes updateRecipe(Integer RID, Integer numberofservings) throws RecipeException {
        Optional<Recipes> searchRecipe = reciperepository.findById(RID);
        Recipes updateRecipe = searchRecipe.orElseThrow(() -> new RecipeException("Service.RECIPE_NOT_FOUND"));
        updateRecipe.setNumberOfServings(numberofservings);
        return updateRecipe;
    }

    //Delete a particular recipe by ID
    public void deleteRecipe(Integer RID) throws RecipeException {
        Optional<Recipes> recipe = reciperepository.findById(RID);
        recipe.orElseThrow(() -> new RecipeException("Service.RECIPE_NOT_FOUND"));
        reciperepository.deleteById(RID);
    }

    //Filter recipes
    public List<Recipes> getSearch(UserDTO recipe) throws RecipeException {
        List<Recipes> returnList = new ArrayList<>();
        List<Recipes> list = reciperepository.findAll();
        list.forEach(r -> {
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
        if (returnList.isEmpty())
            throw new RecipeException("Service.RECIPE_NOT_FOUND");
        return returnList;
    }

}


