package WhosHome;

import java.io.IOException;
import java.net.InetAddress;

public class PingThread extends Thread {

    private InetAddress target;
    private boolean reachable = false;

    public PingThread(InetAddress target){
        this.target = target;
    }

    public boolean isReachable() {
        return reachable;
    }

    @Override
    public void run(){
        boolean r = false;
        if(target == null){return;}
        try {
            r = target.isReachable(500);
        } catch (IOException e) {
            e.printStackTrace();
        }
        reachable = r;
    }


}
