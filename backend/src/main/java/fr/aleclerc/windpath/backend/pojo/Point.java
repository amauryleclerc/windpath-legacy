package fr.aleclerc.windpath.backend.pojo;

import java.time.Instant;

public class Point {
    private double lat;
    private double lon;
    private double ele;
    private double speed;
    private Instant time;


    public Point(double lat, double lon, double ele, double speed, Instant time) {
        this.lat = lat;
        this.lon = lon;
        this.ele = ele;
        this.speed = speed;
        this.time = time;
    }


    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public double getEle() {
        return ele;
    }

    public double getSpeed() {
        return speed;
    }

    public Instant getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Point{" +
                "lat=" + lat +
                ", lon=" + lon +
                ", ele=" + ele +
                ", speed=" + speed +
                ", time=" + time +
                '}';
    }
}
