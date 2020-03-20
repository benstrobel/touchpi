package InternetRadio;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class PlayerThread extends Thread {

    private RadioStation radioStation;

    public PlayerThread(RadioStation radioStation){
        this.radioStation = radioStation;
    }

    @Override
    public void run(){
        BufferedInputStream in;
        try{
            in = new BufferedInputStream(new URL(radioStation.getMp3url()).openStream());
            Player mp3player = new Player(in);
            mp3player.play();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

}
