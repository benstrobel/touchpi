package InternetRadio;

import Interface.Button;
import Interface.Menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InternetRadio{

    private PlayerThread playerThread;
    private Menu menu;

    public void play(RadioStation radioSender){
        if(playerThread != null){
            playerThread.stop();
        }
        playerThread = new PlayerThread(radioSender);
        playerThread.start();
    }

    public Menu getMenu(JPanel panel){
        if(menu != null){return menu;}
        Menu menu = new Menu();
        int x = 0, y = 0;
        menu.addButton(new Button(x*120+20, y*120+20, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("AntenneBayern");
            }
        },4));
        y++;
        menu.addButton(new Button(x*120+20, y*120+20, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bayern1");
            }
        },5));
        x++;
        y = 0;
        menu.addButton(new Button(x*120+20, y*120+20, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bayern2");
            }
        },6));
        y++;
        menu.addButton(new Button(x*120+20, y*120+20, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bayern3");
            }
        },7));
        x++;
        y = 0;
        menu.addButton(new Button(x*120+20, y*120+20, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("BrKlassik");
            }
        },8));
        y++;
        menu.addButton(new Button(x*120+20, y*120+20, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("BrHeimat");
            }
        },9));
        x++;
        y = 0;
        menu.addButton(new Button(x*120+20, y*120+20, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("DasDing");
            }
        },10));
        y++;
        menu.addButton(new Button(x*120+20, y*120+20, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("SWR3");
            }
        },11));
        x++;
        y = 0;
        // Navigation
        Button b = new Button(0, 255, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Back");
            }
        },1);
        b.setOffsetable(false);
        menu.addButton(b);
        Button h = new Button(180, 255, 100, 65, panel, new ActionListener() {
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
            }
        },3);
        n.setOffsetable(false);
        menu.addButton(n);
        this.menu = menu;
        return menu;
    }
}
