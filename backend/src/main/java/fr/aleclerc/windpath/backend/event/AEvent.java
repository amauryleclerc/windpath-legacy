package fr.aleclerc.windpath.backend.event;

import fr.aleclerc.windpath.cqrs.event.IEvent;

import java.util.UUID;

public class AEvent implements IEvent {
    private UUID id;

    protected AEvent(final UUID idTrack){
        this.id = idTrack;
    }

    public UUID getId() {
        return id;
    }

}
