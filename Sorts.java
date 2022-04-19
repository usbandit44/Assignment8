// Assignment #8, CSE205, Arizona State University 
// Name: Arvin Edouard
// StudentID: 1222200512
// Lecture time: Tuesday Thursday 1:30 to 2:45 
// Description: uses a comparator and sorts an arraylist using selection sorting 

import java.util.ArrayList;
import java.util.Comparator;

public class Sorts {

    public static void sort(ArrayList<Restaurant> reviewList, Comparator<Restaurant> xComparator){
         int better;
         for(int i = 0; i < reviewList.size() - 1; i++){
             better = i;
             for(int f = i + 1; f < reviewList.size(); f++){
                if(xComparator.compare(reviewList.get(better), reviewList.get(f)) > 0){
                    better = f;
                }
             }
             swap(reviewList, better, i);
         }
    }

    private static void swap(ArrayList<Restaurant> reviewList, int a, int b){
        Restaurant temp = reviewList.get(a);
        reviewList.set(a, reviewList.get(b));
        reviewList.set(b, temp);
    }
    
}

