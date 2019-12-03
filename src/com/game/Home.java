package com.game;

import com.game.battleship.Battleship;

import java.util.Scanner;

public class Home {
    private static Scanner scanner;
    private int areaP, areaQ;
    private String field1[][], field2[][], missles1[], missles2[];

    public Home() {
        scanner = new Scanner(System.in);
        areaP = areaQ = -1;
    }

    private String[][] intitalize_field(String field[][], int m, int n, String type) {
        int i = 0, x, y, j = 0;
        String tmp;

        tmp = scanner.next();
        x = tmp.charAt(0) - 'A';
        y = tmp.charAt(1) - '0' - 1;
        while (i < m) {
            while (j < n) {
                field[x + i][y + j] = type;
                j++;
            }
            i++;
        }
        if (type.equals("P") && areaP == -1)
            areaP = m * n;
        else if (type.equals("Q") && areaQ == -1)
            areaQ = m * n;

        return field;
    }

    private void initalize_battleship(String name) {
        String type;
        int m, n;

        System.out.print("Type for " + name + ": ");
        type = scanner.next();

        System.out.print("Dimension for " + name + ": ");
        n = scanner.nextInt();
        m = scanner.nextInt();

        System.out.print("Location of " + name + " for player A: ");
        field1 = intitalize_field(field1, m, n, type);

        System.out.print("Location of " + name + " for player B: ");
        field2 = intitalize_field(field2, m, n, type);
    }

    public static void main(String[] args) {
        Battleship battleship;
        Home home = new Home();
        String area, str1 = "", str2 = "";
        int row, column;

        System.out.print("Enter area boundaries: ");
        area = scanner.nextLine();

        row = area.charAt(2) - 'A' + 1;
        column = area.charAt(0) - '0';
        home.field1 = new String[row][column];
        home.field2 = new String[row][column];

        // Initalizing battlefield 1
        home.initalize_battleship("battleship 1");

        // Initalizing battlefield 2
        home.initalize_battleship("battleship 2");

        System.out.print("Missile targets for player A: ");
        str1 = scanner.next();
        str1 += scanner.nextLine();

        System.out.print("Missile targets for player B: ");
        str2 = scanner.nextLine();

        home.missles1 = str1.split(" ");
        home.missles2 = str2.split(" ");

        battleship = new Battleship.Builder()
                .setArea(area)
                .setPlayer1("Player-1", home.field2, home.missles1, home.areaP, home.areaQ)
                .setPlayer2("Player-2", home.field1, home.missles2, home.areaP, home.areaQ)
                .build();

        battleship.start();

    }
}
