package fr.aleclerc.windpath.backend.command.sequencer;

import fr.aleclerc.windpath.backend.bus.IEventPublisher;
import fr.aleclerc.windpath.backend.command.ACommand;
import fr.aleclerc.windpath.backend.command.handler.ICommandHandler;
import fr.aleclerc.windpath.backend.event.AEvent;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;

@Service
public class CommandSequencer implements ICommandSequencer {

    private final static Logger LOGGER = LoggerFactory.getLogger(CommandSequencer.class);
    private final IEventPublisher publisher;
    private final Collection<ICommandHandler<? extends ACommand>> handlers;
    private final Subject<ACommand> commandSubject = PublishSubject.<ACommand>create().toSerialized();

    @Autowired
    public CommandSequencer(final Collection<ICommandHandler<? extends ACommand>> handlers, final IEventPublisher publisher) {
        this.handlers = handlers;
        this.publisher = publisher;

    }

    @PostConstruct
    public void init() {
        commandSubject.concatMap(command -> Observable.fromIterable(handlers)//
                .flatMap(handler -> apply(handler, command)))//
                .flatMapCompletable(publisher::publish)//
                .doOnError(e -> LOGGER.error("Error during apply command", e))
                .retry()//
                .subscribe(() -> LOGGER.error("Infinite flux stopped"), e -> LOGGER.error("Error during apply command", e));
    }


    @SuppressWarnings("unchecked")
    private <T extends ACommand> Observable<AEvent> apply(ICommandHandler<? extends ACommand> handler, T command) {
        Class<?> find = GenericTypeResolver.resolveTypeArgument(handler.getClass(), ICommandHandler.class);
        if (command.getClass().equals(find)) {
            return ((ICommandHandler<T>) handler).handle(command);

        }
        return Observable.empty();

    }


    @Override
    public Completable publish(ACommand command) {
        return Completable.fromAction(() -> commandSubject.onNext(command));
    }


}
