package starwars.entities.actors;

import starwars.SWActor;

public interface SWDroidActorInterface {
    boolean hasOwner();

    void setOwner(SWActor newOwner);
}
