package objectville.cell.utility;

public class WaterStation extends UtilityProvider {

    public WaterStation(int row, int col) {
        super(row, col);
    }

    @Override
    public char getSymbol() {
        return 'W';
    }

    @Override
    public String getUtilityType() {
        return "water";
    }
}
