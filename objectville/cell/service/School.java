package objectville.cell.service;

public class School extends ServiceBuilding {

    public School(int row, int col, int radius) {
        super(row, col, radius);
    }

    @Override
    public char getSymbol() {
        return 'S';
    }

    @Override
    public String getServiceType() {
        return "education";
    }
}
