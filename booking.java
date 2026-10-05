import java.util.Scanner;

class TicketBooking {
    private String passengerName;
    private String bookingStatus;

    public TicketBooking(String passengerName) {
        this.passengerName = passengerName;
        this.bookingStatus = "Confirmed";
    }

    public void displayBookingDetails() {
        System.out.println("Passenger Name: " + passengerName);
        System.out.println("Booking Status: " + bookingStatus);
        System.out.println("Thread Name: " + Thread.currentThread().getName());
        System.out.println("Thread ID: " + Thread.currentThread().getId());
        System.out.println("-----------------------------");
    }
}

class BookingThread extends Thread {
    private TicketBooking booking;

    public BookingThread(TicketBooking booking) {
        this.booking = booking;
    }

    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Booking interrupted.");
        }

        booking.displayBookingDetails();
    }
}

class BookingRunnable implements Runnable {
    private TicketBooking booking;

    public BookingRunnable(TicketBooking booking) {
        this.booking = booking;
    }

    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Booking interrupted.");
        }

        booking.displayBookingDetails();
    }
}

public class RailwayReservation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter passenger 1 name: ");
        String name1 = sc.nextLine();

        System.out.print("Enter passenger 2 name: ");
        String name2 = sc.nextLine();

        System.out.print("Enter passenger 3 name: ");
        String name3 = sc.nextLine();

        System.out.print("Enter passenger 4 name: ");
        String name4 = sc.nextLine();

        TicketBooking booking1 = new TicketBooking(name1);
        TicketBooking booking2 = new TicketBooking(name2);
        TicketBooking booking3 = new TicketBooking(name3);
        TicketBooking booking4 = new TicketBooking(name4);

        BookingThread thread1 = new BookingThread(booking1);
        BookingThread thread2 = new BookingThread(booking2);

        BookingRunnable runnable1 = new BookingRunnable(booking3);
        BookingRunnable runnable2 = new BookingRunnable(booking4);

        Thread thread3 = new Thread(runnable1);
        Thread thread4 = new Thread(runnable2);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("All passenger booking requests have been processed successfully.");

        sc.close();
    }
}
