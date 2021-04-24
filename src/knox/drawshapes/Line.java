package knox.drawshapes;

import java.awt.Graphics;

public class Line extends AbstractShape {
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    public Line(int x1, int y1, int x2, int y2) {
        super();

        startX = x1;
        startY = y1;

        endX = x2;
        endY = y1;
    }

    @Override
    public void draw(Graphics g) {
        g.drawLine(startX, startY, endX, endY);
    }

    public String toString() {
        return String.format("LINE %d %d %d %d", startX, startY, endX, endY);
    }

    @Override
    public void scale(double factor) {
        return;
    }

    @Override
    public void rotate() {
        return;
    }
}
