package fr.aleclerc.windpath.backend.config;


import fr.aleclerc.windpath.backend.handler.JsonWebSocketHandler;
import fr.aleclerc.windpath.backend.projection.ITrackEventStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final ITrackEventStream trackEventStream;

    @Autowired
    public WebSocketConfig(final ITrackEventStream trackEventStream) {
        this.trackEventStream = trackEventStream;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new JsonWebSocketHandler<>(trackEventStream::getStream), "/projection");
    }


}
