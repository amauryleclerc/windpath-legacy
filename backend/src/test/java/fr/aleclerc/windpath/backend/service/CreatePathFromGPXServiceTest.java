package fr.aleclerc.windpath.backend.service;

import fr.aleclerc.windpath.cqrs.command.ICommandSequencer;
import fr.aleclerc.windpath.gpx.Gpx;
import io.reactivex.Completable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CreatePathFromGPXServiceTest {

    private CreatePathFromGPXService service;

    @BeforeEach
    public void init() {
        ICommandSequencer sequencer = Mockito.mock(ICommandSequencer.class);
        Mockito.when(sequencer.publish(Mockito.any())).thenReturn(Completable.complete());
        service = new CreatePathFromGPXService(sequencer);
    }

    @Test
    public void test() {
        service.createPath(new Gpx()).test().assertComplete();
    }
}
