import java.util.ArrayList;
import java.util.Scanner;

public class ReservationSystem {
    private ArrayList<Train> trains = new ArrayList<>();
    private ArrayList<Passenger> passengers = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void addSampleTrains() {
        trains.add(new Train("101", "Express A", 50));
        trains.add(new Train("102", "Express B", 40));
        trains.add(new Train("103", "Express C", 30));
    }

    public void showTrains() {
        System.out.println("\nAvailable Trains:");
        for (Train t : trains) {
            t.displayInfo();
            System.out.println("----------------------");
        }
    }

    public void bookTicket() {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter train number to book: ");
        String trainNum = sc.nextLine();

        Train train = findTrain(trainNum);
        if (train != null) {
            if (train.bookSeat()) {
                passengers.add(new Passenger(name, trainNum));
                System.out.println("Ticket booked successfully!");
            } else {
                System.out.println("Sorry, no seats available.");
            }
        } else {
            System.out.println("Train not found.");
        }
    }

    public void cancelTicket() {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter train number to cancel: ");
        String trainNum = sc.nextLine();

        Passenger p = findPassenger(name, trainNum);
        if (p != null) {
            Train train = findTrain(trainNum);
            train.cancelSeat();
            passengers.remove(p);
            System.out.println("Ticket cancelled successfully!");
        } else {
            System.out.println("No booking found.");
        }
    }

    private Train findTrain(String trainNum) {
        for (Train t : trains) {
            if (t.getTrainNumber().equals(trainNum)) return t;
        }
        return null;
    }

    private Passenger findPassenger(String name, String trainNum) {
        for (Passenger p : passengers) {
            if (p.getName().equalsIgnoreCase(name) && p.getTrainNumber().equals(trainNum)) {
                return p;
            }
        }
        return null;
    }

    public void start() {
        addSampleTrains();
        int choice = 0;
        do {
            System.out.println("\n--- Train Reservation System ---");
            System.out.println("1. Show Trains");
            System.out.println("2. Book Ticket");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1: showTrains(); break;
                case 2: bookTicket(); break;
                case 3: cancelTicket(); break;
                case 4: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }
}
