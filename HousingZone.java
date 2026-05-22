package objectville.cell.zone;

public class HousingZone extends Zone {

    private int receivedLifeStyle;

    public HousingZone(int row, int col) {
        super(row, col);
    }

    public int getReceivedLifeStyle() {
        return this.receivedLifeStyle;
    }

    public void setReceivedLifeStyle(int receivedLifeStyle) {
        this.receivedLifeStyle = receivedLifeStyle;
    }

    @Override
    public char getSymbol() {
        return 'H';
    }

    @Override
    public String getTypeName() {
        return "House";
    }

    @Override
    public int getMinUtility() {
        return Math.min(getReceivedElectricity(), Math.min(getReceivedWater(), getReceivedInternet()));
    }

    @Override
    public void computeLevel() {
        if (getReceivedElectricity() == 0 || getReceivedWater() == 0 || getReceivedInternet() == 0) {
            setLevel(0);
            return;
        }
        if (getLevel() == 3 && getReceivedLifeStyle() == 0) {
            setLevel(2);
        } else if (getLevel() == 2 && !(getHasSecurity() && getHasHealth() && getHasEducation())) {
            setLevel(1);
        } else if (getLevel() == 1) {
        }
        if (getLevel() == 0) {
            setLevel(1);
        } else if (getLevel() == 1 && getHasSecurity() && getHasHealth() && getHasEducation()) {
            setLevel(2);
        } else if (getLevel() == 2 && getReceivedLifeStyle() > 0) {
            setLevel(3);
        }
    }

    @Override
    public int computeOutput() {
        if (getLevel() == 0) {
            return 0;
        } else if (getLevel() == 1) {
            return getMinUtility();
        } else if (getLevel() == 2) {
            return 2 * getMinUtility();
        } else {
            return 2 * getMinUtility() + getReceivedLifeStyle();
        }
    }

    @Override
    public void resetTick() {
        super.resetTick();
        setReceivedLifeStyle(0);
    }
}
