package fr.aleclerc.windpath.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user")
    public Map<String, Object> getUser(@AuthenticationPrincipal OAuth2User user) {
        LOGGER.info("userId : {}", (String) user.getAttributes().get("sub"));
        return user.getAttributes();
    }
}
