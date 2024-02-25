package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;

public class Enemy extends Entity {

    public Enemy() {
        life = 1;
        new Thread(() -> {
            while (life > 0) {
                try {
                    Thread.sleep(1000);
                    timeAlive++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
