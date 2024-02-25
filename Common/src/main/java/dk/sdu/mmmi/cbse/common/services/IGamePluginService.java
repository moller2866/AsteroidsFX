package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Defines the necessary method signatures for game plugin services.
 * Used to manage the lifecycle of plugins, including their initialization and teardown.
 *
 * @author jcs
 */
public interface IGamePluginService {

    /**
     * Initializes the plugin, setting up necessary entities.
     *
     * @param gameData The current game data.
     * @param world    The game world containing entities to be processed.
     * @precondition - gameData must not be null.<br>
     * - world must not be null.
     * @postcondition - The game world is modified with entities initialized by the plugin.
     */
    void start(GameData gameData, World world);

    /**
     * Cleans up entities associated with the plugin.
     *
     * @param gameData The current game data.
     * @param world    The game world containing entities to be processed.
     * @precondition - gameData must not be null.<br>
     * - world must not be null and contains entities added by the plugin.
     * @postcondition - The entities associated with the plugin is removed from the game world.
     */
    void stop(GameData gameData, World world);
}
