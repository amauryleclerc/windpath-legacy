package fr.aleclerc.windpath.backend.util;

import fr.aleclerc.windpath.cqrs.event.IEvent;
import io.reactivex.Completable;

public class DispatcherUtils {
    private DispatcherUtils() {

    }
    private final static String METHODE_NAME = "handle";

    public static <T> Completable dispatcheToHandler(T handler, IEvent event) {
        return Completable.fromAction(() -> handler.getClass().getDeclaredMethod(METHODE_NAME, event.getClass()).invoke(handler, event));
    }
}
