package objectville.cell;

public class RoadCell extends Cell {

    public RoadCell(int row, int col) {
        super(row, col);
    }

    @Override
    public char getSymbol() {
        return 'R';
    }

    @Override
    public boolean isConnectable() {
        return true;
    }
}