package objectville.simulation;

import objectville.map.GameMap;
import objectville.cell.zone.Zone;
import objectville.cell.service.ServiceBuilding;
import objectville.output.SimulationLogger;
import objectville.cell.zone.HousingZone;

public class ServiceDistributor {

    public void distribute(GameMap map, SimulationLogger logger) {
        for (ServiceBuilding service : map.getAllServiceBuildings()) {
            for (Zone zone : map.getAllZones()) {
                double dist = distance(service.getRow(), service.getCol(), zone.getRow(), zone.getCol());
                if (dist <= service.getRadius()) {
                    String type = service.getServiceType();
                    if (type.equals("security")) {
                        zone.setHasSecurity(true);
                        logger.logService(zone.getTypeName(), zone.getRow(), zone.getCol(), type);
                    } else if (zone instanceof HousingZone) {
                        if (type.equals("education"))
                            zone.setHasEducation(true);
                        else if (type.equals("health"))
                            zone.setHasHealth(true);
                        logger.logService(zone.getTypeName(), zone.getRow(), zone.getCol(), type);
                    }
                }
            }
        }
    }

    private double distance(int r1, int c1, int r2, int c2) {
        return Math.sqrt(Math.pow(r2 - r1, 2) + Math.pow(c2 - c1, 2));
    }
}
