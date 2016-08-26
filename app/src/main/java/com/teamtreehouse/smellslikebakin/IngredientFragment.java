package com.teamtreehouse.smellslikebakin;

public class IngredientFragment extends CheckBoxesFragment {
    @Override
    public String[] getContents(int index) {
        return Recipes.ingredients[index].split("`");
    }
}
