package fr.taeron.lamahub.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import fr.taeron.lamahub.SpawnHandler;

public class WorldListener {

	@EventHandler
    public void onItemSpawn(final ItemSpawnEvent event) {
        if (SpawnHandler.isInSpawn(event.getEntity().getLocation())) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onWeatherChange(final WeatherChangeEvent event) {
        if (event.toWeatherState()) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onThunderChange(final ThunderChangeEvent event) {
        if (event.toThunderState()) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onCreatureSpawn(final CreatureSpawnEvent event) {
        event.setCancelled(true);
    }
}
