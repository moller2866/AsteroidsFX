package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class EnemyPlugin implements IGamePluginService {

    private Random random = new Random();
    private Timer timer;

    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Entity enemy = createEnemy(gameData);
                world.addEntity(enemy);
            }
        }, 0, 5000);
    }

    @Override
    public void stop(GameData gameData, World world) {
        timer.cancel();
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Enemy.class) {
                world.removeEntity(e);
            }
        }
    }

    public Entity createEnemy(GameData gameData) {
        Entity enemy = new Enemy();
        enemy.setPolygonCoordinates(-6, 6, 0, 1, 6, 6, 6, -6, 0, -1, -6, -6);

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
        enemy.setX(x);
        enemy.setY(y);
        enemy.setRotation(random.nextInt(360));
        return enemy;
    }

}
