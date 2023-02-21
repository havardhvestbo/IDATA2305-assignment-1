package booking;

public class MovieTicketClient {

    private String customerName;
    private int numberOfTickets;
    private MovieTicketServer movieTicketServer;

    public MovieTicketClient(MovieTicketServer movieTicketServer, String customerName, int numberOfTickets) {
        this.customerName = customerName;
        this.numberOfTickets = numberOfTickets;
        this.movieTicketServer = movieTicketServer;
    }

    public void run() {
        this.movieTicketServer.bookTicket(this.customerName, this.numberOfTickets);

    }
}
