package fr.aleclerc.windpath.network.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import fr.aleclerc.windpath.toolkit.rx.RxUtils;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class JsonWebSocketHandler<T> implements WebSocketHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(JsonWebSocketHandler.class);
    private final Supplier<Observable<T>> streamSupplier;
    private final ObjectMapper mapper;

    private final Map<String, Disposable> subs = new HashMap<>();

    public JsonWebSocketHandler(Supplier<Observable<T>> streamSupplier) {
        this.streamSupplier = streamSupplier;
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new Jdk8Module());
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        LOGGER.info("New webwork session {} - {}", webSocketSession.getId(), webSocketSession.getPrincipal());
        Disposable sub = streamSupplier.get()
                .map(mapper::writeValueAsString)//
                .map(TextMessage::new)//
                .doOnTerminate(webSocketSession::close)
                .subscribe(webSocketSession::sendMessage, RxUtils.logError(LOGGER));
        subs.put(webSocketSession.getId(), sub);


    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        LOGGER.info("Handle message {}", webSocketMessage);
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        LOGGER.error("WebSocket error", throwable);
        dispose(webSocketSession.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        dispose(webSocketSession.getId());
    }

    private void dispose(String id) {
        Optional.ofNullable(subs.get(id)).ifPresentOrElse(Disposable::dispose, () -> LOGGER.warn("Disposable {} not found", id));
    }


    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
