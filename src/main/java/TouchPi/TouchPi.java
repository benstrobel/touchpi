package TouchPi;

import Interface.Interface;
import InternetRadio.InternetRadio;
import Stock.StockInterface;
import WhosHome.WhosHome;

public class TouchPi {

    public static void main(String[] args) {
        new TouchPi();
    }

    private InternetRadio internetRadio;
    private Interface anInterface;
    private StockInterface stockInterface;
    private WhosHome whosHome;

    public TouchPi(){
        whosHome = new WhosHome();
        stockInterface = new StockInterface();
        internetRadio = new InternetRadio();
        anInterface = new Interface(true,internetRadio,stockInterface,whosHome);
        stockInterface.addtoWatchlist("TL0.DE");
        stockInterface.addtoWatchlist("EUNL.DE");
        stockInterface.addtoWatchlist("AMD.DE");
        stockInterface.addtoWatchlist("LHA.DE");
        stockInterface.addtoWatchlist("DBK.DE");
        stockInterface.addtoWatchlist("RY4C.DE");
        anInterface.setCurrentMenu(anInterface.getMainMenu());
        anInterface.repaint();
        while(true){
            try {
                Thread.sleep(33);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            anInterface.repaint();
        }

    }

}
