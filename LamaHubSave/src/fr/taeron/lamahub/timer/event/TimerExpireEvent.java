package fr.taeron.lamahub.timer.event;

import java.util.UUID;

import org.bukkit.event.*;
import com.google.common.base.Optional;

import fr.taeron.lamahub.timer.Timer;


public class TimerExpireEvent extends Event
{
    private static final HandlerList handlers;
    private final Optional<UUID> userUUID;
    private final Timer timer;
    
    public TimerExpireEvent(final Timer timer) {
        this.userUUID = Optional.absent();
        this.timer = timer;
    }
    
    public TimerExpireEvent(final UUID userUUID, final Timer timer) {
        this.userUUID = Optional.fromNullable(userUUID);
        this.timer = timer;
    }
    
    public static HandlerList getHandlerList() {
        return TimerExpireEvent.handlers;
    }
    
    public Optional<UUID> getUserUUID() {
        return this.userUUID;
    }
    
    public Timer getTimer() {
        return this.timer;
    }
    
    public HandlerList getHandlers() {
        return TimerExpireEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
