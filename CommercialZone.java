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
        if (getLevel() == 3 && (getReceivedPopulation() == 0 || getReceivedGoods() == 0)) {
            setLevel(2);
        } else if (getLevel() == 2 && !(getHasSecurity())) {
            setLevel(1);
        } else if (getLevel() == 1) {
        }
        if (getLevel() == 0) {
            setLevel(1);
        } else if (getLevel() == 1 && getHasSecurity()) {
            setLevel(2);
        } else if (getLevel() == 2 && getReceivedPopulation() > 0 && getReceivedGoods() > 0) {
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