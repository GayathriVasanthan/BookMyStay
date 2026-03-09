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
    }
}

public class BookMyStay {
    public static void main(String[] args) {
        System.out.println("Welcome to Book My Stay App");
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
    }
}
