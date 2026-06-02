package objectville.map;

import objectville.cell.Cell;
import objectville.cell.zone.Zone;
import objectville.cell.utility.UtilityProvider;
import objectville.cell.service.ServiceBuilding;
import java.util.List;
import java.util.ArrayList;

public class GameMap {
    private int rows;
    private int cols;
    private Cell[][] grid;

    public GameMap(int rows, int cols, Cell[][] grid) {
        this.rows = rows;
        this.cols = cols;
        this.grid = grid;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    public List<Zone> getAllZones() {
        ArrayList<Zone> zones = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] instanceof Zone) {
                    zones.add((Zone) grid[i][j]);
                }
            }
        }
        return zones;
    }

    public List<UtilityProvider> getAllUtilityProviders() {
        ArrayList<UtilityProvider> utilityProviders = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] instanceof UtilityProvider) {
                    utilityProviders.add((UtilityProvider) grid[i][j]);
                }
            }
        }
        return utilityProviders;
    }

    public List<ServiceBuilding> getAllServiceBuildings() {
        ArrayList<ServiceBuilding> serviceBuildings = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] instanceof ServiceBuilding) {
                    serviceBuildings.add((ServiceBuilding) grid[i][j]);
                }
            }
        }
        return serviceBuildings;
    }

    public List<Cell> getNeighbors(int row, int col) {
        ArrayList<Cell> neighbors = new ArrayList<>();

        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        for (int[] dir : directions) {
            int neighborRow = row + dir[0];
            int neighborCol = col + dir[1];
            if (neighborRow >= 0 && neighborRow < rows && neighborCol >= 0 && neighborCol < cols) {
                neighbors.add(grid[neighborRow][neighborCol]);
            }
        }
        return neighbors;
    }
}