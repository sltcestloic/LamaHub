package fr.taeron.lamahub.match;

import fr.taeron.core.Core;

public class QueueHandler {

	public Queue unrankedEarlyQueue;
	public Queue rankedEarlyQueue;
	public Queue unrankedIronQueue;
	public Queue rankedIronQueue;
	
	public QueueHandler(){
		this.unrankedEarlyQueue = new Queue(Core.getPlugin().getKitManager().getKit("Guerrier"), false);
		this.rankedEarlyQueue = new Queue(Core.getPlugin().getKitManager().getKit("Guerrier"), true);
		this.unrankedIronQueue = new Queue(Core.getPlugin().getKitManager().getKit("Iron"), false);
		this.rankedIronQueue = new Queue(Core.getPlugin().getKitManager().getKit("iron"), true);
	}
}
