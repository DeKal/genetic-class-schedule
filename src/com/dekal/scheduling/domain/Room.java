package com.dekal.scheduling.domain;

public class Room {
    private String number;
    private int seats;

    public Room(String number, int seats) {
        this.number = number;
        this.seats = seats;
    }

    public String getNumber() {
        return number;
    }

    public int getSeats() {
        return seats;
    }
}
