package fr.aleclerc.windpath.backend.config;

import fr.aleclerc.windpath.backend.handler.JsonWebSocketHandler;
import fr.aleclerc.windpath.backend.projection.ITrackEventStream;
import fr.aleclerc.windpath.cqrs.command.ICommand;
import fr.aleclerc.windpath.cqrs.command.ICommandHandler;
import fr.aleclerc.windpath.cqrs.command.sequencer.CommandSequencer;
import fr.aleclerc.windpath.cqrs.event.IEventPublisher;
import fr.aleclerc.windpath.cqrs.event.bus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import org.springframework.web.reactive.socket.server.upgrade.TomcatRequestUpgradeStrategy;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class InjectConfig {




    @Bean
    @Autowired
    public HandlerMapping webSocketMapping(ITrackEventStream trackEventStream) {
        System.err.println("MAPPING");
        Map<String, WebSocketHandler> map = new HashMap<>();
        map.put("/projection", new JsonWebSocketHandler<>(trackEventStream::getStream));
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(2000);
        mapping.setUrlMap(map);
        return mapping;
    }


    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        System.err.println("ADAPTER");
        return new WebSocketHandlerAdapter();
    }


    @Bean
    @Autowired
    public EventBus provideEventBus() {
        return new EventBus();

    }

    @Bean
    @Autowired
    public CommandSequencer provideCommandSequencer(final Collection<ICommandHandler<? extends ICommand>> handlers, final IEventPublisher publisher) {
        return new CommandSequencer(handlers, publisher);

    }
}
