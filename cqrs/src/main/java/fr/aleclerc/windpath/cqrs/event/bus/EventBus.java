package fr.aleclerc.windpath.cqrs.event.bus;

import fr.aleclerc.windpath.cqrs.event.IEvent;
import fr.aleclerc.windpath.cqrs.event.IEventPublisher;
import fr.aleclerc.windpath.cqrs.event.IEventSource;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
public class EventBus implements IEventPublisher, IEventSource{

    private Subject<IEvent> bus = PublishSubject.<IEvent>create().toSerialized();

    @Override
    public Observable<IEvent> getEvents() {
        return bus.observeOn(Schedulers.trampoline()).retry();
    }

    @Override
    public Completable publish(IEvent event) {
        return Completable.fromAction(() -> bus.onNext(event));
    }
}
