package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Defines the necessary method signatures for entity processing services.
 * Used to handle game logic specific to the update cycle of entities.
 *
 * @author jcs
 */
public interface IEntityProcessingService {

    /**
     * Processes the game logic for entities in each update cycle.
     *
     * @param gameData The current game data.
     * @param world    The game world containing entities to be processed.
     * @precondition - gameData must not be null.<br>
     * - world must not be null and should contain the current entities to be processed.
     * @postcondition - Entities within the world might be updated based on the implementation of this method.
     */
    void process(GameData gameData, World world);
}
