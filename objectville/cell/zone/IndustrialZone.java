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
        boolean hasLevel1 = getReceivedPopulation() > 0;
        boolean hasLevel2 = hasLevel1 && getHasSecurity();
        boolean hasLevel3 = hasLevel2 && getReceivedPopulation() > 0;

        int target;
        if (hasLevel3)
            target = 3;
        else if (hasLevel2)
            target = 2;
        else if (hasLevel1)
            target = 1;
        else
            target = 0;

        if (target == 0) {
            setLevel(0);
        } else if (target > getLevel()) {
            setLevel(getLevel() + 1);
        } else if (target < getLevel()) {
            setLevel(getLevel() - 1);
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