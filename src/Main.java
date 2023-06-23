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
        String[] exampArr = new  String[65];
        for (int i = 0; i < arr2.length; i++) {
            exampArr[i] = arr2[i];
        }

        System.out.println();
        System.out.println("Write Your step, Example \"H6 G7\" ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] inputRes = input.split(" ", 2);
        String figure = "";
        int index1 = -1;
        int index2 =-1;

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
                index1 = i;
            }
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i].equals(inputRes[1])) {
                arr2[i] = figure;
                index2 = i;
            }

        }


       check (exampArr,arr2, arr1, inputRes, figure,check,index1,index2);
        main(arr2);


    }
    public static void arrToNewArr (String[] arr1,String[] arr2){
        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = arr1[i];
        }
    }
    public static void check (String[] exampArr,String[] arr2,String[] arr1,String[] inputRes,String figure,String check,int index1, int index2){
        if (figure.contains("p")){
            checkP(exampArr,arr2,arr1,figure,index1,index2, check);
        }else if (figure.contains("r")){
            checkR(exampArr,arr2,arr1,figure,index1,index2, check);
        }

    }
    public static void checkP (String[] exampArr,String[] arr2,String[] arr1,String figure,int index1, int index2,String check){
        if(figure.contains("B")) {
            if (((index1 + 8 == index2 && exampArr[index1+8] != null) || (index1 + 8 != index2 && index1 + 7 != index2 && index1 + 9 != index2 ) || (index1 + 7 == index2 && exampArr[index2]==null) || (index1 + 9 == index2 && exampArr[index2]==null)) || (exampArr[index2] != null && exampArr[index2].contains("B") )) {
                System.out.println("Error step for Black Pawn");
                for (int i = 0; i < exampArr.length; i++) {
                    arr2[i] = exampArr[i];
                }

                input(arr1, arr2, check);

            }
        }else if (figure.contains("W")){
            if (((index1 - 8 == index2 && exampArr[index1-8] != null) || (index1 - 8 != index2 && index1 - 7 != index2 && index1 - 9 != index2 )  || (index1 - 7 == index2 && exampArr[index2]==null) || (index1 - 9 == index2 && exampArr[index2]==null)) || (exampArr[index2] != null && exampArr[index2].contains("W") )){
                System.out.println(exampArr[index2]);
                for (int i = 0; i < exampArr.length; i++) {
                    arr2[i] = exampArr[i];
                }
                System.out.println("Error step for White Pawn");
                input(arr1, arr2, check);

            }


        }
        else {
           arrToNewArr(exampArr,arr2);
            System.out.println("unknown error");
            input(arr1, arr2, check);

        }

    }
    public static void checkR(String[] exampArr,String[] arr2,String[] arr1,String figure,int index1, int index2,String check){

        if ((index1-index2)%8!=0 && arr1[index2].indexOf(arr1[index1].charAt(1))==-1 ){
            arrToNewArr(exampArr,arr2);
            System.out.println("Error step for Rook");
            input(arr1, arr2, check);
        }else if( exampArr[index2]!=null &&  exampArr[index2].indexOf(exampArr[index1].charAt(0))!=-1){
            arrToNewArr(exampArr,arr2);
            System.out.println("Error step for Rook");
            input(arr1, arr2, check);
        }
        if (arr1[index2].indexOf(arr1[index1].charAt(1))!=-1 ){

            if (index1 - index2<0) {
                for (int i = index1; i < index2 + 1; i++) {
                  checkR1(exampArr,arr2,arr1,figure,i,index2, check);
                }
            } else if (index1 - index2>0) {
                for (int i = index1; i > index2 - 1; i--) {
                    checkR1(exampArr,arr2,arr1,figure,i,index2, check);

                }
            }
        } else if((index1-index2)%8==0){
            if (index1 - index2<0) {
                for (int i = index1; i < index2 + 1; i++) {

                    checkR1(exampArr,arr2,arr1,figure,i,index2, check);
                    i = i +7;
                }
            } else if (index1 - index2>0) {
                for (int i = index1; i > index2 - 1; i--) {
                    checkR1(exampArr,arr2,arr1,figure,i,index2, check);
                    i = i -7;

                }
            }


        }

    }
    public static void checkR1(String[] exampArr,String[] arr2,String[] arr1,String figure,int i, int index2,String check){
        if (figure.contains("B")) {
            if (arr2[i] != null && i != index2 ) {
                arrToNewArr(exampArr,arr2);
                System.out.println("Error step for Rook");
                input(arr1, arr2, check);
            }else if (i == index2 && exampArr[i]!=null && exampArr[i].contains("B")) {
                arrToNewArr(exampArr,arr2);
                System.out.println("Error step for Rook");
                input(arr1, arr2, check);

            }
        }else if(figure.contains("W")){
            if (arr2[i] != null && i != index2 ) {
                arrToNewArr(exampArr,arr2);
                System.out.println("Error step for Rook");
                input(arr1, arr2, check);
            }else if (i == index2 && exampArr[i]!=null && exampArr[i].contains("W")) {
                arrToNewArr(exampArr,arr2);
                System.out.println("Error step for Rook");
                input(arr1, arr2, check);

            }
        }
    }

}
