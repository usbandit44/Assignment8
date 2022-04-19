// Assignment #8, CSE205, Arizona State University 
// Name: Arvin Edouard
// StudentID: 1222200512
// Lecture time: Tuesday Thursday 1:30 to 2:45 
// Description: class with compare method that takes 2 restaurant and returns a based on ratings

import java.util.Comparator;

public class ReviewRatingComparator implements Comparator<Restaurant>{

    public ReviewRatingComparator(){
        
    }

    public int compare(Restaurant a, Restaurant b){
        int starDiff = a.getStars() - b.getStars(); // return + if b has more stars return - if a has more stars
        if(starDiff == 0){
            int nameDiff = a.getRestaurantName().compareTo(b.getRestaurantName());// returns 1 if a before after b -1 if b before before a
            if(nameDiff == 0){
                int locDiff = a.getLocation().compareTo(b.getLocation());// returns 1 if a comes before b -1 if b before before a
                if(locDiff == 0){
                    int reviewDiff = a.getReview().compareTo(b.getReview());// returns 1 if a comes before b -1 if b before before a
                    return reviewDiff;
                }else{
                    return locDiff;
                }
            }else{
                return nameDiff;
            }
        }else{
            return starDiff;
        }
    }
    
}