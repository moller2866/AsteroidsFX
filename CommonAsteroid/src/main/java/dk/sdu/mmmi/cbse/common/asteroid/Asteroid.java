package dk.sdu.mmmi.cbse.common.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Asteroid extends Entity {

    private int level;

    private double[] asteroidPolygon = new double[]{
            -6, 1.5,   // Point 1
            -4, 4.5,   // Point 2
            0, 4.5,    // Point 3
            1.5, 7,    // Point 4
            3, 5,      // Point 5
            5, 1.5,    // Point 6
            3.5, -2,   // Point 7
            2.5, -5,   // Point 8
            -1, -6.5,  // Point 9
            -3.5, -5.5,// Point 10
            -5, -1,    // Point 11
            -3.5, -2.5 // Point 12
    };

    public Asteroid(int level) {
        this.level = level;
        life = 1;
        new Thread(() -> {
            while (life > 0) {
                try {
                    Thread.sleep(1000);
                    timeAlive += 1;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void setPolygonCoordinates() {
        setPolygonCoordinates(Arrays.stream(asteroidPolygon).map(i -> i * Math.pow(2, level)).toArray());
    }

    public List<Entity> split() {
        List<Entity> entities = new ArrayList<>();
        if (level > 0) {
            for (int i = 0; i < 2; i++) {
                Asteroid asteroid = new Asteroid(level - 1);
                asteroid.setPolygonCoordinates();
                asteroid.setX(this.getX());
                asteroid.setY(this.getY());
                asteroid.setRotation(this.getRotation() + (i == 0 ? 45 : -45));
                entities.add(asteroid);
            }
        }
        return entities;
    }
}
