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
    }

    public Reservation getNextRequest() {
        return queue.poll();
    }

    public boolean hasRequests() {
        return !queue.isEmpty();
    }
}

class RoomInventory {
    private Map<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
    }

    public void addRoomType(String type, int count) {
        inventory.put(type, count);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public void updateAvailability(String type, int count) {
        inventory.put(type, count);
    }

    public void displayInventory() {
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " Rooms Available: " + entry.getValue());
        }
    public boolean allocateRoom(String type) {
        int available = getAvailability(type);
        if (available > 0) {
            inventory.put(type, available - 1);
            return true;
        }
        return false;
    }
}

class BookingService {
    private RoomInventory inventory;
    private Map<String, Set<String>> allocatedRooms;

    BookingService(RoomInventory inventory) {
        this.inventory = inventory;
        allocatedRooms = new HashMap<>();
    }

    public void processRequest(Reservation reservation) {
        if (inventory.allocateRoom(reservation.roomType)) {
            String roomId = reservation.roomType + "-" + UUID.randomUUID().toString();
            allocatedRooms.computeIfAbsent(reservation.roomType, k -> new HashSet<>()).add(roomId);
            System.out.println("Reservation Confirmed: " + reservation.getDetails() + " | Room ID: " + roomId);
        } else {
            System.out.println("Reservation Failed: " + reservation.getDetails() + " | No rooms available");
        }
abstract class Room {
    String type;
    int beds;
    double price;

    Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    public String getDetails() {
        return type + " Room - Beds: " + beds + ", Price: $" + price;
    }
}

class SingleRoom extends Room {
    SingleRoom() {
        super("Single", 1, 100.0);
    }
}

class DoubleRoom extends Room {
    DoubleRoom() {
        super("Double", 2, 180.0);
    }
}

class SuiteRoom extends Room {
    SuiteRoom() {
        super("Suite", 3, 300.0);
    }
}

public class BookMyStay {
    public static void main(String[] args) {
        System.out.println("Welcome to Book My Stay App");
        System.out.println("Hotel Booking System v3.1");

        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single", 5);
        inventory.addRoomType("Double", 3);
        inventory.addRoomType("Suite", 2);

        inventory.displayInventory();

        inventory.updateAvailability("Single", 4);
        System.out.println("After booking one Single Room:");
        inventory.displayInventory();
        System.out.println("Hotel Booking System v6.1");

        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single", 2);
        inventory.addRoomType("Double", 1);
        inventory.addRoomType("Suite", 1);

        BookingRequestQueue requestQueue = new BookingRequestQueue();
        requestQueue.addRequest(new Reservation("Alice", "Single"));
        requestQueue.addRequest(new Reservation("Bob", "Suite"));
        requestQueue.addRequest(new Reservation("Charlie", "Single"));
        requestQueue.addRequest(new Reservation("Diana", "Double"));
        requestQueue.addRequest(new Reservation("Eve", "Single"));

        BookingService bookingService = new BookingService(inventory);

        while (requestQueue.hasRequests()) {
            Reservation r = requestQueue.getNextRequest();
            bookingService.processRequest(r);
        }
        SingleRoom single = new SingleRoom();
        DoubleRoom dbl = new DoubleRoom();
        SuiteRoom suite = new SuiteRoom();

        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        System.out.println("Welcome to Book My Stay App");
        System.out.println("Hotel Booking System v2.1");
        System.out.println(single.getDetails() + " | Available: " + singleAvailable);
        System.out.println(dbl.getDetails() + " | Available: " + doubleAvailable);
        System.out.println(suite.getDetails() + " | Available: " + suiteAvailable);
        System.out.println("Welcome to Book My Stay App");

    }
}
