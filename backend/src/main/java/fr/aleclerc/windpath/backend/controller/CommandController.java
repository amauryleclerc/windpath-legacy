package fr.aleclerc.windpath.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import fr.aleclerc.windpath.backend.WindpathConstants;
import fr.aleclerc.windpath.backend.command.ACommand;
import fr.aleclerc.windpath.backend.command.CreateTrackCommand;
import fr.aleclerc.windpath.backend.pojo.Point;
import fr.aleclerc.windpath.cqrs.command.ICommandSequencer;
import io.reactivex.Completable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Collections;
import java.util.UUID;

@RestController
public class CommandController {

    private final ICommandSequencer commandSequencer;

    @Autowired
    public CommandController(final ICommandSequencer commandSequencer) {
        this.commandSequencer = commandSequencer;
    }

    @PostMapping(WindpathConstants.COMMAND_PATH)
    public Completable getUser(@RequestBody ACommand command) {
        return commandSequencer.publish(command);
    }

    public static void main(String args[]) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
        CreateTrackCommand command = new CreateTrackCommand(UUID.randomUUID(), "TOTO", Collections.singletonList(new Point(1, 1, 1, 1, Instant.now())));

        System.out.println("mapper.writeValueAsString(command) = " + mapper.writeValueAsString(command));
    }

}
