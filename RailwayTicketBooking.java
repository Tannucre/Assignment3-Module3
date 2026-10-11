import java.util.Scanner;

class TicketBooking {

    int availableSeats;

    TicketBooking(int availableSeats) {
        this.availableSeats = availableSeats;
    }
    synchronized void bookSeat(int seats, String userName) {

        if (seats <= availableSeats) {

            availableSeats = availableSeats - seats;

            System.out.println(
                    userName + " booked " + seats + " seat(s) successfully"
            );

        } else {
            System.out.println(
                    userName + " booking failed. Not enough seats"
            );
        }
    }
}
class User1 extends Thread {

    TicketBooking booking;
    int seats;

    User1(TicketBooking booking, int seats) {
        this.booking = booking;
        this.seats = seats;
    }

    public void run() {
        booking.bookSeat(seats, "User1");
    }
}

class User2 extends Thread {

    TicketBooking booking;
    int seats;

    User2(TicketBooking booking, int seats) {
        this.booking = booking;
        this.seats = seats;
    }

    public void run() {
        booking.bookSeat(seats, "User2");
    }
}

public class RailwayTicketBooking {

    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);

        System.out.print("Available Seats: ");
        int availableSeats = sc.nextInt();

        System.out.print("User1 wants to book: ");
        int user1Seats = sc.nextInt();

        System.out.print("User2 wants to book: ");
        int user2Seats = sc.nextInt();

        TicketBooking booking = new TicketBooking(availableSeats);

        User1 user1 = new User1(booking, user1Seats);
        User2 user2 = new User2(booking, user2Seats);

        user1.start();
        user1.join();

        user2.start();
        user2.join();

        sc.close();
    }
}