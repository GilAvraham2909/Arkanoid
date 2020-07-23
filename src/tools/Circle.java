package tools;

import biuoop.DrawSurface;
import geomerty.Point;
import sprites.Sprite;

import java.awt.Color;

public class Circle implements Sprite {
    private double x;
    private double y;
    private double radius;
    private Color color;
    private Boolean fill;

    public Circle(double x, double y, double radius, Color color, Boolean fill) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        this.fill = fill;
    }
    public Circle(Point center, double radius, Color color, Boolean fill) {
        this.x = center.getX();
        this.y = center.getY();
        this.radius = radius;
        this.color = color;
        this.fill = fill;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawCircle((int) this.x, (int) this.y, (int) this.radius);
        if (this.fill) {
            d.fillCircle((int) this.x, (int) this.y, (int) this.radius);
        }
    }

    @Override
    public void timePassed() {
        return;
    }
}
