package com.example.agritech;

import java.util.Comparator;


//Sortfeed in ascending order of price
public class SortFeed implements Comparator<FeedModel> {

    private int price;

// method to be in another class to order the arraylist
    public static final Comparator<FeedModel> ASCENDING_COMPARATOR = new Comparator<FeedModel>() {
        @Override
        public int compare(FeedModel t0, FeedModel t1) {
            return t0.getPrice() - t1.getPrice();
        }
    };

//
    @Override
    public int compare(FeedModel t1, FeedModel t2) {

//        int price1 = t1.getPrice();
//        int price2 = t2.getPrice();

//        return  price1-price2;

        return t1.getPrice() - t2.getPrice();



    }
}
