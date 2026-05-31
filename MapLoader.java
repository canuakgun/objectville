package objectville.map;

import objectville.cell.Cell;
import objectville.cell.EmptyCell;
import objectville.cell.RoadCell;
import objectville.cell.zone.HousingZone;
import objectville.cell.zone.IndustrialZone;
import objectville.cell.zone.CommercialZone;
import objectville.cell.utility.PowerPlant;
import objectville.cell.utility.WaterStation;
import objectville.cell.utility.InternetHub;
import objectville.cell.service.PoliceStation;
import objectville.cell.service.Hospital;
import objectville.cell.service.School;
import objectville.exception.SE116ConfigurationException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapLoader {

    public GameMap load(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new SE116ConfigurationException("File not found: " + filePath);
        }

        int rows = lines.size();
        int cols = lines.get(0).length();
        Cell[][] grid = new Cell[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char c = lines.get(i).charAt(j);
                switch (c) {
                    case 'E':
                        grid[i][j] = new EmptyCell(i, j);
                        break;
                    case 'R':
                        grid[i][j] = new RoadCell(i, j);
                        break;
                    case 'H':
                        grid[i][j] = new HousingZone(i, j);
                        break;
                    case 'I':
                        grid[i][j] = new IndustrialZone(i, j);
                        break;
                    case 'C':
                        grid[i][j] = new CommercialZone(i, j);
                        break;
                    case 'P':
                        grid[i][j] = new PowerPlant(i, j);
                        break;
                    case 'W':
                        grid[i][j] = new WaterStation(i, j);
                        break;
                    case 'T':
                        grid[i][j] = new InternetHub(i, j);
                        break;
                    case 'F':
                        grid[i][j] = new PoliceStation(i, j, 5);
                        break;
                    case 'D':
                        grid[i][j] = new Hospital(i, j, 3);
                        break;
                    case 'S':
                        grid[i][j] = new School(i, j, 4);
                        break;

                    default:
                        throw new SE116ConfigurationException("Invalid character: " + c);
                }
            }
        }
        return new GameMap(rows, cols, grid);
    }
}