import org.junit.Test;
import static org.junit.Assert.*;

public class RubikTest {

    private Rubik cube;

    @Test
    public void getFace() {
        cube = new Rubik(3);
        assertEquals(cube.getFace('u'), "ooo\n".repeat(3));
    }

    @Test
    public void u() {
        cube = new Rubik(3);
        cube.u();
        assertEquals("ggg\n" + "yyy\n" + "yyy\n", cube.getFace('l'));
        assertEquals("bbb\n" + "www\n" + "www\n", cube.getFace('r'));
        assertEquals("www\n" + "ggg\n" + "ggg\n", cube.getFace('f'));
        assertEquals("yyy\n" + "bbb\n" + "bbb\n", cube.getFace('b'));
    }

    @Test
    public void d() {
        cube = new Rubik(3);
        cube.d(2);
        assertEquals("yyy\n" + "bbb\n" + "bbb\n", cube.getFace('l'));
        assertEquals("www\n" + "ggg\n" + "ggg\n", cube.getFace('r'));
        assertEquals("ggg\n" + "yyy\n" + "yyy\n", cube.getFace('f'));
        assertEquals("bbb\n" + "www\n" + "www\n", cube.getFace('b'));
    }

    @Test
    public void f() {
        cube = new Rubik(3);
        cube.f(1);
        assertEquals("yyr\n".repeat(3), cube.getFace('l'));
        assertEquals("oww\n".repeat(3), cube.getFace('r'));
        assertEquals("ooo\n" + "ooo\n" + "yyy\n", cube.getFace('u'));
        assertEquals("www\n" + "rrr\n" + "rrr\n", cube.getFace('d'));
    }

    @Test
    public void b() {
        cube = new Rubik(3);
        cube.b(1);
        assertEquals("ryy\n".repeat(3), cube.getFace('l'));
        assertEquals("wwo\n".repeat(3), cube.getFace('r'));
        assertEquals("yyy\n" + "ooo\n" + "ooo\n", cube.getFace('u'));
        assertEquals("rrr\n" + "rrr\n" + "www\n", cube.getFace('d'));
    }

    @Test
    public void r() {
        cube = new Rubik(3);
        cube.r(2);
        assertEquals("grr\n".repeat(3), cube.getFace('f'));
        assertEquals("oob\n".repeat(3), cube.getFace('b'));
        assertEquals("ogg\n".repeat(3), cube.getFace('u'));
        assertEquals("rbb\n".repeat(3), cube.getFace('d'));
    }

    @Test
    public void l() {
        cube = new Rubik(3);
        cube.l();
        assertEquals("ogg\n".repeat(3), cube.getFace('f'));
        assertEquals("bbr\n".repeat(3), cube.getFace('b'));
        assertEquals("boo\n".repeat(3), cube.getFace('u'));
        assertEquals("grr\n".repeat(3), cube.getFace('d'));
    }

    @Test
    public void turnUp() {
        cube = new Rubik(3);
        cube.turnUp();
        assertEquals("rrr\n".repeat(3), cube.getFace('f'));
    }

    @Test
    public void turnDown() {
        cube = new Rubik(3);
        cube.turnDown();
        assertEquals("ooo\n".repeat(3), cube.getFace('f'));
    }

    @Test
    public void turnLeft() {
        cube = new Rubik(3);
        cube.turnLeft();
        assertEquals("www\n".repeat(3), cube.getFace('f'));
    }

    @Test
    public void turnRight() {
        cube = new Rubik(3);
        cube.turnRight();
        assertEquals("yyy\n".repeat(3), cube.getFace('f'));
    }
}