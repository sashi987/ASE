package com.example.shashi.giveaway;

/**
 * Created by Shashi on 28-04-2015.
 */
public class Categorydetails {
    public String itemcategory;
    public int count;



    public void setCount(int value) {
        this.count = value;
    }

    public void setItemcategory(String category) {
       this.itemcategory = category;
    }
    public int getCount(){return count;}
    public String getItemcategory(){return itemcategory;}
}
