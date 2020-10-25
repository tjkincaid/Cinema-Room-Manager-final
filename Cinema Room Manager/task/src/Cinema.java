
import com.sun.source.tree.WhileLoopTree;

import java.util.Arrays;
import java.util.Scanner;


public class Cinema {
    static int rows = 0;
    static int seatsPerRow= 0;
    static String theaterSize= "";

    static int pickRow = 0;
    static int pickSeat = 0;
    static int menuPick = 99;
    static int amountSold = 0;
    static Long purchasedSeats = 0L;
    static double totalSeats;

    // static String[][] cinema = new String[rows][seatsPerRow];
    public static void main(String[] args) {
        // Write your code here
        scanTheaterSize();
        String[][] cinema = new String[rows][seatsPerRow];
        createTheater(cinema);
        while (menuPick != 0) {
            showMenu(cinema);
        }

    }

    private static void showMenu(String[][] cinema) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1.  Show the seats");
        System.out.println("2.  Buy a Ticket");
        System.out.println("3.  Statistics");
        System.out.println("0.  Exit");

        menuPick = scanner.nextInt();

        switch (menuPick){
            case 0:
                break;
            case 1:
                printTheater(cinema);
                break;
            case 2:
                pickASeat(cinema);
                costOfSeat(cinema, pickRow);
                //checkTheaterSeat(cinema);
                break;
            case 3:
                //tickets purchased
                numberPurchased(cinema);
                // % of tickets purchased
                percentageSeats(cinema);

                // current income
                printAmountSold();
                // total possible income
                totalIncome(cinema);

                break;
            default:
                break;
        }
    }

    private static void printAmountSold() {
        System.out.println("Current income: $" + amountSold);
    }

    private static void percentageSeats(String[][] cinema) {
        purchasedSeats = Arrays.stream(cinema).flatMap(Arrays::stream).filter(str -> str.equals("B")).count();
        double seats = seatsPerRow;
        double theRows = rows;
        double percent = 0.00;
        if(purchasedSeats != 0){
            totalSeats = seats * theRows;
            percent = Math.round((purchasedSeats / totalSeats * 100.00) * 100.00) / 100.00;
            System.out.println("Percentage: " + percent + "%");
        } else {
            System.out.println("Percentage: 0.00%");
        }


    }

    private static void numberPurchased(String[][] cinema) {
        purchasedSeats = Arrays.stream(cinema).flatMap(Arrays::stream).filter(str -> str.equals("B")).count();
        System.out.println("Number of purchased tickets: " + purchasedSeats);

    }

    private static void costOfSeat(String[][] cinema, int pickRow) {
        //if(checkTheaterSeat(cinema)) {
        if (theaterSize.equals("s")) {
            System.out.println("Ticket price: $10");
            amountSold = amountSold + 10;
        } else {
            if (pickRow <= (rows / 2)) {
                System.out.println("Ticket price: $10");
                amountSold = amountSold + 10;
            } else {
                System.out.println("Ticket price: $8");
                amountSold = amountSold + 8;
            }
        }
        //}
    }

    private static void checkTheaterSeat(String[][] cinema) {
        String seat = cinema[pickRow - 1][pickSeat - 1];
        if (seat.equals("B")) {
            System.out.println("That ticket has already been purchased!");
            pickASeat(cinema);
        } else {
            cinema[pickRow - 1][pickSeat - 1] = "B";
        }
        //return true;
        //printTheater(cinema);
    }

    private static void pickASeat(String[][] cinema) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a row number: ");
        pickRow = scanner.nextInt();

        System.out.println("Enter a seat number in that row: ");
        pickSeat = scanner.nextInt();

        validateSeat(cinema);
        validateRow(cinema);
        checkTheaterSeat(cinema);
    }

    private static void validateRow(String[][] cinema) {
        if (pickRow < 1 || pickRow > rows) {
            System.out.println("Wrong input!");
            pickASeat(cinema);
        }

    }

    private static void validateSeat(String[][] cinema) {
        if (pickSeat < 1 || pickSeat > seatsPerRow) {
            System.out.println("Wrong input!");
            pickASeat(cinema);
        }

    }

    private static void scanTheaterSize() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows: ");
        rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row: ");
        seatsPerRow = scanner.nextInt();
    }

    private static void totalIncome(String[][] cinema) {
        //int totalSeats = seatsPerRow*rows;
        int total = calculateTotal(seatsPerRow, rows);
        printTotal(total);

    }

    private static void printTotal(int total) {
        System.out.printf("Total Income: $%d", total);
        System.out.println();
    }

    private static int calculateTotal(int seatsPerRow, int rows) {
        int totalRevenue = 0;
        int frontHalfSeats = 0;
        int backHalfSeats = 0;
        int totalSeats= rows * seatsPerRow;

        if (totalSeats < 60){
            totalRevenue = totalSeats * 10;
            theaterSize = "s";
        } else {
            theaterSize = "l";
            if(rows % 2 == 1){
                frontHalfSeats = (rows/2) * seatsPerRow * 10;
                backHalfSeats = ((rows/2) + 1)  * seatsPerRow * 8;
            } else {
                frontHalfSeats = (rows/2) * seatsPerRow * 10;
                backHalfSeats = (rows/2) * seatsPerRow  * 8;
            }

            totalRevenue = frontHalfSeats + backHalfSeats;

        }


        return totalRevenue;
    }

    private static void printTheater(String[][] cinema) {
        System.out.println("Cinema:");
        String lineOne = " ";
        for (int r = 1; r <= seatsPerRow; r++) {
            lineOne = lineOne + " " + r;
        }
        System.out.println(lineOne);
        String printRow = "";
        for (int i = 0; i < rows; i++){
            int row = i + 1;
            printRow = printRow + row;
            for (int j = 0; j < seatsPerRow; j++){
                printRow = printRow + " " + cinema[i][j];
            }
            System.out.println(printRow);
            printRow = "";
        }
    }

    private static void createTheater(String[][] cinema) {
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < seatsPerRow; j++){
                cinema[i][j] = "S";
            }
        }
    }
}