package fr.taeron.lamahub.user;

import org.bukkit.plugin.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.event.player.*;import org.bukkit.configuration.MemorySection;
import org.bukkit.event.*;
import fr.taeron.core.util.Config;
import fr.taeron.core.util.GuavaCompat;
import fr.taeron.lamahub.LamaHub;
import java.util.*;

public class UserManager implements Listener
{
    private final LamaHub plugin;
    private Config config;
    private final Map<UUID, LamaUser> users;
    
	public UserManager(final LamaHub plugin) {
        this.users = new HashMap<UUID, LamaUser>();
        this.plugin = plugin;
        this.reloadUserData();
        plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final UUID uuid = event.getPlayer().getUniqueId();
        this.users.putIfAbsent(uuid, new LamaUser(uuid));
    }
    
    public Map<UUID, LamaUser> getUsers() {
        return this.users;
    }
    
    public LamaUser getUserAsync(final UUID uuid) {
        synchronized (this.users) {
            final LamaUser revert;
            final LamaUser user = this.users.putIfAbsent(uuid, revert = new LamaUser(uuid));
            return (LamaUser)GuavaCompat.firstNonNull(user, revert);
        }
    }
    
    public LamaUser getUser(final UUID uuid) {
        final LamaUser revert;
        final LamaUser user = this.users.putIfAbsent(uuid, revert = new LamaUser(uuid));
        return (LamaUser)GuavaCompat.firstNonNull(user, revert);
    }
    
    public void reloadUserData() {
    	new BukkitRunnable(){
			@Override
			public void run() {
				config = new Config(plugin, "joueurs");
		        final Object object = config.get("joueurs");
		        if (object instanceof MemorySection) {
		            final MemorySection section = (MemorySection)object;
		            final Collection<String> keys = (Collection<String>)section.getKeys(false);
		            for (final String id : keys) {
		                users.put(UUID.fromString(id), (LamaUser)config.get(section.getCurrentPath() + '.' + id));
		            }
		        }
			}
    	}.runTaskAsynchronously(plugin);
    }
    
    public void saveUserData() {
    	new BukkitRunnable(){
			@Override
			public void run() {
		        final Set<Map.Entry<UUID, LamaUser>> entrySet = users.entrySet();
		        final Map<String, LamaUser> saveMap = new LinkedHashMap<String, LamaUser>(entrySet.size());
		        for (final Map.Entry<UUID, LamaUser> entry : entrySet) {
		            saveMap.put(entry.getKey().toString(), entry.getValue());
		        }
		        config.set("joueurs", saveMap);
		        config.save();
			}
    	}.runTaskAsynchronously(plugin);
    }
}
