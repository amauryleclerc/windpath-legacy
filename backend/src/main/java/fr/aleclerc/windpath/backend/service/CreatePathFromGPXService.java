package fr.aleclerc.windpath.backend.service;

import fr.aleclerc.windpath.backend.command.ACommand;
import fr.aleclerc.windpath.backend.command.CreateTrackCommand;
import fr.aleclerc.windpath.backend.pojo.Point;
import fr.aleclerc.windpath.cqrs.command.ICommandSequencer;
import fr.aleclerc.windpath.gpx.Gpx;
import io.reactivex.Completable;
import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CreatePathFromGPXService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CreatePathFromGPXService.class);
    private final ICommandSequencer commandSequencer;

    @Autowired
    public CreatePathFromGPXService(ICommandSequencer commandSequencer) {
        this.commandSequencer = commandSequencer;

    }


    @PostMapping(value = "/service/gpx", consumes = "application/xml", produces = "application/json")
    public Observable<UUID> createPath(@RequestBody Gpx gpx, @AuthenticationPrincipal OAuth2User user) {
        return this.createTrackCommand(gpx, (String) user.getAttributes().get("sub"))//
                .flatMapSingle( cmd -> commandSequencer.publish(cmd).toSingleDefault(cmd))
                .map(ACommand::getId);
    }


    private Observable<CreateTrackCommand> createTrackCommand(Gpx gpx, String userId) {
        return Observable.fromIterable(gpx.getTrk())//
                .flatMapSingle(trk -> Observable.fromIterable(trk.getTrkseg())//
                        .flatMap(trkSeg -> Observable.fromIterable(trkSeg.getTrkpt())//
                                .map(this::convert))
                        .toList()//
                        .map(points -> convert(trk, points, userId))
                );//
    }

    private Point convert(Gpx.Trk.Trkseg.Trkpt trkpt) {
        double lat = trkpt.getLat().doubleValue();
        double lon = trkpt.getLon().doubleValue();
        double ele = trkpt.getEle().doubleValue();
        double speed = Optional.ofNullable(trkpt.getSpeed()).map(BigDecimal::doubleValue).orElse(0D);
        Instant time = Instant.ofEpochMilli(trkpt.getTime().toGregorianCalendar().getTimeInMillis());
        return new Point(lat, lon, ele, speed, time);

    }

    private CreateTrackCommand convert(Gpx.Trk trk, List<Point> points, String userId) {
        UUID id = UUID.randomUUID();
        String name = trk.getName();
        return new CreateTrackCommand(id, name, points,userId);
    }

}
