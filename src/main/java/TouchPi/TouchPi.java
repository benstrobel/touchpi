package TouchPi;

import Interface.Interface;
import InternetRadio.InternetRadio;
import Stock.StockInterface;
import net.nitrado.api.Nitrapi;
import net.nitrado.api.common.exceptions.*;
import net.nitrado.api.services.Service;

public class TouchPi {

    public static void main(String[] args) {
        new TouchPi();
    }

    private InternetRadio internetRadio;
    private Interface anInterface;
    private StockInterface stockInterface;

    public TouchPi(){
        anInterface = new Interface(false);
        stockInterface = new StockInterface();
        stockInterface.addtoWatchlist("TSLA");
        stockInterface.addtoWatchlist("IWDA.AS");
        stockInterface.addtoWatchlist("AMD");
        stockInterface.addtoWatchlist("LHA.DE");
        stockInterface.addtoWatchlist("DB");
        internetRadio = new InternetRadio(anInterface.getPanel(),anInterface,stockInterface);
        anInterface.setCurrentMenu(anInterface.getMainMenu(internetRadio,stockInterface));
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

    public void test(){
        try
        {
            Nitrapi api = new Nitrapi("a");
            Service [] service = api.getServices();
        }catch (NitrapiErrorException e) {
            // There was an error in our request to the api.
            // ...
        } catch (NitrapiAccessTokenInvalidException e) {
            // The access token is no longer valid.
            // ...
        } catch (NitrapiHttpException e) {
            // There was an error connecting to the api.
            // ...
        } catch (NitrapiConcurrencyException e) {
            // The same action is already running.
            // ...
        } catch (NitrapiMaintenanceException e) {
            // The Nitrapi is currently down for maintenance.
            // ...
        }

    }

}
