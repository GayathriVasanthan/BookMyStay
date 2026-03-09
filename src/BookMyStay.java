import java.util.*;

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

    public Map<String, Integer> getAllAvailability() {
        return new HashMap<>(inventory);
    }
}

class SearchService {
    private RoomInventory inventory;
    private Map<String, Room> roomTypes;

    SearchService(RoomInventory inventory) {
        this.inventory = inventory;
        roomTypes = new HashMap<>();
        roomTypes.put("Single", new SingleRoom());
        roomTypes.put("Double", new DoubleRoom());
        roomTypes.put("Suite", new SuiteRoom());
    }

    public void searchAvailableRooms() {
        Map<String, Integer> availability = inventory.getAllAvailability();
        for (Map.Entry<String, Integer> entry : availability.entrySet()) {
            if (entry.getValue() > 0) {
                Room room = roomTypes.get(entry.getKey());
                System.out.println(room.getDetails() + " | Available: " + entry.getValue());
            }
        }
    }
}

public class BookMyStay {
    public static void main(String[] args) {
        System.out.println("Welcome to Book My Stay App");
        System.out.println("Hotel Booking System v4.1");

        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single", 5);
        inventory.addRoomType("Double", 0);
        inventory.addRoomType("Suite", 2);

        SearchService searchService = new SearchService(inventory);
        System.out.println("Available Rooms:");
        searchService.searchAvailableRooms();
    }
}
