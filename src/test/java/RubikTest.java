import org.junit.Test;

import static org.junit.Assert.*;

public class RubikTest {

    private Rubik cube;

    @Test
    public void getFace() {
        cube = new Rubik(3);
        assertEquals(cube.getFace(Side.UP), "ooo\n".repeat(3));
    }

    @Test
    public void u() {
        cube = new Rubik(3);
        cube.rotateUp();
        assertEquals("ggg\n" + "yyy\n" + "yyy\n", cube.getFace(Side.LEFT));
        assertEquals("bbb\n" + "www\n" + "www\n", cube.getFace(Side.RIGHT));
        assertEquals("www\n" + "ggg\n" + "ggg\n", cube.getFace(Side.FRONT));
        assertEquals("yyy\n" + "bbb\n" + "bbb\n", cube.getFace(Side.BEHIND));
    }

    @Test
    public void d() {
        cube = new Rubik(3);
        cube.rotateDown(2);
        assertEquals("yyy\n" + "bbb\n" + "bbb\n", cube.getFace(Side.LEFT));
        assertEquals("www\n" + "ggg\n" + "ggg\n", cube.getFace(Side.RIGHT));
        assertEquals("ggg\n" + "yyy\n" + "yyy\n", cube.getFace(Side.FRONT));
        assertEquals("bbb\n" + "www\n" + "www\n", cube.getFace(Side.BEHIND));
    }

    @Test
    public void f() {
        cube = new Rubik(3);
        cube.rotateFront(1);
        assertEquals("yyr\n".repeat(3), cube.getFace(Side.LEFT));
        assertEquals("oww\n".repeat(3), cube.getFace(Side.RIGHT));
        assertEquals("ooo\n" + "ooo\n" + "yyy\n", cube.getFace(Side.UP));
        assertEquals("www\n" + "rrr\n" + "rrr\n", cube.getFace(Side.DOWN));
    }

    @Test
    public void b() {
        cube = new Rubik(3);
        cube.rotateBehind(1);
        assertEquals("ryy\n".repeat(3), cube.getFace(Side.LEFT));
        assertEquals("wwo\n".repeat(3), cube.getFace(Side.RIGHT));
        assertEquals("yyy\n" + "ooo\n" + "ooo\n", cube.getFace(Side.UP));
        assertEquals("rrr\n" + "rrr\n" + "www\n", cube.getFace(Side.DOWN));
    }

    @Test
    public void r() {
        cube = new Rubik(3);
        cube.rotateRight(2);
        assertEquals("grr\n".repeat(3), cube.getFace(Side.FRONT));
        assertEquals("oob\n".repeat(3), cube.getFace(Side.BEHIND));
        assertEquals("ogg\n".repeat(3), cube.getFace(Side.UP));
        assertEquals("rbb\n".repeat(3), cube.getFace(Side.DOWN));
    }

    @Test
    public void l() {
        cube = new Rubik(3);
        cube.rotateLeft();
        assertEquals("ogg\n".repeat(3), cube.getFace(Side.FRONT));
        assertEquals("bbr\n".repeat(3), cube.getFace(Side.BEHIND));
        assertEquals("boo\n".repeat(3), cube.getFace(Side.UP));
        assertEquals("grr\n".repeat(3), cube.getFace(Side.DOWN));
    }

    @Test
    public void turnUp() {
        cube = new Rubik(3);
        cube.turnUp();
        assertEquals("rrr\n".repeat(3), cube.getFace(Side.FRONT));
    }

    @Test
    public void turnDown() {
        cube = new Rubik(3);
        cube.turnDown();
        assertEquals("ooo\n".repeat(3), cube.getFace(Side.FRONT));
    }

    @Test
    public void turnLeft() {
        cube = new Rubik(3);
        cube.turnLeft();
        assertEquals("www\n".repeat(3), cube.getFace(Side.FRONT));
    }

    @Test
    public void turnRight() {
        cube = new Rubik(3);
        cube.turnRight();
        assertEquals("yyy\n".repeat(3), cube.getFace(Side.FRONT));
    }
}