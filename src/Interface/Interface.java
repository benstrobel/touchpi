package Interface;

import InternetRadio.InternetRadio;

import javax.swing.*;
import java.awt.event.*;

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
        if(graphic.getCurrentMenu() != null){
            for(Button button : graphic.getCurrentMenu().getButtons()){
                panel.remove(button.getButton());
            }
        }
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

    public Menu getMainMenu(InternetRadio internetRadio){
        Menu menu = new Menu();
        Button r = new Button(20, 20, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentMenu(internetRadio.getSelectionMenu());
            }
        },13);
        r.setOffsetable(false);
        menu.addButton(r);
        // Navigation
        Button b = new Button(0, 255, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Back");
            }
        },1);
        b.setOffsetable(false);
        menu.addButton(b);
        Button h = new Button(190, 255, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Home");
            }
        },2);
        h.setOffsetable(false);
        menu.addButton(h);
        Button n = new Button(380, 255, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Next");
                setCurrentMenu(internetRadio.getPlayingMenu());
            }
        },3);
        n.setOffsetable(false);
        menu.addButton(n);
        return menu;
    }

    public JPanel getPanel(){
        return panel;
    }


}
