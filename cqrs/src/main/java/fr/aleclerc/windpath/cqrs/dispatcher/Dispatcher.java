package fr.aleclerc.windpath.cqrs.dispatcher;

import fr.aleclerc.windpath.cqrs.event.IEvent;
import io.reactivex.Completable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Dispatcher {

    private final static Logger LOGGER = LoggerFactory.getLogger(Dispatcher.class);

    private Dispatcher() {

    }

    public static <T> Completable dispatchToHandler(T handler, IEvent event) {
        return Completable.fromAction(() -> Arrays.stream(handler.getClass().getMethods())
                .filter(m -> m.isAnnotationPresent(EventHandler.class))
                .filter(m -> m.getParameterCount() == 1)//
                .filter(m -> m.getParameterTypes()[0].equals(event.getClass()))//
                .findAny()
                .ifPresentOrElse(m -> {
                    try {
                        m.invoke(handler,event);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        LOGGER.error("Error on inkoke handler {} for {}", handler.getClass().getSimpleName(), event.getClass().getSimpleName(), e);
                    }
                },() -> LOGGER.warn("Handler not found for {} on {}", event.getClass().getSimpleName(), handler.getClass().getSimpleName())));
    }
}
