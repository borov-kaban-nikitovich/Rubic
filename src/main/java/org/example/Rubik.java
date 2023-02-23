package org.example;

import java.util.Arrays;

public final class Rubik {
    final class Face {
        private final int size;
        private char[][] cells;

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
        public void rotate() {
            Face tempFace = new Face('x', size);
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    tempFace.cells[i][j] = this.cells[j][i];
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    this.cells[i][j] = tempFace.cells[i][size - 1 - j];
        }
    }


    private final int size;
    private Face up, down, left, right, front, behind;

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

    public void printFace(char side) {
        if (side != 'u' && side != 'd' && side != 'l' && side != 'r' && side != 'f' && side != 'b')
            throw new IllegalArgumentException("Face must be u, d, l, r, f or b!");
        System.out.println(getFace(side).toString());
    }

    private Face getFace(char side) {
        return switch (side) {
            case 'u' -> up;
            case 'd' -> down;
            case 'l' -> left;
            case 'r' -> right;
            case 'f' -> front;
            case 'b' -> behind;
            default -> throw new IllegalArgumentException("Face must be u, d, l, r, f or b!");
        };
    }

    // Rotates Up by 90 degrees (clockwise)
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
    }

    public void f(){
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
    }

    public void b(){
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
    }

    public void l() {
        l(1);
    }

    // Turns the whole cube up by 90 degrees
    public void turnUp() {

    }

    // Turns the whole cube down by 90 degrees
    public void turnDown() {

    }

    // Turns the whole cube left by 90 degrees
    public void turnLeft() {

    }

    // Turns the whole cube right by 90 degrees
    public void turnRight() {

    }
}
