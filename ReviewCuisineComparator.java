// Assignment #8, CSE205, Arizona State University 
// Name: Arvin Edouard
// StudentID: 1222200512
// Lecture time: Tuesday Thursday 1:30 to 2:45 
// Description: class with compare method that takes 2 restaurant and returns a based on the cuisine

import java.util.Comparator;

public class ReviewCuisineComparator implements Comparator<Restaurant> {

    public ReviewCuisineComparator(){
        
    }

    public int compare(Restaurant a, Restaurant b) {
        int cuisineDiff = a.getCuisine().getName().compareTo(b.getCuisine().getName());// returns 1 if a before after b
                                                                                       // -1 if b before after a
        if (cuisineDiff == 0) {
            int priceDiff = a.getPriceRange() - b.getPriceRange();// returns + if a is chearper - if b is
            if (priceDiff == 0) {
                int nameDiff = a.getRestaurantName().compareTo(b.getRestaurantName());// return + if a comes first - if
                                                                                      // b comes first
                if (nameDiff == 0) {
                    int locDiff = a.getLocation().compareTo(b.getLocation());// returns 1 if a comes before b -1 if b
                                                                             // before before a
                    if (locDiff == 0) {
                        int reviewDiff = b.getReview().compareTo(a.getReview());// returns 1 if a comes before b -1 if b
                                                                                // before before a
                        return reviewDiff;
                    } else {
                        return locDiff;
                    }
                } else {
                    return nameDiff;
                }
            } else {
                return priceDiff;
            }
        } else {
            return cuisineDiff;
        }
    }

}
