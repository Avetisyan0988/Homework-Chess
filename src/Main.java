import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String[] numbering = {"A", "B", "C", "D", "E", "F", "G", "H"};
        String[] boardInArray = new String[66];
        String[] board = new String[64];
        chessBoard(board);
        arrayStr(boardInArray);
        String check = "jjj";
        if (args.length != 0) {
            boardInArray = args;
            check = args[64];
        }
        printNumbering(numbering);
        printPlus();
        rows(boardInArray);
        if (boardInArray[65] != null) {
            System.out.println(" ");
            System.out.println(args[65]);
            boardInArray[65] = null;
        }
        input(board, boardInArray, check);

    }

    private static void arrayStr(String[] row) {
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

    private static void printPlus() {
        System.out.println();
        System.out.print("   +");
        for (int i = 0; i < 8; i++) {
            System.out.print("---+");
        }
    }

    private static void printNumbering(String[] numbering) {
        System.out.print("   ");
        for (int i = 0; i < 8; i++) {
            System.out.print("  " + numbering[i] + " ");
        }
    }

    private static void rows(String[] row1) {
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

    private static void chessBoard(String[] board) {
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

    private static void input(String[] arr1, String[] arr2, String check) {
        String[] exampArr = new String[65];
        System.arraycopy(arr2, 0, exampArr, 0, exampArr.length);
        System.out.println();
        System.out.println("Write Your step, Example \"H6 G7\" ");
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine().toUpperCase();
        if (!inputString.contains(" ")) {
            System.out.println("You forgot to put a space");
            input(arr1, arr2, check);
        }
        if (inputString.length() != 5) {
            System.out.println("You wrote the command incorrectly");
            input(arr1, arr2, check);
        }
        String[] inputRes = inputString.split(" ", 2);
        String figure = "";
        String figureColor = " ";
        boolean b = true;
        int index1 = -1;
        int index2 = -1;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i].equals(inputRes[0])) {
                if (arr2[i] == null) {
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
                    figureColor = "W";
                } else {
                    arr2[64] = "W";
                    figureColor = "B";
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
        check(exampArr, arr2, arr1, figure, check, index1, index2);
        for (int i = 0; i < exampArr.length; i++) {
            if (arr2[i] != null && arr2[i].contains(figure.charAt(0) + "K")) {
                index1 = i;
                break;
            }
        }
        checkCheck(exampArr, arr2, arr1, figure, index1, check, b);
        for (int i = 0; i < exampArr.length; i++) {
            if (arr2[i] != null && arr2[i].contains(figureColor + "K")) {
                index1 = i;
                break;
            }
        }
        b = false;
        checkCheck(exampArr, arr2, arr1, figureColor, index1, check, b);
        main(arr2);
    }

    private static void arrToNewArr(String[] arr1, String[] arr2) {
        System.arraycopy(arr1, 0, arr2, 0, arr1.length);
    }

    private static void check(String[] exampArr, String[] arr2, String[] arr1, String figure, String check, int index1, int index2) {
        if (figure.contains("p")) {
            checkP(exampArr, arr2, arr1, figure, index1, index2, check);
        } else if (figure.contains("r")) {
            checkR(exampArr, arr2, arr1, figure, index1, index2, check);
        } else if (figure.contains("b")) {
            checkB(exampArr, arr2, arr1, figure, index1, index2, check);
        } else if (figure.contains("Q")) {
            checkQ(exampArr, arr2, arr1, figure, index1, index2, check);
        } else if (figure.contains("n")) {
            checkN(exampArr, arr2, arr1, figure, index1, index2, check);
        } else if (figure.contains("K")) {
            checkK(exampArr, arr2, arr1, figure, index1, index2, check);
        }
    }

    private static void checkK(String[] exampArr, String[] arr2, String[] arr1, String figure, int index1, int index2, String check) {
        if (index1 - 1 != index2 && index1 + 1 != index2 && index1 + 8 != index2 && index1 - 8 != index2 && index1 + 7 != index2 && index1 - 7 != index2 && index1 + 9 != index2 && index1 - 9 != index2) {
            errorMassage("King", exampArr, arr2, arr1, check);
        }
        if (exampArr[index2] != null && exampArr[index2].indexOf(figure.charAt(0)) != -1) {
            errorMassage("King", exampArr, arr2, arr1, check);
        }
    }

    private static void checkN(String[] exampArr, String[] arr2, String[] arr1, String figure, int index1, int index2, String check) {
        if (index1 != index2 + 15 && index1 != index2 - 15 && index1 != index2 + 17 && index1 != index2 - 17 && index1 != index2 + 10 && index1 != index2 - 10 && index1 != index2 + 6 && index1 != index2 - 6) {
            errorMassage("Knight", exampArr, arr2, arr1, check);
        }
        if (exampArr[index2] != null && exampArr[index2].indexOf(figure.charAt(0)) != -1) {
            errorMassage("Knight", exampArr, arr2, arr1, check);
        }
    }

    private static void checkQ(String[] exampArr, String[] arr2, String[] arr1, String figure, int index1, int index2, String check) {
        String[] qStepList = new String[65];
        String[] qStepList1 = new String[65];
        checkB1(0, 7, index1, arr1, qStepList, "x1");
        checkB1(+1, 9, index1, arr1, qStepList, "x2");
        checkB1(0, -9, index1, arr1, qStepList, "x3");
        checkB1(+1, -7, index1, arr1, qStepList, "x4");
        checkQ1(8, index1, arr1, qStepList1, "x5");
        checkQ1(-8, index1, arr1, qStepList1, "x6");
        qStepList[index1] = "Q";
        qStepList1[index1] = "Q";
        if (qStepList[index2] == null && qStepList1[index2] == null) {
            errorMassage("Queen", exampArr, arr2, arr1, check);
        }
        checkStep("Queen", qStepList, index2, index1, exampArr, arr2, arr1, check, figure);
        checkStep("Queen", qStepList1, index2, index1, exampArr, arr2, arr1, check, figure);
    }

    private static void checkQ1(int x, int index1, String[] arr1, String[] bStepList, String s) {
        for (int i = index1; i < arr1.length && i > -1; i = i + x) {
            bStepList[i] = s;
        }
        for (int i = index1; i < arr1.length && i > -1; i++) {
            if ((i + 1) % 8 == 0) {
                bStepList[i] = s;
                break;
            }
            bStepList[i] = s;
        }
        for (int i = index1; i < arr1.length && i > -1; i--) {
            if (i % 8 == 0) {
                bStepList[i] = s + "-";
                break;
            }
            bStepList[i] = s + "-";
        }
    }

    private static void checkB(String[] exampArr, String[] arr2, String[] arr1, String figure, int index1, int index2, String check) {
        String[] bStepList = new String[65];
        checkB1(0, 7, index1, arr1, bStepList, "x1");
        checkB1(+1, 9, index1, arr1, bStepList, "x2");
        checkB1(0, -9, index1, arr1, bStepList, "x3");
        checkB1(+1, -7, index1, arr1, bStepList, "x4");
        bStepList[index1] = "b";
        if (bStepList[index2] == null) {
            errorMassage("Bishop", exampArr, arr2, arr1, check);
        }
        checkStep("Bishop", bStepList, index2, index1, exampArr, arr2, arr1, check, figure);
    }

    private static void checkB1(int y, int x, int index1, String[] arr1, String[] bStepList, String s) {
        for (int i = index1; i < arr1.length && i > -1; i = i + x) {
            if ((i + y) % 8 == 0) {
                bStepList[i] = s;
                break;
            }
            bStepList[i] = s;
        }
    }

    private static void checkP(String[] exampArr, String[] arr2, String[] arr1, String figure, int index1, int index2, String check) {
        if (figure.contains("B")) {
            if (arr1[index1].contains("7") && index2 == index1 + 16 && exampArr[index2] == null) {
                System.out.print(" ");
            } else if (((index1 + 8 == index2 && exampArr[index1 + 8] != null) || (index1 + 8 != index2 && index1 + 7 != index2 && index1 + 9 != index2) || (index1 + 7 == index2 && exampArr[index2] == null) || (index1 + 9 == index2 && exampArr[index2] == null)) || (exampArr[index2] != null && exampArr[index2].contains("B"))) {
                errorMassage("Pawn", exampArr, arr2, arr1, check);
            }
        } else if (figure.contains("W")) {
            if (arr1[index1].contains("2") && index2 == index1 - 16 && exampArr[index2] == null) {
                System.out.print(" ");
            } else if (((index1 - 8 == index2 && exampArr[index1 - 8] != null) || (index1 - 8 != index2 && index1 - 7 != index2 && index1 - 9 != index2) || (index1 - 7 == index2 && exampArr[index2] == null) || (index1 - 9 == index2 && exampArr[index2] == null)) || (exampArr[index2] != null && exampArr[index2].contains("W"))) {
                errorMassage("Pawn", exampArr, arr2, arr1, check);
            }
        } else {
            errorMassage("Unknown", exampArr, arr2, arr1, check);
        }
    }

    private static void checkR(String[] exampArr, String[] arr2, String[] arr1, String figure, int index1, int index2, String check) {
        if ((index1 - index2) % 8 != 0 && arr1[index2].indexOf(arr1[index1].charAt(1)) == -1) {
            errorMassage("Rook", exampArr, arr2, arr1, check);

        } else if (exampArr[index2] != null && exampArr[index2].indexOf(exampArr[index1].charAt(0)) != -1) {
            errorMassage("Rook", exampArr, arr2, arr1, check);
        }
        if (arr1[index2].indexOf(arr1[index1].charAt(1)) != -1) {
            if (index1 - index2 < 0) {
                for (int i = index1; i < index2 + 1; i++) {
                    checkR1(exampArr, arr2, arr1, figure, i, index2, check);
                }
            } else if (index1 - index2 > 0) {
                for (int i = index1; i > index2 - 1; i--) {
                    checkR1(exampArr, arr2, arr1, figure, i, index2, check);
                }
            }
        } else if ((index1 - index2) % 8 == 0) {
            if (index1 - index2 < 0) {
                for (int i = index1; i < index2 + 1; i++) {
                    checkR1(exampArr, arr2, arr1, figure, i, index2, check);
                    i = i + 7;
                }
            } else if (index1 - index2 > 0) {
                for (int i = index1; i > index2 - 1; i--) {
                    checkR1(exampArr, arr2, arr1, figure, i, index2, check);
                    i = i - 7;
                }
            }
        }
    }

    private static void checkR1(String[] exampArr, String[] arr2, String[] arr1, String figure, int i, int index2, String check) {
        if (figure.contains("B")) {
            if (arr2[i] != null && i != index2) {
                errorMassage("Rook", exampArr, arr2, arr1, check);
            } else if (i == index2 && exampArr[i] != null && exampArr[i].contains("B")) {
                errorMassage("Rook", exampArr, arr2, arr1, check);
            }
        } else if (figure.contains("W")) {
            if (arr2[i] != null && i != index2) {
                errorMassage("Rook", exampArr, arr2, arr1, check);
            } else if (i == index2 && exampArr[i] != null && exampArr[i].contains("W")) {
                errorMassage("Rook", exampArr, arr2, arr1, check);
            }
        }
    }

    private static void checkStep(String figureName, String[] bStepList, int index2, int index1, String[] exampArr, String[] arr2, String[] arr1, String check, String figure) {
        for (int i = 0; i < arr1.length; i++) {
            boolean bool = false;
            boolean bool1 = true;
            if (i > index2 && i < index1) {
                bool = true;
            } else if (i > index1 && i < index2) {
                bool = true;
            }
            if (figureName.contains("Queen") && arr1[i].indexOf(arr1[index1].charAt(1)) != -1 && arr1[index2].indexOf(arr1[index1].charAt(1)) == -1) {
                bool1 = false;
            }
            if (bStepList[i] != null && bStepList[index2] != null && bStepList[i].contains(bStepList[index2]) && exampArr[i] != null && bool && bool1) {
                errorMassage(figureName, exampArr, arr2, arr1, check);
            } else if (i == index2 && exampArr[index2] != null && exampArr[index2].indexOf(figure.charAt(0)) != -1) {
                errorMassage(figureName, exampArr, arr2, arr1, check);
            }
        }
    }

    private static void errorMassage(String figureName, String[] exampArr, String[] arr2, String[] arr1, String check) {
        arrToNewArr(exampArr, arr2);
        System.out.println("Error step for " + figureName);
        input(arr1, arr2, check);
    }

    private static void checkCheck(String[] exampArr, String[] arr2, String[] arr1, String figure, int index1, String check, boolean b) {
        String figureColor = "W";
        if (figure.contains("W")) {
            figureColor = "B";
        }
        checkCheck3(0, 7, index1, arr1, arr2, figureColor, exampArr, check, b);
        checkCheck3(+1, 9, index1, arr1, arr2, figureColor, exampArr, check, b);
        checkCheck3(0, -9, index1, arr1, arr2, figureColor, exampArr, check, b);
        checkCheck3(+1, -7, index1, arr1, arr2, figureColor, exampArr, check, b);
        checkCheck1(8, index1, exampArr, arr1, arr2, figureColor, figure, check, b);
        checkCheck1(-8, index1, exampArr, arr1, arr2, figureColor, figure, check, b);
        checkCheck4( index1, exampArr, arr1, arr2, figureColor, check);
        checkCheck5(exampArr,arr2,arr1,figureColor,index1,check);
    }

    private static void checkCheck1(int x, int index1, String[] exampArr, String[] arr1, String[] arr2, String figureColor, String figure, String check, boolean b) {
        for (int i = index1; i < arr1.length && i > -1; i = i + x) {
            if (i != index1 && arr2[i] != null && exampArr[index1].indexOf(figure.charAt(0)) != -1) {
                break;
            }
            checkCheck2(i, index1, arr2, figureColor, "r", exampArr, arr1, check, b);
        }
        for (int i = index1; i < arr1.length && i > -1; i++) {
            if (i != index1 && arr2[i] != null && exampArr[index1].indexOf(figure.charAt(0)) != -1) {
                break;
            }
            if ((i + 1) % 8 == 0) {
                checkCheck2(i, index1, arr2, figureColor, "r", exampArr, arr1, check, b);
                break;
            }
            checkCheck2(i, index1, arr2, figureColor, "r", exampArr, arr1, check, b);
        }
        for (int i = index1; i < arr1.length && i > -1; i--) {
            if (i != index1 && arr2[i] != null && exampArr[index1].indexOf(figure.charAt(0)) != -1) {
                break;
            }
            if (i % 8 == 0) {
                checkCheck2(i, index1, arr2, figureColor, "r", exampArr, arr1, check, b);
                break;
            }
            checkCheck2(i, index1, arr2, figureColor, "r", exampArr, arr1, check, b);
        }
    }

    private static void checkCheck2(int i, int index1, String[] arr2, String figureColor, String figure, String[] exampArr, String[] arr1, String check, boolean b) {
        if (i != index1 && arr2[i] != null && arr2[i].contains(figureColor) && (arr2[i].contains("Q") || arr2[i].contains(figure))) {
            if (b) {
                System.out.println("You cannot make that move because the king is in check.");
                arrToNewArr(exampArr, arr2);
                input(arr1, arr2, check);
            }
            arr2[65] = "Attention!!! Check is declared.";
        }
    }

    private static void checkCheck3(int y, int x, int index1, String[] arr1, String[] arr2, String figureColor, String[] exampArr, String check, boolean b) {
        for (int i = index1; i < arr1.length && i > -1; i = i + x) {
            if (i != index1 && arr2[i] != null && arr2[i].indexOf(exampArr[index1].charAt(0)) != -1) {
                break;
            }
            if ((i + y) % 8 == 0) {
                checkCheck2(i, index1, arr2, figureColor, "b", exampArr, arr1, check, b);
                break;
            }
            checkCheck2(i, index1, arr2, figureColor, "b", exampArr, arr1, check, b);
        }
    }

    private static void checkCheck4( int index1, String[] exampArr, String[] arr1, String[] arr2, String figureColor,  String check) {
        if (figureColor.contains("B")) {
            if (arr2[index1 - 7] != null && arr2[index1 - 7].contains("Bp") || arr2[index1 - 9] != null && arr2[index1 - 9].contains("Bp")) {
                if (arr2[64].contains("W")) {
                    System.out.println("You cannot make that move because the king is in check.");
                    arrToNewArr(exampArr, arr2);
                    input(arr1, arr2, check);
                } else {
                    arr2[65] = "Attention!!! Check is declared.";
                }
            } else if (figureColor.contains("W")) {
                if (arr2[index1 + 7] != null && arr2[index1 + 7].contains("Wp") || arr2[index1 + 9] != null && arr2[index1 + 9].contains("Wp")) {
                    if (arr2[64].contains("B")) {
                        System.out.println("You cannot make that move because the king is in check.");
                        arrToNewArr(exampArr, arr2);
                        input(arr1, arr2, check);
                    } else {
                        arr2[65] = "Attention!!! Check is declared.";
                    }
                }
            }
        }
    }

    private static void checkCheck5(String[] exampArr, String[] arr2, String[] arr1, String figureColor, int index1, String check) {
        for (int i = 0; i < exampArr.length; i++) {
            if(arr2[i] != null && arr2[i].contains("Wn") && figureColor.contains("W")){
                if (i == index1 + 15 || i == index1 - 15 || i == index1 + 17 || i == index1 - 17 || i == index1 + 10 || i == index1 - 10 || i == index1 + 6 || i == index1 - 6) {
                    if (arr2[64].contains("B")) {
                        System.out.println("You cannot make that move because the king is in check.");
                        arrToNewArr(exampArr, arr2);
                        input(arr1, arr2, check);
                    } else {
                        arr2[65] = "Attention!!! Check is declared.";
                    }
                }
            }
            if(arr2[i] != null && arr2[i].contains("Bn") && figureColor.contains("B")){
                if (i == index1+9999 || i == index1 + 15 || i == index1 - 15 || i == index1 + 17 || i == index1 - 17 || i == index1 + 10 || i == index1 - 10 || i == index1 + 6 || i == index1 - 6) {
                    if (arr2[64].contains("W")) {
                        System.out.println("You cannot make that move because the king is in check.");
                        arrToNewArr(exampArr, arr2);
                        input(arr1, arr2, check);
                    } else {
                        arr2[65] = "Attention!!! Check is declared.";
                    }
                }
            }
        }
    }
}
