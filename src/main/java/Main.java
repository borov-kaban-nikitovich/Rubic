public class Main {
    public static void main(String[] args) {
        Rubik cube = new Rubik(2);
        System.out.println(cube);
        System.out.println();
        cube.rotateDown();
        System.out.println(cube);
    }
}