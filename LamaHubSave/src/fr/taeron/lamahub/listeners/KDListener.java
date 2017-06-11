package fr.taeron.lamahub.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.user.LamaUser;

public class KDListener implements Listener{

	
	@EventHandler
	public void update(PlayerDeathEvent e){
		if(!(e.getEntity() instanceof Player)){
			return;
		}
		LamaUser ap = LamaHub.getInstance().getUserManager().getUser(e.getEntity().getUniqueId());
		ap.setDeaths(ap.getDeaths() + 1);
		ap.setKS(0);
		if(e.getEntity().getKiller() != null){
			LamaUser ap2 = LamaHub.getInstance().getUserManager().getUser(e.getEntity().getKiller().getUniqueId());
			ap2.setKills(ap2.getKills() + 1);
			ap2.setKS(ap2.getKS() + 1);
		}
	}
}
