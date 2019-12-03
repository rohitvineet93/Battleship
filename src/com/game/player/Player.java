package com.game.player;

public class Player {
    private String field[][], missles[], name;
    private int areaP, areaQ, index;

    public Player(String name, String field[][], String missles[], int areaP, int areaQ) {
        this.field = field;
        this.missles = missles;
        this.areaP = areaP;
        this.areaQ = areaQ;
        index = 0;
        this.name = name;
    }

    public boolean play() {
        boolean flag = true;
        String missle, type;
        int row, column;
        while (flag && index < missles.length) {
            missle = missles[index++];
            row = missle.charAt(0) - 'A';
            column = missle.charAt(1) - '0' - 1;
            type = field[row][column];

            if (type != null) {
                if (type.equals("P")) {
                    areaP--;
                    field[row][column] = "-";
                } else if (type.equals("Q")) {
                    areaQ -= 0.5;
                    field[row][column] = "q";
                } else if (type.equals("q")) {
                    areaQ -= 0.5;
                    field[row][column] = "-";
                } else
                    flag = false;
            } else
                flag = false;

            if (areaP == 0 && areaQ == 0) {
                System.out.println(name + " won the battle");
                return true;
            }
            if (!flag) {
                System.out.println(name + " fires a missile with target " + missle + " which missed");
                break;
            } else
                System.out.println(name + " fires a missile with target " + missle + " which hit");
        }

        if (flag && index >= missles.length) {
            System.out.println(name + " has no more missiles left");
            flag = false;
        }
        return flag;
    }
}
