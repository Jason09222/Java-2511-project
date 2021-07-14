package unsw.loopmania;


import javafx.beans.property.SimpleIntegerProperty;

public class Barracks extends Building{
    public Barracks(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public Ally produceAlly(LoopManiaWorld l) {
        if (getCharacterStepOn()) {
            //TODO: create a new class of ally
            // and put it in the global data
            PathPosition position = super.getNearestPath(l);
            Ally newAlly = new Ally(position);
            l.getAllies().add(newAlly);
        }
        characterLeave();
        return null;
    }

}
