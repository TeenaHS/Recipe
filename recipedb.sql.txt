drop database recipedb;
create schema recipedb;
Use  recipedb;
Create table Recipes ( RID integer(10) auto_increment primary key, Dish varchar(50), Foodtype varchar(10),Numberofservings integer(10), Ingredients varchar(200),  Instruction varchar (1000));


Insert into recipes values(1,'Aloo Matar','Veg',3,'Potato,Onion,Tomato,Peas,Garlic,Oil,Salt,Water,Spices','Firstly, prepare tomato puree by adding two medium-sized tomatoes into the blender with little water and keep them aside.Add oil to the POT. Once the oil is hot, add cumin seeds, let the cumin splutter, add onions, ginger-garlic paste, green chili, and fry until onions turn light brown.
Then add prepared tomato puree, red chili powder, turmeric powder, coriander powder, garam masala powder, and cook for 1-2 minutes.
Add peas, potatoes, and salt and mix well. Then add water and give a stir.
Close the Instant Pot with pressure valve to Sealing. Cook on High Pressure for 3 minutes.
Once the pot beeps, let the pressure release naturally for 5 minutes and then do a QUICK RELEASE.
Lastly, remove the lid away from you, add fresh cream');

Insert into recipes values(2,'Chicken Tikka Kabab','Non-Veg',4,'Chicken,Onion,Spices,Salt,Yogurt,Bell pepper,Garlic','Take a bowl. Add chicken cubes to it. Then add all the marination ingredients such as Greek yoghurt, ginger, garlic, lemon juice, Kashmiri red chilli powder, turmeric powder, garam masala, coriander powder, Kasuri methi and salt. Give the ingredients a good mix and make sure all the cubes are coated well. Let the chicken cubes rest for 30 minutes.
Once marinated and you are about to grill the cubes, add the onion, and bell pepper cubes to the marinade. Give it a mix.
Thread the chicken and vegetables alternatively on the skewer. Brush it with some oil. Place them on a prepared tray and bake in a preheated oven at 240 C or 460 F for 9  to 10 mins, turn them and bake for another 9 to 10 mins');


select * from recipes;






