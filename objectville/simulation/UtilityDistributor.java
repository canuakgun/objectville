package objectville.simulation;

import objectville.map.GameMap;
import objectville.cell.Cell;
import objectville.cell.zone.IndustrialZone;
import objectville.cell.zone.Zone;
import objectville.cell.utility.UtilityProvider;
import objectville.output.SimulationLogger;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

public class UtilityDistributor {

    public void distribute(GameMap map, SimulationLogger logger) {
        for (UtilityProvider provider : map.getAllUtilityProviders()) {
            bfs(map, provider, logger);
        }
    }

    private void bfs(GameMap map, UtilityProvider provider, SimulationLogger logger) {
        String type = provider.getUtilityType();
        int remaining = provider.getCapacity();

        Queue<Cell> queue = new LinkedList<>();
        Set<Cell> visited = new HashSet<>();

        queue.add(provider);
        visited.add(provider);

        while (!queue.isEmpty() && remaining > 0) {
            Cell current = queue.poll();
            for (Cell neighbor : map.getNeighbors(current.getRow(), current.getCol())) {
                if (!visited.contains(neighbor) && neighbor.isConnectable()) {
                    visited.add(neighbor);
                    queue.add(neighbor);

                    if (neighbor instanceof Zone) {
                        Zone zone = (Zone) neighbor;
                        if (type.equals("internet") && zone instanceof IndustrialZone) {
                            continue;
                        }

                        int demand;
                        if (type.equals("electricity"))
                            demand = zone.getElectricityDemand();
                        else if (type.equals("water"))
                            demand = zone.getWaterDemand();
                        else
                            demand = zone.getInternetDemand();

                        if (demand == 0)
                            continue;

                        int give = Math.min(demand, remaining);
                        remaining -= give;

                        if (type.equals("electricity")) {
                            zone.setReceivedElectricity(zone.getReceivedElectricity() + give);
                            zone.setElectricityDemand(demand - give);
                        } else if (type.equals("water")) {
                            zone.setReceivedWater(zone.getReceivedWater() + give);
                            zone.setWaterDemand(demand - give);
                        } else {
                            zone.setReceivedInternet(zone.getReceivedInternet() + give);
                            zone.setInternetDemand(demand - give);
                        }

                        if (give > 0) {
                            logger.logUtility(zone.getTypeName(), zone.getRow(), zone.getCol(), give, type);
                        }
                    }
                }
            }
        }
    }
}