package TouchPi;

import Interface.Interface;
import InternetRadio.InternetRadio;

public class TouchPi {

    public static void main(String[] args) {
        new TouchPi();
    }

    private InternetRadio internetRadio;
    private Interface anInterface;

    public TouchPi(){
        anInterface = new Interface(true);
        internetRadio = new InternetRadio(anInterface.getPanel(),anInterface);
        anInterface.setCurrentMenu(anInterface.getMainMenu(internetRadio));
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
