package fr.aleclerc.windpath.cqrs.command;

import io.reactivex.Completable;

public interface ICommandSequencer {

    /**
     * Publish a command
     * @param command
     * @return
     */
    Completable publish(ICommand command);
}
