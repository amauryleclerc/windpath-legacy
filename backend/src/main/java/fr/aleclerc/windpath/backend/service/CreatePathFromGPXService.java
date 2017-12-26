package fr.aleclerc.windpath.backend.service;

import fr.aleclerc.windpath.backend.command.CreateTrackCommand;
import fr.aleclerc.windpath.backend.pojo.Point;
import fr.aleclerc.windpath.backend.pojo.gpx.Gpx;
import fr.aleclerc.windpath.cqrs.command.ICommandSequencer;
import io.reactivex.Completable;
import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.adapter.rxjava.RxJava2Adapter;
import reactor.core.publisher.Flux;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
public class CreatePathFromGPXService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CreatePathFromGPXService.class);
    private final ICommandSequencer commandSequencer;

    @Autowired
    public CreatePathFromGPXService(ICommandSequencer commandSequencer) {
        this.commandSequencer = commandSequencer;

    }


    @PostMapping(value = "/service/gpx", consumes = "application/xml", produces = "application/xml")
    public Completable createPath(@RequestBody Gpx gpx) {
        return this.createTrackCommand(gpx)//
                .flatMapCompletable(commandSequencer::publish);
    }


    private Observable<CreateTrackCommand> createTrackCommand(Gpx gpx) {
        return Observable.fromIterable(gpx.getTrk())//
                .flatMapSingle(trk -> Observable.fromIterable(trk.getTrkseg())//
                        .flatMap(trkSeg -> Observable.fromIterable(trkSeg.getTrkpt())//
                                .map(this::convert))
                        .toList()//
                        .map(points -> convert(trk, points))
                );//
    }

    private Point convert(Gpx.Trk.Trkseg.Trkpt trkpt) {
        double lat = trkpt.getLat().doubleValue();
        double lon = trkpt.getLon().doubleValue();
        double ele = trkpt.getEle().doubleValue();
        double speed = trkpt.getSpeed().doubleValue();
        Instant time = Instant.ofEpochMilli(trkpt.getTime().toGregorianCalendar().getTimeInMillis());
        return new Point(lat, lon, ele, speed, time);

    }

    private CreateTrackCommand convert(Gpx.Trk trk, List<Point> points) {
        UUID id = UUID.randomUUID();
        String name = trk.getName();
        return new CreateTrackCommand(id, name, points);
    }

}
