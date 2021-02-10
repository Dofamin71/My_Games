package com.example.tic_tac_toe;

import java.util.ArrayList;

public class Lobby {        // класс комнаты
    public String name;     // название комнаты
    public int amount;      // кол-во игроков
    public ArrayList<Integer> matrix = new ArrayList<>();   // матрица игрового поля

    public Lobby() {}

    public Lobby(String name, int amount) {
        this.name = name;
        this.amount = amount;

        for (int i = 0; i < 9; ++i) {
            matrix.add(i, 0);
        }
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
