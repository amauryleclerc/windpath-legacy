package fr.aleclerc.windpath.cqrs.event;

import io.reactivex.Observable;

public interface IEventSource {

    /**
     * Infinite stream of event
     * @return
     */
    Observable<IEvent> getEvents();
}
