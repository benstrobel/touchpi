package Interface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Graphic {

    private Menu currentMenu;
    public static BufferedImage [] images;
    private String [] texturepaths = {"btn.png","backbtn.png","homebtn.png","music.png","antennebayern.png","bayern1.png","bayern2.png","bayern3.png","brklassik.png","brheimat.png","dasding.png","swr3.png","pauseplay.png","radio.png"};
    private Rectangle2D bgrect = new Rectangle2D.Double(0,0,480,320);

    public Graphic(){
        loadTextures();
    }

    private void loadTextures(){
        images = new BufferedImage[texturepaths.length];
        URL toload;
        for(int i = 0; i < texturepaths.length; i++){
            toload = this.getClass().getResource("/"+texturepaths[i]);
            if(toload != null){
                try{
                    images[i] = ImageIO.read(toload);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public void offset(int offset){
        currentMenu.offset(offset);
    }

    public void setCurrentMenu(Menu currentMenu){
        this.currentMenu = currentMenu;
    }

    public JPanel setupPanel(){
        JPanel panel = new JPanel(){
            @Override
            public void paint(java.awt.Graphics g){
                Graphics2D g2d = (Graphics2D)g;
                g2d.setColor(Color.BLACK);
                g2d.draw(bgrect);
                g2d.fill(bgrect);
                if(currentMenu != null){
                    currentMenu.repaint(g2d);
                }
            }
        };
        panel.setLayout(null);
        return panel;
    }
}
