package fr.taeron.lamahub.listeners;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ProtocolBlocker implements Listener{

	
	@EventHandler
	public void handleConnection(PlayerJoinEvent e){
		if(((CraftPlayer)e.getPlayer()).getHandle().playerConnection.networkManager.getVersion() > 5){
			e.getPlayer().kickPlayer("Â§7Pour des raisons de compatibilitÃ© des plugins, merci d'utiliser une version 1.7 pour jouer sur le §a§nLamaHub §7!");
		}
	}
}
