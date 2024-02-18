package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService {

    private Random random = new Random();

    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            if (enemy.getLife() <= 0) {
                world.removeEntity(enemy);
                return;
            }
            if (enemy.getTimeAlive() > 10) {
                System.out.println("TIMES UP! ENEMY GONE!");
                world.removeEntity(enemy);
                return;
            }
            updateEnemy(enemy, gameData);

            if (random.nextInt(1000) > 990) {
                shoot(enemy, gameData, world);
            }
        }
    }

    private void updateEnemy(Entity enemy, GameData gameData) {
        double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
        double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
        enemy.setX(enemy.getX() + changeX);
        enemy.setY(enemy.getY() + changeY);

        if (enemy.getX() < 0) {
            enemy.setX(gameData.getDisplayWidth() - 1);
        }
        if (enemy.getX() > gameData.getDisplayWidth()) {
            enemy.setX(1);
        }
        if (enemy.getY() < 0) {
            enemy.setY(gameData.getDisplayHeight() - 1);
        }
        if (enemy.getY() > gameData.getDisplayHeight()) {
            enemy.setY(1);
        }

        enemy.setRotation(enemy.getRotation() + (random.nextInt(7) - 3));

    }

    private void shoot(Entity enemy, GameData gameData, World world) {
        for (BulletSPI bulletSPI : getBulletSPIs()) {
            Entity bullet = bulletSPI.createBullet(enemy, gameData);
            bullet.setX(enemy.getX());
            bullet.setY(enemy.getY());
            bullet.setRotation(random.nextInt(360));
            world.addEntity(bullet);
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

}
