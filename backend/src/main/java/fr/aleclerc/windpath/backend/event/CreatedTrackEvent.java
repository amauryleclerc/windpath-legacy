package fr.aleclerc.windpath.backend.event;

import fr.aleclerc.windpath.backend.pojo.Point;

import java.util.List;
import java.util.UUID;

public class CreatedTrackEvent extends AEvent {

    private final String name;
    private final List<Point> points;

    public CreatedTrackEvent(final UUID id, final String name, final List<Point> points, final String userId) {
        super(id,userId);
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public List<Point> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "CreatedTrackEvent{" +
                "name='" + name + '\'' +
                ", points=" + points +
                '}';
    }
}
