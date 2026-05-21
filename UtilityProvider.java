package objectville.cell.utility;

import objectville.cell.Cell;

public abstract class UtilityProvider extends Cell {
    private final int capacity = 100;

    public UtilityProvider(int row, int col) {
        super(row, col);
    }

    public int getCapacity() {
        return capacity;
    }

    public abstract String getUtilityType();

    @Override
    public boolean isConnectable() {
        return false;
    }

    public abstract char getSymbol();
}
