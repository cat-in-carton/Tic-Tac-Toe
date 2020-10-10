package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        //System.out.print("Enter cells: ");
        char[][] symbols = new char[3][3];
        // Ввод данных в игру (для промежуточных этапов)
        /*
        String s = scanner.nextLine();
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++, k++) {
                symbols[i][j] = s.charAt(k) == '_' ? ' ' : s.charAt(k);
            }
        }
        */
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                symbols[i][j] = ' ';
            }
        }
        output(symbols);
        int a;
        int b;
        int player = 0;
        do {
            do {
                a = move();
                b = a / 10 - 1;
                a = 4 - a % 10 - 1;
                if (symbols[a][b] == 'X' || symbols[a][b] == 'O') {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            } while (symbols[a][b] == 'X' || symbols[a][b] == 'O');
            if (player % 2 == 0) {
                symbols[a][b] = 'X';
            } else {
                symbols[a][b] = 'O';
            }
            player++;
            output(symbols);
        } while (endOfGame(symbols).equals("Game not finished"));
        System.out.println(endOfGame(symbols));
    }

    public static int move() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the coordinates: ");
        int a;
        int b;
        do {
            while (!scan.hasNextInt()) {
                System.out.println("You should enter numbers!");
                System.out.print("Enter the coordinates: ");
                scan.nextLine();
            }
            a = scan.nextInt();
            b = scan.nextInt();
            if (a < 1 || a > 3 || b < 1 || b > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                System.out.print("Enter the coordinates: ");
            }
        } while (a < 1 || a > 3 || b < 1 || b > 3);
        return a * 10 + b;
    }

    public static void output(char[][] symb) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(symb[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static String endOfGame (char[][] symb) {
        int countX = 0;
        int countO = 0;
        int count3 = 0;
        char row = ' ';
        boolean emptyCells = false;
        boolean normalXO = false;
        for (int i = 0; i < 3; i++) {
            if (symb[0][i] == symb[1][i] && symb[1][i] == symb[2][i] &&
                    (symb[0][i] == 'X' || symb[0][i] == 'O')) {
                    count3++;
                    row = symb[0][i];
            }
            if (symb[i][0] == symb[i][1] && symb[i][1] == symb[i][2] &&
                    (symb[i][0] == 'X' || symb[i][0] == 'O')) {
                    count3++;
                    row = symb[i][0];
            }
            for (int j = 0; j < 3; j++) {
                countX = symb[i][j] == 'X' ? countX + 1 : countX;
                countO = symb[i][j] == 'O' ? countO + 1 : countO;
                if (symb[i][j] == ' ' || symb[i][j] == '_') {
                    emptyCells = true;
                }
            }
        }
        if (symb[0][0] == symb[1][1] && symb[1][1] == symb[2][2] &&
                (symb[0][0] == 'X' || symb[0][0] == 'O')) {
            count3++;
            row = symb[0][0];
        }
        if (symb[2][0] == symb[1][1] && symb[1][1] == symb[0][2] &&
                (symb[2][0] == 'X' || symb[2][0] == 'O')) {
            count3++;
            row = symb[2][0];
        }
        if (Math.abs(countX - countO) <= 1) {
            normalXO = true;
        }
        if (normalXO) {
            if (count3 == 1) {
                return row + " wins";
            } else if (!emptyCells && count3 == 0) {
                return "Draw";
            } else if (emptyCells && count3 == 0) {
                return "Game not finished";
            }
        }
        /*if (!normalXO || count3 > 1) {
            return "Impossible";
        }*/
        return "loh";
    }
}
