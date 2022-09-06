package com.assessment.Recipe;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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

    @Test
    public void deleteRecipe() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/recipes/{rid}", 1)
                )
                .andExpect(status().isOk());
    }
}




