package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;


class CollisionTest {
    @Mock
    private Bullet mockBullet;
    @Mock
    private Entity mockPlayer;
    @Mock
    private Asteroid mockAsteroid1;
    @Mock
    private Asteroid mockAsteroid2;
    @Mock
    private GameData mockGameData;
    @Mock
    private World mockWorld;
    Collision collision;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        collision = new Collision();
        mockAsteroid1.setLife(1);
        mockAsteroid2.setLife(1);
        mockPlayer.setLife(3);

        when(mockAsteroid1.getX()).thenReturn(1.0);
        when(mockAsteroid1.getY()).thenReturn(1.0);
        when(mockAsteroid1.getRadius()).thenReturn(10.0);

        when(mockAsteroid2.getX()).thenReturn(1.0);
        when(mockAsteroid2.getY()).thenReturn(1.0);
        when(mockAsteroid2.getRadius()).thenReturn(10.0);

        when(mockBullet.getX()).thenReturn(100.0);
        when(mockBullet.getY()).thenReturn(100.0);
        when(mockBullet.getRadius()).thenReturn(10.0);
        when(mockBullet.getLife()).thenReturn(1);
        when(mockBullet.getParentID()).thenReturn("1");

        when(mockPlayer.getX()).thenReturn(500.0);
        when(mockPlayer.getY()).thenReturn(500.0);
        when(mockPlayer.getRadius()).thenReturn(10.0);
        when(mockPlayer.getLife()).thenReturn(3);
        when(mockPlayer.getID()).thenReturn("1");


        when(mockWorld.getEntities())
                .thenReturn(new ArrayList<>(Arrays.asList(mockAsteroid2, mockPlayer,mockBullet, mockAsteroid1)));
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void testNoAsteroidCollision() {
        collision.process(mockGameData, mockWorld);
        verify(mockAsteroid1, times(0)).split(mockWorld);
        verify(mockAsteroid2, times(0)).split(mockWorld);
    }

    @Test
    void testAsteroidAndBulletCollision() {
        when(mockAsteroid1.getX()).thenReturn(100.0);
        when(mockAsteroid1.getY()).thenReturn(100.0);
        collision.process(mockGameData, mockWorld);
        verify(mockAsteroid1, times(1)).split(mockWorld);
        verify(mockBullet, times(1)).setLife(0);

    }

    @Test
    void testAsteroidAndBulletNotColliding() {
        collision.process(mockGameData, mockWorld);
        verify(mockAsteroid1, times(0)).split(mockWorld);
        verify(mockBullet, times(0)).setLife(0);
    }

    @Test
    void testAsteroidAndPlayerCollision() {
        when(mockAsteroid1.getX()).thenReturn(500.0);
        when(mockAsteroid1.getY()).thenReturn(500.0);
        collision.process(mockGameData, mockWorld);
        verify(mockAsteroid1, times(1)).split(mockWorld);
        verify(mockPlayer, times(1)).setLife(2);
    }

    @Test
    void testWhenPlayerAndPLayerBulletCollide() {
        when(mockPlayer.getX()).thenReturn(100.0);
        when(mockPlayer.getY()).thenReturn(100.0);
        collision.process(mockGameData, mockWorld);
        verify(mockPlayer, times(0)).setLife(2);
        verify(mockBullet, times(0)).setLife(0);
    }

    @Test
    void testWhenLifeIsZero() {
        when(mockAsteroid1.getX()).thenReturn(100.0);
        when(mockAsteroid1.getY()).thenReturn(100.0);
        when(mockAsteroid1.getLife()).thenReturn(0);
        when(mockBullet.getLife()).thenReturn(0);
        collision.process(mockGameData, mockWorld);
        verify(mockWorld, times(1)).removeEntity(mockAsteroid1);
        verify(mockWorld, times(1)).removeEntity(mockBullet);
    }
}