package fr.taeron.lamahub.listeners;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import fr.taeron.lamahub.Config;
import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.SpawnHandler;
import fr.taeron.lamahub.inventory.Parametre;
import fr.taeron.lamahub.user.LamaUser;
import net.minecraft.server.v1_7_R4.EntityItem;


public class CoreListener implements Listener{

	
	private HashMap<Player, Boolean> fall = new HashMap<>();
	
	@EventHandler
	public void blockBreak(BlockBreakEvent e){
		if(!e.getPlayer().isOp() || e.getPlayer().getGameMode() != GameMode.CREATIVE){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void blockPlace(BlockPlaceEvent e){
		if(!e.getPlayer().isOp() || e.getPlayer().getGameMode() != GameMode.CREATIVE){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
    public void onEntityDamage(final FoodLevelChangeEvent event) {
        final HumanEntity humanEntity = event.getEntity();
        if (humanEntity instanceof Player && SpawnHandler.isInSpawn((Entity)humanEntity)) {
            event.setCancelled(true);
        }
    }
	
	@EventHandler
    public void onDamage(final EntityDamageEvent e) {
        if (e.getEntity() instanceof Player && SpawnHandler.isInSpawn(e.getEntity())) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onPlayerItem(final PlayerDropItemEvent event) {
        if (SpawnHandler.isInSpawn((Entity)event.getPlayer())) {
            event.getItemDrop().remove();
        }
        if(event.getItemDrop().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase("�bStone Sword")){
        	event.setCancelled(true);
        } 
    }
    
    @EventHandler
    public void moveItemsInSpawn(InventoryClickEvent e){
    	if(e.getInventory().getType() == InventoryType.CRAFTING && SpawnHandler.isInSpawn(e.getWhoClicked())){
    		e.setCancelled(true);
    	}
    }
    
    @EventHandler
    public void onPlayerDeath(final PlayerDeathEvent event) {
        event.getDrops().clear();
        event.setDeathMessage((String)null);
        event.setDroppedExp(0);
        event.getEntity().getWorld().strikeLightningEffect(event.getEntity().getLocation());
        final Player player = event.getEntity();
        new BukkitRunnable() {
            public void run() {
                player.spigot().respawn();
            }
        }.runTask(LamaHub.getInstance());
    }
    
    @SuppressWarnings("deprecation")
	@EventHandler
	public void onSoup(PlayerInteractEvent e){
		CraftPlayer p = (CraftPlayer) e.getPlayer();
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			if(p.getItemInHand().equals(new ItemStack(Material.MUSHROOM_SOUP))){
				if(p.getHealth() < 20){
					if(p.getHealth() < 13){
						if(p.getFoodLevel() < 13){
							p.setHealth(p.getHealth() + 7);
							p.setFoodLevel((int) (p.getFoodLevel() + 7));
							e.getPlayer().getItemInHand().setType(Material.BOWL);
						} else {
							p.setFoodLevel(20);
							p.setHealth(p.getHealth() + 7);
							e.getPlayer().getItemInHand().setType(Material.BOWL);
						}
					} else {
						p.setHealth(20);
						e.getPlayer().getItemInHand().setType(Material.BOWL);
					}
				} else {
					if(p.getFoodLevel() < 20){
						p.setFoodLevel((int) (p.getFoodLevel() + 7));
						e.getPlayer().getItemInHand().setType(Material.BOWL);
					} else {
						return;
					}
				}
			}
		}		
	}
    
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
    	e.setJoinMessage(null);
        LamaHub.getInstance().getInventoryHandler().spawnInventory.applyTo(e.getPlayer(), true, true);
    }
    
    @SuppressWarnings("deprecation")
	@EventHandler
    public void chat(AsyncPlayerChatEvent e){
    	Player p = e.getPlayer();
    	String message = e.getMessage();
    	for(Player pAll : Bukkit.getOnlinePlayers()){
    		if(message.contains(pAll.getName())){
    			if(pAll == p){return;}
    			if(!Parametre.isNotificationEnabled()){return;}
    			pAll.playSound(pAll.getLocation(), Sound.LEVEL_UP, 5F, 1F);
    		}
    	}
    }
    
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
    	e.setQuitMessage(null);
    }
    
    @EventHandler
    public void onPlayerRespawn(final PlayerRespawnEvent event) {
        event.setRespawnLocation(Bukkit.getWorlds().get(0).getSpawnLocation().clone().add(0.5, 0.5, 0.5));
        final Player player = event.getPlayer();
        new BukkitRunnable() {
            public void run() {
            	LamaHub.getInstance().getInventoryHandler().spawnInventory.applyTo(player, true, true);
            }
        }.runTaskLater(LamaHub.getInstance(), 1L);
    }
    
    @EventHandler
  	public void onPlayerDropItemEvent(PlayerDropItemEvent event) {
		if(!SpawnHandler.isInSpawn(event.getPlayer().getLocation())){
			Item item = event.getItemDrop();
			ItemStack itemstack = item.getItemStack();
			Location location = item.getLocation();
			EntityItem ei = new EntityItem(
				((CraftWorld)location.getWorld()).getHandle(),
		      	location.getX(),
		      	location.getY(),
		      	location.getZ(),
		      	CraftItemStack.asNMSCopy(itemstack)) {
		    	@Override
		      	public boolean a(EntityItem entityitem) {
		    		return false;
		    	}
		    	};
		    	ei.pickupDelay = 40;
		    	((Item)ei.getBukkitEntity()).setVelocity(item.getVelocity());
		    	((CraftWorld)location.getWorld()).getHandle().addEntity(ei);
		    	item.remove();
		} else {
			event.setCancelled(true);
		}
  	}
    
    @EventHandler
    public void entitySpawn(ItemSpawnEvent e){
    	new BukkitRunnable(){
			@Override
			public void run() {
				e.getEntity().remove();
			}
    	}.runTaskLater(LamaHub.getInstance(), 80l);
    }
    

    
    @EventHandler
    public void noKit(PlayerMoveEvent e){
    	if(e.getFrom().getBlockY() == e.getTo().getBlockY()){
    		return;
    	}
    	if(!e.getPlayer().getLocation().getWorld().getName().equalsIgnoreCase("FFASoup")){
    		return;
    	}
    	if(e.getTo().getBlockY() < 140 && e.getTo().getBlockY() > 138 && e.getPlayer().getInventory().contains(Config.FFA_SELECTOR_ITEM) && e.getPlayer().getGameMode() != GameMode.CREATIVE){
    		e.getPlayer().setFallDistance(0f);
    		LamaHub.getInstance().getInventoryHandler().ffaInventory.applyTo(e.getPlayer(), true, true);
    		e.getPlayer().setFallDistance(0f);
    	} else if (!e.getPlayer().getInventory().contains(Config.FFA_SELECTOR_ITEM) && e.getTo().getBlockY() > 130){
    		this.fall.put(e.getPlayer(), true);
    	}
    }
    
	@EventHandler
    public void fall(EntityDamageEvent e){
    	if(e.getCause() != DamageCause.FALL){
    		return;
    	}
    	if(!(e.getEntity() instanceof Player)){
    		return;
    	}
    	Player p = (Player) e.getEntity();
    	if(this.fall.containsKey(p)){
    		e.setCancelled(true);
    		this.fall.remove(p);
    		return;
    	}
    	if(e.isCancelled()){
    		return;
    	}
    	LamaUser user = LamaHub.getInstance().getUserManager().getUser(p.getUniqueId());
    	if(user.getCurrentKitName().equalsIgnoreCase("Stomper")){
    		if(p.getNearbyEntities(5, 5, 5).size() > 0 && e.getDamage() > 4){
            	p.getWorld().playSound(p.getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);
    		}
    		for(Entity ent : p.getNearbyEntities(5, 5, 5)){
    			if(ent instanceof Player){
    				Player victimp = (Player) ent;
    				LamaUser victim = LamaHub.getInstance().getUserManager().getUser(victimp.getUniqueId());
    				victim.setLastAttacker(p);
    				if(victim.getCurrentKitName().equalsIgnoreCase("AntiStomper") || victimp.isSneaking()){
    					victimp.damage(4.0);
    				} else {
    					victimp.damage(e.getDamage());
    				}
    			}
    		}
    		if(e.getDamage() > 4){
    			e.setDamage(4.0);
    		}
    	}
    }
	
	@EventHandler
	public void onKangaroo(PlayerInteractEvent e){
		if(e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK){
			return;
		}
		if(e.getPlayer().getItemInHand().getType() != Material.FIREWORK){
			return;
		}
		LamaUser user = LamaHub.getInstance().getUserManager().getUser(e.getPlayer().getUniqueId());
		if(!user.getCurrentKitName().equalsIgnoreCase("Kangaroo")){
			return;
		} else {
			e.setCancelled(true);
			if(System.currentTimeMillis() - user.lastKangarooTime() < 2000){
				return;
			} else {
                this.fall.put(e.getPlayer(), true);
				if(e.getPlayer().isSneaking()){
					Vector vector = e.getPlayer().getEyeLocation().getDirection();
                    vector.multiply(2.8f);
                    vector.setY(0.8f);
                    e.getPlayer().setVelocity(vector);
                    PlayerVelocityEvent ev = new PlayerVelocityEvent(e.getPlayer(), vector);
                    Bukkit.getPluginManager().callEvent(ev);
				} else {
					Vector vector = e.getPlayer().getEyeLocation().getDirection();
					vector.multiply(1.6f);
                    vector.setY(1.2);
                    e.getPlayer().setVelocity(vector);
                    PlayerVelocityEvent ev = new PlayerVelocityEvent(e.getPlayer(), vector);
                    Bukkit.getPluginManager().callEvent(ev);
				}
				user.setLastKangarooTime(System.currentTimeMillis());
			}
		}
	}
}
