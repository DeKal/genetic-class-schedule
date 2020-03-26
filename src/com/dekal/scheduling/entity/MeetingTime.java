package com.dekal.scheduling.entity;

public class MeetingTime  implements HasToFullString {
    private String id;
    private String time;

    public MeetingTime(String id, String time) {
        this.id = id;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String toFullString() {
        return "id: " + id + ", time: " + time;
    }
}
