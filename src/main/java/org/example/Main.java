package org.example;

public class Main {
    public static void main(String[] args) {
        Rubic cube = new Rubic(2);
        //System.out.println(cube.getSize());
        //System.out.println(cube.getFace('u').getCell(0));
        cube.printFace('b');
        //cube.print();
    }
}