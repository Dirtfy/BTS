package com.dirtfy.bts.volley;

import androidx.annotation.NonNull;

public class Transit {
    String arrivalStop;
    String arrivalTime;

    String departureStop;
    String departureTime;

    String distance;
    String duration;
    String cost;

    String type;
    String name;

    boolean booking;

    public Transit(String arrivalStop, String arrivalTime,
                   String departureStop, String departureTime,
                   String distance, String duration, String cost,
                   String type, String name,
                   boolean booking) {
        this.arrivalStop = arrivalStop;
        this.arrivalTime = arrivalTime;
        this.departureStop = departureStop;
        this.departureTime = departureTime;
        this.distance = distance;
        this.duration = duration;
        this.cost = cost;
        this.type = type;
        this.name = name;
        this.booking = booking;
    }

    @NonNull
    @Override
    public String toString() {
        String string = "arrivalStop : "+arrivalStop+
                "\narrivalTime : "+arrivalTime+"" +
                "\ndepartureStop : "+departureStop+
                "\ndepartureTime : "+departureTime+
                "\ndistance : "+distance+
                "\nduration : "+duration+
                "\ntype : "+type+
                "\nname : "+name+
                "\nbooking : "+booking+
                "\n\n";

        return string;
    }

    public String getArrivalStop() {
        return arrivalStop;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getDepartureStop() {
        return departureStop;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getDistance() {
        return distance;
    }

    public String getDuration() {
        return duration;
    }

    public String getCost() {
        return cost;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public boolean isBooking() {
        return booking;
    }
}
