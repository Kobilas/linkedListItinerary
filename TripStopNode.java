package mkobilas.homework.roadtrip;
/**
 * TripStopNode is an object node in the doubly linked list that wraps a TripStop object in it. The object also
 *   contains pointers to the next and previous TripStopNodes in the linked list. This TripStopNode class also contains
 *   the necessary accessor and mutator methods for the TripStopNode objects.
 * @author Matthew Kobilas
 *   matthew.kobilas@stonybrook.edu
 */
public class TripStopNode {
    private TripStop data = new TripStop();
    //Pointer to the next TripStopNode in the Itinerary
    private TripStopNode next;
    //Pointer to the previous TripStopNode in the Itinerary
    private TripStopNode prev;
    /**
     * TripStopNode takes the TripStop object that is to be wrapped in the node as a parameter, then creates an object
     *   TripStopNode with pointers for the next and previous TripStopNodes not initialized to anything.
     * @param initData
     *   TripStop initData is the TripStop object that is to be wrapped in this TripStopNode to be used as data in the 
     *     linked list.
     * @precondition
     *   initData cannot be null since that is basically an empty space in the linked list.
     * @postcondition
     *   TripStopNode object with TripStop object wrapped in it. The TripStopNode's next and previous pointers are not
     *     yet initialized.
     * @throws IllegalArgumentException
     *   Throws an exception if TripStop initData is equal to null.
     */
    public TripStopNode(TripStop initData){
        if(initData ==  null)
            throw new IllegalArgumentException("Argument initial data cannot be null.");
        data.setLocation(initData.getLocation());
        data.setDistance(initData.getDistance());
        data.setActivity(initData.getActivity());
    }
    /**
     * Accessor method for the TripStop object contained within this TripStopNode.
     * @return data
     *   Returns the reference to the TripStop object for the data in this TripStopNode.
     */
    public TripStop getData(){
        return data;
    }
    /**
     * Mutator method for the TripStop object contained within this TripStopNode.
     * @param newData
     *   TripStop newData is the new TripStop object that will be contained within this TripStopNode.
     * @precondition
     *   newData cannot be equal to null since that is basically an empty node in the linked list.
     * @postcondition
     *   This TripStopNode data is set to newData.
     * @throws IllegalArgumentException
     *   Exception thrown if TripStop newData is equal to null.
     */
    public void setData(TripStop newData){
        if(newData ==  null)
            throw new IllegalArgumentException("Argument new data cannot be null.");
        data.setLocation(newData.getLocation());
        data.setDistance(newData.getDistance());
        data.setActivity(newData.getActivity());
    }
    /**
     * Accessor method for this TripStopNode's reference to the next TripStopNode in the linked list.
     * @return next
     *   Returns the reference to the next TripStopNode in the linked list that this TripStopNode object is contained in
     */
    public TripStopNode getNext(){
        return next;
    }
    /**
     * Mutator method for this TripStopNode's reference to the next TripStopNode in the linked list.
     * @param newNext
     *   TripStopNode newNext is the new reference for next to be set equal to.
     * @postcondition
     *   TripStopNode next is set to newNext
     */
    public void setNext(TripStopNode newNext){
        next = newNext;
    }
    /**
     * Accessor method for this TripStopNode's reference to the previous TripStopNode in the linked list.
     * @return prev
     *   Returns the reference to the previous TripStopNode in the linked list that this TripStopNode object is
     *     contained in.
     */
    public TripStopNode getPrev(){
        return prev;
    }
    /**
     * Mutator method for this TripStopNode's reference to the previous TripStopNode in the linked list.
     * @param newPrev
     *   TripStopNode newPrev is the new reference for prev to be set equal to.
     * @postcondition
     *   TripStopNode prev is set to newPrev
     */
    public void setPrev(TripStopNode newPrev){
        prev = newPrev;
    }
}
