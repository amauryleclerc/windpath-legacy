package fr.aleclerc.windpath.backend.command;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fr.aleclerc.windpath.cqrs.command.ICommand;

import java.util.UUID;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public abstract class ACommand implements ICommand {

    private final UUID idTrack;
    private final String userId;

    protected ACommand(final UUID idTrack, final String userId) {
        this.idTrack = idTrack;
        this.userId = userId;
    }

    public UUID getId() {
        return idTrack;
    }

    public String getUserId() {
        return userId;
    }
}
