package booking;

import java.util.logging.Logger;

/**
 * This class is the movie ticket client
 * It's an extends of thread, that runs booking of tickets
 *
 * @author Ole Kristian Dvergsdal
 * @author Håvard Vestbø
 * @version 1.0
 */
public class MovieTicketClient extends Thread {
    private final String customerName;
    private final int numberOfTickets;
    private final MovieTicketServer movieTicketServer;
    private static final Logger logger = Logger.getLogger(MovieTicketClient.class.getName());

    /**
     * The movie ticket client.
     * @param movieTicketServer the movie server the client want to book tickets from.
     * @param customerName the name of the customer.
     * @param numberOfTickets number of tickets the customer wants.
     * @throws IllegalArgumentException throws exception if a value is not valid.
     */
    public MovieTicketClient(MovieTicketServer movieTicketServer, String customerName, int numberOfTickets) throws IllegalArgumentException {
        try {
            stringChecker(customerName);
            numberChecker(numberOfTickets);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            this.logger.warning("Caught IllegalArgumentException: " + illegalArgumentException.getMessage());
        }
        this.customerName = customerName;
        this.numberOfTickets = numberOfTickets;
        this.movieTicketServer = movieTicketServer;
    }

    /**
     * Runs the booking ot tickets.
     */
    @Override
    public void run() {
        this.movieTicketServer.bookTicket(this.customerName, this.numberOfTickets);
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
     * Checks that a given number is not below 1.
     * @param n the number that is checked.
     * @return the number
     */
    private int numberChecker(int n) {
        if(n < 1) {
            throw new IllegalArgumentException("Input cant be less then 1");
        }
        return n;
    }
}
