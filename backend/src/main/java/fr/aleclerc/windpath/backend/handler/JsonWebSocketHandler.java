package fr.aleclerc.windpath.backend.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.adapter.rxjava.RxJava2Adapter;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;

public class JsonWebSocketHandler<T> implements WebSocketHandler {

    private final Supplier<Observable<T>> streamSupplier;
    private final ObjectMapper mapper;

    public JsonWebSocketHandler(Supplier<Observable<T>> streamSupplier) {
        this.streamSupplier = streamSupplier;
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new Jdk8Module());

    }

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {
        System.err.println("SESSION");
        return webSocketSession.send(RxJava2Adapter.observableToFlux(streamSupplier.get()//
                        .map(mapper::writeValueAsString)//
                        .map(webSocketSession::textMessage),
                BackpressureStrategy.ERROR));
    }
}
