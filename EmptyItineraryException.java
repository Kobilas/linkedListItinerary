package mkobilas.homework.roadtrip;
/**
 * The EmptyItineraryException extends the class Exception. This class is used to throw the exception
 *   EmptyItineraryException which is used throughout the program. This exception is usually used to represent that the
 *   Itinerary object is empty at the moment that the exception is thrown.
 * @author Matthew Kobilas
 *   matthew.kobilas@stonybrook.edu
 */
public class EmptyItineraryException extends Exception{
    private static final long serialVersionUID = -8138826929290633962L;
    /**
     * The empty constructor for the EmptyItineraryException.
     * @postcondition
     *   Creates an object of type EmptyItineraryException which inherits the methods of the class Exception.
     */
    EmptyItineraryException(){
    }
    /**
     * The constructor for the EmptyItineraryException with a String message parameter which is used to tell the user
     *   what went wrong with the program and where.
     * @param message
     *   String message is a message sent when the printStackTrace() method is called.
     * @postcondition
     *   Creates an object of type EmptyItineraryException with a message of what went wrong in the program. It inherits
     *     the methods of the class Exception.
     */
    EmptyItineraryException(String message){
        super(message);
    }
    /**
     * The constructor for the EmptyItineraryException with a String message and Throwable cause parameter which is used
     *   to rethrow the exception in a try-catch block, or change it to a different exception. The String message is 
     *   used to convey to the user what went wrong with program and where.
     * @param message
     *   String message is a message sent when the printStackTrace() method is called.
     * @param cause
     *   Throwable cause is used to rethrow or change the exception type within a try-catch block.
     * @postcondition
     *   Creates an object of type EmptyItineraryException with a message of what went wrong in the program, and a
     *     previous Throwable cause for why the exception was thrown. 
     */
    EmptyItineraryException(String message, Throwable cause){
        super(message, cause);
    }
}
