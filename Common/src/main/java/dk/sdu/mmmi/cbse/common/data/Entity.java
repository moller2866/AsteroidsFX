package dk.sdu.mmmi.cbse.common.data;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.UUID;

public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();

    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;
    protected int life;
    protected double timeAlive;
    private double radius;

    public String getID() {
        return ID.toString();
    }


    public void setPolygonCoordinates(double... coordinates) {
        this.polygonCoordinates = coordinates;

        double[][] coordinateSets = new double[coordinates.length / 2][2];
        for (int i = 0; i < coordinates.length; i += 2) {
            coordinateSets[i / 2][0] = coordinates[i];
            coordinateSets[i / 2][1] = coordinates[i + 1];
        }
        double[] lengths = new double[coordinateSets.length];
        for (int i = 0; i < coordinateSets.length; i++) {
            lengths[i] = Math.sqrt(Math.pow(coordinateSets[i][0], 2) + Math.pow(coordinateSets[i][1], 2));
        }
        radius = Arrays.stream(lengths).sum() / lengths.length;
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }


    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }


    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotation() {
        return rotation;
    }

    public int getLife() {
        return life;
    }

    public double getTimeAlive() {
        return timeAlive;
    }

    public double getRadius() {
        return radius;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
