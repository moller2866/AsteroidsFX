package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Defines the method signature for post-entity processing services.
 * Used for operations that should occur after all entity processing has been completed.
 *
 * @author jcs
 */
public interface IPostEntityProcessingService {

    /**
     * Performs post-processing operations for entities.
     *
     * @param gameData The current game data.
     * @param world    The game world containing entities to be processed.
     * @precondition - gameData must not be null.<br>
     * - world must not be null and contains all entities that have been updated in the current cycle.
     * @postcondition - The method may modify entities based on post-processing logic.
     */
    void process(GameData gameData, World world);
}
