package fr.aleclerc.windpath.backend.event;

import fr.aleclerc.windpath.cqrs.event.IEvent;

import java.util.UUID;

public class AEvent implements IEvent {
    private UUID id;
    private String action;

    protected AEvent(final UUID idTrack){
        this.id = idTrack;
        this.action = this.getClass().getSimpleName();
    }

    public UUID getId() {
        return id;
    }


    public String getAction() {
        return action;
    }
}
