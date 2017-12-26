package fr.aleclerc.windpath.backend.projection;

import fr.aleclerc.windpath.backend.event.AEvent;
import io.reactivex.Observable;

public interface ITrackEventStream {

    /**
     * Infinite track event stream
     * @return
     */
    Observable<AEvent> getStream();
}
