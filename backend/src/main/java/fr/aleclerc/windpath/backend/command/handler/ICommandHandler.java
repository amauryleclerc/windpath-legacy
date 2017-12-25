package fr.aleclerc.windpath.backend.command.handler;

import fr.aleclerc.windpath.backend.command.ACommand;
import fr.aleclerc.windpath.backend.event.AEvent;
import io.reactivex.Observable;

public interface ICommandHandler<T extends ACommand> {

    Observable<AEvent> handle(T command);
}
