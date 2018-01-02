package fr.aleclerc.windpath.backend.event;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fr.aleclerc.windpath.cqrs.event.IEvent;

import java.util.UUID;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class AEvent implements IEvent {
    private final UUID id;
    private final String userId;

    protected AEvent(final UUID idTrack,  final String userId){
        this.id = idTrack;
        this.userId = userId;
    }

    public UUID getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }
}
