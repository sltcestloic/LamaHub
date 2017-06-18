package fr.taeron.lamahub.inventory;

import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.inventory.type.DuelLobbyInventory;
import fr.taeron.lamahub.inventory.type.FFAInventory;
import fr.taeron.lamahub.inventory.type.SpawnInventory;

public class InventoryHandler
{
    public SpawnInventory spawnInventory;
    public FFAInventory ffaInventory;
    public DuelLobbyInventory duelLobbyInventory;
    
    public InventoryHandler(final LamaHub plugin) {
        this.spawnInventory = new SpawnInventory(plugin);
        this.ffaInventory = new FFAInventory(plugin);
        this.duelLobbyInventory = new DuelLobbyInventory(plugin);
    }
}
