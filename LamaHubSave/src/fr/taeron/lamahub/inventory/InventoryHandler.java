package fr.taeron.lamahub.inventory;

import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.inventory.type.SpawnInventory;

public class InventoryHandler
{
    public SpawnInventory spawnInventory;
    
    public InventoryHandler(final LamaHub plugin) {
        this.spawnInventory = new SpawnInventory(plugin);
    }
}
