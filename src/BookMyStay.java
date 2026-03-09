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
    }
}
