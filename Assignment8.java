// Assignment: Assignment8.java
// Name: Arvin Edouard
// StudentID: 1222200512
// Lecture: Tuesday Thursday 1:30 to 2:45
// Description: program that prints menu ad allows user to complete multiple choices fo restaurants

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Assignment8 {
    public static void main(String[] args) {
        // Menu options
        char inputOpt = ' ';
        String inputLine;
        // Restaurant and Cuisine information
        String restaurantName, cuisineName;
        String review = null, location, signatureDish, priceRange;

        int rating;
        // Output information
        String outFilename, inFilename;
        String outMsg, inMsg;
        // Restaurant manager
        ReviewManager reviewManager = new ReviewManager();
        // Operation result
        boolean opResult;     
        
        try {
            printMenu();
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader stdin = new BufferedReader(isr);

            do {
                System.out.print("\nWhat action would you like to perform?\n");
                inputLine = stdin.readLine().trim();
                if (inputLine.isEmpty()) {
                    continue;
                }
                inputOpt = inputLine.charAt(0);
                inputOpt = Character.toUpperCase(inputOpt);

                switch (inputOpt) {

                    case 'A': // Add a new Restaurant Review
                        System.out.print("Please enter the restaurant information:\n");
                        System.out.print("Enter the restaurant name:\n");
                        restaurantName = stdin.readLine().trim();
                        System.out.print("Enter the review:\n");
                        review = stdin.readLine().trim();
                        System.out.print("Enter the price range:\n");
                        priceRange = stdin.readLine().trim();
                        System.out.print("Enter the rating:\n");
                        rating = Integer.parseInt(stdin.readLine().trim());
                        System.out.print("Enter the cuisine name:\n");
                        cuisineName = stdin.readLine().trim();
                        System.out.print("Enter the location:\n");
                        location = stdin.readLine().trim();
                        System.out.print("Enter the signature dish\n");
                        signatureDish = stdin.readLine().trim();
                        
                        /*********************************************************************
                        * Complete the code by calling the addReview method.                 *
                        * If the review has been added successfully, show                    *
                        * "Restaurant added\n" on screen, otherwise "Restaurant NOT added\n" *
                        **********************************************************************/
                        boolean added = reviewManager.addReview(restaurantName, rating, review, priceRange, 
                        cuisineName, location, signatureDish); 
                        if(added){
                            System.out.print("Restaurant added\n");
                        }else if(!added){
                            System.out.print("Restaurant NOT added\n");
                        }

                        break;
                    case 'D': // Search a Restaurant
                        System.out.print("Please enter the restaurant name to search:\n");
                        restaurantName = stdin.readLine().trim();
                        System.out.print("Please enter the restaurant's location':\n");
                        location = stdin.readLine().trim();
                        
                        /*********************************************************************
                        * Complete the code. If a restaurant review exists, print            *
                        * "Restaurant found. Here's the review:\n"                           *
                        * Otherwise, print "Restaurant not found. Please try again\n"        *
                        **********************************************************************/                 
                        int search = reviewManager.restaurantExists(restaurantName, location);
                        if(search == -1){
                            System.out.print("Restaurant not found. Please try again\n");
                        }else{
                            System.out.print("Restaurant found. Here's the review:\n" + reviewManager.getRestaurant(search).getReview() + "\n");
                        }
                        break;
                    case 'E': // Search a cuisine
                        System.out.print("Please enter the cuisine name to search:\n");
                        cuisineName = stdin.readLine().trim();
                        
                        /*******************************************************************************
                        * Complete the code. If a cuisine is found, show on the screen how many       *
                        * restaurants match that cuisine by printing                                  *
                        * "%s Restaurants matching %s cuisine were found:\n" followed by the reviews. *
                        * Otherwise, print "Cuisine: %s was NOT found\n"                              *
                        ******************************************************************************/   
                        ArrayList<Integer> found = reviewManager.cuisineExists(cuisineName);
                        if(found.get(0) == -1){
                            System.out.print("Cuisine: " + cuisineName + " was NOT found\n");
                        }else{
                            String reviewFound = found.size() + " Restaurants matching " + cuisineName + " cuisine were found:\n";
                            System.out.print(reviewFound);
                            for(int i = 0; i < found.size(); i++){
                                System.out.print(reviewManager.getRestaurant(found.get(i)).toString() + "\n\n");
                            }
                            System.out.print("\n");
                        }
                        break;
   
                    case 'L': // List restaurant's reviews
                        System.out.print( reviewManager.listReviews() + "\n\n");
                        break;
                        
                     /******************************************************************************************
                     * Complete the code by adding two cases:                                                  *
                     * case 'N': sorts the restaurant reviews by rating and prints "sorted by rating\n"        *
                     * case 'P': sorts the restaurant reviews by cuisine name and prints "sorted by cuisine\n" *
                     ******************************************************************************************/                        
                    case 'N': 
                        reviewManager.sortByRating();
                        System.out.print("sorted by rating\n");
                        break;
                    
                    case 'P':
                        reviewManager.sortByCuisine();
                        System.out.print("sorted by cuisine\n");
                        break;
                        
                    case 'Q': // Quit
                        break;

                    case 'R': // Remove a review
                        System.out.print("Please enter the restaurant name of the review to remove:\n");
                        restaurantName = stdin.readLine().trim();
                        System.out.print("Please enter the location to remove:\n");
                        location = stdin.readLine().trim();
                        
                        /*******************************************************************************
                        * Complete the code. If a review for a certain restaurant at a given location  *
                        * is found, remove the review and print that it was removed. Otherwise         *
                        * print that it was NOT removed.                                               * 
                        *******************************************************************************/
                        boolean remove = reviewManager.remomveReview(restaurantName, location);
                        if(remove){
                            System.out.print( restaurantName + ", " + location + " was removed\n");
                        }else{
                            System.out.print(restaurantName + ", " + location + " was NOT removed\n");
                        }
                        break;
                        
                    case 'T': // Close reviewList
                        reviewManager.closeReviewManager();
                        System.out.print("Restaurant management system was reset\n");
                        break;

                    case 'U': // Write restaurant names and reviews to a text file
                        System.out.print("Please enter a file name that we will write to:\n");
                        outFilename = stdin.readLine().trim();
                        System.out.print("Please enter the name of the restaurant:\n");
                        restaurantName = stdin.readLine().trim();
                        System.out.println("Please enter a review to save locally:\n");
                        review = stdin.readLine().trim();
                        outMsg = restaurantName + "\n" + review + "\n";
                        
                        /*************************************************************************************
                        * Add a try and catch block to write the string outMsg into the user-specified file  *
                        * Then, print in the screen that the file " is written\n"                            *
                        * In case of an IO Exception, print "Write string inside the file error\n"           *                                                             
                        *************************************************************************************/ 
                        try {
                            FileWriter fw = new FileWriter(outFilename);
                            BufferedWriter bw = new BufferedWriter(fw);
                            PrintWriter outfile = new PrintWriter(bw);
                            outfile.print(outMsg + "\n");
                            System.out.print(outFilename + " is written\n");
                            bw.close();
                        } catch (Exception e) {
                            //TODO: handle exception
                            System.out.print("Write string in file error\n");
                        }                   
                        break;  

                    case 'V': // Read strings from a text file
                        System.out.print("Please enter a file name which we will read from:\n");
                        inFilename = stdin.readLine().trim();
                        
                        /******************************************************************************************
                        * Add a try and catch block to read from the above text file. Confirm that the file       *
                        * " was read\n" and then print "The contents of the file are:\n" followed by the contents *
                        * In case of an IO Exception, print "Read string from file error\n"                       *  
                        * In case of a file not found exception, print that the file " was not found\n"           *                                                             
                        ******************************************************************************************/ 
                        try {
                            FileReader fr = new FileReader(inFilename);
                            BufferedReader br = new BufferedReader(fr);
                            System.out.print(inFilename + " was read\n");
                            System.out.print("The contents of the file are:\n");
                            String line = br.readLine();
                            while(line != null){
                                System.out.println(line);
                                line = br.readLine();
                            }
                            System.out.print("\n");
                            br.close();

                        }
                        catch (FileNotFoundException e){
                            System.out.print(inFilename + " was not found\n");
                        }
                         catch (Exception e) {
                            //TODO: handle exception
                            System.out.print("Read string from file error\n");
                        } 
                
                        break;
 
                    case 'W': // Serialize ReviewManager to a data file
                        System.out.print("Please enter a file name to write:\n");
                        outFilename = stdin.readLine().trim();
                        
                        /****************************************************************************
                        * Add a try and catch block to serialize ReviewManager to a data file.      *
                        * Catch two exceptions and print the corresponding messages on the screen:  *
                        * "Not serializable exception\n"                                            *
                        * "Data file written exception\n"                                           * 
                        ****************************************************************************/   
                        try {
                            FileOutputStream reviewIn = new FileOutputStream("ReviewManager.object");
                            ObjectOutputStream objectToFile = new ObjectOutputStream(reviewIn);
                            objectToFile.writeObject(reviewManager);
                            objectToFile.close();
                        } catch (NotSerializableException e) {
                            //TODO: handle exception
                            System.out.print("Not serializable exception\n");
                        } catch (Exception e)  {
                            System.out.println("Data file written exception\n");
                        }         
                        break;

                    case 'X': // Deserialize ReviewManager from a data file
                        System.out.print("Please enter a file name which we will read from:\n");
                        inFilename = stdin.readLine().trim();
                        
                        /*****************************************************************************
                         * Add a try and catch block to deserialize ReviewManager from a data file.  *
                         * Catch three exceptions and print the corresponding messages on the screen:*
                         * "Class not found exception\n"                                             *
                         * "Not serializable exception\n"                                            * 
                         * "Data file read exception\n"                                              *
                         ****************************************************************************/  
                        try {
                            FileInputStream reviewOut = new FileInputStream("ReviewManager.object");
                            ObjectInputStream readObject = new ObjectInputStream(reviewOut);
                            ReviewManager readManager = null;
                            readManager = (ReviewManager) readObject.readObject();
                            reviewManager = readManager;
                            readObject.close();
                            System.out.print(inFilename + " was read\n");
                        } catch(ClassNotFoundException e){
                            System.out.print("Class not found exception\n");
                        } catch (NotSerializableException e){
                            System.out.print("Not serializable exception\n");
                        } catch (Exception e) {
                            //TODO: handle exception
                            System.out.print("Data file read exception\n");
                        }
                        break;

                    case '?': // Display help
                        printMenu();
                        break;

                    default:
                        System.out.print("Unknown action\n");
                        break;
                }

            } while (inputOpt != 'Q' || inputLine.length() != 1);
        }
        catch (IOException exception) {
            System.out.print("IO Exception\n");
        }
    }

    public static void printMenu() {
        System.out.println("Welcome to Kelp! ");
        System.out.println("Find or post reviews for your favorite (and not so favorite) restaurants.");

        System.out.print("Choice\t\tAction\n" + "------\t\t------\n" + "A\t\tAdd a review\n"
                + "D\t\tSearch for a restaurant\n" + "E\t\tSearch for a cuisine\n"
                + "L\t\tList all reviews\n" + "N\t\tSort by stars\n" + "P\t\tSort by cuisine\n"
                + "Q\t\tQuit\n" + "R\t\tRemove a review\n"
                + "U\t\tAdd personal review to a local file\n" + "V\t\tRetrieve personal review from a local file\n"
                + "W\t\tSave reviews to a file\n"
                + "X\t\tUpload reviews from a file\n"
                + "T\t\t(admin) reset database\n"
                + "?\t\tDisplay Help\n");
    }
}
