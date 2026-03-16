import junit.framework.TestCase;

/**
 * Pruebas para Tower.
 */
public class TowerTest extends TestCase
{
    private Tower tower;

    /**
     * Prepara la torre antes de cada prueba.
     */
    public void setUp()
    {
        tower = new Tower(20, 50);
    }

    /**
     * Prueba que la torre inicie vacía.
     */
    public void testInitialState()
    {
        assertEquals(0, tower.height());
        assertEquals(0, tower.liddedCups().length);
        assertEquals(0, tower.stackingItems().length);
        assertTrue(tower.ok());
    }

    /**
     * Prueba agregar una taza.
     */
    public void testPushCup()
    {
        tower.pushCup(3);

        assertTrue(tower.ok());
        assertEquals(5, tower.height());
        assertEquals(1, tower.stackingItems().length);
        assertEquals("cup", tower.stackingItems()[0][0]);
        assertEquals("3", tower.stackingItems()[0][1]);
    }

    /**
     * Prueba agregar una tapa.
     */
    public void testPushLid()
    {
        tower.pushLid(2);

        assertTrue(tower.ok());
        assertEquals(1, tower.height());
        assertEquals(1, tower.stackingItems().length);
        assertEquals("lid", tower.stackingItems()[0][0]);
        assertEquals("2", tower.stackingItems()[0][1]);
    }

    /**
     * Prueba unir taza y tapa.
     */
    public void testMergeCupAndLid()
    {
        tower.pushCup(3);
        tower.pushLid(3);

        assertTrue(tower.ok());
        assertEquals(6, tower.height());
        assertEquals(1, tower.liddedCups().length);
        assertEquals(3, tower.liddedCups()[0]);
        assertEquals(2, tower.stackingItems().length);
        assertEquals("cup", tower.stackingItems()[0][0]);
        assertEquals("3", tower.stackingItems()[0][1]);
        assertEquals("lid", tower.stackingItems()[1][0]);
        assertEquals("3", tower.stackingItems()[1][1]);
    }

    /**
     * Prueba unir tapa y taza.
     */
    public void testMergeLidAndCup()
    {
        tower.pushLid(5);
        tower.pushCup(5);

        assertTrue(tower.ok());
        assertEquals(10, tower.height());
        assertEquals(1, tower.liddedCups().length);
        assertEquals(5, tower.liddedCups()[0]);
    }

    /**
     * Prueba taza repetida.
     */
    public void testRepeatedCup()
    {
        tower.pushCup(3);
        tower.pushCup(3);

        assertFalse(tower.ok());
        assertEquals(1, tower.stackingItems().length);
    }

    /**
     * Prueba tapa repetida.
     */
    public void testRepeatedLid()
    {
        tower.pushLid(4);
        tower.pushLid(4);

        assertFalse(tower.ok());
        assertEquals(1, tower.stackingItems().length);
    }

    /**
     * Prueba popCup.
     */
    public void testPopCup()
    {
        tower.pushCup(2);
        tower.pushCup(4);

        tower.popCup();

        assertTrue(tower.ok());
        assertEquals(3, tower.height());
        assertEquals(1, tower.stackingItems().length);
        assertEquals("2", tower.stackingItems()[0][1]);
    }

    /**
     * Prueba popLid.
     */
    public void testPopLid()
    {
        tower.pushLid(2);
        tower.pushLid(5);

        tower.popLid();

        assertTrue(tower.ok());
        assertEquals(1, tower.height());
        assertEquals(1, tower.stackingItems().length);
        assertEquals("2", tower.stackingItems()[0][1]);
    }

    /**
     * Prueba removeCup.
     */
    public void testRemoveCup()
    {
        tower.pushCup(2);
        tower.pushCup(4);
        tower.pushCup(6);

        tower.removeCup(4);

        assertTrue(tower.ok());
        assertEquals(2, tower.stackingItems().length);
        assertEquals("2", tower.stackingItems()[0][1]);
        assertEquals("6", tower.stackingItems()[1][1]);
    }

    /**
     * Prueba removeLid.
     */
    public void testRemoveLid()
    {
        tower.pushLid(2);
        tower.pushLid(4);
        tower.pushLid(6);

        tower.removeLid(4);

        assertTrue(tower.ok());
        assertEquals(2, tower.stackingItems().length);
        assertEquals("2", tower.stackingItems()[0][1]);
        assertEquals("6", tower.stackingItems()[1][1]);
    }

    /**
     * Prueba altura total.
     */
    public void testHeight()
    {
        tower.pushCup(3);
        tower.pushLid(3);
        tower.pushCup(5);
        tower.pushLid(2);

        assertEquals(16, tower.height());
    }

    /**
     * Prueba tazas tapadas.
     */
    public void testLiddedCups()
    {
        tower.pushCup(2);
        tower.pushLid(2);
        tower.pushCup(5);
        tower.pushLid(5);
        tower.pushCup(7);

        int[] result = tower.liddedCups();

        assertEquals(2, result.length);
        assertEquals(2, result[0]);
        assertEquals(5, result[1]);
    }

    /**
     * Prueba stackingItems.
     */
    public void testStackingItems()
    {
        tower.pushCup(3);
        tower.pushLid(3);
        tower.pushCup(5);
        tower.pushLid(2);

        String[][] result = tower.stackingItems();

        assertEquals(4, result.length);
        assertEquals("cup", result[0][0]);
        assertEquals("3", result[0][1]);
        assertEquals("lid", result[1][0]);
        assertEquals("3", result[1][1]);
        assertEquals("cup", result[2][0]);
        assertEquals("5", result[2][1]);
        assertEquals("lid", result[3][0]);
        assertEquals("2", result[3][1]);
    }

    /**
     * Prueba orderTower.
     */
    public void testOrderTower()
    {
        tower.pushCup(2);
        tower.pushCup(6);
        tower.pushCup(4);

        tower.orderTower();

        String[][] result = tower.stackingItems();

        assertTrue(tower.ok());
        assertEquals("6", result[0][1]);
        assertEquals("4", result[1][1]);
        assertEquals("2", result[2][1]);
    }

    /**
     * Prueba reverseTower.
     */
    public void testReverseTower()
    {
        tower.pushCup(2);
        tower.pushCup(4);
        tower.pushCup(6);

        tower.reverseTower();

        String[][] result = tower.stackingItems();

        assertTrue(tower.ok());
        assertEquals("6", result[0][1]);
        assertEquals("4", result[1][1]);
        assertEquals("2", result[2][1]);
    }

    /**
     * Prueba el límite de altura.
     */
    public void testHeightLimit()
    {
        tower = new Tower(15, 10);
        tower.pushCup(6);

        assertFalse(tower.ok());
        assertEquals(0, tower.stackingItems().length);
    }
}