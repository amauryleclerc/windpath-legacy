package fr.aleclerc.windpath.backend.bus;

import fr.aleclerc.windpath.backend.event.AEvent;
import io.reactivex.Completable;

public interface IEventPublisher {
    Completable publish(AEvent event);
}
