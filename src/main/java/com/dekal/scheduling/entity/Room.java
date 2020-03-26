package com.dekal.scheduling.entity;

public class Room implements HasToFullString {
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

    public String toFullString() {
        return "rooms# : " + number + ", max seat: " + seats;
    }
}
