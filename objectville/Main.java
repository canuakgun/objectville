package objectville;

import objectville.map.GameMap;
import objectville.map.MapLoader;
import objectville.simulation.Simulation;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2)
            return;
        MapLoader loader = new MapLoader();
        GameMap map = loader.load(args[0]);
        int ticks = Integer.parseInt(args[1]);
        Simulation sim = new Simulation(map);
        sim.run(ticks);
    }
}