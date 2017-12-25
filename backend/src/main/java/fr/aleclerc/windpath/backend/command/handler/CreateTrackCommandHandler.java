package fr.aleclerc.windpath.backend.command.handler;

import fr.aleclerc.windpath.backend.command.CreateTrackCommand;
import fr.aleclerc.windpath.backend.event.AEvent;
import fr.aleclerc.windpath.backend.event.CreatedTrackEvent;
import io.reactivex.Observable;
import org.springframework.stereotype.Component;

@Component
public class CreateTrackCommandHandler implements ICommandHandler<CreateTrackCommand> {



    @Override
    public Observable<AEvent> handle(CreateTrackCommand command) {
        return Observable.fromCallable(() -> new CreatedTrackEvent(command.getId(), command.getName(), command.getPoints()));
    }
}
