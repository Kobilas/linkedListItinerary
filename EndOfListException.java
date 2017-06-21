package mkobilas.homework.roadtrip;
/**
 * The EndOfListException extends the class Exception. This class is used to throw the exception
 *   EndOfListException which is used throughout the program. This exception is usually used to represent that the
 *   cursor in the Itinerary object is set to null and that the linked list has ended and that there are no more
 *   TripStopNode elements in it.
 * @author Matthew Kobilas
 *   matthew.kobilas@stonybrook.edu
 */
public class EndOfListException extends Exception{
    private static final long serialVersionUID = -5345423059428100930L;
    /**
     * The empty constructor for the EndOfListException
     * @postcondition
     *   Creates an object of type EndOfListException which inherits the methods of the class Exception.
     */
    EndOfListException(){
    }
    /**
     * The constructor for the EndOfListException with a String message parameter which is used to tell the user
     *   what went wrong with the program and where.
     * @param message
     *   String message is a message sent when the printStackTrace() method is called.
     * @postcondition
     *   Creates an object of type EndOfListException with a message of what went wrong in the program. It inherits
     *     the methods of the class Exception.
     */
    EndOfListException(String message){
        super(message);
    }
    /**
     * The constructor for the EndOfListException with a String message and Throwable cause parameter which is used
     *   to rethrow the exception in a try-catch block, or change it to a different exception. The String message is 
     *   used to convey to the user what went wrong with program and where.
     * @param message
     *   String message is a message sent when the printStackTrace() method is called.
     * @param cause
     *   Throwable cause is used to rethrow or change the exception type within a try-catch block.
     * @postcondition
     *   Creates an object of type EndOfListException with a message of what went wrong in the program, and a
     *     previous Throwable cause for why the exception was thrown. 
     */
    EndOfListException(String message, Throwable cause){
        super(message, cause);
    }
}
