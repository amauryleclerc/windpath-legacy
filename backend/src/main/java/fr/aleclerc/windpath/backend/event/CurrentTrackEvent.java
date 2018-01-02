package fr.aleclerc.windpath.backend.event;

import fr.aleclerc.windpath.backend.pojo.Track;

public class CurrentTrackEvent extends AEvent {

    private final Track track;

    public CurrentTrackEvent(Track track) {
        super(track.getId(), track.getUserId());
        this.track = track;
    }

    public Track getTrack() {
        return track;
    }
}
