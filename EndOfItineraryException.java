package mkobilas.homework.roadtrip;
/**
 * The EndOfItineraryException extends the class Exception. This class is used to throw the exception
 *   EndOfItineraryException which is used throughout the program. This exception is usually used to represent that the
 *   cursor reference in the Itinerary object hit either the head or tail and that it cannot move any further.
 * @author Matthew Kobilas
 *   matthew.kobilas@stonybrook.edu
 */
public class EndOfItineraryException extends Exception{
    private static final long serialVersionUID = -3154349734126298263L;
    /**
     * The empty constructor for the EndOfItineraryException
     * @postcondition
     *   Creates an object of type EndOfItineraryException which inherits the methods of the class Exception.
     */
    EndOfItineraryException(){
    }
    /**
     * The constructor for the EndOfItineraryException with a String message parameter which is used to tell the user
     *   what went wrong with the program and where.
     * @param message
     *   String message is a message sent when the printStackTrace() method is called.
     * @postcondition
     *   Creates an object of type EndOfItineraryException with a message of what went wrong in the program. It inherits
     *     the methods of the class Exception.
     */
    EndOfItineraryException(String message){
        super(message);
    }
    /**
     * The constructor for the EndOfItineraryException with a String message and Throwable cause parameter which is used
     *   to rethrow the exception in a try-catch block, or change it to a different exception. The String message is 
     *   used to convey to the user what went wrong with program and where.
     * @param message
     *   String message is a message sent when the printStackTrace() method is called.
     * @param cause
     *   Throwable cause is used to rethrow or change the exception type within a try-catch block.
     * @postcondition
     *   Creates an object of type EndOfItineraryException with a message of what went wrong in the program, and a
     *     previous Throwable cause for why the exception was thrown. 
     */
    EndOfItineraryException(String message, Throwable cause){
        super(message, cause);
    }
}
