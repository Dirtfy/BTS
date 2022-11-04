package com.dirtfy.bts.volley;

public class Transit {
    String arrivalStop;
    String arrivalTime;

    String departureStop;
    String departureTime;

    String distance;
    String duration;

    String type;
    String name;

    public Transit(String arrivalStop, String arrivalTime,
                   String departureStop, String departureTime,
                   String distance, String duration,
                   String type, String name) {
        this.arrivalStop = arrivalStop;
        this.arrivalTime = arrivalTime;
        this.departureStop = departureStop;
        this.departureTime = departureTime;
        this.distance = distance;
        this.duration = duration;
        this.type = type;
        this.name = name;
    }
}
