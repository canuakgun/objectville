package objectville.cell.service;

import objectville.cell.Cell;

public abstract class ServiceBuilding extends Cell {
    private int radius;

    public ServiceBuilding(int row, int col, int radius) {
        super(row, col);
        this.radius = radius;
    }

    public int getRadius() {
        return this.radius;
    }

    public abstract String getServiceType();

    @Override
    public boolean isConnectable() {
        return false;
    }

    public abstract char getSymbol();
}
