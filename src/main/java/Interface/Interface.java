package Interface;

import InternetRadio.InternetRadio;
import Stock.StockInterface;
import WhosHome.WhosHome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Interface {

    private JFrame frame;
    private JPanel panel;
    private Graphic graphic;
    private Interface anInterface = this;

    private InternetRadio internetRadio;
    private StockInterface stockInterface;
    private WhosHome whosHome;

    public Interface(boolean fullscreen, InternetRadio internetRadio, StockInterface stockInterface, WhosHome whosHome){
        this.internetRadio = internetRadio;
        this.stockInterface = stockInterface;
        this.whosHome = whosHome;
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
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(Graphic.images[18],new Point(8,8), "blank cursor");
        if(fullscreen){
            panel.setCursor(cursor);
        }
        frame.setVisible(true);
    }

    public void repaint(){
        frame.repaint();
    }

    public Menu getCurrentMenu(){
        return graphic.getCurrentMenu();
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

    public Menu getMainMenu(){
        Menu menu = new Menu("TouchPiMain");

        Button r = new Button(20, 20, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentMenu(internetRadio.getSelectionMenu(anInterface));
            }
        },13);
        r.setOffsetable(false);
        menu.addButton(r);

        Button s = new Button(140, 20, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentMenu(stockInterface.getMenu(anInterface));
            }
        },14);
        s.setOffsetable(false);
        menu.addButton(s);

        Button w = new Button(260, 20, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentMenu(whosHome.getMenu(anInterface));
            }
        },19);
        w.setOffsetable(false);
        menu.addButton(w);

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
                setCurrentMenu(internetRadio.getPlayingMenu(anInterface));
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
