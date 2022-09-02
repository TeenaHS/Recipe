RECIPE ASSIGNMENT
---

## Project Assessment:

Create a standalone java application which allows users to manage their favourite recipes. It should allow adding, updating, removing and fetching recipes. Additionally users should be able to filter available recipes based on one or more of the following criteria:

1. Whether or not the dish is vegetarian
2. The number of servings
3. Specific ingredients (either include or exclude)
4. Text search within the instructions.

For example, the API should be able to handle the following search requests:

 - All vegetarian recipes
 - Recipes that can serve 4 persons and have “potatoes” as an ingredient
 - Recipes without “salmon” as an ingredient that has “oven” in the instructions.
---
This project has functionalities to Create,Read,Update, Delete(CRUD) recipes and search a recipe from the list of recipes based on user's requirement.

## Instructions:

Clone the repository to your local 
```
https://github.com/TeenaHS/Recipe.git
```
Go to root directory of the code. Run following command to run the application.
```
mvn clean install
```

In case, If you want to run the application directly from IDE. Import the project to your favorite IDE as 'Existing maven project'. Select project from the IDE and run it as java application. Don't forget to update maven. I have configured the port to 8085 in properties. So the application can be accessed by `http://localhost:8085`

Application is then ready to run.

TableScripts for the database can be found in resources folder with the name Recipedb.txt

Swagger is integrated for easy access of API. It can be accessed via : `http://localhost:8085/swagger-ui/index.html`

Url for open api doc: `http://localhost:8085/v3/api-docs`

Json objects required to test the endpoints are given below.

```
Json to add recipe(POST method):
{
        "dish": "Jelly Pudding",
        "foodType": "VEG",
        "numberOfServings": 4,
        "ingredients": "Jelly mix",
        "instructions": "Add the jelly mix in hot water and stir for 10 minutes"
}
```
```
Json to update recipe(PUT method):
{
    "foodType": "NON-VEG"
}
```

## API Details
- Create Recipe

Create recipe service is used to create new recipe. It is POST request which accepts JSON body for recipe.
```
POST 
		'Content-Type: application/json'
		'/recipes'
		
		'Accept: application/json' -d 
		'{ \ 
		   "dish": "string", \ 
		   "foodType": "string",\
		   "numberOfServings": 0, \ 
		   "ingredients": "string",\
		   "instruction": "string", \ 
		}'	
```

- Update Recipe

Update recipe service is used to update existing recipe. It is PUT request which accepts JSON body for recipe along-with ID as path parameter.
```
PUT 
		'Content-Type: application/json' 
		'/recipes/{rid}'
		
		'Accept: application/json' -d 
		'{ \  
		   "numberOfServings": 0, \ 
		}'	 
```

- Get All Recipes

Get all recipe service is used to get list of all the recipes. It is GET request.
```
GET 
		'Accept: application/json' 
		'/recipes'
```

- Find Recipe by ID

Find recipe by ID can be used to fetch particular Recipe. It is GET request which accept ID as path parameter.
```
GET 
		'Accept: application/json' 
		'/recipes/{rid}'
```

- Delete Recipe By ID

Delete recipe by ID can be used to delete particular Recipe. It is DELETE request which accept ID as path parameter.
```
GET 
		'Accept: application/json' 
		'/recipes/{rid}'
```

