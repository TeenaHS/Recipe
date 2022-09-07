package com.assessment.Recipe.service;

import com.assessment.Recipe.dto.RecipeDTO;
import com.assessment.Recipe.dto.UserDTO;
import com.assessment.Recipe.entity.Recipes;
import com.assessment.Recipe.exception.RecipeException;
import com.assessment.Recipe.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeRepository reciperepository;

    /**
     * @return Will return list of all Recipes in database
     * @throws RecipeException
     */
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

    /**
     * @param rid Recipes id which need to be found
     * @return one record matching the Recipes ID
     * @throws RecipeException
     */
    public RecipeDTO getRecipe(Integer rid) throws RecipeException {
        Optional<Recipes> optional = reciperepository.findById(rid);
        Recipes recipe = optional.orElseThrow(() -> new RecipeException("Service.RECIPE_NOT_FOUND"));
        RecipeDTO recipe2 = new RecipeDTO();
        recipe2 = RecipeDTO.valueOf(recipe);
        return recipe2;
    }

    /**
     * @param recipe information need to save in database
     * @return Saved object into database
     * @throws RecipeException
     */
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

    /**
     * @param rid Recipes id which need to be updated
     * @param numberofservings Information need to be updated
     * @return Saved object into database
     * @throws RecipeException
     */
    public Recipes updateRecipe(Integer rid, Integer numberofservings) throws RecipeException {
        Optional<Recipes> searchRecipe = reciperepository.findById(rid);
        Recipes updateRecipe = searchRecipe.orElseThrow(() -> new RecipeException("Service.RECIPE_NOT_FOUND"));
        updateRecipe.setNumberOfServings(numberofservings);
        return updateRecipe;
    }

    /**
     * @param rid Recipe id which need to be deleted
     * @throws RecipeException
     */
    public void deleteRecipe(Integer rid) throws RecipeException {
        Optional<Recipes> recipe = reciperepository.findById(rid);
        recipe.orElseThrow(() -> new RecipeException("Service.RECIPE_NOT_FOUND"));
        reciperepository.deleteById(rid);
    }

    /**
     * @param recipe information saved in database
     * @return will return list of recipes that matches the criteria
     * @throws RecipeException
     */
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


