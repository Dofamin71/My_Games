package com.example.tic_tac_toe;

public class Room {
    public Room() {}

    public Room(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String name;
    public int amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
