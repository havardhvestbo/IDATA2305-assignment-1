package booking;

import java.util.logging.Logger;

/**
 * This class represent the movie thicket server.
 * It handles the client requests.
 *
 * @author Ole Kristian Dvergsdal
 * @author Håvard Vestbø
 * @version 1.0
 */
public class MovieTicketServer {
    private final String movieName;
    private int availableSeats;
    private static final Logger logger = Logger.getLogger(MovieTicketServer.class.getName());

    /**
     * The movie ticket server.
     * @param movieName name the movie.
     * @param availableSeats number of seats that is available.
     * @throws IllegalArgumentException throws exception if a value is not valid.
     */
    public MovieTicketServer(String movieName, int availableSeats) throws IllegalArgumentException{
        try {
            stringChecker(movieName);
            numberChecker(availableSeats);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            this.logger.warning("IllegalArgumentException was caught: " + illegalArgumentException.getMessage());
        }
        this.movieName = movieName;
        this.availableSeats = availableSeats;
    }

    /**
     * Order tickets for the movie.
     * The methods handle the order info and returns if there is ticket left or not.
     * @param customerName name of the customer.
     * @param numberOfSeats number of seats the customer wants to order.
     */
    public synchronized void bookTicket(String customerName, int numberOfSeats) {
        System.out.println("Hi," + customerName + " : " + availableSeats + " : Seats available for " + movieName);
        if ((availableSeats - numberOfSeats) < 0) {
            System.out.println("Hi," + customerName + " : Seats not available for " + movieName);
        }
        else {
            System.out.println("Hi," + customerName + " : " + numberOfSeats + " Seats booked successfully for " + movieName);
            decrementAvailableSears(numberOfSeats);
        }
    }

    /**
     * Checks that a given number is not below 0.
     * @param n the number that is checked.
     * @return the number
     */
    private int numberChecker(int n) {
        if(n < 0) {
            throw new IllegalArgumentException("Input cant be less then 0");
        }
        return n;
    }

    /**
     * Checks the String is valid.
     * If the string is empty or null, an Illegal Argument exception is thrown.
     * @param string the string that is being checked.
     * @return the string.
     */
    private String stringChecker(String string) {
        if(string.isEmpty()) {
            throw new IllegalArgumentException("Invalid string input");
        }
        return string;
    }

    /**
     * Decrease the number aof available seats in the
     * @param n number of seats that is taken away from the available seats.
     */
    private void decrementAvailableSears(int n) {
        try {
            numberChecker(n);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            logger.warning(illegalArgumentException.getMessage());
        }
        this.availableSeats -= n;
    }
}
