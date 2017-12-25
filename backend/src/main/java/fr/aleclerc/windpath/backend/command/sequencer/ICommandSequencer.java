package fr.aleclerc.windpath.backend.command.sequencer;

import fr.aleclerc.windpath.backend.command.ACommand;
import io.reactivex.Completable;

public interface ICommandSequencer {

    Completable publish(ACommand command);
}
