package fr.aleclerc.windpath.backend.command.handler;

import fr.aleclerc.windpath.backend.command.CreateTrackCommand;
import fr.aleclerc.windpath.backend.event.AEvent;
import fr.aleclerc.windpath.backend.event.CreatedTrackEvent;
import fr.aleclerc.windpath.cqrs.command.ICommandHandler;
import fr.aleclerc.windpath.cqrs.event.IEvent;
import io.reactivex.Observable;
import org.springframework.stereotype.Component;

@Component
public class CreateTrackCommandHandler implements ICommandHandler<CreateTrackCommand> {



    @Override
    public Observable<IEvent> handle(CreateTrackCommand command) {
        return Observable.fromCallable(() -> new CreatedTrackEvent(command.getId(), command.getName(), command.getPoints(), command.getUserId()));
    }
}
