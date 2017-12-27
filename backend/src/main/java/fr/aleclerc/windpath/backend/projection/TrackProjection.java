package fr.aleclerc.windpath.backend.projection;

import fr.aleclerc.windpath.backend.event.AEvent;
import fr.aleclerc.windpath.backend.event.CreatedTrackEvent;
import fr.aleclerc.windpath.backend.event.CurrentTrackEvent;
import fr.aleclerc.windpath.backend.pojo.Track;
import fr.aleclerc.windpath.backend.service.CreatePathFromGPXService;
import fr.aleclerc.windpath.cqrs.dispatcher.Dispatcher;
import fr.aleclerc.windpath.cqrs.dispatcher.EventHandler;
import fr.aleclerc.windpath.cqrs.event.IEventSource;
import fr.aleclerc.windpath.toolkit.rx.RxUtils;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class TrackProjection implements ITrackEventStream {

    private final static Logger LOGGER = LoggerFactory.getLogger(CreatePathFromGPXService.class);
    private Map<UUID, Track> trackMap = new HashMap<>();
    private Subject<AEvent> eventSubject = PublishSubject.<AEvent>create().toSerialized();

    public TrackProjection(final IEventSource eventSource) {

        eventSource.getEvents()//
                .flatMapCompletable(evt -> Dispatcher.dispatchToHandler(this, evt))//
                .subscribe(RxUtils.nothingToDoOnComplete(), RxUtils.logError(LOGGER));

    }

    @EventHandler
    public void handle(CreatedTrackEvent event) {
        System.err.println("PROJ");
        System.err.println(event);
        Track track = new Track(event.getId(), event.getName(), event.getPoints());
        trackMap.put(event.getId(), track);
        eventSubject.onNext(event);
    }


    @Override
    public Observable<AEvent> getStream() {
        return Observable.fromIterable(trackMap.values())//
                .<AEvent>map(CurrentTrackEvent::new)//
                .concatWith(eventSubject);
    }
}
