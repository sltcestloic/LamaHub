package fr.taeron.lamahub.inventory;

public class Parametre {
	
	private static boolean notification;
	
	public Parametre(){
		notification =  true;
	}

	public static boolean isNotificationEnabled() {
		return notification;
	}

	public static void setNotification(boolean notification) {
		Parametre.notification = notification;
	}

}
