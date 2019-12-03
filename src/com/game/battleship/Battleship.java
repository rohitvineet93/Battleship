package com.game.battleship;

import com.game.player.Player;

public class Battleship implements Action {
    private int m, n;
    private Player player1, player2;

    public Battleship(String area, Player player1, Player player2) {
        m = area.charAt(0);
        n = area.charAt(1);
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void start() {
        boolean flag = false;
        while (!flag) {
            flag = player1.play();
            if(!flag)
                flag = player2.play();
        }
    }

    @Override
    public void stop() {
    }

    public static class Builder {
        String area;
        Player player1, player2;

        public Builder setArea(String area) {
            this.area = area;
            return this;
        }

        public Builder setPlayer1(String name, String[][] field1, String[] missle1, int areaP, int areaQ) {
            this.player1 = new Player(name, field1, missle1, areaP, areaQ);
            return this;
        }

        public Builder setPlayer2(String name, String[][] field2, String[] missle2, int areaP, int areaQ) {
            this.player2 = new Player(name, field2, missle2, areaP, areaQ);
            return this;
        }

        public Battleship build() {
            return new Battleship(area, player1, player2);
        }

    }
}
