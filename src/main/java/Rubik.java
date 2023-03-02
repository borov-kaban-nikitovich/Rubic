import java.util.Arrays;
import java.util.Random;

public final class Rubik {
    private final class Face {
        private final int size;
        private final char[][] cells;

        public Face(char color, int size) {
            this.size = size;
            this.cells = new char[size][size];
            for (int i = 0; i < size; i++)
                Arrays.fill(cells[i], color);
        }

        @Override
        public String toString() {
            StringBuilder out = new StringBuilder();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++)
                    out.append(cells[i][j]);
                out.append('\n');
            }
            return out.toString();
        }

        // Rotates face by 90 degrees clockwise
        private void rotate() {
            char[][] tempCells = new char[size][size];
            for (int i = 0; i < size; i++)
                Arrays.fill(tempCells[i], 'x');
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    tempCells[i][j] = this.cells[j][i];
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    this.cells[i][j] = tempCells[i][size - 1 - j];
        }
    }


    private final int size;
    private final Face up, down, left, right, front, behind;

    public Rubik(int size) {
        if (size < 2) throw new IllegalArgumentException("Rubic must be at least 2x2x2!");
        this.size = size;
        // Let's fill each face with standard color
        up = new Face('o', size);
        down = new Face('r', size);
        left = new Face('y', size);
        right = new Face('w', size);
        front = new Face('g', size);
        behind = new Face('b', size);
    }


    public String getFace(char side) {
        return switch (side) {
            case 'u' -> up.toString();
            case 'd' -> down.toString();
            case 'l' -> left.toString();
            case 'r' -> right.toString();
            case 'f' -> front.toString();
            case 'b' -> behind.toString();
            default -> throw new IllegalArgumentException("Face must be u, d, l, r, f or b!");
        };
    }

    // Rotates upper face by 90 degrees (clockwise)
    public void u(int layers) {
        if (layers > size) throw new IllegalArgumentException("Too many layers!");
        up.rotate();
        for (int i = 0; i < layers; i++) {
            char[] tempLayer = behind.cells[i].clone();
            behind.cells[i] = left.cells[i];
            left.cells[i] = front.cells[i];
            front.cells[i] = right.cells[i];
            right.cells[i] = tempLayer;
        }
        if (layers == size) for (int i = 0; i < 3; i++)
            down.rotate();
    }

    public void u() {
        u(1);
    }

    public void d(int layers) {
        if (layers > size) throw new IllegalArgumentException("Too many layers!");
        down.rotate();
        for (int i = size - 1; i > size - 1 - layers; i--) {
            char[] tempLayer = behind.cells[i].clone();
            behind.cells[i] = right.cells[i];
            right.cells[i] = front.cells[i];
            front.cells[i] = left.cells[i];
            left.cells[i] = tempLayer;
        }
        if (layers == size) for (int i = 0; i < 3; i++)
            up.rotate();
    }

    public void d() {
        d(1);
    }

    public void f(int layers) {
        if (layers > size) throw new IllegalArgumentException("Too many layers!");
        front.rotate();
        for (int i = 0; i < layers; i++) {
            char[] tempLayer = up.cells[size - 1 - i].clone();
            for (int j = 0; j < size; j++) {
                up.cells[size - 1 - i][j] = left.cells[size - 1 - j][size - 1 - i];
                left.cells[size - 1 - j][size - 1 - i] = down.cells[i][size - 1 - j];
                down.cells[i][size - 1 - j] = right.cells[j][i];
                right.cells[j][i] = tempLayer[j];
            }
        }
        if (layers == size) for (int i = 0; i < 3; i++)
            behind.rotate();
    }

    public void f() {
        f(1);
    }

    public void b(int layers) {
        if (layers > size) throw new IllegalArgumentException("Too many layers!");
        behind.rotate();
        for (int i = 0; i < layers; i++) {
            char[] tempLayer = down.cells[size - 1 - i].clone();
            for (int j = 0; j < size; j++) {
                down.cells[size - 1 - i][j] = right.cells[size - 1 - j][size - 1 - i];
                right.cells[size - 1 - j][size - 1 - i] = up.cells[i][size - 1 - j];
                up.cells[i][size - 1 - j] = left.cells[j][i];
                left.cells[j][i] = tempLayer[j];
            }
        }
        if (layers == size) for (int i = 0; i < 3; i++)
            front.rotate();
    }

    public void b() {
        b(1);
    }

    public void r(int layers) {
        if (layers > size) throw new IllegalArgumentException("Too many layers!");
        right.rotate();
        for (int i = 0; i < layers; i++) {
            char[] tempLayer = new char[size];
            for (int j = 0; j < size; j++) {
                tempLayer[j] = up.cells[size - 1 - j][size - 1 - i];
                up.cells[size - 1 - j][size - 1 - i] = front.cells[size - 1 - j][size - 1 - i];
                front.cells[size - 1 - j][size - 1 - i] = down.cells[size - 1 - j][size - 1 - i];
                down.cells[size - 1 - j][size - 1 - i] = behind.cells[j][i];
                behind.cells[j][i] = tempLayer[j];
            }
        }
        if (layers == size) for (int i = 0; i < 3; i++)
            left.rotate();
    }

    public void r() {
        r(1);
    }

    public void l(int layers) {
        if (layers > size) throw new IllegalArgumentException("Too many layers!");
        left.rotate();
        for (int i = 0; i < layers; i++) {
            char[] tempLayer = new char[size];
            for (int j = 0; j < size; j++) {
                tempLayer[j] = up.cells[j][i];
                up.cells[j][i] = behind.cells[size - 1 - j][size - 1 - i];
                behind.cells[size - 1 - j][size - 1 - i] = down.cells[j][i];
                down.cells[j][i] = front.cells[j][i];
                front.cells[j][i] = tempLayer[j];
            }
        }
        if (layers == size) for (int i = 0; i < 3; i++)
            right.rotate();
    }

    public void l() {
        l(1);
    }

    public void turnUp() {
        r(size);
    }

    public void turnDown() {
        l(size);
    }

    public void turnLeft() {
        u(size);
    }

    public void turnRight() {
        d(size);
    }

    public void randomize() {
        Random rand = new Random();
        int rotates = rand.nextInt(10, 100);
        for (int i = 0; i < rotates; i++) {
            int layers = rand.nextInt(1, size + 1);
            switch (rand.nextInt(0, 6)) {
                case 0 -> u(layers);
                case 1 -> d(layers);
                case 2 -> l(layers);
                case 3 -> r(layers);
                case 4 -> f(layers);
                case 5 -> b(layers);
                default -> throw new IllegalStateException(); // IDEA says it's necessary :/
            }
            ;
        }
    }

    private boolean isSolved() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (
                        up.cells[i][j] != up.cells[0][0] || down.cells[i][j] != down.cells[0][0] ||
                        left.cells[i][j] != left.cells[0][0] || right.cells[i][j] != right.cells[0][0] ||
                        front.cells[i][j] != front.cells[0][0]
                )
                    return false;
        return true;
    }

    // I don't know how to solve it :(
    public void solve() {
        if (size != 2) throw new IllegalArgumentException("You can use solve() only for 2x2x2");
        while (true) {
            f();
            r();
            u();
            for (int i = 0; i < 3; i++) {
                r();
            }
            for (int i = 0; i < 3; i++) {
                u();
            }
            for (int i = 0; i < 3; i++) {
                f();
            }
            if (isSolved()) return;
        }
    }

    // Просто полезный метод. Выводит весь кубик.
    public void print() {
        // up
        for (int i = 0; i < size; i++) {
            System.out.print(" ".repeat(size));
            for (int j = 0; j < size; j++) {
                System.out.print(up.cells[i][j]);
            }
            System.out.println();
        }
        // left, front, right, behind
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                System.out.print(left.cells[i][j]);
            for (int j = 0; j < size; j++)
                System.out.print(front.cells[i][j]);
            for (int j = 0; j < size; j++)
                System.out.print(right.cells[i][j]);
            for (int j = 0; j < size; j++)
                System.out.print(behind.cells[i][j]);
            System.out.println();
        }
        // down
        for (int i = 0; i < size; i++) {
            System.out.print(" ".repeat(size));
            for (int j = 0; j < size; j++) {
                System.out.print(down.cells[i][j]);
            }
            System.out.println();
        }
    }
}
