// Assignment #8, CSE205, Arizona State University 
// Name: Arvin Edouard
// StudentID: 1222200512
// Lecture time: Tuesday Thursday 1:30 to 2:45 
// Description: Resturant class that allows the object to be instaciated get instance variables and print toString

import java.io.Serializable;

public class Restaurant implements Serializable{

    private static final long serialVersionUID = 205L; 
    private String restaurantName;
    private int stars;
    private String review;
    private int priceRange;
    private String location;
    private Cuisine cuisine;

    public Restaurant(String name, int s, String r, int price, String l, Cuisine c){
        restaurantName = name;
        stars = s;
        review = r;
        priceRange = price;
        location = l;
        cuisine = c;

    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public int getStars() {
        return stars;
    }

    public int getPriceRange() {
        return priceRange;
    }

    public String getLocation() {
        return location;
    }

    public String getReview() {
        return review;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    @Override
    public String toString() {
        int starNumber = this.getStars();
        int priceNumber = this.priceRange;
        String star = "";
        String price = "";

        for(int i = 0; i < starNumber; i++){
            star = star.concat("*");
        }

        for(int i = 0; i < priceNumber; i++){
            price = price.concat("$");
        }
        String a = restaurantName + " restaurant\n" + star + "\t\t" + price + "\n" +  cuisine.toString() + "Location: " + 
        location + "\n" + "Review:\t" + review;
        return a;
    }
}