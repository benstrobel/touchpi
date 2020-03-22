package WhosHome;

import Interface.Button;
import Interface.Interface;
import Interface.Menu;
import InternetRadio.InternetRadio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class WhosHome {

    private Date wtime = null, gtime = null, mtime = null, ptime = null, btime = null;

    public Menu getMenu(Interface anInterface){
        Menu menu = new Menu("WhosHomeMain");
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

    public void update(){
        InetAddress w = null, g = null, m = null, p = null, b= null;
        w = getInetAddress("WilliHandy.fritz.box");
        g = getInetAddress("GertiHandy.fritz.box");
        m = getInetAddress("MaxiHandy.fritz.box");
        p = getInetAddress("PhilippHandy.fritz.box");
        b = getInetAddress("BenHandy.fritz.box");
        System.out.println("Pinging...");
        boolean [] result = ping(new InetAddress[] {w,g,m,p,b});
        if(result[0]){
            wtime = new Date();
        }
        if(result[1]){
            gtime = new Date();
        }
        if(result[2]){
            mtime = new Date();
        }
        if(result[3]){
            ptime = new Date();
        }
        if(result[4]){
            btime = new Date();
        }
        System.out.println("Willi war zuletzt um " + wtime + " aktiv.");
        System.out.println("Gerti war zuletzt um " + gtime + " aktiv.");
        System.out.println("Maxi war zuletzt um " + mtime + " aktiv.");
        System.out.println("Philipp war zuletzt um " + ptime + " aktiv.");
        System.out.println("Bene war zuletzt um " + btime + " aktiv.");
    }

    private InetAddress getInetAddress(String ip){
        try{
            return InetAddress.getByName(ip);
        }catch (UnknownHostException e){
        }
        return null;
    }

    private boolean [] ping(InetAddress [] inetAddress){
        PingThread [] pingThreads = new PingThread[inetAddress.length];
        boolean [] results = new boolean[inetAddress.length];
        for(int i = 0; i < inetAddress.length; i++){
            pingThreads[i] = new PingThread(inetAddress[i]);
            pingThreads[i].start();
        }
        for(int i = 0; i < inetAddress.length; i++){
            try {
                pingThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            results[i] = pingThreads[i].isReachable();
        }
        return results;
    }
}
