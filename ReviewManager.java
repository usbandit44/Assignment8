// Assignment #8, CSE205, Arizona State University 
// Name: Arvin Edouard
// StudentID: 1222200512
// Lecture time: Tuesday Thursday 1:30 to 2:45 
// Description: class for ReviewManager object that allows for different manipulations of the arraylist inside the manag/er

import java.io.Serializable;
import java.util.ArrayList;

public class ReviewManager implements Serializable {
    // The serialVersionUID is used to verify compatibility of senders and
    // receivers. See the document for more details:
    // https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/Serializable.html
    private static final long serialVersionUID = 205L;

    ArrayList<Restaurant> reviewList;

     public ReviewManager() {
        reviewList = new ArrayList<>();
    }

    public int restaurantExists(String name, String location){
        boolean match = false;
        int check = 0;
        int quit = 0;
        while(quit < reviewList.size()){
            if(reviewList.get(check).getRestaurantName().equals(name) && reviewList.get(check).getLocation().equals(location)){
                match = true;
                quit = reviewList.size();
            }else{
                check++;
                quit++;
            }
    
        }
        if(match){
            return check;
        }else{
            return -1;
        }
    }

    public ArrayList<Integer> cuisineExists(String a){
        ArrayList<Integer> matches = new ArrayList<Integer>();
        ArrayList<Integer> noMatch = new ArrayList<Integer>();
        noMatch.add(-1);
        for(int i = 0; i < reviewList.size(); i++){
            if(a.equals(reviewList.get(i).getCuisine().getName())){
                matches.add(i);
            }
        }
        if(matches.size() == 0){
            return noMatch;
        }else{
            return matches;
        }
    } 

    public Restaurant getRestaurant(int a){
        return reviewList.get(a);
    }

    public boolean remomveReview(String name, String location){
        if(this.restaurantExists(name, location) == -1){
            return false;
        }else{
            reviewList.remove(this.restaurantExists(name, location));
            return true;
        }
    }

    public void sortByRating(){
        ReviewRatingComparator compare = new ReviewRatingComparator();
        Sorts.sort(reviewList, compare);
    }

    public void sortByCuisine(){
        ReviewCuisineComparator compare = new ReviewCuisineComparator();
        Sorts.sort(reviewList, compare);
    }

    public String listReviews(){
        String restaurants = "";
        if(reviewList.size() > 0){
            for(int i = 0; i < reviewList.size(); i++){
                restaurants = restaurants.concat("\n" + reviewList.get(i).toString() + "\n");
            }
            return restaurants;
        }else{
            return "\n\nNo Reviews available\n";
        }
       
    }

    public void closeReviewManager(){
        reviewList.clear();
    }


    /**
     * Add a Restaurant object to the reviewList and return true if such an object
     * is added successfully. Otherwise, return false. Two restaurants are
     * considered duplicated if they have exactly the same restaurant name and
     * cuisine name.
     * 
     * @param  restaurantName the name of the restaurant
     * @param  stars the number of stars for the restaurant
     * @param  review   the restaurant review
     * @param  priceRange    the integer price range
     * @param  cuisineName  the Cuisine's name
     * @param  location   the restaurant location (street address)
     * @param  signatureDish  most famous dish
     * @return            true if the operation is successful; false otherwise
     */
    public boolean addReview(String restaurantName, int stars, String review, String priceRange, String cuisineName, String location, String signatureDish) {
        if (restaurantExists(restaurantName, location) == -1) {
            int price = priceRange.length();
            Cuisine newCuisine = new Cuisine(signatureDish, cuisineName);
            Restaurant newRestaurant = new Restaurant(restaurantName, stars, review, price, location, newCuisine);
            reviewList.add(newRestaurant);
            return true;
        }
        return false;
    }

}

/*boolean notMatch = true;
        int check = 0;
        while(notMatch){
            if(name.equals(reviewList.get(check).getRestaurantName()) && location.equals(reviewList.get(check).getLocation())){
                notMatch = false;
            }else{
                check++;
            }
            if(check == reviewList.size()){
                notMatch = false;
            }
        }
        if(check == reviewList.size()){
            return false;
        }else{
            return true;
        }*/