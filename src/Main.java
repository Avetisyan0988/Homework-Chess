import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        String[] numbering = {"A", "B", "C", "D", "E", "F", "G", "H"};
        String[] boardInArray = new String[65];
        String[] board = new String[64];
        doskaChess(board);
        arrayStr(boardInArray);
        String check = "jjj";

        if (args.length != 0) {
            boardInArray = args;
            check = args[64];

        }


        printNumbering(numbering);
        printPlus();
        rows(boardInArray);
        input(board, boardInArray, check);

    }

    public static void arrayStr(String[] row) {
        row[0] = "Br |";
        row[1] = "Bn |";
        row[2] = "Bb |";
        row[3] = "BQ |";
        row[4] = "BK |";
        row[5] = "Bb |";
        row[6] = "Bn |";
        row[7] = "Br |";
        for (int i = 8; i < 16; i++) {
            row[i] = "Bp |";
            row[i + 40] = row[i].replace("B", "W");
        }
        for (int i = 0; i < 8; i++) {
            row[i + 56] = row[i].replace("B", "W");

        }

    }

    public static void printPlus() {
        System.out.println();
        System.out.print("   +");
        for (int i = 0; i < 8; i++) {
            System.out.print("---+");

        }
    }

    public static void printNumbering(String[] numbering) {

        System.out.print("   ");
        for (int i = 0; i < 8; i++) {
            System.out.print("  " + numbering[i] + " ");

        }

    }

    public static void rows(String[] row1) {

        int x = 8;
        for (int j = 0; j < 64; j++) {
            if (j % 8 == 0 && j != 0) {

                printPlus();
                System.out.println();
                System.out.print(x + "  ");
                System.out.print("|");
                x--;


            } else if (j == 0) {
                System.out.println();
                System.out.print(x + "  ");
                System.out.print("|");
                x--;

            }
            if (row1[j] != null) {
                System.out.print(row1[j]);
            } else {
                System.out.print("   |");
            }


        }
        printPlus();


    }

    public static void doskaChess(String[] board) {
        int x = 8;
        for (int i = 0; i < 64; i += 8) {
            if (i % 8 == 0 && i != 0) {
                x--;
            }
            board[i] = "A" + x;
            board[i + 1] = "B" + x;
            board[i + 2] = "C" + x;
            board[i + 3] = "D" + x;
            board[i + 4] = "E" + x;
            board[i + 5] = "F" + x;
            board[i + 6] = "G" + x;
            board[i + 7] = "H" + x;

        }
    }

    public static void input(String[] arr1, String[] arr2, String check) {
        System.out.println();
        System.out.println("Write Your step, Example \"H6 G7\" ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] inputRes = input.split(" ", 2);
        String figure = "";

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i].equals(inputRes[0])) {
                if (arr2[i] == null){
                    System.out.println("There is no piece in the box You selected, please choose the correct box.");
                    input(arr1, arr2, check);
                }
                figure = arr2[i];
                if (figure.contains(check)) {
                    System.out.println("Please play opposite colors, You are playeing with the same player.");
                    input(arr1, arr2, check);
                }
                if (!figure.contains("W")) {
                    arr2[64] = "B";
                } else {
                    arr2[64] = "W";
                }

                arr2[i] = null;
            }
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i].equals(inputRes[1])) {
                arr2[i] = figure;
            }

        }

        main(arr2);


    }

}
