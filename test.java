import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        Cuisine k = new Cuisine("A", "D");
        Restaurant a = new Restaurant("Five Guys", 4, "Greasy burgers, good but not amazing", 2, "Mill Ave", k);
        Restaurant b = new Restaurant("Chick Fil A", 3, "Good chicken and fries. Too polite!", 1, "University Dr", k);
        Restaurant c = new Restaurant("Spinatos", 5, "Amazing pizzas", 2, "Rio Salado Dr", k);
        Restaurant d = new Restaurant("Hiro", 5, "Best sushi in town!", 2, "90th St", k);
        Restaurant e = new Restaurant("Chick Fil A", 5, "Love the fries! Very clean", 1, "Dobson Rd", k);

        ArrayList<Restaurant> testList = new ArrayList<Restaurant>();
        ReviewRatingComparator compare = new ReviewRatingComparator();
        ReviewCuisineComparator compare2 = new ReviewCuisineComparator();
        testList.add(a);
        testList.add(b);
        testList.add(c);
        testList.add(d);
        testList.add(e);
        Sorts.sort(testList, compare2);

        String restaurants = "";
        for(int i = 0; i < testList.size(); i++){
            restaurants += "\n" + testList.get(i).toString() + "\n";
        }
        System.out.println(restaurants);

    }
    
}
