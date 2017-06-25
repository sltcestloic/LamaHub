package fr.taeron.lamahub.match;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.taeron.core.kits.Kit;
import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.user.LamaUser;

public class Queue {

	private Kit kit;
	private boolean ranked;
	private ArrayList<UUID> queue;
	private int playing;
	
	public Queue(Kit kit, boolean ranked){
		this.kit = kit;
		this.ranked = ranked;
		this.queue = new ArrayList<UUID>();
		this.playing = 0;
	}
	
	public ArrayList<UUID> getQueue(){
		return this.queue;
	}
	
	public boolean isRanked(){
		return this.ranked;
	}
	
	public Kit getKit(){
		return this.kit;
	}
	
	public void addPlayer(Player p){
		if(this.queue.contains(p.getUniqueId())){
			p.sendMessage("§cTu es déjà dans cette queue.");
			return;
		}
		LamaUser user = LamaHub.getInstance().getUserManager().getUser(p.getUniqueId());
		if(user.getQueue() != null){
			p.sendMessage("§cTu es déjà dans une queue.");
			return;
		}
		if(this.queue.size() < 1){
			this.queue.add(p.getUniqueId());
			user.setCurrentQueue(this);
		} else {
			new PlayerDuel(this, Bukkit.getPlayer(this.queue.get(0)), p, LamaHub.getInstance().getArenaManager().getRandomArena(), this.kit, this.ranked);
			this.queue.clear();
			this.playing += 2;
		}
	}
	
	public int getPlaying(){
		return this.playing;
	}
	
	public void removePlayer(Player p){
		this.queue.remove(p.getUniqueId());
	}
}
