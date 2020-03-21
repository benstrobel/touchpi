package InternetRadio;

import Interface.Button;
import Interface.Interface;
import Interface.Menu;
import Stock.StockInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InternetRadio{

    private PlayerThread playerThread;
    private Menu menu;
    private RadioStation currentlyplaying, lastplayed;
    private JPanel panel;
    private Interface anInterface;
    private InternetRadio internetRadio;
    private StockInterface stockInterface;

    public InternetRadio(JPanel panel, Interface anInterface, StockInterface stockInterface){
        this.panel = panel;
        this.anInterface = anInterface;
        this.stockInterface = stockInterface;
        internetRadio = this;
    }

    public void play(RadioStation radioSender){
        if(playerThread != null){
            playerThread.stop();
        }
        playerThread = new PlayerThread(radioSender);
        currentlyplaying = radioSender;
        playerThread.start();
    }

    public void stop(){
        if(playerThread != null){
            playerThread.stop();
            lastplayed = currentlyplaying;
            currentlyplaying = null;
        }
    }

    public Menu getPlayingMenu(){
        Menu menu = new Menu();
        int radioid = -1;
        for(int i = 0; i < RadioStation.values().length; i++){
            if(RadioStation.values()[i] == currentlyplaying){
                radioid = i;
                break;
            }
        }
        Button icon = new Button(0, 0, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        }, radioid+4);
        icon.setOffsetable(false);
        menu.addButton(icon);
        // Pause / Play
        Button p = new Button(190, 155, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentlyplaying == null && lastplayed != null){
                    System.out.println("Resume");
                    play(lastplayed);
                }else{
                    System.out.println("Pause");
                    stop();
                }
            }
        }, 12);
        p.setOffsetable(false);
        menu.addButton(p);
        // Navigation
        Button b = new Button(0, 255, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Back");
                anInterface.setCurrentMenu(getSelectionMenu());
            }
        },1);
        b.setOffsetable(false);
        menu.addButton(b);
        Button h = new Button(190, 255, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Home");
                anInterface.setCurrentMenu(anInterface.getMainMenu(internetRadio,stockInterface));
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

    public Menu getSelectionMenu(){
        Menu menu = new Menu();
        int x = 0, y = 0;
        menu.addButton(new Button(x*120+20, y*120+20, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("AntenneBayern");
                play(RadioStation.ANTENNEBAYERN);
                anInterface.setCurrentMenu(getPlayingMenu());
            }
        },4));
        y++;
        menu.addButton(new Button(x*120+20, y*120+20, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bayern1");
                play(RadioStation.BAYERN1);
                anInterface.setCurrentMenu(getPlayingMenu());
            }
        },5));
        x++;
        y = 0;
        menu.addButton(new Button(x*120+20, y*120+20, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bayern2");
                play(RadioStation.BAYERN2);
                anInterface.setCurrentMenu(getPlayingMenu());
            }
        },6));
        y++;
        menu.addButton(new Button(x*120+20, y*120+20, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bayern3");
                play(RadioStation.BAYERN3);
                anInterface.setCurrentMenu(getPlayingMenu());
            }
        },7));
        x++;
        y = 0;
        menu.addButton(new Button(x*120+20, y*120+20, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("BrKlassik");
                play(RadioStation.BRKLASSIK);
                anInterface.setCurrentMenu(getPlayingMenu());
            }
        },8));
        y++;
        menu.addButton(new Button(x*120+20, y*120+20, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("BrHeimat");
                play(RadioStation.BRKLASSIK);
                anInterface.setCurrentMenu(getPlayingMenu());
            }
        },9));
        x++;
        y = 0;
        menu.addButton(new Button(x*120+20, y*120+20, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("DasDing");
                play(RadioStation.DASDING);
                anInterface.setCurrentMenu(getPlayingMenu());
            }
        },10));
        y++;
        menu.addButton(new Button(x*120+20, y*120+20, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("SWR3");
                play(RadioStation.SWR3);
                anInterface.setCurrentMenu(getPlayingMenu());
            }
        },11));
        x++;
        y = 0;
        // Navigation
        Button b = new Button(0, 255, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Back");
                anInterface.setCurrentMenu(anInterface.getMainMenu(internetRadio,stockInterface));
            }
        },1);
        b.setOffsetable(false);
        menu.addButton(b);
        Button h = new Button(190, 255, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Home");
                anInterface.setCurrentMenu(anInterface.getMainMenu(internetRadio,stockInterface));
            }
        },2);
        h.setOffsetable(false);
        menu.addButton(h);
        Button n = new Button(380, 255, 100, 65, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Next");
                anInterface.setCurrentMenu(getPlayingMenu());
            }
        },3);
        n.setOffsetable(false);
        menu.addButton(n);
        this.menu = menu;
        return menu;
    }
}
