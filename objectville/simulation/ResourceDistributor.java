package objectville.simulation;

import objectville.map.GameMap;
import objectville.cell.zone.*;
import objectville.output.SimulationLogger;

public class ResourceDistributor {
    public void distribute(GameMap map, SimulationLogger logger) {

        int populationPool = 0;
        int goodsPool = 0;
        int lifestylePool = 0;

        for (Zone zone : map.getAllZones()) {
            if (zone instanceof HousingZone)
                populationPool += zone.getCurrentOutput();
            else if (zone instanceof IndustrialZone)
                goodsPool += zone.getCurrentOutput();
            else if (zone instanceof CommercialZone)
                lifestylePool += zone.getCurrentOutput();
        }

        long industrialCount = map.getAllZones().stream().filter(z -> z instanceof IndustrialZone).count();
        long commercialCount = map.getAllZones().stream().filter(z -> z instanceof CommercialZone).count();
        long housingCount = map.getAllZones().stream().filter(z -> z instanceof HousingZone).count();

        int popPerZone = (industrialCount + commercialCount) > 0
                ? populationPool / (int) (industrialCount + commercialCount)
                : 0;
        int goodsPerZone = commercialCount > 0 ? goodsPool / (int) commercialCount : 0;
        int lifestylePerZone = housingCount > 0 ? lifestylePool / (int) housingCount : 0;

        for (Zone zone : map.getAllZones()) {
            if (zone instanceof IndustrialZone) {
                ((IndustrialZone) zone).setReceivedPopulation(popPerZone);
                if (popPerZone > 0)
                    logger.logResource(zone.getTypeName(), zone.getRow(), zone.getCol(), popPerZone, "population");
            } else if (zone instanceof CommercialZone) {
                ((CommercialZone) zone).setReceivedPopulation(popPerZone);
                ((CommercialZone) zone).setReceivedGoods(goodsPerZone);
                if (popPerZone > 0) {
                    logger.logResource(zone.getTypeName(), zone.getRow(), zone.getCol(), popPerZone, "population");
                }
                if (goodsPerZone > 0) {
                    logger.logResource(zone.getTypeName(), zone.getRow(), zone.getCol(), goodsPerZone, "goods");
                }
            } else if (zone instanceof HousingZone) {
                ((HousingZone) zone).setReceivedLifeStyle(lifestylePerZone);
                if (lifestylePerZone > 0) {
                    logger.logResource(zone.getTypeName(), zone.getRow(), zone.getCol(), lifestylePerZone, "lifestyle");
                }
            }

        }
    }
}