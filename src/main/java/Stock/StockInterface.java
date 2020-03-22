package Stock;

import Interface.Button;
import Interface.Interface;
import Interface.Menu;
import InternetRadio.InternetRadio;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class StockInterface {

    private StockInterface stockInterface = this;
    private ArrayList<String> watchlist = new ArrayList<>();

    public void addtoWatchlist(String watchentry){
        watchlist.add(watchentry);
    }

    public Menu getMenu(Interface anInterface){
        Thread loader = new Thread(){
            @Override
            public void run(){
                Menu menu = getProperMenu(anInterface);
                if(anInterface.getCurrentMenu().getiD().equals("StockFiller")){
                    anInterface.setCurrentMenu(menu);
                }
            }
        };
        loader.start();
        return getFillerMenu(anInterface);
    }

    private Menu getFillerMenu(Interface anInterface){
        Menu menu = new Menu("StockFiller");
        Stock stock = null;
        for(int i = 0; i < watchlist.size(); i++){
            menu.addNewText(watchlist.get(i),20, i*50+40, 30, Color.white);
        }
        // Navigation
        Button b = new Button(0, 255, 100, 65, anInterface.getPanel(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Back");
                anInterface.setCurrentMenu(anInterface.getMainMenu());
            }
        },1);
        b.setOffsetable(false);
        menu.addButton(b);
        Button h = new Button(190, 255, 100, 65, anInterface.getPanel(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Home");
                anInterface.setCurrentMenu(anInterface.getMainMenu());
            }
        },2);
        h.setOffsetable(false);
        menu.addButton(h);
        Button n = new Button(380, 255, 100, 65, anInterface.getPanel(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Refresh");
                anInterface.setCurrentMenu(getMenu(anInterface));
            }
        },17);
        n.setOffsetable(false);
        menu.addButton(n);
        return menu;
    }

    private Menu getProperMenu(Interface anInterface){
        Menu menu = new Menu("StockProper");
        Stock stock = null;
        for(int i = 0; i < watchlist.size(); i++){
            String watchentry = watchlist.get(i);
            try {
                stock = YahooFinance.get(watchentry);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(stock == null){return menu;}
            BigDecimal change = stock.getQuote().getChangeInPercent();
            if(change.compareTo(new BigDecimal(0)) > 0){
                menu.addNewText(watchentry,20, i*50+40, 30, Color.white);
                menu.addButton(new Button(170, i*50+40-25, 30, 30, anInterface.getPanel(), new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                }, 15));
                menu.addNewText(" "+stock.getQuote().getChangeInPercent()+"%",220, i*50+40, 30, Color.green);
                menu.addNewText(""+stock.getQuote().getPrice()+" €", 350, i*50+40,30, Color.WHITE);
            }else{
                menu.addNewText(watchentry,20, i*50+40, 30, Color.white);
                menu.addButton(new Button(170, i*50+40-25, 30, 30, anInterface.getPanel(), new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                }, 16));
                menu.addNewText(stock.getQuote().getChangeInPercent()+"%",220, i*50+40, 30, Color.red);
                menu.addNewText(""+stock.getQuote().getPrice()+" €", 350, i*50+40,30, Color.WHITE);
            }
        }
        // Navigation
        Button b = new Button(0, 255, 100, 65, anInterface.getPanel(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Back");
                anInterface.setCurrentMenu(anInterface.getMainMenu());
            }
        },1);
        b.setOffsetable(false);
        menu.addButton(b);
        Button h = new Button(190, 255, 100, 65, anInterface.getPanel(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Home");
                anInterface.setCurrentMenu(anInterface.getMainMenu());
            }
        },2);
        h.setOffsetable(false);
        menu.addButton(h);
        Button n = new Button(380, 255, 100, 65, anInterface.getPanel(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Refresh");
                anInterface.setCurrentMenu(getMenu(anInterface));
            }
        },17);
        n.setOffsetable(false);
        menu.addButton(n);
        return menu;
    }


}
