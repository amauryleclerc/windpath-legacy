package fr.aleclerc.windpath.cqrs.command;

import fr.aleclerc.windpath.cqrs.event.IEvent;
import io.reactivex.Observable;

public interface ICommandHandler<T extends ICommand> {

    /**
     * Transform a command to events
     * @param command
     * @return
     */
    Observable<IEvent> handle(T command);
}
