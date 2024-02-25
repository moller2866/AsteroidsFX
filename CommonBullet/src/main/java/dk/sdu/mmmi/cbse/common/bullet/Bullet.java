package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;

/**
 * @author corfixen
 */
public class Bullet extends Entity {
    String parentID;

    public String getParentID() {
        return parentID;
    }

    public Bullet(String parentID) {
        this.parentID = parentID;
        life = 1;
        new Thread(() -> {
            while (life > 0) {
                try {
                    Thread.sleep(500);
                    timeAlive += 0.5;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
