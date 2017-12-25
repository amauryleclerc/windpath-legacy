package fr.aleclerc.windpath.backend.command;


import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.UUID;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public abstract class ACommand {

    private UUID idTrack;

    protected ACommand(final UUID idTrack){
        this.idTrack = idTrack;
    }

    public UUID getId() {
        return idTrack;
    }
}
