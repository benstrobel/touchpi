package Interface;

import java.awt.*;
import java.util.ArrayList;

public class Menu {

    private ArrayList<Button> buttons = new ArrayList<>();
    private ArrayList <GraphicText> textList = new ArrayList<GraphicText>();
    private static int maxiD = 0;
    private String iD;

    public Menu(String iD){
        this.iD = iD;
    }

    public String getiD() {
        return iD;
    }

    public void addButton(Button button){
        buttons.add(button);
    }

    public ArrayList<Button> getButtons() {
        return buttons;
    }

    public void repaint(Graphics2D g2d, double orientation){
        for(Button button : buttons){
            button.repaint(g2d,orientation);
        }
        for(GraphicText graphicText : textList){
            graphicText.repaint(g2d,orientation);
        }
    }

    public void offset(int offset){
        for(Button button : buttons){
            button.offset(offset);
        }
    }

    public void addNewText(String text, int x, int y, int size, Color color){
        textList.add(new GraphicText(maxiD++,text,x,y, size,"Times New Roman", color));
    }
}
