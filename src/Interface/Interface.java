package Interface;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Interface {

    private JFrame frame;
    private JPanel panel;
    private Graphic graphic;

    public Interface(boolean fullscreen){
        frame = new JFrame("TouchPi");
        frame.setSize(480,320);
        frame.setLocation(500,500);
        frame.setResizable(false);
        graphic = new Graphic();
        panel = graphic.setupPanel();
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        if(fullscreen){
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
        addMouseListener();
        frame.setVisible(true);
    }

    public void repaint(){
        frame.repaint();
    }

    public void setCurrentMenu(Menu currentMenu){
        graphic.setCurrentMenu(currentMenu);
        panel.repaint();
    }

    private void offset(int offset){
        graphic.offset(offset);
    }

    private int lastdragx = -1, lastdragy = -1, curx = -1, cury = -1;
    private int offset = 0;

    public void addMouseListener(){
        panel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                curx = e.getX();
                cury = e.getY();
                if(lastdragx != -1 && lastdragy != -1){
                    if(offset < (lastdragx-curx) && (offset - (lastdragx-curx)) > -300){
                        offset -= (lastdragx-curx);
                    }
                    offset(offset);
                }
                lastdragx = curx;
                lastdragy = cury;
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                lastdragx = -1;
                lastdragy = -1;
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public JPanel getPanel(){
        return panel;
    }


}
