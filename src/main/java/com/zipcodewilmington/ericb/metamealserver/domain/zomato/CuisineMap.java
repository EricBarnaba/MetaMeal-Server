package com.zipcodewilmington.ericb.metamealserver.domain.zomato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CuisineMap {
    private HashMap<String, Integer> map;


    public CuisineMap(){
        this.map = new HashMap<>();
        buildMap();
    }

    private void buildMap(){
        map.put("afghani", 6);
        map.put("african", 152);
        map.put("american",1);
        map.put("asian",3);
        map.put("bbq",193);
        map.put("bar_food",227);
        map.put("beverages", 270);
        map.put("breakfast",182);
        map.put("bubble_tea", 247);
        map.put("burger",168);
        map.put("cafe",30);
        map.put("cajun", 491);
        map.put("caribbean", 158);
        map.put("chinese",25);
        map.put("coffee_and_tea", 161);
        map.put("crepes", 881);
        map.put("deli",192);
        map.put("desserts", 100);
        map.put("dim_sum", 411);
        map.put("diner",541);
        map.put("donuts",959);
        map.put("drinks_only", 268);
        map.put("european", 38);
        map.put("fast_food", 40);
        map.put("filipino", 112);
        map.put("french", 45);
        map.put("frozen_yogurt", 501);
        map.put("german", 134);
        map.put("greek", 156);
        map.put("healthy_food",143);
        map.put("ice_cream", 233);
        map.put("indian", 148);
        map.put("international", 154);
        map.put("irish", 135);
        map.put("italian", 55);
        map.put("jamaican", 207);
        map.put("japanese", 60);
        map.put("korean", 67);
        map.put("latin_american", 136);
        map.put("mediterranean", 70);
        map.put("mexican",73);
        map.put("middle_eastern", 137);
        map.put("moroccan", 147);
        map.put("new_american", 996);
        map.put("peruvian", 162);
        map.put("pizza", 82);
        map.put("pub_food", 983);
        map.put("ramen", 320);
        map.put("salad", 998);
        map.put("sandwich", 304);
        map.put("seafood",83);
        map.put("southern", 471);
        map.put("southwestern",966);
        map.put("spanish",89);
        map.put("steak", 141);
        map.put("sushi", 177);
        map.put("tapas", 179);
        map.put("tea", 163);
        map.put("tex-mex", 150);
        map.put("thai", 95);
        map.put("vegetarian",308);
        map.put("vietnamese",99);


    }


}
