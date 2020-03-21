package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Button {

    private int x, y;
    private int width, height;
    private int textureID = -1;
    private JButton button;
    private boolean offsetable = true;
    private int offset = 0;

    public Button(int x, int y, int width, int height, JPanel panel, ActionListener actionListener){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.textureID = -1;
        button = new JButton();
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setBounds(x,y,width,height);
        button.setBorderPainted(false);
        button.addActionListener(actionListener);
        button.setVisible(true);
        button.setLayout(null);
        panel.add(button);
    }

    public Button(int x, int y, int width, int height, JPanel panel, ActionListener actionListener, int textureID){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.textureID = textureID;
        button = new JButton();
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setIcon(new ImageIcon(Graphic.images[textureID]));
        button.setBounds(x,y,width,height);
        button.setBorderPainted(false);
        button.addActionListener(actionListener);
        button.setVisible(true);
        button.setLayout(null);
        panel.add(button);
    }

    public void repaint(Graphics2D g2d, double orientation){
        if(textureID > 0){
            g2d.drawImage(Graphic.orientate(Graphic.images[textureID], orientation), x+offset, y , width, height, null);
        }
    }

    public void setTextureID(int textureID) {
        this.textureID = textureID;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTextureID() {
        return textureID;
    }

    public JButton getButton() {
        return button;
    }

    public void setOffsetable(boolean offsetable){
        this.offsetable = offsetable;
    }

    public void offset(int offset){
        if(offsetable){
            this.offset = offset;
            button.setLocation(x+offset,y);
        }
    }

}
