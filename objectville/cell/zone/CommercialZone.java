package objectville.cell.zone;

public class CommercialZone extends Zone {
    private int receivedPopulation;
    private int receivedGoods;

    public CommercialZone(int row, int col) {
        super(row, col);
    }

    public int getReceivedPopulation() {
        return this.receivedPopulation;
    }

    public int getReceivedGoods() {
        return this.receivedGoods;
    }

    public void setReceivedPopulation(int receivedPopulation) {
        this.receivedPopulation = receivedPopulation;
    }

    public void setReceivedGoods(int receivedGoods) {
        this.receivedGoods = receivedGoods;
    }

    @Override
    public char getSymbol() {
        return 'C';
    }

    @Override
    public String getTypeName() {
        return "Commercial";
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
        boolean hasLevel1 = getReceivedPopulation() > 0 && getReceivedGoods() > 0;
        boolean hasLevel2 = hasLevel1 && getHasSecurity();
        boolean hasLevel3 = hasLevel2 && getReceivedPopulation() > 0 && getReceivedGoods() > 0;

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
            return 2 * getMinUtility() + Math.min(getReceivedPopulation(), getReceivedGoods());
        }
    }

    @Override
    public void resetTick() {
        super.resetTick();
        setReceivedPopulation(0);
        setReceivedGoods(0);
    }

}