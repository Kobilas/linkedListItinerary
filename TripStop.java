package mkobilas.homework.roadtrip;
/**
 * The TripStop class is used to create an object of type TripStop which holds the variables for location, distance, and
 *   activity at the trip stop. This TripStop class also contains the necessary accessor and mutator methods for the
 *   information held within the TripStop object.
 * @author Matthew Kobilas
 *   matthew.kobilas@stonybrook.edu
 */
public class TripStop {
    private String location;
    private int distance;
    private String activity;
    /**
     * Creates a TripStop object that does not take any initial arguments.
     * @postcondition
     *   Creates a TripStop object with String location equal to null, int distance equal to 0, and String activity
     *     equal to null.
     */
    public TripStop(){
        location = null;
        distance = 0;
        activity = null;
    }
    /**
     * Creates a TripStop object that takes initial parameters for location, distance, and activity for the trip stop.
     * @param initLocation
     *   String initLocation is the location of the trip stop.
     * @param initDistance
     *   int initDistance is the distance in miles to the trip stop.
     * @param initActivity
     *   String initActivity is the activity happening at the trip stop.
     * @precondition
     *   int initDistance must be greater than or equal to 0 since negative distances cannot exist.
     * @postcondition
     *   Creates a TripStop object initialized with a location, distance, and an activity.
     * @throws IllegalArgumentException
     *   Throws an exception if the distance is less than 0.
     */
    public TripStop(String initLocation, int initDistance, String initActivity){
        if(initDistance < 0)
            throw new IllegalArgumentException("Argument distance " + initDistance + " cannot be negative.");
        location = initLocation;
        distance = initDistance;
        activity = initActivity;
    }
    /**
     * Accessor method for the String location of this TripStop object.
     * @return location
     *   Returns the String location of this TripStop object.
     */
    public String getLocation(){
        return location;
    }
    /**
     * Mutator method for the String location of this TripStop object.
     * @param newLocation
     *   String newLocation is what location is set to when the method runs.
     * @postcondition
     *   location of this TripStop object is set to newLocation.
     */
    public void setLocation(String newLocation){
        location = newLocation;
    }
    /**
     * Accessor method for the int distance of this TripStop object.
     * @return distance
     *   Returns the int distance of this TripStop object.
     */
    public int getDistance(){
        return distance;
    }
    /**
     * Mutator method for the int distance of this TripStop object.
     * @param newDistance
     *   int newDistance is what distance is set to when the method runs.
     * @precondition
     *   newDistance must be greater than or equal to 0 since negative distances do not exist.
     * @postcondition
     *   distance of this TripStop object is set to newDistance.
     * @throws IllegalArgumentException
     *   Throw an exception if newDistance is less than 0.
     */
    public void setDistance(int newDistance){
        if(newDistance < 0)
            throw new IllegalArgumentException("Argument distance " + newDistance + "cannot be negative.");
        distance = newDistance;
    }
    /**
     * Accessor method for the String activity of this TripStop object.
     * @return activity
     *   Returns the String activity of this TripStop object.
     */
    public String getActivity(){
        return activity;
    }
    /**
     * Mutator method for the String activity of this TripStop object.
     * @param newActivity
     *   String newActivity is what activity is set to when the method runs.
     * @postcondition
     *   activity of this TripStop object is set to newActivity.
     */
    public void setActivity(String newActivity){
        activity = newActivity;
    }
    /**
     * toString method for the TripStop object which returns a nicely formatted line of text summarizing what is
     *   contained within this TripStop object.
     * @return result
     *   String result is returned with a formatted String of the location, activity, and distance of the TripStop
     *     object.
     */
    public String toString(){
        //Decides between the word "mile" or "miles" depending upon the number of miles.
        String milesOrMile;
        if(distance ==  1)
            milesOrMile = " mile";
        else
            milesOrMile = " miles";
        String result = String.format("%-25s%-75s%4d%s", getLocation(), getActivity(), getDistance(), milesOrMile);
        return result;
    }
    /**
     * Method which copies this TripStop object and returns an Object. This object can then by typecasted into a
     *   TripStop object with the same variables contained within this TripStop object.
     * @return result
     *   Object result is a an object instantiated as a TripStop object that is initialized with the same contents of
     *     this TripStop object.
     */
    public Object deepCopy(){
        TripStop result = new TripStop();
        result.setLocation(getLocation());
        result.setDistance(getDistance());
        result.setActivity(getActivity());
        return result;
    }
}
