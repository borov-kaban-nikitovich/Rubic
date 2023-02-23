package org.example;

//final class Cell {
//    private char color = 'w';
//    public Cell(char color) {
//        if (color != 'r' && color != 'o' && color != 'w' && color != 'y' && color != 'g' && color != 'b')
//            throw new IllegalArgumentException("Color must be r, o, w, y, g or b!");
//        this.color = color;
//    }
//    public char getColor() {
//        return this.color;
//    }
//}

import java.util.Arrays;

final class Face {
    private final int size;
    private final char[] cells;

    public Face(char color, int size) {
        this.size = size;
        this.cells = new char[size * size];
        Arrays.fill(cells, color);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int j = 0; j < size; j++)
            out.append(cells[j]);
        for (int i = 1; i < size; i++) {
            out.append('\n');
            for (int j = 0; j < size; j++)
                out.append(cells[i * size + j]);
        }
        return out.toString();
    }

    public char getCell(int i) {
        return cells[i];
    }
}

public final class Rubic {
    private final int size;
    private Face up, down, left, right, front, behind;

    public Rubic(int size) {
        if (size < 2)
            throw new IllegalArgumentException("Rubic must be at least 2x2x2!");
        this.size = size;
        // Let's fill each face with standard color
        up = new Face('o', size);
        down = new Face('r', size);
        left = new Face('y', size);
        right = new Face('w', size);
        front = new Face('g', size);
        behind = new Face('b', size);
    }

    public int getSize() {
        return size;
    }

    public void printFace(char side) {
        if (side != 'u' && side != 'd' && side != 'l' && side != 'r' && side != 'f' && side != 'b')
            throw new IllegalArgumentException("Face must be u, d, l, r, f or b!");
        System.out.println(getFace(side).toString());
    }

    public Face getFace(char side) {
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

    public void print() {
        // up
        for (int i = 0; i < size; i++) {
            System.out.print(" ".repeat(size + 1));
            for (int j = 0; j < size; j++) {
                System.out.print(getFace('u').getCell(size * i + j));
            }
            System.out.println();
        }
        // left, front, right, behind
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                System.out.print(getFace('l').getCell(size * i + j));
            System.out.print(' ');
            for (int j = 0; j < size; j++)
                System.out.print(getFace('f').getCell(size * i + j));
            System.out.print(' ');
            for (int j = 0; j < size; j++)
                System.out.print(getFace('r').getCell(size * i + j));
            System.out.print(' ');
            for (int j = 0; j < size; j++)
                System.out.print(getFace('b').getCell(size * i + j));
            System.out.println();
        }
        // down
        for (int i = 0; i < size; i++) {
            System.out.print(" ".repeat(size + 1));
            for (int j = 0; j < size; j++) {
                System.out.print(getFace('d').getCell(size * i + j));
            }
            System.out.println();
        }
    }
}
