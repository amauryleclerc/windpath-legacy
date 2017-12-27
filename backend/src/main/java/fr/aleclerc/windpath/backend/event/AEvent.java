package fr.aleclerc.windpath.backend.event;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fr.aleclerc.windpath.cqrs.event.IEvent;

import java.util.UUID;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class AEvent implements IEvent {
    private UUID id;

    protected AEvent(final UUID idTrack){
        this.id = idTrack;
    }

    public UUID getId() {
        return id;
    }

}
