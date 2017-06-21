package mkobilas.homework.roadtrip;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * The main driver class for the entire Road Trip program. This class hosts the menu used to pick the options that will
 *   manipulate the various objects created by the other classes including Itineraries, TripStopNodes, and TripStops.
 *   The main method comes with two instantiated and initialized Itinerary objects which may be swapped between and have
 *   TripStopNodes copied between. The other options allow for the user to move the cursor, delete, edit, and add
 *   TripStopNodes throughout the Itinerary using the cursor as a reference. The menu also allows the user to print out
 *   a formatted table of the contents of the Itinerary that is currently being edited.
 * @author Matthew
 *   matthew.kobilas@stonybrook.edu
 */
public class TripPlanner{
    //Used to determine whether the first or second Itinerary object is being handled currently.
    private static boolean operatingSecondItinerary = false;
    /**
     * The main method for the TripPlanner class contains a while loop that runs until the user presses the 'q' command,
     *   causing the program to halt operation and quit gracefully. The menu also allows the user to move the cursor
     *   forward and backward using the 'f' and 'b' keys respectively. This method also allows the user to add a
     *   TripStopNode to the Itinerary before the cursor using the 'i' command. The user can also add a TripStopNode to
     *   the end of the Itinerary using the 'a' command. The 'd' command deletes the TripStopNode currently being 
     *   referenced by the cursor and then moving the cursor forward to the next TripStopNode. The 'h' and 't' commands
     *   move the cursor to the start and the end of the Itinerary object, respectively. Pressing 'e' will allow the
     *   user to edit the data of the currently selected TripStopNode, changing the parameters of the TripStop object
     *   wrapped within it. The 's' key will switch the Itinerary that is currently being used. 'o' will insert the
     *   selected object from the other Itinerary to the currently selected one. 'r' will replace the Itinerary 
     *   completely with the other. The 'p' key will print the currently selected Itinerary along with a summary. The 
     *   'c' key will completely clear the currently selected Itinerary.
     * @param args
     *   The parameter String[] args is simply used to create a main method in this class, and is not used otherwise.
     * @precondition
     *   Valid input from the keyboard that corresponds with the prompts that appear on-screen.
     * @postcondition
     *   The two Itineraries found in the main method are manipulated as requested by the user.
     * @postcondition
     *   Program shuts down if the user enters 'q'.
     */
    public static void main(String[] args){
    Scanner keyboard = new Scanner(System.in);
    //Used to determine whether the response entered matches any of the options presented in the menu.
    boolean invalidResponse = false;
    //String input from keyboard for the answer to the menu options.
    String unformattedAnswer;
    //char answer to the menu options
    char answer;
    Itinerary firstItinerary = new Itinerary();
    Itinerary secondItinerary = new Itinerary();
    String tempLocation;
    String tempActivity;
    int tempDistance;
    //Stores the location of the cursor in order to send the cursor back to that TripStopNode once an operation is done.
    int storeLocation;
    //Holds the information to the oldDistance of a TripStop when editing the TripStop object's distance.
    int oldDistance;
    System.out.print("Welcome to TripPlanner!");
    while(true){
        if(invalidResponse){
            System.out.println("\nNo such operation.");
            invalidResponse = false;
        }
        else{
            printOptions();
            System.out.println("");
        }
        //Finds what answer the user input.
        unformattedAnswer = keyboard.nextLine();
        System.out.println("");
        if(unformattedAnswer.length() != 1){
            invalidResponse = true;
            continue;
        }
        answer = unformattedAnswer.toLowerCase().charAt(0);
        if(answer ==  'q')
            break;
        switch(answer){
            case 'f':
                if(operatingSecondItinerary){
                    try{
                        secondItinerary.cursorForward();
                        System.out.println("Cursor moved forward.");
                        continue;
                    }
                    catch(EndOfItineraryException err){
                        System.out.println("Cursor is at the end of the second itinerary and cannot move any further " +
                          "forwards.");
                        continue;
                    }
                    catch(EmptyItineraryException err){
                        System.out.println("Second itinerary is currently empty.");
                        continue;
                    }
                }
                else{
                    try{
                        firstItinerary.cursorForward();
                        System.out.println("Cursor moved forward.");
                        continue;
                    }
                    catch(EndOfItineraryException err){
                        System.out.println("Cursor is at the end of the first itinerary and cannot move any further " + 
                          "forwards.");
                        continue;
                    }
                    catch(EmptyItineraryException err){
                        System.out.println("First itinerary is currently empty.");
                        continue;
                    }
                }
            case 'b':
                if(operatingSecondItinerary){
                    try{
                        secondItinerary.cursorBackward();
                        System.out.println("Cursor moved backward.");
                        continue;
                    }
                    catch(EndOfItineraryException err){
                        System.out.println("Cursor is at the start of the second itinerary and cannot move any " +
                          "further backwards.");
                        continue;
                    }
                    catch(EmptyItineraryException err){
                        System.out.println("Second itinerary is currently empty.");
                        continue;
                    }
                }
                else{
                    try{
                        firstItinerary.cursorBackward();
                        System.out.println("Cursor moved backwards.");
                        continue;
                    }
                    catch(EndOfItineraryException err){
                        System.out.println("Cursor is at the start of the first itinerary and cannot move any " +
                          "further backwards.");
                        continue;
                    }
                    catch(EmptyItineraryException err){
                        System.out.println("First itinerary is currently empty.");
                        continue;
                    }
                }
            case 'i':
                if(operatingSecondItinerary){
                    System.out.print("Enter Location: ");
                    tempLocation = keyboard.nextLine();
                    System.out.print("\nEnter Distance: ");
                    while(true){
                        try{
                            tempDistance = Integer.parseInt(keyboard.nextLine());
                            if(tempDistance < 0){
                                System.out.print("\nThis distance is invalid -- distances must be >= 0. Try again.");
                                System.out.print("\nEnter Distance: ");
                            }
                            else
                                break;
                        }
                        catch(InputMismatchException err){
                            System.out.print("\nThis distance is invalid -- distances must be integers. Try again.");
                            System.out.print("\nEnter Distance: ");
                        }
                        catch(NumberFormatException err){
                            System.out.print("\nThis distance is invalid -- distances must be integers. Try again.");
                            System.out.print("\nEnter Distance: ");
                        }
                    }
                    System.out.print("\nEnter Activity: ");
                    tempActivity = keyboard.nextLine();
                    try{
                        secondItinerary.insertBeforeCursor(new TripStop(tempLocation, tempDistance, tempActivity));
                        System.out.println("\nAdded.");
                        continue;
                    }
                    catch(EndOfItineraryException err){
                        System.out.println("Cursor is at the start of the second itinerary and cannot move any " +
                          "further backwards.");
                        continue;
                    }
                    catch(EmptyItineraryException err){
                        System.out.println("Second itinerary is currently empty.");
                        continue;
                    }
                    catch(IllegalArgumentException err){
                        System.out.println("New TripStop object cannot be null.");
                        continue;
                    }
                }
                else{
                    System.out.print("Enter Location: ");
                    tempLocation = keyboard.nextLine();
                    System.out.print("\nEnter Distance: ");
                    while(true){
                        try{
                            tempDistance = Integer.parseInt(keyboard.nextLine());
                            if(tempDistance < 0){
                                System.out.print("\nThis distance is invalid -- distances must be >= 0. Try again.");
                                System.out.print("\nEnter Distance: ");
                            }
                            else
                                break;
                        }
                        catch(InputMismatchException err){
                            System.out.print("\nThis distance is invalid -- distances must be integers. Try again.");
                            System.out.print("\nEnter Distance: ");
                        }
                        catch(NumberFormatException err){
                            System.out.print("\nThis distance is invalid -- distances must be integers. Try again.");
                            System.out.print("\nEnter Distance: ");
                        }
                    }
                    System.out.print("\nEnter Activity: ");
                    tempActivity = keyboard.nextLine();
                    try{
                        firstItinerary.insertBeforeCursor(new TripStop(tempLocation, tempDistance, tempActivity));
                        System.out.println("\nAdded.");
                        continue;
                    }
                    catch(EndOfItineraryException err){
                        System.out.println("Cursor is at the start of the first itinerary and cannot move any " +
                          "further backwards.");
                        continue;
                    }
                    catch(EmptyItineraryException err){
                        System.out.println("First itinerary is currently empty.");
                        continue;
                    }
                    catch(IllegalArgumentException err){
                        System.out.println("New TripStop object cannot be null.");
                        continue;
                    }
                }
            case 'a':
                if(operatingSecondItinerary){
                    System.out.print("Enter Location: ");
                    tempLocation = keyboard.nextLine();
                    System.out.print("\nEnter Distance: ");
                    while(true){
                        try{
                            tempDistance = Integer.parseInt(keyboard.nextLine());
                            if(tempDistance < 0){
                                System.out.print("\nThis distance is invalid -- distances must be >= 0. Try again.");
                                System.out.print("\nEnter Distance: ");
                            }
                            else
                                break;
                        }
                        catch(InputMismatchException err){
                            System.out.print("\nThis distance is invalid -- distances must be integers. Try again.");
                            System.out.print("\nEnter Distance: ");
                        }
                        catch(NumberFormatException err){
                            System.out.print("\nThis distance is invalid -- distances must be integers. Try again.");
                            System.out.print("\nEnter Distance: ");
                        }
                    }
                    System.out.print("\nEnter Activity: ");
                    tempActivity = keyboard.nextLine();
                    try{
                        secondItinerary.appendToTail(new TripStop(tempLocation, tempDistance, tempActivity));
                        System.out.println("\nAdded.");
                        continue;
                    }
                    catch(IllegalArgumentException err){
                        System.out.println("New TripStop object cannot be null.");
                        continue;
                    }
                }
                else{
                    System.out.print("Enter Location: ");
                    tempLocation = keyboard.nextLine();
                    System.out.print("\nEnter Distance: ");
                    while(true){
                        try{
                            tempDistance = Integer.parseInt(keyboard.nextLine());
                            if(tempDistance < 0){
                                System.out.print("\nThis distance is invalid -- distances must be >= 0. Try again.");
                                System.out.print("\nEnter Distance: ");
                            }
                            else
                                break;
                        }
                        catch(InputMismatchException err){
                            System.out.print("\nThis distance is invalid -- distances must be integers. Try again.");
                            System.out.print("\nEnter Distance: ");
                        }
                        catch(NumberFormatException err){
                            System.out.print("\nThis distance is invalid -- distances must be integers. Try again.");
                            System.out.print("\nEnter Distance: ");
                        }
                    }
                    System.out.print("\nEnter Activity: ");
                    tempActivity = keyboard.nextLine();
                    try{
                        firstItinerary.appendToTail(new TripStop(tempLocation, tempDistance, tempActivity));
                        System.out.println("\nAdded.");
                        continue;
                    }
                    catch(IllegalArgumentException err){
                        System.out.println("New TripStop object cannot be null.");
                        continue;
                    }
                }
            case 'd':
                if(operatingSecondItinerary){
                    try{
                        secondItinerary.removeCursor();
                        System.out.println("Deleted cursor.");
                        continue;
                    }
                    catch(EndOfItineraryException err){
                        System.out.println("Cursor is at the start of the second itinerary and cannot move any " +
                          "further backwards.");
                        continue;
                    }
                    catch(EndOfListException err){
                        System.out.println("Cursor is currently set to null because second itinerary is empty.");
                        continue;
                    }
                    catch(EmptyItineraryException err){
                        System.out.println("Second itinerary is currently empty.");
                        continue;
                    }
                    catch(NullPointerException err){
                        System.out.println("Cursor cannot locate object in second itinerary that is supposed to be " + 
                          "changed.");
                        continue;
                    }
                }
                else{
                    try{
                        firstItinerary.removeCursor();
                        System.out.println("Deleted cursor.");
                        continue;
                    }
                    catch(EndOfItineraryException err){
                        System.out.println("Cursor is at the start of the first itinerary and cannot move any " +
                          "further backwards.");
                        continue;
                    }
                    catch(EndOfListException err){
                        System.out.println("Cursor is currently set to null because first itinerary is empty.");
                        continue;
                    }
                    catch(EmptyItineraryException err){
                        System.out.println("First itinerary is currently empty.");
                        continue;
                    }
                    catch(NullPointerException err){
                        System.out.println("Cursor cannot locate object in second itinerary that is supposed to be " + 
                          "changed.");
                        continue;
                    }
                }
            case 'h':
                if(operatingSecondItinerary){
                    secondItinerary.resetCursorToHead();
                    System.out.println("Cursor sent to the start of the itinerary.");
                    continue;
                }
                else{
                    firstItinerary.resetCursorToHead();
                    System.out.println("Cursor sent to the start of the itinerary.");
                    continue;
                }
            case 't':
                if(operatingSecondItinerary){
                    if(secondItinerary.getCursorStop() ==  null){
                        System.out.println("Second itinerary is currently empty.");
                        continue;
                    }
                    else{
                        secondItinerary.resetCursorToTail();
                        System.out.println("Cursor sent to the end of the itinerary.");
                        continue;
                    }
                }
                else{
                    if(firstItinerary.getCursorStop() ==  null){
                        System.out.println("Second itinerary is currently empty.");
                        continue;
                    }
                    else{
                        firstItinerary.resetCursorToTail();
                        System.out.println("Cursor sent to the end of the itinerary.");
                        continue;
                    }
                }
            case 'e':
                if(operatingSecondItinerary){
                    try{
                        System.out.print("Edit Location, or press enter without typing anything to keep: " +
                          secondItinerary.getCursorStop().getLocation() + "  ");
                    }
                    catch(NullPointerException err){
                        System.out.println("The second itinerary is currently empty.");
                        continue;
                    }
                    //Asks user for input for the new location.
                    tempLocation = keyboard.nextLine();
                    System.out.print("\nEdit Distance, or press -1 without typing anything to keep: " +
                      firstItinerary.getCursorStop().getDistance() + "  ");
                    //Asks user for input for the new distance.
                    oldDistance = secondItinerary.getCursorStop().getDistance();
                    while(true){
                        try{
                            tempDistance = Integer.parseInt(keyboard.nextLine());
                            if((tempDistance < 0) && (tempDistance != -1)){
                                System.out.print("\nThis distance is invalid -- distances must be >= 0. Try again.");
                                System.out.print("\nEdit Distance, or press -1 without typing anything to keep: " +
                                  oldDistance + "  ");
                            }
                            else
                                break;
                        }
                        catch(InputMismatchException err){
                            System.out.print("\nThis distance is invalid -- distances must be integers. Try again.");
                            System.out.print("\nEdit Distance, or press -1 without typing anything to keep: " +
                              oldDistance + "  ");
                        }
                        catch(NumberFormatException err){
                            System.out.print("\nThis distance is invalid -- distances must be integers. Try again.");
                            System.out.print("\nEdit Distance, or press -1 without typing anything to keep: " +
                              oldDistance + "  ");
                        }
                    }
                    //Asks user for input for the new activity.
                    System.out.print("\nEdit Activity, or press enter without typing anything to keep: " + 
                      secondItinerary.getCursorStop().getActivity() + "  ");
                    tempActivity = keyboard.nextLine();
                    if(!(tempLocation.isEmpty()))
                        secondItinerary.getCursorStop().setLocation(tempLocation);
                    if(!(tempActivity.isEmpty()))
                        secondItinerary.getCursorStop().setActivity(tempActivity);
                    if(tempDistance != -1){
                        secondItinerary.getCursorStop().setDistance(tempDistance);
                        secondItinerary.changeDistance(tempDistance - oldDistance);
                    }
                    System.out.println("Edits applied.");
                    continue;
                }
                else{
                    try{
                        System.out.print("Edit Location, or press enter without typing anything to keep: " +
                          firstItinerary.getCursorStop().getLocation() + "  ");
                    }
                    catch(NullPointerException err){
                        System.out.println("The first itinerary is currently empty.");
                        continue;
                    }
                    tempLocation = keyboard.nextLine();
                    System.out.print("\nEdit Distance, or press -1 to keep: " +
                      firstItinerary.getCursorStop().getDistance() + "  ");
                    oldDistance = firstItinerary.getCursorStop().getDistance();
                    while(true){
                        try{
                            tempDistance = Integer.parseInt(keyboard.nextLine());
                            if((tempDistance < 0) && (tempDistance != -1)){
                                System.out.print("\nThis distance is invalid -- distances must be >= 0. Try again.");
                                System.out.print("\nEdit Distance, or press -1 to keep: " +
                                  oldDistance + "  ");
                            }
                            else
                                break;
                        }
                        catch(InputMismatchException err){
                            System.out.print("\nThis distance is invalid -- distances must be integers. Try again.");
                            System.out.print("\nEdit Distance, or press -1 to keep: " +
                              oldDistance + "  ");
                        }
                        catch(NumberFormatException err){
                            System.out.print("\nThis distance is invalid -- distances must be integers. Try again.");
                            System.out.print("\nEdit Distance, or press -1 to keep: " +
                              oldDistance + "  ");
                        }
                    }
                    System.out.print("\nEdit Activity, or press enter without typing anything to keep: " +
                      firstItinerary.getCursorStop().getActivity() + "  ");
                    tempActivity = keyboard.nextLine();
                    if(!(tempLocation.isEmpty()))
                        firstItinerary.getCursorStop().setLocation(tempLocation);
                    if(!(tempActivity.isEmpty()))
                        firstItinerary.getCursorStop().setActivity(tempActivity);
                    if(tempDistance != -1){
                        firstItinerary.getCursorStop().setDistance(tempDistance);
                        firstItinerary.changeDistance(tempDistance - oldDistance);
                    }
                    System.out.println("Edits applied.");
                    continue;
                }
            case 's':
                if(operatingSecondItinerary){
                    operatingSecondItinerary = false;
                    System.out.println("Itinerary switched.");
                    continue;
                }
                else{
                    operatingSecondItinerary = true;
                    System.out.println("Itinerary switched");
                    continue;
                }
            case 'o':
                if(operatingSecondItinerary){
                    try{
                        if(secondItinerary.getCursorLocation() ==  secondItinerary.getStopsCount()){
                            try{
                                secondItinerary.appendToTail((TripStop)firstItinerary.getCursorStop().deepCopy());
                            }
                            catch(NullPointerException err){
                                System.out.println("Second itinerary currently empty.");
                                continue;
                            }
                        }
                        else{
                            secondItinerary.cursorForward();
                            secondItinerary.insertBeforeCursor((TripStop)firstItinerary.getCursorStop().deepCopy());
                            secondItinerary.cursorBackward();
                            secondItinerary.cursorBackward();
                        }
                        System.out.println("Added.");
                        continue;
                    }
                    catch(EndOfItineraryException err){
                        System.out.println("Cursor is at the start of the second itinerary and cannot move any " +
                          "further backwards.");
                        continue;
                    }
                    catch(EmptyItineraryException err){
                        System.out.println("Second itinerary is currently empty.");
                        continue;
                    }
                }
                try{
                    if(firstItinerary.getCursorLocation() ==  firstItinerary.getStopsCount()){
                        try{
                            firstItinerary.appendToTail((TripStop)secondItinerary.getCursorStop().deepCopy());
                        }
                        catch(NullPointerException err){
                            System.out.println("First itinerary currently empty.");
                            continue;
                        }
                    }
                    else{
                        firstItinerary.cursorForward();
                        firstItinerary.insertBeforeCursor((TripStop)secondItinerary.getCursorStop().deepCopy());
                        firstItinerary.cursorBackward();
                        firstItinerary.cursorBackward();
                    }
                    System.out.println("Added.");
                    continue;
                }
                catch(EndOfItineraryException err){
                    System.out.println("Cursor is at the start of the first itinerary and cannot move any " +
                      "further backwards.");
                    continue;
                }
                catch(EmptyItineraryException err){
                    System.out.println("First itinerary is currently empty.");
                    continue;
                }
            case 'r':
                if(operatingSecondItinerary){
                    storeLocation = firstItinerary.getCursorLocation();
                    if(secondItinerary.getCursorStop() != null){
                        for(int i = 0; i <= secondItinerary.getStopsCount(); i++ ){
                            try{
                                secondItinerary.removeCursor();
                            }
                            catch(EmptyItineraryException err){
                                System.out.println("Error occurred: Tried removing no elements in second itinerary.");
                            }
                            catch(EndOfItineraryException err){
                                System.out.println("Error occurred: Cursor hit the edge of the second itinerary.");
                            }
                            catch(EndOfListException err){
                                System.out.println("Error occurred: Cursor was set to null while removing objects in " +
                                  "second itinerary.");
                            }
                        }
                        if(secondItinerary.getCursorStop() != null){
                            secondItinerary.resetCursorToHead();
                            try{
                                secondItinerary.removeCursor();
                            }
                            catch(EmptyItineraryException err){
                                System.out.println("Error occurred: Tried removing no elements in second itinerary.");
                            }
                            catch(EndOfItineraryException err){
                                System.out.println("Error occurred: Cursor hit the edge of the second itinerary.");
                            }
                            catch(EndOfListException err){
                                System.out.println("Error occurred: Cursor was set to null while removing objects in " +
                                  "second itinerary.");
                            }
                        }
                    }
                    firstItinerary.resetCursorToHead();
                    for(int j = 0; j < firstItinerary.getStopsCount(); j++ ){
                        secondItinerary.appendToTail((TripStop)firstItinerary.getCursorStop().deepCopy());
                        if(j < (firstItinerary.getStopsCount()-1)){
                            try{
                                firstItinerary.cursorForward();
                            }
                            catch(EndOfItineraryException err){
                                System.out.println("Cursor hit the end of the first itinerary.");
                            }
                            catch(EmptyItineraryException err){
                                System.out.println("First itinerary is currently empty.");
                            }
                        }
                    }
                    firstItinerary.resetCursorToHead();
                    for(int k = 0; k < storeLocation-1; k++ ){
                        try{
                            firstItinerary.cursorForward();
                        }
                        catch(EndOfItineraryException err){
                            System.out.println("Cursor hit the end of the first itinerary.");
                        }
                        catch(EmptyItineraryException err){
                            System.out.println("First itinerary is currently empty.");
                        }
                    }
                    System.out.println("Replaced second itinerary with the first itinerary.");
                    continue;
                }
                else{
                    storeLocation = secondItinerary.getCursorLocation();
                    if(firstItinerary.getCursorStop() != null)
                        for(int i = 0; i <= firstItinerary.getStopsCount(); i++ ){
                            try{
                                firstItinerary.removeCursor();
                            }
                            catch(EmptyItineraryException err){
                                System.out.println("Error occurred: Tried removing no elements in first itinerary.");
                            }
                            catch(EndOfItineraryException err){
                                System.out.println("Error occurred: Cursor hit the edge of the first itinerary.");
                            }
                            catch(EndOfListException err){
                                System.out.println("Error occurred: Cursor was set to null while removing objects in" +
                                  " first itinerary.");
                            }
                        }
                    if(firstItinerary.getCursorStop() != null){
                        firstItinerary.resetCursorToHead();
                        try{
                            firstItinerary.removeCursor();
                        }
                        catch(EmptyItineraryException err){
                            System.out.println("Error occurred: Tried removing no elements in first itinerary.");
                        }
                        catch(EndOfItineraryException err){
                            System.out.println("Error occurred: Cursor hit the edge of the first itinerary.");
                        }
                        catch(EndOfListException err){
                            System.out.println("Error occurred: Cursor was set to null while removing objects in " +
                              "first itinerary.");
                        }
                    }
                    for(int j = 0; j < secondItinerary.getStopsCount(); j++ ){
                        firstItinerary.appendToTail((TripStop)secondItinerary.getCursorStop().deepCopy());
                        if(j < (secondItinerary.getStopsCount()-1)){
                            try{
                                secondItinerary.cursorForward();
                            }
                            catch(EndOfItineraryException err){
                                System.out.println("Cursor hit the end of the second itinerary.");
                            }
                            catch(EmptyItineraryException err){
                                System.out.println("Second itinerary is currently empty.");
                            }
                        }
                    }
                    secondItinerary.resetCursorToHead();
                    for(int k = 0; k < storeLocation-1; k++ ){
                        try{
                            secondItinerary.cursorForward();
                        }
                        catch(EndOfItineraryException err){
                            System.out.println("Cursor hit the end of the second itinerary.");
                        }
                        catch(EmptyItineraryException err){
                            System.out.println("Second itinerary is currently empty.");
                        }
                    }
                    System.out.println("Replaced first itinerary with the second itinerary.");
                    continue;
                }
            case 'p':
                if(operatingSecondItinerary){
                    System.out.println(secondItinerary.toString());
                    continue;
                }
                else{
                    System.out.println(firstItinerary.toString());
                    continue;
                }
            case 'c':
                if(operatingSecondItinerary){
                    if(secondItinerary.getCursorStop() != null){
                        for(int i = 0; i <= secondItinerary.getStopsCount(); i++ ){
                            try{
                                secondItinerary.removeCursor();
                            }
                            catch(EmptyItineraryException err){
                                System.out.println("Error occurred: Tried removing no elements in second itinerary.");
                            }
                            catch(EndOfItineraryException err){
                                System.out.println("Error occurred: Cursor hit the edge of the second itinerary.");
                            }
                            catch(EndOfListException err){
                                System.out.println("Error occurred: Cursor was set to null while removing objects in " +
                                  "second itinerary.");
                            }
                        }
                        if(secondItinerary.getCursorStop() != null){
                            secondItinerary.resetCursorToHead();
                            try{
                                secondItinerary.removeCursor();
                            }
                            catch(EmptyItineraryException err){
                                System.out.println("Error occurred: Tried removing no elements in second itinerary.");
                            }
                            catch(EndOfItineraryException err){
                                System.out.println("Error occurred: Cursor hit the edge of the second itinerary.");
                            }
                            catch(EndOfListException err){
                                System.out.println("Error occurred: Cursor was set to null while removing objects in " +
                                  "second itinerary.");
                            }
                        }
                        System.out.println("Second itinerary has been cleared.");
                    }
                    else
                        System.out.println("Second itinerary was already empty.");
                    continue;
                }
                else{
                    if(firstItinerary.getCursorStop() != null){
                        for(int i = 0; i <= firstItinerary.getStopsCount(); i++ ){
                            try{
                                firstItinerary.removeCursor();
                            }
                            catch(EmptyItineraryException err){
                                System.out.println("Error occurred: Tried removing no elements in first itinerary.");
                            }
                            catch(EndOfItineraryException err){
                                System.out.println("Error occurred: Cursor hit the edge of the first itinerary.");
                            }
                            catch(EndOfListException err){
                                System.out.println("Error occurred: Cursor was set to null while removing objects in " +
                                  "first itinerary.");
                            }
                        }
                        if(firstItinerary.getCursorStop() != null){
                            firstItinerary.resetCursorToHead();
                            try{
                                firstItinerary.removeCursor();
                            }
                            catch(EmptyItineraryException err){
                                System.out.println("Error occurred: Tried removing no elements in first itinerary.");
                            }
                            catch(EndOfItineraryException err){
                                System.out.println("Error occurred: Cursor hit the edge of the first itinerary.");
                            }
                            catch(EndOfListException err){
                                System.out.println("Error occurred: Cursor was set to null while removing objects in " +
                                  "first itinerary.");
                            }
                        }
                        System.out.println("First itinerary has been cleared.");
                    }
                    else
                        System.out.println("First itinerary was already empty.");
                    continue;
                }
            default:
                invalidResponse = true;
                continue;
        }
    }
    keyboard.close();
    System.out.println("SHUTTING DOWN");
    System.exit(0);
    }
    /**
     * Method used to print out the menu along with which Itinerary is currently being worked.
     * @postcondition
     *   Prints out the menu along with the options and what they do. Also prints out which Itinerary is currently
     *     being worked.
     */
    public static void printOptions(){
        if(operatingSecondItinerary)
            System.out.print("\nSecond Itinerary Menu:\n");
        else
            System.out.print("\nFirst Itinerary Menu:\n");
        System.out.println("F - Cursor forward\nB - Cursor backward\nI - Insert before cursor\nA - Append " + 
          "to tail\nD - Delete and move cursor forward\nH - Cursor to head\nT - Cursor to tail\nE - Edit cursor\nS " +
          "- Switch itinerary\nO - Insert cursor from other itinerary after cursor from this itinerary\nR - " + 
          "Replace this itinerary with a copy of the other itinerary\nP - Print current itinerary, including summary" + 
          "\nC - Clear current itinerary\nQ - Quit");
    }
}