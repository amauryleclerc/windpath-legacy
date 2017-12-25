package fr.aleclerc.windpath.backend.bus;

import fr.aleclerc.windpath.backend.event.AEvent;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import org.springframework.stereotype.Component;

@Component
public class EventBus implements IEventSource, IEventPublisher {

    private Subject<AEvent> bus = PublishSubject.<AEvent>create().toSerialized();

    @Override
    public Observable<AEvent> getEvents() {
        return bus.observeOn(Schedulers.trampoline()).retry();
    }

    @Override
    public Completable publish(AEvent event) {
        System.err.println(event);
        return Completable.fromAction(() -> bus.onNext(event));
    }
}
