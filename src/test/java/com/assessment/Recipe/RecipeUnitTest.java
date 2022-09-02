package com.assessment.Recipe;

import com.assessment.Recipe.entity.Recipes;
import com.assessment.Recipe.exception.RecipeException;
import com.assessment.Recipe.repository.RecipeRepository;
import com.assessment.Recipe.service.RecipeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class RecipeUnitTest extends RecipeApplicationTests{
    @MockBean
    private RecipeRepository reciperepository;
    @Autowired
    private RecipeService recipeservice ;

    @Test
    public void getAllRecipesTest() throws RecipeException {
        when(reciperepository.findAll()).thenReturn(Stream.of(new Recipes(1, "Aloo Matar", "Veg", 3, "Potato,Onion,Tomato,Peas,Garlic,Oil,Salt,Water,Spices", "Cook Aloo matar"),
                new Recipes(2, "Chicken Tikka Kabab", "Non-Veg", 4, "Chicken,Onion,Spices,Salt,Yogurt,Bell pepper,Garlic", " Cook Chicken ")).collect(Collectors.toList()));
        assertEquals(2, recipeservice.getAllRecipes().size());
    }

    @Test
    public void getRecipeTest() throws RecipeException {
        Recipes recipes = new Recipes();
        when(reciperepository.findById(anyInt())).thenReturn(Optional.of(recipes));
        recipeservice.getRecipe(anyInt());
    }

    @Test
    public void updateRecipeTest() throws RecipeException {
        Recipes recipe = new Recipes();
        Integer rid = 2;
        recipe.setNumberOfServings(6);
        when(reciperepository.findById(rid)).thenReturn(Optional.of(recipe));
        assertEquals(recipe,recipeservice.updateRecipe(rid,recipe.getNumberOfServings()));
    }

    @Test
    public void deleteRecipeTest() throws RecipeException {
        reciperepository.deleteById(1);
        verify(reciperepository).deleteById(1);
    }

}


