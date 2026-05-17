package objectville.cell.service;

public class PoliceStation extends ServiceBuilding {

    public PoliceStation(int row, int col, int radius) {
        super(row, col, radius);
    }

    @Override
    public char getSymbol() {
        return 'F';
    }

    @Override
    public String getServiceType() {
        return "security";
    }

}
