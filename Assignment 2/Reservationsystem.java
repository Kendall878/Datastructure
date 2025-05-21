import java.util.Scanner;

public class Reservationsystem {
    static final int ROWS = 6; // makes 6 rows A-f
    static final int COLS = 10; // makes 10 coloumn
    static char[][] seats = new char[ROWS][COLS]; // 'O' = open, 'X' = reserved

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeSeating();

        while (true) {
            System.out.println("\n- Movie Theater Seat Reservation -");
            System.out.println("1. View seating chart");
            System.out.println("2. Reserve a seat");
            System.out.println("3. Cancel a reservation");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    displaySeatingChart();
                    break;
                case 2:
                    reserveSeat(scanner);
                    break;
                case 3:
                    cancelSeat(scanner);
                    break;
                case 4:
                    System.out.println("Thank you for using the reservation system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    static void initializeSeating() {
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLS; j++)
                seats[i][j] = 'O';
    }

    static void displaySeatingChart() {
        System.out.println("\nSeating Chart ('O' = Open, 'X' = Reserved)");
        System.out.print("   ");
        for (int j = 0; j < COLS; j++)
            System.out.print((j + 1) + " ");
        System.out.println();

        for (int i = 0; i < ROWS; i++) {
            System.out.print((char) ('A' + i) + ": ");
            for (int j = 0; j < COLS; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void reserveSeat(Scanner scanner) {
        System.out.print("Enter seat to reserve (e.g., A5): ");
        String input = scanner.next().toUpperCase();

        if (input.length() < 2 || input.length() > 3) {
            System.out.println("Invalid seat format.");
            return;
        }

        int row = input.charAt(0) - 'A';
        int col;
        try {
            col = Integer.parseInt(input.substring(1)) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid seat number.");
            return;
        }

        if (!isValidSeat(row, col)) {
            System.out.println("Seat does not exist.");
            return;
        }

        if (seats[row][col] == 'X') {
            System.out.println("That seat is already taken.");
            suggestAvailableSeat();
        } else {
            seats[row][col] = 'X';
            System.out.println("Seat " + input + " reserved successfully.");
        }
    }

    static void cancelSeat(Scanner scanner) {
        System.out.print("Enter seat to cancel (e.g., A5): ");
        String input = scanner.next().toUpperCase();

        if (input.length() < 2 || input.length() > 3) {
            System.out.println("Invalid seat format.");
            return;
        }

        int row = input.charAt(0) - 'A';
        int col;
        try {
            col = Integer.parseInt(input.substring(1)) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid seat number.");
            return;
        }

        if (!isValidSeat(row, col)) {
            System.out.println("Seat does not exist.");
            return;
        }

        if (seats[row][col] == 'O') {
            System.out.println("That seat is not reserved.");
        } else {
            seats[row][col] = 'O';
            System.out.println("Seat " + input + " has been canceled.");
        }
    }

    static boolean isValidSeat(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLS;
    }

    static void suggestAvailableSeat() {
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLS; j++)
                if (seats[i][j] == 'O') {
                    System.out.println("Suggested available seat: " + (char) ('A' + i) + (j + 1));
                    return;
                }

        System.out.println("Sorry, no seats available.");
    }
}
