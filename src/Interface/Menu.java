package Interface;

import java.awt.*;
import java.util.ArrayList;

public class Menu {

    private ArrayList<Button> buttons = new ArrayList<>();

    public void addButton(Button button){
        buttons.add(button);
    }

    public ArrayList<Button> getButtons() {
        return buttons;
    }

    public void repaint(Graphics2D g2d){
        for(Button button : buttons){
            button.repaint(g2d);
        }
    }

    public void offset(int offset){
        for(Button button : buttons){
            button.offset(offset);
        }
    }
}
