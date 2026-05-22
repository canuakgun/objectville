package objectville.cell.zone;

public class IndustrialZone extends Zone {
    private int receivedPopulation;

    public IndustrialZone(int row, int col) {
        super(row, col);
    }

    public int getReceivedPopulation() {
        return this.receivedPopulation;
    }

    public void setReceivedPopulation(int receivedPopulation) {
        this.receivedPopulation = receivedPopulation;
    }

    @Override
    public char getSymbol() {
        return 'I';
    }

    @Override
    public String getTypeName() {
        return "Industrial";
    }

    @Override
    public int getMinUtility() {
        return Math.min(getReceivedElectricity(), getReceivedWater());
    }

    @Override
    public void computeLevel() {
        if (getReceivedElectricity() == 0 || getReceivedWater() == 0) {
            setLevel(0);
            return;
        }
        if (getLevel() == 3 && getReceivedPopulation() == 0) {
            setLevel(2);
        } else if (getLevel() == 2 && !(getHasSecurity())) {
            setLevel(1);
        } else if (getLevel() == 1) {
        }
        if (getLevel() == 0) {
            setLevel(1);
        } else if (getLevel() == 1 && getHasSecurity()) {
            setLevel(2);
        } else if (getLevel() == 2 && getReceivedPopulation() > 0) {
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
            return 2 * getMinUtility() + getReceivedPopulation();
        }
    }

    @Override
    public void resetTick() {
        super.resetTick();
        setReceivedPopulation(0);
    }
}