package fr.aleclerc.windpath.cqrs.command.sequencer;

import fr.aleclerc.windpath.cqrs.command.ICommand;
import fr.aleclerc.windpath.cqrs.command.ICommandHandler;
import fr.aleclerc.windpath.cqrs.command.ICommandSequencer;
import fr.aleclerc.windpath.cqrs.event.IEvent;
import fr.aleclerc.windpath.cqrs.event.IEventPublisher;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.GenericTypeResolver;

import javax.annotation.PostConstruct;
import java.util.Collection;


public class CommandSequencer implements ICommandSequencer {


    private final static Logger LOGGER = LoggerFactory.getLogger(CommandSequencer.class);
    private final IEventPublisher publisher;
    private final Collection<ICommandHandler<? extends ICommand>> handlers;
    private final Subject<ICommand> commandSubject = PublishSubject.<ICommand>create().toSerialized();


    public CommandSequencer(final Collection<ICommandHandler<? extends ICommand>> handlers, final IEventPublisher publisher) {
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
    private <T extends ICommand> Observable<IEvent> apply(ICommandHandler<? extends ICommand> handler, T command) {
        Class<?> find = GenericTypeResolver.resolveTypeArgument(handler.getClass(), ICommandHandler.class);
        if (command.getClass().equals(find)) {
            return ((ICommandHandler<T>) handler).handle(command);

        }
        return Observable.empty();

    }


    @Override
    public Completable publish(ICommand command) {
        return Completable.fromAction(() -> commandSubject.onNext(command));
    }
}
