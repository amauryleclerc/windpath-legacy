package fr.aleclerc.windpath.backend.config;

import fr.aleclerc.windpath.cqrs.command.ICommand;
import fr.aleclerc.windpath.cqrs.command.ICommandHandler;
import fr.aleclerc.windpath.cqrs.command.sequencer.CommandSequencer;
import fr.aleclerc.windpath.cqrs.event.IEventPublisher;
import fr.aleclerc.windpath.cqrs.event.bus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collection;

@Configuration
public class InjectConfig {


//    @Bean
//    @Autowired
//    public HandlerMapping webSocketMapping(ITrackEventStream trackEventStream) {
//        System.err.println("MAPPING");
//        Map<String, WebSocketHandler> map = new HashMap<>();
//        map.put("/projection", new JsonWebSocketHandler<>(trackEventStream::getStream));
//        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
//        mapping.setOrder(2000);
//        mapping.setUrlMap(map);
//        return mapping;
//    }
//
//
//    @Bean
//    public WebSocketHandlerAdapter handlerAdapter() {
//        System.err.println("ADAPTER");
//        return new WebSocketHandlerAdapter();
//    }


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

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}
