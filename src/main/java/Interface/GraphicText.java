package Interface;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class GraphicText {

    private String text, font;
    private int x,y,size,id;
    private Color color;

    public GraphicText(int id, String text, int x, int y, int size, String font, Color color){
        this.id = id;
        this.text = text;
        this.x = x;
        this.y = y;
        this.size = size;
        this.font = font;
        this.color = color;
    }

    public void repaint(Graphics2D g2d, double orientation){
        g2d.setFont(new Font(font, Font.PLAIN, size));
        g2d.setColor(color);
        AffineTransform orig = g2d.getTransform();
        g2d.rotate(Math.toRadians(orientation));
        g2d.drawString(text,x,y);
        g2d.setTransform(orig);
    }

    public int getId() {
        return id;
    }
}
