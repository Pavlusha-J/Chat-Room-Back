package com.example.websocketssample;

public class TimeSMS {
    private String time;
    private String zone;

    // Konstruktoriai, getteriai, setteriai
    public TimeSMS() {}

    public TimeSMS(String time, String zone) {
        this.time = time;
        this.zone = zone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}

