package fr.aleclerc.windpath.backend.util;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import org.slf4j.Logger;

public class RxUtils {
    private RxUtils() {

    }

    public static Consumer<? super Throwable> logError(Logger logger) {
        return e -> logger.error("Error", e);
    }

    public static Action nothingToDoOnComplete() {
        return () -> {};
    }

}
