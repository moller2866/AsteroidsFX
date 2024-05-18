package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.ArrayList;
import java.util.List;

public class Collision implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        List<Entity> entities = new ArrayList<>(world.getEntities());
        for (int i = 0; i < entities.size(); i++) {
            for (int j = i + 1; j < entities.size(); j++) {
                Entity firstEntity = entities.get(i);
                Entity secondEntity = entities.get(j);

                if (isColliding(firstEntity, secondEntity)) {
                    if (bulletParentCollision(firstEntity, secondEntity)) {
                        continue;
                    }
                    if (firstEntity instanceof Asteroid && secondEntity instanceof Asteroid) {
                        continue;
                    }
                    asteroidCollision(firstEntity, secondEntity, world);
                    defaultCollision(firstEntity, secondEntity, world);
                }
            }
        }
    }

    private void defaultCollision(Entity firstEntity, Entity secondEntity, World world) {
        firstEntity.setLife(firstEntity.getLife() - 1);
        secondEntity.setLife(secondEntity.getLife() - 1);

        if (firstEntity.getLife() <= 0) {
            world.removeEntity(firstEntity);
        }
        if (secondEntity.getLife() <= 0) {
            world.removeEntity(secondEntity);
        }
    }

    private boolean bulletParentCollision(Entity firstEntity, Entity secondEntity) {
        if (firstEntity instanceof Bullet) {
            return (((Bullet) firstEntity).getParentID().equals(secondEntity.getID()));
        }
        if (secondEntity instanceof Bullet) {
            return ((Bullet) secondEntity).getParentID().equals(firstEntity.getID());
        }
        return false;
    }

    private void asteroidCollision(Entity firstEntity, Entity secondEntity, World world) {
        if (firstEntity instanceof Asteroid) {
            Asteroid asteroid = (Asteroid) firstEntity;
            asteroid.split().forEach(world::addEntity);
        }
        if (secondEntity instanceof Asteroid) {
            Asteroid asteroid = (Asteroid) secondEntity;
            asteroid.split().forEach(world::addEntity);
        }
    }


    private boolean isColliding(Entity entity, Entity entity1) {
        double distance = Math.sqrt(Math.pow(entity.getX() - entity1.getX(), 2) + Math.pow(entity.getY() - entity1.getY(), 2));
        return distance < entity.getRadius() + entity1.getRadius();
    }

}