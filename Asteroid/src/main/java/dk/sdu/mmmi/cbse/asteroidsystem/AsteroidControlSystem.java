package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class AsteroidControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            updateAsteroid(asteroid, gameData);
        }

    }
    
    private void updateAsteroid(Entity asteroid, GameData gameData) {
        double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
        double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));
        asteroid.setX(asteroid.getX() + changeX);
        asteroid.setY(asteroid.getY() + changeY);

        if (asteroid.getX() < 0) {
            asteroid.setX(gameData.getDisplayWidth() - 1);
        }
        if (asteroid.getX() > gameData.getDisplayWidth()) {
            asteroid.setX(1);
        }
        if (asteroid.getY() < 0) {
            asteroid.setY(gameData.getDisplayHeight() - 1);
        }
        if (asteroid.getY() > gameData.getDisplayHeight()) {
            asteroid.setY(1);
        }
    }
}
