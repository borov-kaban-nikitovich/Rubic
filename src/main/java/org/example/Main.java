package org.example;

public class Main {
    public static void main(String[] args) {
        Rubik cube = new Rubik(3);
        cube.printFace('r');
        cube.b(2);
        cube.printFace('r');
    }
}