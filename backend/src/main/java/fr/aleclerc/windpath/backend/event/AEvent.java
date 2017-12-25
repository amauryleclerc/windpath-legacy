package fr.aleclerc.windpath.backend.event;

import java.util.UUID;

public class AEvent {
    private UUID idTrack;

    protected AEvent(final UUID idTrack){
        this.idTrack = idTrack;
    }

    public UUID getId() {
        return idTrack;
    }
}
