package Observer;

import ActorProperties.Message;

public interface Observer { void update(String nameActorReceive, int size, Message message, boolean x); }
