package fr.aleclerc.windpath.backend.command;

import fr.aleclerc.windpath.backend.pojo.Point;

import java.util.List;
import java.util.UUID;

public class CreateTrackCommand extends ACommand {


    private final String name;
    private final List<Point> points;

    public CreateTrackCommand(final UUID id,final String name,final List<Point> points) {
        super(id);
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public List<Point> getPoints() {
        return points;
    }
}
