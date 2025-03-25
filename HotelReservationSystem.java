import java.util.*;

class Room {
    int roomNumber;
    String category;
    boolean isBooked;
    double price;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isBooked = false;
    }
}

class Reservation {
    String customerName;
    Room room;
    double amountPaid;

    public Reservation(String customerName, Room room) {
        this.customerName = customerName;
        this.room = room;
        this.amountPaid = room.price;
        room.isBooked = true;
    }
}

public class HotelReservationSystem {
    static List<Room> rooms = new ArrayList<>();
    static List<Reservation> reservations = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeRooms();
        while (true) {
            System.out.println("\n1. View Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Bookings");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> viewAvailableRooms();
                case 2 -> makeReservation();
                case 3 -> viewBookings();
                case 4 -> { System.out.println("Exiting..."); return; }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    static void initializeRooms() {
        rooms.add(new Room(10, "Single", 100.0));
        rooms.add(new Room(20, "Double", 200.0));
        rooms.add(new Room(30, "Suite", 300.0));
        rooms.add(new Room(40, "Single", 100.0));
    }

    static void viewAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        boolean hasAvailableRooms = false;
        for (Room room : rooms) {
            if (!room.isBooked) {
                System.out.println("Room " + room.roomNumber + " - " + room.category + " ($" + room.price + ")");
                hasAvailableRooms = true;
            }
        }
        if (!hasAvailableRooms) {
            System.out.println("No available rooms.");
        }
    }

    static void makeReservation() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        viewAvailableRooms();
        System.out.print("Enter room number to book: ");
        int roomNumber = scanner.nextInt();
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && !room.isBooked) {
                reservations.add(new Reservation(name, room));
                System.out.println("Booking confirmed for " + name + " in Room " + room.roomNumber);
                return;
            }
        }
        System.out.println("Room not available!");
    }

    static void viewBookings() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations yet.");
            return;
        }
        System.out.println("\nReservations:");
        for (Reservation res : reservations) {
            System.out.println(res.customerName + " - Room " + res.room.roomNumber + " ($" + res.amountPaid + ")");
        }
    }
}
