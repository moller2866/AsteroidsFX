package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AsteroidPlugin implements IGamePluginService {

    private Timer timer;
    private Random random = new Random();

    @Override
    public void start(GameData gameData, World world) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                world.addEntity(createAsteroid(gameData));
            }
        }, 0, 5000);
    }

    @Override
    public void stop(GameData gameData, World world) {
        timer.cancel();
        for (Entity e : world.getEntities(Asteroid.class)) {
            world.removeEntity(e);
        }
    }

    private Entity createAsteroid(GameData gameData) {
        int asteroidLevel = random.nextInt(3);
        Asteroid asteroid = new Asteroid(asteroidLevel);
        asteroid.setPolygonCoordinates(asteroid.getAsteroidPolygon());

        int edge = random.nextInt(4);

        int x = 0;
        int y = 0;
        switch (edge) {
            case 0:
                x = gameData.getDisplayWidth();
                y = random.nextInt(gameData.getDisplayHeight());
                break;
            case 1:
                x = random.nextInt(gameData.getDisplayWidth());
                y = gameData.getDisplayHeight();
                break;
            case 2:
                y = random.nextInt(gameData.getDisplayHeight());
                break;
            case 3:
                x = random.nextInt(gameData.getDisplayWidth());
                break;
        }
        asteroid.setX(x);
        asteroid.setY(y);
        asteroid.setRotation(random.nextInt(360));
        return asteroid;
    }
}
