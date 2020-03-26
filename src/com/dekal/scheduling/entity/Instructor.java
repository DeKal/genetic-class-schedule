package com.dekal.scheduling.entity;

public class Instructor implements HasToFullString{
    private String id;
    private String name;

    public Instructor(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String toFullString() {
        return "id: " + id + " name: " + name;
    }
}
