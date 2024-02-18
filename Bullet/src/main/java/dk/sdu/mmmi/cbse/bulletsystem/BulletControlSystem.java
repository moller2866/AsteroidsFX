package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    private int bulletSpeed = 2;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(Bullet.class)) {
            if (bullet.getTimeAlive() > 1) {
                world.removeEntity(bullet);
                break;
            }
            if (bullet.getX() < 0) {
                bullet.setX(gameData.getDisplayWidth() - 1);
            }
            if (bullet.getX() > gameData.getDisplayWidth()) {
                bullet.setX(1);
            }
            if (bullet.getY() < 0) {
                bullet.setY(gameData.getDisplayHeight() - 1);
            }
            if (bullet.getY() > gameData.getDisplayHeight()) {
                bullet.setY(1);
            }

            double changedX = Math.cos(Math.toRadians(bullet.getRotation()));
            double changedY = Math.sin(Math.toRadians(bullet.getRotation()));
            bullet.setX((bullet.getX()) + changedX * bulletSpeed);
            bullet.setY((bullet.getY()) + changedY * bulletSpeed);
        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new Bullet();
        setShape(bullet);
        bullet.setX(shooter.getX());
        bullet.setY(shooter.getY());
        bullet.setRotation(shooter.getRotation());
        return bullet;
    }

    private void setShape(Entity entity) {
        entity.setPolygonCoordinates(-2, -2, 2, 0, -2, 2);
    }

}
