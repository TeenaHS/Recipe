package com.assessment.Recipe;

import com.assessment.Recipe.dto.RecipeDTO;
import com.assessment.Recipe.entity.Recipes;
import com.assessment.Recipe.exception.RecipeException;
import com.assessment.Recipe.repository.RecipeRepository;
import com.assessment.Recipe.service.RecipeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RecipeUnitTest extends RecipeApplicationTests{
    @MockBean
    private RecipeRepository reciperepository;
    @Autowired
    private RecipeService recipeservice ;

    /**
     * Unit testcases for service class
     * Test case to Get all recipes
     * @throws RecipeException
     */
    @Test
    public void getAllRecipesTest() throws RecipeException {
        when(reciperepository.findAll()).thenReturn(Stream.of(new Recipes(1, "Aloo Matar", "Veg", 3, "Potato,Onion,Matar", "Cook Aloo matar"),
                new Recipes(2, "Chicken Tikka Kabab", "Non-Veg", 4, "Chicken,Onion,Spices", " Cook Chicken ")).collect(Collectors.toList()));
        assertEquals(2, recipeservice.getAllRecipes().size());
    }

    /**
     * Test case to Get a particular recipe
     * @throws RecipeException
     */
    @Test
    public void getRecipeTest() throws RecipeException {
        Recipes recipes = new Recipes();
        when(reciperepository.findById(anyInt())).thenReturn(Optional.of(recipes));
        recipeservice.getRecipe(anyInt());
    }

    /**
     * Test case to Add a new recipe
     * @throws RecipeException
     */
    @Test
    public void addRecipeTest() throws RecipeException{
        Recipes recipe=new Recipes(9, "Aloo Matar", "Veg", 3, "Potato,Onion,Matar", "Cook Aloo matar");
        RecipeDTO recipedto=new RecipeDTO(9, "Aloo Matar", "Veg", 3, "Potato,Onion,Matar", "Cook Aloo matar");
        when(reciperepository.save(any(Recipes.class))).thenReturn(recipe);
        assertEquals(recipe,recipeservice.addRecipe(recipedto));
    }

    /**
     * Test case to Update a particular recipe
     * @throws RecipeException
     */
    @Test
    public void updateRecipeTest() throws RecipeException {
        Recipes recipe = new Recipes();
        Integer rid = 2;
        recipe.setNumberOfServings(6);
        when(reciperepository.findById(rid)).thenReturn(Optional.of(recipe));
        assertEquals(recipe,recipeservice.updateRecipe(rid,recipe.getNumberOfServings()));
    }

    /**
     * Test case to Delete a particular recipe
     * @throws RecipeException
     */
    @Test
    public void deleteRecipeTest() throws RecipeException {
        reciperepository.findById(anyInt());
        verify(reciperepository).findById(anyInt());
    }

    /************************************************************************/

    /**
     * Test cases for invalid scenarios
     * @throws RecipeException
     */
    @Test
    public void getAllRecipesInvalidTest() throws RecipeException{
        List<Recipes> recipes=new ArrayList<Recipes>();
        when(reciperepository.findAll()).thenReturn(recipes);
        try
        {
            recipeservice.getAllRecipes();
        }
        catch(RecipeException recipe)
        {
            String successMessage = "Recipe not found";
            assertEquals(successMessage, recipe.getMessage());
        }
    }

    /**
     * @throws RecipeException
     */
    @Test
    public void getRecipeInvalidTest() throws RecipeException{
        Optional<Recipes> recipes=Optional.of(new Recipes());
        when(reciperepository.findById(anyInt())).thenReturn(recipes);
        try
        {
            recipeservice.getRecipe(anyInt());
        }
        catch(RecipeException recipe)
        {
            String successMessage = "Recipe not found";
            assertEquals(successMessage, recipe.getMessage());
        }
    }

    /**
     * @throws RecipeException
     */
    @Test
    public void updateRecipeInvalidTest() throws RecipeException{
        Optional<Recipes> recipes=Optional.of(new Recipes());
        when(reciperepository.findById(1)).thenReturn(recipes);
        try
        {
            recipeservice.updateRecipe(1,5);
        }
        catch(RecipeException recipe)
        {
            String successMessage = "Recipe not found";
            assertEquals(successMessage, recipe.getMessage());
        }
    }

    /**
     * @throws RecipeException
     */
    @Test
    public void deleteRecipeInvalidTest() throws RecipeException{
        Optional<Recipes> recipes=Optional.of(new Recipes());
        when(reciperepository.findById(1)).thenReturn(recipes);
        try
        {
            recipeservice.deleteRecipe(1);
        }
        catch(RecipeException recipe)
        {
            String successMessage = "Recipe not found";
            assertEquals(successMessage, recipe.getMessage());
        }
    }

}
