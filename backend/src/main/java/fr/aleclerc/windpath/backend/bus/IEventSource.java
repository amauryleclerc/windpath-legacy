package fr.aleclerc.windpath.backend.bus;


import fr.aleclerc.windpath.backend.event.AEvent;
import io.reactivex.Observable;

public interface IEventSource {

    Observable<AEvent> getEvents();

}
