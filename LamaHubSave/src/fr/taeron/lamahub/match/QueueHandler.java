package fr.taeron.lamahub.match;

import fr.taeron.core.Core;

public class QueueHandler {

	public Queue unrankedEarlyQueue;
	public Queue rankedEarlyQueue;
	public Queue unrankedIronQueue;
	public Queue rankedIronQueue;
	
	public QueueHandler(){
		this.unrankedEarlyQueue = new Queue(Core.getPlugin().getKitManager().getKit("EarlyHG"), false);
		this.rankedEarlyQueue = new Queue(Core.getPlugin().getKitManager().getKit("EarlyHG"), true);
		this.unrankedIronQueue = new Queue(Core.getPlugin().getKitManager().getKit("Iron"), false);
		this.rankedIronQueue = new Queue(Core.getPlugin().getKitManager().getKit("Iron"), true);
	}
	
	public void endDuel(PlayerDuel d){
		d.clear();
		d = null;
	}
	
	public static double[] getEstimations(final double rankingA, final double rankingB) {
        return new double[] { 1.0 / (1.0 + Math.pow(10.0, (rankingB - rankingA) / 400.0)), 1.0 / (1.0 + Math.pow(10.0, (rankingA - rankingB) / 400.0)) };
    }
    
    public static int getConstant(final int ranking) {
        if (ranking < 2000) {
            return 32;
        }
        if (ranking < 2401) {
            return 24;
        }
        return 16;
    }
    
    public static int[] getNewRankings(final int rankingA, final int rankingB, final boolean victoryA) {
        final double[] estimations = getEstimations(rankingA, rankingB);
        return new int[] { (int)Math.round(rankingA + getConstant(rankingA) * ((victoryA ? 1 : 0) - estimations[0])), (int)Math.round(rankingB + getConstant(rankingB) * ((victoryA ? 0 : 1) - estimations[1])) };
    }
}
