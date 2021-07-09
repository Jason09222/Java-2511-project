package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class HeroCastle extends Building {
    private boolean offerWindow = false;

    public HeroCastle(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public void checkPathType() {
        // Cannot be placed expect one spot
    }

    public boolean checkPathCycle() {
        if (this.getPathCycle() >= 5) {
            return true;
        }
        return false;
    }

    public boolean getOfferWindow() {
        return this.offerWindow;
    }

    public void openOfferWindow() {
        this.offerWindow = true;
    }

    public void closeOfferWindow() {
        this.offerWindow = false;
    }
}
