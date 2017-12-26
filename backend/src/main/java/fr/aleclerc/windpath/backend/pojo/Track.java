package fr.aleclerc.windpath.backend.pojo;

import java.util.Collections;
import java.util.UUID;
import java.util.List;

public class Track {
    private UUID id;
    private String name;
    private List<Point> points;


    public Track(UUID id, String name, List<Point> points) {
        this.id = id;
        this.name = name;
        this.points = points;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Point> getPoints() {
        return Collections.unmodifiableList(points);
    }
}