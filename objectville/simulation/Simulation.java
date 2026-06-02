package objectville.simulation;

import objectville.map.GameMap;
import objectville.cell.zone.Zone;
import objectville.cell.zone.HousingZone;
import objectville.cell.zone.IndustrialZone;
import objectville.cell.zone.CommercialZone;
import objectville.output.SimulationLogger;

public class Simulation {
    private GameMap map;
    private ServiceDistributor serviceDistributor;
    private UtilityDistributor utilityDistributor;
    private ResourceDistributor resourceDistributor;
    private SimulationLogger logger;

    public Simulation(GameMap map) {
        this.map = map;
        this.serviceDistributor = new ServiceDistributor();
        this.utilityDistributor = new UtilityDistributor();
        this.resourceDistributor = new ResourceDistributor();
        this.logger = new SimulationLogger();
    }

    public void run(int totalTicks) {
        for (int tick = 1; tick <= totalTicks; tick++) {
            logger.logTickStart(tick);

            for (Zone zone : map.getAllZones())
                zone.resetTick();

            serviceDistributor.distribute(map, logger);
            utilityDistributor.distribute(map, logger);

            if (tick > 1)
                resourceDistributor.distribute(map, logger);

            for (Zone zone : map.getAllZones()) {
                int oldLevel = zone.getLevel();
                zone.computeLevel();
                int newLevel = zone.getLevel();

                int output = zone.computeOutput();
                zone.setCurrentOutput(output);
                zone.updateUtilityDemand();

                String resourceName = "";
                if (zone instanceof HousingZone)
                    resourceName = "population";
                else if (zone instanceof IndustrialZone)
                    resourceName = "goods";
                else if (zone instanceof CommercialZone)
                    resourceName = "lifestyle";
                logger.logProduction(zone.getTypeName(), zone.getRow(), zone.getCol(), output, resourceName);

                if (newLevel != oldLevel) {
                    logger.logLevelChange(zone.getTypeName(), zone.getRow(), zone.getCol(), oldLevel, newLevel);
                }
            }
        }
    }
}