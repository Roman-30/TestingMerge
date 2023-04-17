package com.example.demo123;

public class Line {
    private Point o1;
    private Point o2;

    public Line(Point o1, Point o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    public Point getO1() {
        return o1;
    }

    public void setO1(Point o1) {
        this.o1 = o1;
    }

    public Point getO2() {
        return o2;
    }

    public void setO2(Point o2) {
        this.o2 = o2;
    }
}
