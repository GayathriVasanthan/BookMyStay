import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getDetails() {
        return "Guest: " + guestName + ", Room Type: " + roomType;
    }
}

class BookingRequestQueue {
    private Queue<Reservation> queue;

    BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        queue.add(reservation);
        System.out.println("Request added: " + reservation.getDetails());
    }

    public void displayQueue() {
        System.out.println("Current Booking Requests:");
        for (Reservation r : queue) {
            System.out.println(r.getDetails());
        }
    }
}

public class BookMyStay {
    public static void main(String[] args) {
        System.out.println("Welcome to Book My Stay App");
        System.out.println("Hotel Booking System v5.1");

        BookingRequestQueue requestQueue = new BookingRequestQueue();

        Reservation r1 = new Reservation("Alice", "Single");
        Reservation r2 = new Reservation("Bob", "Suite");
        Reservation r3 = new Reservation("Charlie", "Double");

        requestQueue.addRequest(r1);
        requestQueue.addRequest(r2);
        requestQueue.addRequest(r3);

        requestQueue.displayQueue();
    }
}
