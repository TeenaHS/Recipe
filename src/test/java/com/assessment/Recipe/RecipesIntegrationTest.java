package com.assessment.Recipe;
import com.assessment.Recipe.dto.SearchDTO;
import com.assessment.Recipe.entity.Recipes;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import javax.transaction.Transactional;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Transactional
public class RecipesIntegrationTest extends RecipeApplicationTests {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * Integration testcases
     * Test case to Get all recipes
     * @throws Exception
     */
    @Test
    public void recipeList() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    /**
     * Test case to Get a particular recipe
     * @throws Exception
     */
    @Test
    public void recipe() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/recipes/{rid}", 2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)

                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    /**
     * Test case to Add a recipe
     * @throws Exception
     */
    @Test
    public void recipeAdd() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/recipes")
                        .content(asJsonString(new Recipes()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    private String asJsonString(Recipes recipes) {
        try {
            return new ObjectMapper().writeValueAsString(recipes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Test case to Update a particular recipe
     * @throws Exception
     */
    @Test
    public void updateRecipe() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put("/recipes/{rid}",2)
                        .content(asJsonString(new Recipes(2, "dish", "foodType",7 , "ingredients", "instruction")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    /**
     * Test case to Delete a particular recipe
     * @throws Exception
     */
    @Test
    public void deleteRecipe() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/recipes/{rid}", 3)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void searchRecipe() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                        .post("/recipes/filter")
                        .content(asJsonString(new SearchDTO("Non-Veg",4,"Chicken","bowl")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    private String asJsonString(SearchDTO recipes) {
        try {
            return new ObjectMapper().writeValueAsString(recipes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
