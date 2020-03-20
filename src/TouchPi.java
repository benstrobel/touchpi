import Interface.Interface;
import InternetRadio.InternetRadio;

public class TouchPi {

    public static void main(String[] args) {
        new TouchPi();
    }

    private InternetRadio internetRadio = new InternetRadio();
    private Interface anInterface;

    public TouchPi(){
        //test(RadioStation.BAYERN3);
        //internetRadio.play(RadioStation.BAYERN3);
        anInterface = new Interface(false);
        anInterface.setCurrentMenu(internetRadio.getMenu(anInterface.getPanel()));
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
