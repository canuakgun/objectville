package objectville.cell.service;

public class Hospital extends ServiceBuilding {

    public Hospital(int row, int col, int radius) {
        super(row, col, radius);
    }

    @Override
    public char getSymbol() {
        return 'D';
    }

    @Override
    public String getServiceType() {
        return "health";
    }
}
