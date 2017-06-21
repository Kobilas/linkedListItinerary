package mkobilas.homework.roadtrip;
import java.lang.StringBuilder;
/**
 * The Itinerary class is the actual doubly-linked list object that is used in the driver class. It stores multiple
 *   TripStopNode objects which each reference the next and previous ones in order to allow for a chain of TripStopNodes
 *   Each TripStopNode hosts a TripStop object which holds the actual data for where the trip stop is when the user is
 *   using the program to plan a trip. The Itinerary class holds most of the methods called in the main driver class and
 *   is used to manipulate the many TripStopNode objects.
 * @author Matthew Kobilas
 *   matthew.kobilas@stonybrook.edu
 */
public class Itinerary {
    private TripStopNode head;
    private TripStopNode tail;
    private TripStopNode cursor;
    //printCursor always starts at the head of the Itinerary and moves to the tail. Used to print the data for all the
      //TripStopNode objects in the Itinerary.
    private TripStopNode printCursor;
    //Keeps track of the number of TripStopNode objects in the Itinerary
    private int stopsCount = 0;
    //Keeps track of the total distance among all TripStopNode objects in the Itinerary
    private int totalDistance = 0;
    //Keeps track of the TripStopNode that the cursor is currently referencing. Used to print the location of the cursor
      //in the formatted table.
    private int cursorLocation = 0;
    /**
     * The Itinerary method is used to create the linked list. It initializes the tools used to navigate the linked list
     *   to null until the user creates TripStopNodes that are to be inserted into the linked list.
     * @postcondition
     *   Creates an object of type Itinerary with the navigation tools initialized to null. This Itinerary is a doubly-
     *     linked list which can hold multiple TripStopNodes.
     */
    public Itinerary(){
        head = null;
        tail = null;
        cursor = null;
    }
    /**
     * Accessor method for the number of TripStopNode objects that are found within this Itinerary object.
     * @return stopsCount
     *   Returns int stopsCount, which is incremented whenever a TripStopNode is added to this Itinerary and decremented
     *     whenever a TripStopNode is removed from this Itinerary.
     */
    public int getStopsCount(){
        return stopsCount;
    }
    /**
     * Accessor method for the sum of all the distances found within the TripStop objects found within the TripStopNode
     *   objects found within this Itinerary.
     * @return totalDistance
     *   Returns int totalDistance, which is the total distance of all the TripStop objects found within this Itinerary.
     */
    public int getTotalDistance(){
        return totalDistance;
    }
    /**
     * Mutator method to change the totalDistance of all the TripStopNode objects. Used mainly to change the distance if
     *   the user edits a TripStopNode to a different distance.
     * @param change
     *   int change is the number of miles to change int totalDistance by. This can be negative as it will simply mean
     *     that the user reduced the distance of a TripStop object.
     */
    public void changeDistance(int change){
        totalDistance += change;
    }
    /**
     * Accessor method which returns the TripStop object that the cursor is currently pointing to.
     * @return TripStop
     *   Returns an object of type TripStop so that the user can edit it or so that it may be copied elsewhere.
     */
    public TripStop getCursorStop(){
        if(cursor ==  null)
            return null;
        return cursor.getData();
    }
    /**
     * Accessor method which returns which stop the cursor is currently pointing to. Used mainly for printing out a neat
     *   table of all the TripStopNodes found within this Itinerary if the user requests it. Increments whenever the
     *   cursor moves forward, decrements if the cursor moves backward, and gets set to 1 or getStopsCount() if the user
     *   moves the cursor to the head or tail, respectively.
     * @return cursorLocation
     *   int cursorLocation of which TripStopNode that the cursor is currently pointing to.
     */
    public int getCursorLocation(){
        return cursorLocation;
    }
    /**
     * Moves the cursor back to the start of this Itinerary.
     * @postcondition
     *   cursor is now equal to head and cursorLocation now equals 1.
     */
    public void resetCursorToHead(){
        if(head ==  null)
            cursor = null;
        else{
            cursor = head;
        }
        cursorLocation = 1;
    }
    /**
     * Moves the printCursor back to the start of this Itinerary. Used mainly for printing out a neat table of all the
     *   TripStopNodes found within this Itinerary.
     * @postcondition
     *   printCursor is now equal to head.
     */
    public void resetPrintCursorToHead(){
        if(head ==  null)
            printCursor = null;
        else{
            printCursor = head;
        }
    }
    /**
     * Moves the cursor back to the end of this Itinerary.
     * @postcondition
     *   cursor is now equal to tail and cursorLocation now equals getStopsCount()
     */
    public void resetCursorToTail(){
        if(tail ==  null)
            cursor = null;
        else{
            cursor = tail;
        }
        cursorLocation = getStopsCount();
    }
    /**
     * Moves the cursor forward so that it is now pointing to the next TripStopNode in this Itinerary.
     * @precondition
     *   Itinerary cannot be empty.
     * @precondition
     *   Cursor cannot already be at the end of Itinerary; the cursor has to have somewhere to move forward to.
     * @postcondition
     *   Cursor now references the next TripStopNode in this Itinerary.
     * @throws EndOfItineraryException
     *   Throws an exception if the cursor hits the tail of this Itinerary.
     * @throws EmptyItineraryException
     *   Throws an exception if the cursor is equal to null; the Itinerary is empty.
     */
    public void cursorForward() throws EndOfItineraryException, EmptyItineraryException{
        if(cursor ==  null)
            throw new EmptyItineraryException("Itinerary is currently empty.");
        if(cursor ==  tail)
            throw new EndOfItineraryException("Cursor is at the end of the itinerary and cannot move any further " + 
              "forwards.");
        cursor = cursor.getNext();
        cursorLocation++ ;
    }
    /**
     * Moves the printCursor forward so that it is now pointing to the next TripStopNode in this Itinerary.
     * @precondition
     *   Itinerary cannot be empty.
     * @precondition
     *   printCursor cannot already be at the end of this Itinerary; the printCursor has to have somewhere to move 
     *     forward to.
     * @postcondition 
     *   printCursor now references the next TripStopNode in this Itinerary.
     * @throws EndOfItineraryException
     *   Throws an exception if the cursor hits the tail of this Itinerary.
     * @throws EmptyItineraryException
     *   Throws an exception if the printCursor is equal to null; the Itinerary is empty.
     */
    public void printCursorForward() throws EndOfItineraryException, EmptyItineraryException{
        if(printCursor ==  null)
            throw new EmptyItineraryException("Itinerary is currently empty.");
        if(printCursor ==  tail)
            throw new EndOfItineraryException("Print cursor is at the end of the itinerary and cannot move any " + 
              "further forwards.");
        printCursor = printCursor.getNext();
    }
    /**
     * Moves the cursor backward so that it is now pointing to the previous TripStopNode in this Itinerary.
     * @precondition
     *   Itinerary cannot be empty.
     * @precondition
     *   cursor cannot already be at the head of this Itinerary; the cursor has to have somewhere to move backward to.
     * @postcondition
     *   cursor now references the previous TripStopNode in this Itinerary.
     * @throws EndOfItineraryException
     *   Throws an exception if the cursor hits the head of this Itinerary.
     * @throws EmptyItineraryException
     *   Throws an exception if the cursor is equal to null; the Itinerary is empty.
     */
    public void cursorBackward() throws EndOfItineraryException, EmptyItineraryException{
        if(cursor ==  null)
            throw new EmptyItineraryException("Itinerary is currently empty.");
        if(cursor ==  head)
            throw new EndOfItineraryException("Cursor is at the start of the itinerary and cannot move any further " + 
              "backwards.");
        cursor = cursor.getPrev();
        cursorLocation--;
    }
    /**
     * Inserts a new TripStopNode behind the cursor with a given set of data, newStop, of type TripStop. If there were
     *   no TripStopNode objects in the Itinerary before calling this method, the new TripStopNode is set to be the
     *   head, tail, and cursor of this Itinerary.
     * @param newStop
     *   TripStop newStop is the data to be wrapped in the new TripStopNode that is inserted behind the cursor.
     * @precondition
     *   TripStop newStop cannot be null as this would be the same as an empty node in the Itinerary.
     * @postcondition
     *   This Itinerary has a new TripStopNode added to it behind the TripStopNode which the cursor is currently
     *     referencing. The cursor now references the newly created TripStopNode.
     * @throws IllegalArgumentException
     *   Throws an exception if the TripStop newStop parameter is equal to null.
     * @throws EndOfItineraryException
     *   Throws an exception if the cursor moves backwards into the head of the Itinerary before the TripStop newStop is
     *     added to the Itinerary, which should not happen.
     * @throws EmptyItineraryException
     *   Throws an exception if the cursor tries to move backward before the TripStop newStop is added to the Itinerary,
     *     which should not happen.
     */
    public void insertBeforeCursor(TripStop newStop) throws EndOfItineraryException, EmptyItineraryException{
        if(newStop ==  null)
            throw new IllegalArgumentException("Argument new stop cannot be null.");
        TripStopNode newNode = new TripStopNode(newStop);
        //If the Itinerary has TripStopNode objects in it
        if(cursor != null){
            //If cursor is at the head of the Itinerary and the newNode will be set as the new head.
            if(cursor ==  head){
                newNode.setNext(head);
                head.setPrev(newNode);
                head = newNode;
                resetCursorToHead();
            }
            //If the cursor is anywhere but the head.
            else{
                newNode.setNext(cursor);
                newNode.setPrev(cursor.getPrev());
                (cursor.getPrev()).setNext(newNode);
                cursor.setPrev(newNode);
                cursorBackward();
                cursorLocation++ ;
            }
        }
        //If the Itinerary does not have at least one TripStopNode object in it
        else{
            head = newNode;
            tail = newNode;
            resetCursorToHead();
        }
        stopsCount++ ;
        totalDistance += newStop.getDistance();
    }
    /**
     * Adds a new TripStopNode to the end of the Itinerary with data, newStop, of type TripStop.
     * @param newStop
     *   TripStop newStop is the data that is to be wrapped in the new TripStopNode that is being added to the Itinerary
     * @precondition
     *   TripStop newStop cannot be equal to null since it would be the same thing as an empty node in the Itinerary.
     * @postcondition
     *   The Itinerary has an additional TripStopNode added to the Itinerary. If the Itinerary was empty before, the new
     *     TripStopNode is set to be the head, tail, and cursor references. If the Itinerary was not empty before, the
     *     new TripStopNode is simply added to the tail and set to be the new tail reference.
     * @throws IllegalArgumentException
     *   Throws an exception if TripStop newStop parameter is equal to null.
     */
    public void appendToTail(TripStop newStop){
        if(newStop ==  null)
            throw new IllegalArgumentException("Argument new data cannot be null.");
        TripStopNode newNode = new TripStopNode(newStop);
        //If the Itinerary is not empty.
        if(tail != null){
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
        //If the Itinerary does not have at least one TripStopNode object in it
        else{
            tail = newNode;
            head = newNode;
            resetCursorToHead();
        }
        stopsCount++ ;
        totalDistance += newStop.getDistance();
    }
    /**
     * Removes the TripStopNode located at the cursor by removing all references to it and changing them to either the
     *   next TripStopNode or setting them to null. The TripStop object that was contained within the TripStopNode is
     *   then returned. If there is only one TripStopNode object left in the Itinerary, then the head, tail, and cursor
     *   references are set to null.
     * @precondition
     *   The Itinerary cannot be empty.
     * @return removed
     *   TripStop removed is returned once the TripStopNode wrapping it is removed from the Itinerary.
     * @throws EndOfListException
     *   Throws an exception if the cursor is equal to null; the Itinerary is empty.
     * @throws EndOfItineraryException
     *   Throws an exception if the cursor moves backward into the head of the Itinerary, which should not happen.
     * @throws EmptyItineraryException
     *   Throws an exception if the cursor is equal to null at the time the cursor is set to move, which should not
     *   happen.
     */
    public TripStop removeCursor() throws EndOfListException, EndOfItineraryException, EmptyItineraryException{
        if(cursor ==  null)
            throw new EndOfListException("Cursor is set to null because the list is empty.");
        TripStop removed = cursor.getData();
        stopsCount--;
        totalDistance -= removed.getDistance();
        //If the Itinerary has only one TripStopNode object.
        if(getStopsCount() ==  0){
            cursor = null;
            head = null;
            tail = null;
            cursorLocation = 0;
            return removed;
        }
        //If the cursor is at the tail or before the tail.
        if((cursor ==  tail) ||  (cursor.getNext() ==  tail)  && (getStopsCount() > 0)){
            //If the cursor is at the tail.
            if(cursor ==  tail){
                cursorBackward();
                cursor.getNext().setPrev(null);
                cursor.setNext(null);
                tail = cursor;
                return removed;
            }
            //If the cursor is at the TripStopNode before the tail.
            else{
                cursor.setData(cursor.getNext().getData());
                cursor.getNext().setPrev(null);
                cursor.setNext(null);
                tail = cursor;
                return removed;
            }
        }
        //If the TripStopNode after the cursor and after that TripStopNode object after that exist.
        if((cursor.getNext() != null) && (cursor.getNext().getNext() != null)){
            cursor.setData(cursor.getNext().getData());
            cursor.getNext().setPrev(null);
            cursor.getNext().getNext().setPrev(cursor);
            cursor.setNext(cursor.getNext().getNext());
            return removed;
        }
        //Every other reason. More inefficient than the other ways used to remove the TripStopNode above.
        cursor.getPrev().setNext(cursor.getNext());
        cursor.getNext().setPrev(cursor.getPrev());
        cursor.setPrev(null);
        cursorForward();
        cursorLocation--;
        return removed;
    }
    /**
     * Returns a String of a neat table of information of what is contained within the Itinerary, along with the
     *   current location of the cursor in the Itinerary. After appending the table with the information of the
     *   Itinerary to the StringBuilder object, the method appends a summary of the stops before and after the cursor,
     *   along with the total distance of the Itinerary thus far.
     * @return result.toString()
     *   Returns String result.toString(), which is a StringBuilder object with all the appended String objects attached
     *     calling its toString() method to convert it to a String.
     */
    public String toString(){
        //Efficient way to append Strings in a loop.
        StringBuilder result = new StringBuilder();
        //Counts the stopsBefore the cursor to be used in the summary.
        int stopsBefore = 0;
        //Counts the stopsAfter the cursor to be used in the summary.
        int stopsAfter = 0;
        //Clarifies between the numbers requiring plural forms or not.
        String stopsOrStopBefore;
        String stopsOrStopAfter;
        String milesOrMile;
        result.append("// \">\" indicates cursor\n");
        resetPrintCursorToHead();
        //Determines and prints the location of the cursor in the table.
        for(int i = 0; i < getStopsCount(); i++ ){
            if(printCursor ==  cursor)
                result.append(">");
            else
                result.append(" ");
            result.append(printCursor.getData().toString() + "\n");
            if(i+1 != getStopsCount()){
                try{
                printCursorForward();
                }
                catch(EndOfItineraryException err){
                    err.printStackTrace();
                }
                catch(EmptyItineraryException err){
                    err.printStackTrace();
                }
            }
        }
        //Block of code determines where to use a plural form of the nouns used and how many stops before and after the
          //cursor.
        if((getStopsCount()-(getStopsCount()-cursorLocation+1)) <= 0){
            stopsBefore = 0;
            stopsOrStopBefore = " stops ";
        }
        else{
            stopsBefore = (getStopsCount()-(getStopsCount()-cursorLocation+1));
            if(stopsBefore != 1)
                stopsOrStopBefore = " stops ";
            else
                stopsOrStopBefore = " stop ";
        }
        if((getStopsCount() - cursorLocation) <= 0){
            stopsAfter = 0;
            stopsOrStopAfter = " stops ";
        }
        else{
            stopsAfter = (getStopsCount() - cursorLocation);
            if(stopsAfter != 1)
                stopsOrStopAfter = " stops ";
            else
                stopsOrStopAfter = " stop ";
        }
        if(getTotalDistance() ==  1)
            milesOrMile = " mile. ";
        else
            milesOrMile = " miles. ";
        //Appends the summary to the end of the table.
        result.append("Summary: This trip has " + getStopsCount() + " stops, totaling " + getTotalDistance() +
          milesOrMile + "There are " + stopsBefore + stopsOrStopBefore + "before the cursor " + 
          "and " + stopsAfter + stopsOrStopAfter + "after the cursor.");
        return result.toString();
    }
}