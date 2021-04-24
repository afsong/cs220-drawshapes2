package knox.drawshapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Triangle extends AbstractShape {
    protected Point center;
    protected int radius;
    protected int halfSideLen;
    protected int[] pointsX;
    protected int[] pointsY;

    public Triangle(Color color, Point center, int radius) {
        super(center, color);
        double halfSideLen = Math.sqrt(3 * Math.pow((double) radius, (double) 2) / 4);
        boundingBox = new BoundingBox(center.x - (int) halfSideLen, center.x + (int) halfSideLen, center.y - radius,
                center.y + radius / 2);
        this.halfSideLen = (int) halfSideLen;
        this.center = center;
        this.radius = radius;

        pointsX = new int[3];
        pointsY = new int[3];

        pointsX[0] = center.x;
        pointsX[1] = center.x - (int) halfSideLen;
        pointsX[2] = center.x + (int) halfSideLen;

        pointsY[0] = center.y - radius;
        pointsY[1] = center.y + radius / 2;
        pointsY[2] = center.y + radius / 2;
    }

    /*
     * (non-Javadoc)
     * 
     * @see drawshapes.sol.Shape#draw(java.awt.Graphics)
     */
    @Override
    public void draw(Graphics g) {
        if (isSelected()) {
            g.setColor(color.darker());
        } else {
            g.setColor(getColor());
        }

        g.fillPolygon(pointsX, pointsY, 3);
    }

    public String toString() {
        return String.format("TRIANGLES (%d, %d) height=%d side length=%d color=%s selected? %s", getAnchorPoint().x,
                getAnchorPoint().y, radius, halfSideLen * 2, Util.colorToString(getColor()), selected);
    }

    public String encode() {
        return String.format("RECTANGLE %d %d %d %d %s %s", getAnchorPoint().x, getAnchorPoint().y, radius,
                halfSideLen * 2, Util.colorToString(getColor()), selected);
    }

    @Override
    public void scale(double factor) {
        this.radius = (int) (this.radius * factor);
        this.halfSideLen = (int) (this.halfSideLen * factor);
    }

    @Override
    public void rotate() {
        double rotateAngle = Math.PI / 4.0;

        for (int i = 0; i < pointsX.length; i++) {
            double x = pointsX[i];
            double y = pointsY[i];

            pointsX[i] = (int) (center.getX() + (x - center.getX()) * Math.cos(rotateAngle)
                    - (y - center.getY()) * Math.sin(rotateAngle));

            pointsY[i] = (int) (center.getY() + (x - center.getX()) * Math.sin(rotateAngle)
                    + (y - center.getY()) * Math.cos(rotateAngle));
        }
    }

}