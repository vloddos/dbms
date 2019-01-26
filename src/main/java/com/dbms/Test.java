package com.dbms;

import com.github.jinahya.bit.io.BitOutput;
import com.github.jinahya.bit.io.DefaultBitOutput;
import com.github.jinahya.bit.io.StreamByteInput;

import java.io.*;
import java.util.Collection;
import java.util.List;

public class Test {

    static void printCollection(Collection<?> c) {
        for (Object e : c) {
            System.out.println(e);
        }
    }

    public static abstract class Shape {
        public abstract void draw(Canvas c);
    }

    public static class Circle extends Shape {

        private int x, y, radius;

        public Circle(int x, int y, int radius) {
            this.x = x;
            this.y = y;
            this.radius = radius;
        }

        public void draw(Canvas c) {
            System.out.println(String.format("C[x=%s y=%s radius=%s]", x, y, radius));
        }
    }

    public static class Rectangle extends Shape {

        private int x, y, width, height;

        public Rectangle(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public void draw(Canvas c) {
            System.out.println(String.format("R[x=%s y=%s width=%s height=%s]", x, y, width, height));
        }
    }

    public static class Canvas {

        public void draw(Shape s) {
            s.draw(this);
        }

        public void drawAll(List<? extends Shape> shapes) {
            shapes.forEach(this::draw);
        }
    }

    public static void main(String[] args) throws Exception {
        /*var canvas = new Canvas();
        var c = new Circle(23, 4, 45);
        var r = new Rectangle(5, 4, 23, 77);
        canvas.draw(c);
        canvas.draw(r);
        var l = Arrays.asList(c, c);
        canvas.drawAll(l);*/


        /*Collection<?> c = new ArrayList<String>();
        ((ArrayList<String>) c).add("avb");
        ((ArrayList<Boolean>) c).add(true);
        ((ArrayList<Integer>) c).add(534);
        c.add(null);
        printCollection(c);
        System.out.println(((ArrayList<String>) c).get(0));
        System.out.println(((ArrayList<String>) c).get(1));//ClassCastException*/
    }
}

