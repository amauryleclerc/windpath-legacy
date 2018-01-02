package fr.aleclerc.windpath.backend.controller;

import fr.aleclerc.windpath.backend.WindpathConstants;
import fr.aleclerc.windpath.backend.command.ACommand;
import fr.aleclerc.windpath.cqrs.command.ICommandSequencer;
import io.reactivex.Completable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandController {

    private final ICommandSequencer commandSequencer;

    @Autowired
    public CommandController(final ICommandSequencer commandSequencer) {
        this.commandSequencer = commandSequencer;
    }

    @PostMapping(WindpathConstants.COMMAND_PATH)
    public Completable getUser(@RequestBody ACommand command, @AuthenticationPrincipal OAuth2User user) {
        return commandSequencer.publish(command);
    }

}
