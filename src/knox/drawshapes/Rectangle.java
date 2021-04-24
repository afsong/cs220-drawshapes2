package knox.drawshapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Rectangle extends AbstractShape {
    protected int width;
    protected int height;
    protected int[] pointsX;
    protected int[] pointsY;
    protected Point center;

    public Rectangle(Point center, int width, int height, Color color) {
        super(new Point(center.x, center.y), color);

        boundingBox = new BoundingBox(center.x - width / 2, center.x + width / 2, center.y - height / 2,
                center.y + height / 2);
        this.width = width;
        this.height = height;
        this.center = center;
        pointsX = new int[4];
        pointsY = new int[4];
        pointsX[0] = center.x - width / 2;
        pointsX[1] = center.x + width / 2;
        pointsX[3] = center.x - width / 2;
        pointsX[2] = center.x + width / 2;
        pointsY[0] = center.y - height / 2;
        pointsY[1] = center.y - height / 2;
        pointsY[3] = center.y + height / 2;
        pointsY[2] = center.y + height / 2;
    }

    public Rectangle(int left, int right, int top, int bottom) {
        this(new Point(right - (right - left) / 2, bottom - (bottom - top) / 2), right - left, bottom - top,
                Color.BLUE);
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
        g.fillPolygon(pointsX, pointsY, 4);
    }

    public String toString() {
        return String.format("RECTANGLE (%d, %d) width=%d height=%d color=%s selected? %s", getAnchorPoint().x,
                getAnchorPoint().y, width, height, Util.colorToString(getColor()), selected);
    }

    public String encode() {
        return String.format("RECTANGLE %d %d %d %d %s %s", getAnchorPoint().x, getAnchorPoint().y, width, height,
                Util.colorToString(getColor()), selected);
    }

    @Override
    public void scale(double factor) {
        this.width = (int) (this.width * factor);
        this.height = (int) (this.height * factor);
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
