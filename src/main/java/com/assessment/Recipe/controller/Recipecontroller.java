package com.assessment.Recipe.controller;

import com.assessment.Recipe.dto.RecipeDTO;
import com.assessment.Recipe.dto.UserDTO;
import com.assessment.Recipe.entity.Recipes;
import com.assessment.Recipe.service.Recipeservice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
public class Recipecontroller {
    @Autowired
    Recipeservice recipeservice;
    @Autowired
    private Environment environment;


    //Get a list of recipes
    @Operation(summary ="Get Recipes",description ="Get a list of recipes", tags = "Get")
    @ApiResponses(value={@ApiResponse(responseCode = "200",description = "Recipe list" ),
    @ApiResponse(responseCode = "404", description = "Recipe list not found")})
    @GetMapping(value= "/recipes")
    public ResponseEntity<List<RecipeDTO>> getAllRecipes() throws Exception {
        List<RecipeDTO> recipeList = recipeservice.getAllRecipes();
        log.info("List of recipes");
        return new ResponseEntity<>(recipeList, HttpStatus.OK);

    }

    //get particular recipe by ID
    @Operation(summary ="Get Recipe",description ="Get a particular recipe by ID", tags = "Get")
    @ApiResponses(value={@ApiResponse(responseCode = "200",description = "Recipe" ),
            @ApiResponse(responseCode = "404", description = "Recipe not found")})
    @GetMapping(value= "/recipes/{rid}")
    public ResponseEntity<RecipeDTO>getRecipe(@PathVariable Integer rid) throws Exception {
        RecipeDTO recipe = recipeservice.getRecipe(rid);
        log.info("Getting a particular recipe by ID:"+ rid);
        return new ResponseEntity<>(recipe, HttpStatus.OK);

    }

    //Add a recipe
    @Operation(summary ="Add Recipe",description ="Add a recipe", tags = "Post")
    @ApiResponses(value={@ApiResponse(responseCode = "200",description = "Add a recipe" ),
            @ApiResponse(responseCode = "404", description = "Recipe added")})
    @PostMapping(value= "/recipes")
    public ResponseEntity<String> addRecipe (@RequestBody RecipeDTO recipe) throws Exception {
        Integer rid = recipeservice.addRecipe(recipe);
        String successMessage = "Recipe inserted successfully : " + rid;
        log.info("Adding the recipe...");
        log.info("Recipe is added to the list");
        return new ResponseEntity<>(successMessage, HttpStatus.CREATED);

    }

    //Update a particular recipe by ID
    @Operation(summary ="Update Recipe",description ="Update a particular recipe by ID", tags = "Update")
    @PutMapping(value= "/recipes/{rid}" )
    public ResponseEntity<Recipes> updateRecipe(@PathVariable Integer rid, @RequestBody RecipeDTO recipe) throws Exception {
       Recipes updateExistingRecipe = recipeservice.updateRecipe(rid,recipe.getNumberOfServings());
        log.info("Updating the recipe...");
        log.info("Updated a particular recipe by ID:"+ rid);
        return new ResponseEntity<>(updateExistingRecipe, HttpStatus.OK);

    }
    //Delete a particular recipe by ID
    @Operation(summary ="Delete Recipe",description ="Delete a particular recipe by ID", tags = "Delete")
    @DeleteMapping(value= "/recipes/{rid}" )
    public ResponseEntity<String> deleteRecipe (@PathVariable Integer rid) throws Exception {
        recipeservice.deleteRecipe(rid);
        String successMessage = environment.getProperty("API.DELETE_SUCCESS");
        System.out.print(successMessage);
        log.info("Deleting the recipe...");
        log.info("Deleted a particular recipe by ID:"+ rid);
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }

    //Filter recipes
    @Operation(summary ="Filter Recipe",description ="Filter recipes ", tags = "Post")
    @PostMapping(value = "/recipes/filter")
    public ResponseEntity<List<Recipes>> getSearch (@RequestBody UserDTO recipe) throws Exception {
        List<Recipes> searchRecipe =  recipeservice.getSearch(recipe);
        log.info("Filtering the recipe...");
        log.info("Recipe is filtered");
        return new ResponseEntity<>(searchRecipe, HttpStatus.OK);
    }

}