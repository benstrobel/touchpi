package Interface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Graphic {

    private Menu currentMenu;
    public static BufferedImage [] images;
    private String [] texturepaths = {"btn.png","backbtn.png","homebtn.png","music.png","antennebayern.png","bayern1.png","bayern2.png","bayern3.png","brklassik.png","brheimat.png","dasding.png","swr3.png","pauseplay.png","radio.png", "stock.png","up.png","down.png","refresh.png","cursor.png"};
    private Rectangle2D bgrect = new Rectangle2D.Double(0,0,480,320);
    private double orientation = 0.0;

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
                    currentMenu.repaint(g2d,orientation);
                }
            }
        };
        panel.setLayout(null);
        return panel;
    }

    public static BufferedImage orientate(BufferedImage image, double orientation){
        AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(orientation), image.getWidth()/2,image.getHeight()/2);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        return op.filter(image,null);
    }
}
