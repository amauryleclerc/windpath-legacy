package fr.aleclerc.windpath.cqrs.event;

import io.reactivex.Completable;

public interface IEventPublisher {
    /**
     * Publish an event
     * @param event
     * @return
     */
    Completable publish(IEvent event);
}
