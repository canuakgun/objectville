package objectville.cell.zone;

import objectville.cell.Cell;

public abstract class Zone extends Cell {
    private int electricityDemand = 1;
    private int waterDemand = 1;
    private int internetDemand = 1;
    // Utility fields
    private int receivedElectricity;
    private int receivedWater;
    private int receivedInternet;

    // Service fields
    private boolean hasSecurity;
    private boolean hasHealth;
    private boolean hasEducation;

    // General fields
    private int level;
    private int currentOutput;
    private int utilityDemand;

    public Zone(int row, int col) {
        super(row, col);
        this.level = 0;
        this.utilityDemand = 1;
    }

    public int getReceivedElectricity() {
        return this.receivedElectricity;
    }

    public int getReceivedWater() {
        return this.receivedWater;
    }

    public int getReceivedInternet() {
        return this.receivedInternet;
    }

    public boolean getHasSecurity() {
        return this.hasSecurity;
    }

    public boolean getHasHealth() {
        return this.hasHealth;
    }

    public boolean getHasEducation() {
        return this.hasEducation;
    }

    public int getLevel() {
        return this.level;
    }

    public int getCurrentOutput() {
        return this.currentOutput;
    }

    public int getUtilityDemand() {
        return this.utilityDemand;
    }

    public void setReceivedElectricity(int receivedElectricity) {
        this.receivedElectricity = receivedElectricity;
    }

    public void setReceivedWater(int receivedWater) {
        this.receivedWater = receivedWater;
    }

    public void setReceivedInternet(int receivedInternet) {
        this.receivedInternet = receivedInternet;
    }

    public void setHasSecurity(boolean hasSecurity) {
        this.hasSecurity = hasSecurity;
    }

    public void setHasHealth(boolean hasHealth) {
        this.hasHealth = hasHealth;
    }

    public void setHasEducation(boolean hasEducation) {
        this.hasEducation = hasEducation;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setCurrentOutput(int currentOutput) {
        this.currentOutput = currentOutput;
    }

    public void setUtilityDemand(int utilityDemand) {
        this.utilityDemand = utilityDemand;
    }

    public int getElectricityDemand() {
        return electricityDemand;
    }

    public int getWaterDemand() {
        return waterDemand;
    }

    public int getInternetDemand() {
        return internetDemand;
    }

    public void setElectricityDemand(int d) {
        electricityDemand = d;
    }

    public void setWaterDemand(int d) {
        waterDemand = d;
    }

    public void setInternetDemand(int d) {
        internetDemand = d;
    }

    public void resetTick() {
        setReceivedElectricity(0);
        setReceivedWater(0);
        setReceivedInternet(0);
        setHasSecurity(false);
        setHasHealth(false);
        setHasEducation(false);

        updateUtilityDemand();
    }

    public void updateUtilityDemand() {
        int demand = Math.max(1, currentOutput);
        electricityDemand = demand;
        waterDemand = demand;
        internetDemand = demand;
    }

    @Override
    public boolean isConnectable() {
        return true;
    }

    @Override
    public abstract char getSymbol();

    public abstract int getMinUtility();

    public abstract void computeLevel();

    public abstract int computeOutput();

    public abstract String getTypeName();

}