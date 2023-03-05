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


    public String getFace(Side side) {
        return switch (side) {
            case UP -> up.toString();
            case DOWN -> down.toString();
            case LEFT -> left.toString();
            case RIGHT -> right.toString();
            case FRONT -> front.toString();
            case BEHIND -> behind.toString();
        };
    }

    // Rotates upper face by 90 degrees (clockwise)
    public void rotateUp(int layers) {
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

    public void rotateUp() {
        rotateUp(1);
    }

    public void rotateDown(int layers) {
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

    public void rotateDown() {
        rotateDown(1);
    }

    public void rotateFront(int layers) {
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

    public void rotateFront() {
        rotateFront(1);
    }

    public void rotateBehind(int layers) {
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

    public void rotateBehind() {
        rotateBehind(1);
    }

    public void rotateRight(int layers) {
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

    public void rotateRight() {
        rotateRight(1);
    }

    public void rotateLeft(int layers) {
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

    public void rotateLeft() {
        rotateLeft(1);
    }

    public void turnUp() {
        rotateRight(size);
    }

    public void turnDown() {
        rotateLeft(size);
    }

    public void turnLeft() {
        rotateUp(size);
    }

    public void turnRight() {
        rotateDown(size);
    }

    public void randomize() {
        Random rand = new Random();
        int rotates = rand.nextInt(10, 100);
        for (int i = 0; i < rotates; i++) {
            int layers = rand.nextInt(1, size + 1);
            switch (rand.nextInt(0, 6)) {
                case 0 -> rotateUp(layers);
                case 1 -> rotateDown(layers);
                case 2 -> rotateLeft(layers);
                case 3 -> rotateRight(layers);
                case 4 -> rotateFront(layers);
                case 5 -> rotateBehind(layers);
                default -> throw new IllegalStateException(); // IDEA says it's necessary :/
            }
        }
    }

//    private boolean isSolved() {
//        for (int i = 0; i < size; i++)
//            for (int j = 0; j < size; j++)
//                if (
//                        up.cells[i][j] != up.cells[0][0] || down.cells[i][j] != down.cells[0][0] ||
//                        left.cells[i][j] != left.cells[0][0] || right.cells[i][j] != right.cells[0][0] ||
//                        front.cells[i][j] != front.cells[0][0]
//                )
//                    return false;
//        return true;
//    }

//    // I don't know how to solve it :(
//    public void solve() {
//        if (size != 2) throw new IllegalArgumentException("You can use solve() only for 2x2x2");
//        while (true) {
//            f();
//            r();
//            u();
//            for (int i = 0; i < 3; i++) {
//                r();
//            }
//            for (int i = 0; i < 3; i++) {
//                u();
//            }
//            for (int i = 0; i < 3; i++) {
//                f();
//            }
//            if (isSolved()) return;
//        }
//    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        // up
        for (int i = 0; i < size; i++) {
            out.append(" ".repeat(size));
            for (int j = 0; j < size; j++) {
                out.append(up.cells[i][j]);
            }
            out.append('\n');
        }
        // left, front, right, behind
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                out.append(left.cells[i][j]);
            for (int j = 0; j < size; j++)
                out.append(front.cells[i][j]);
            for (int j = 0; j < size; j++)
                out.append(right.cells[i][j]);
            for (int j = 0; j < size; j++)
                out.append(behind.cells[i][j]);
            out.append('\n');
        }
        // down
        for (int i = 0; i < size; i++) {
            out.append(" ".repeat(size));
            for (int j = 0; j < size; j++) {
                out.append(down.cells[i][j]);
            }
            out.append('\n');
        }
        return out.toString();
    }
}
